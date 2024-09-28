# Keerthana B

terraform {
    required_providers {
        aws = {
            source = "hashicorp/aws"
            
        }
    }
}

# Define provider for AWS
provider "aws" {
  region = "eu-west-1"  # Choose your desired region
}

# Create an IAM Role for EC2 with S3 Read Access
resource "aws_iam_role" "ec2_role" {
  name = "EC2S3AccessRole"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Principal = {
          Service = "ec2.amazonaws.com"
        }
        Effect = "Allow"
        Sid = ""
      },
    ]
  })
}

# Attach S3 Read Only Policy to the Role
resource "aws_iam_role_policy_attachment" "s3_readonly_access" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
  role       = aws_iam_role.ec2_role.name
}

# Create an S3 bucket for storing the Spring Boot JAR file
resource "aws_s3_bucket" "expense_lens_bucket_backend" {
  bucket = "expense-lens-jar-backend"  # Replace with a globally unique bucket name

  tags = {
    Name = "ExpenseLensJARBucket"
    "env" = "dev"
  }

}

# Upload the Spring Boot JAR file to the S3 bucket
resource "aws_s3_object" "jar_file" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.id  # Use id to refer to the bucket name
  key    = "ExpenseLens-0.0.1-SNAPSHOT.jar"  # Object key in S3
  source = "../target/ExpenseLens-0.0.1-SNAPSHOT.jar"  # Path to the JAR file locally
  etag   = filemd5("../target/ExpenseLens-0.0.1-SNAPSHOT.jar")  # Avoids re-upload if unchanged

  tags = {
    Name = "ExpenseLensJAR"
    Environment = "Dev"
  }
}

# Create a security group for the EC2 instance
resource "aws_security_group" "springboot_sg" {
  name        = "expense-lens-sg"
  description = "Allow HTTP and SSH traffic"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Allow SSH from anywhere (can be restricted)
  }

  ingress {
    from_port   = 8080  # Spring Boot runs on port 9090
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Allow HTTP access to the Spring Boot app from anywhere
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]  # Allow all outbound traffic
  }
}

# Create an IAM Instance Profile for the EC2 Role
resource "aws_iam_instance_profile" "ec2_instance_profile" {
  name = "EC2S3AccessProfile"  # You can name it anything unique
  role = aws_iam_role.ec2_role.name
}

# Create an EC2 instance for the Spring Boot application (free tier eligible: t2.micro)
resource "aws_instance" "expense_lens_backend" {
  ami           = "ami-0c3a915e3f3aa0e0d"  # Amazon Linux 2 AMI (Free Tier eligible)
  instance_type = "t2.micro"  # Free tier eligible instance type
  key_name      = "expense-lens-key"
  security_groups = [aws_security_group.springboot_sg.name]
  iam_instance_profile = aws_iam_instance_profile.ec2_instance_profile.name  # Use the IAM Instance Profile

# Automatically assign a public IP
  associate_public_ip_address = true

  # User data script to install Java, download Spring Boot JAR from S3, and run it
  user_data = <<-EOF
              #!/bin/bash
              sudo yum update -y
              sudo yum install -y java-17-amazon-corretto  # Install Java 17 (Amazon Linux 2)
              aws s3 cp s3://${aws_s3_bucket.expense_lens_bucket_backend.bucket}/ExpenseLens-0.0.1-SNAPSHOT.jar /home/ec2-user/ExpenseLens-0.0.1-SNAPSHOT.jar  # Download the Spring Boot JAR from S3
              nohup java -jar /home/ec2-user/ExpenseLens-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &  # Run the Spring Boot application in the background
              EOF

  tags = {
    Name = "ExpenseLensBackend"
  }

  # Enable monitoring to keep free-tier usage track
  monitoring = false
}


# Output the public IP address to access the Spring Boot app
output "expense_lens_app_public_ip" {
  value = aws_instance.expense_lens_backend.public_ip
  description = "Public IP to access the Spring Boot ExpenseLens App"
}
