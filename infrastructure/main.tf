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


# Create an S3 bucket for storing the Spring Boot JAR file
resource "aws_s3_bucket" "expense_lens_bucket_backend" {
  bucket = "expense-lens-jar-backend"  # Replace with a globally unique bucket name

  tags = {
    Name = "ExpenseLensJARBucket"
    "env" = "dev"
  }

}

resource "aws_s3_bucket_public_access_block" "bucket-public-access" {
    bucket = aws_s3_bucket.expense_lens_bucket_backend.id

    block_public_acls = false
    block_public_policy = false
    ignore_public_acls = false
    restrict_public_buckets = false
}

resource "aws_s3_bucket_website_configuration" "spring-config" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.id

  index_document {
    suffix = "index.html"
  }
}

resource "aws_s3_object" "index-html" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.bucket
  key    = "index.html"  # This is the key for the object in S3
  source = "../index.html"  # Path to the index.html file on your local system
  acl    = "public-read"  # Make it publicly readable (important for a website)

  # Optional: Set the content-type
  content_type = "text/html"
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


resource "aws_s3_bucket_ownership_controls" "bucket-ownership" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.id

  rule{
    object_ownership = "BucketOwnerPreferred"
  }

}


resource "aws_s3_bucket_acl" "bucket-acl" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.id
  acl = "public-read"

  depends_on = [ aws_s3_bucket_ownership_controls.bucket-ownership, 
  aws_s3_bucket_public_access_block.bucket-public-access ]
}

resource "aws_s3_bucket_policy" "name" {
  bucket = aws_s3_bucket.expense_lens_bucket_backend.id

   policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Sid = "PublicReadGetObject" 
        Effect = "Allow"
        Principal = "*"
        Action = "s3:GetObject"
        Resource = "${aws_s3_bucket.expense_lens_bucket_backend.arn}/*"
      }
    ]
  })
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



# Create an EC2 instance for the Spring Boot application (free tier eligible: t2.micro)
resource "aws_instance" "expense_lens_backend" {
  ami           = "ami-03cc8375791cb8bcf"  
  instance_type = "t2.micro"  # Free tier eligible instance type
  key_name      = "expense-lens-key"
  security_groups = [aws_security_group.springboot_sg.name]

# Automatically assign a public IP
  associate_public_ip_address = true

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

# Output the public IP address to access the Spring Boot app
output "expense_lens_s3_jar_public_ip" {
  value = aws_s3_bucket.expense_lens_bucket_backend.website_endpoint
  description = "Static website hosting of s3 for jar"
}

variable private_key_path{
  description = "Path to the SSH private key to be used for authentication"
  default = "~/.ssh/expense-lens-key"
}
