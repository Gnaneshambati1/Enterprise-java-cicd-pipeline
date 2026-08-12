
# 🚀 Enterprise Java CI/CD Pipeline

A complete **Enterprise Java CI/CD pipeline** built using **Spring Boot, Maven, Jenkins, SonarQube, Docker, GitHub, and AWS EC2**.

This project demonstrates how source code moves automatically from **GitHub → Jenkins → Maven → JUnit → SonarQube → Docker → Deployment**.

---

## 📌 Project Overview

The goal of this project is to implement an automated CI/CD pipeline for a Spring Boot microservice.

Whenever new code is pushed to GitHub, Jenkins can automatically trigger the pipeline and perform the complete software delivery process.

### 🔄 CI/CD Flow

```text
Developer
    │
    ▼
GitHub
    │
    │ Webhook
    ▼
Jenkins
    │
    ├── Checkout
    │
    ├── Maven Build
    │
    ├── JUnit Tests
    │
    ├── Package
    │
    ├── SonarQube Analysis
    │
    └── Docker Build
            │
            ▼
       Docker Container
            │
            ▼
        AWS EC2
```

---

## 🛠️ Technologies Used

| Technology   | Purpose                         |
| ------------ | ------------------------------- |
| Java 21      | Application development         |
| Spring Boot  | Microservice framework          |
| Maven        | Build and dependency management |
| JUnit        | Unit testing                    |
| Git          | Version control                 |
| GitHub       | Source code management          |
| Jenkins      | CI/CD automation                |
| SonarQube    | Code quality analysis           |
| Docker       | Application containerization    |
| AWS EC2      | Cloud deployment                |
| Linux/Ubuntu | Server environment              |

---

## 🏗️ Project Architecture

```text
                    ┌───────────────┐
                    │   Developer   │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │    GitHub     │
                    │ Source Code   │
                    └───────┬───────┘
                            │
                         Webhook
                            │
                            ▼
                    ┌───────────────┐
                    │    Jenkins    │
                    └───────┬───────┘
                            │
             ┌──────────────┼──────────────┐
             ▼              ▼              ▼
        Maven Build     JUnit Test    SonarQube
             │              │              │
             └──────────────┼──────────────┘
                            ▼
                    ┌───────────────┐
                    │ Docker Image  │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Docker        │
                    │ Container     │
                    └───────┬───────┘
                            │
                            ▼
                       AWS EC2
```

---

## 🔄 CI/CD Pipeline Stages

### 1️⃣ Checkout

Jenkins pulls the latest source code from the GitHub repository.

```text
GitHub → Jenkins Workspace
```

---

### 2️⃣ Build

Maven compiles the Spring Boot application.

```bash
./mvnw clean compile
```

This verifies that the application source code can be compiled successfully.

---

### 3️⃣ Test

JUnit tests are executed automatically.

```bash
./mvnw test
```

The pipeline verifies that the application passes its automated tests before continuing.

Example result:

```text
Tests run: 3
Failures: 0
Errors: 0
```

---

### 4️⃣ Package

The application is packaged using Maven.

```bash
./mvnw package -DskipTests
```

This generates the application artifact.

---

### 5️⃣ SonarQube Code Quality

The project is analyzed using SonarQube.

The pipeline checks areas such as:

* Code quality
* Bugs
* Vulnerabilities
* Code smells
* Maintainability
* Security issues

Example Maven command:

```bash
./mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
-Dsonar.projectKey=employee-cicd
```

---

### 6️⃣ Docker Containerization

The Spring Boot application is packaged into a Docker image.

Example:

```bash
docker build -t employee-cicd:1.0 .
```

The container is then started using Docker.

```bash
docker run -d \
  --name employee-app \
  -p 8081:8080 \
  employee-cicd:1.0
```

---

### 7️⃣ Deployment

The Dockerized application runs on an **AWS EC2 Ubuntu server**.

Application flow:

```text
Spring Boot
     ↓
Docker Image
     ↓
Docker Container
     ↓
AWS EC2
     ↓
Application
```

---

## 📁 Project Structure

```text
employee-cicd/
│
├── .gitignore
├── Dockerfile
├── Jenkinsfile
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── ...
│
└── target/
    └── application-artifacts
```

### Important Files

| File          | Description                          |
| ------------- | ------------------------------------ |
| `pom.xml`     | Maven configuration and dependencies |
| `Jenkinsfile` | Defines the CI/CD pipeline           |
| `Dockerfile`  | Defines the Docker image             |
| `src/main`    | Application source code              |
| `src/test`    | Unit tests                           |
| `README.md`   | Project documentation                |
| `target/`     | Generated Maven build artifacts      |

---

## 🐳 Docker Configuration

The application uses a Java 21 compatible Docker runtime.

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## ⚙️ Jenkins Pipeline

The Jenkins pipeline contains stages for:

```text
Checkout
   ↓
Build
   ↓
Test
   ↓
Package
   ↓
SonarQube Analysis
   ↓
Docker Build
   ↓
Deployment
```

The pipeline is defined in:

```text
Jenkinsfile
```

---

## ☁️ AWS Deployment

The application is deployed on an AWS EC2 Ubuntu instance.

### Infrastructure

```text
AWS
└── EC2
    └── Ubuntu
        ├── Jenkins
        ├── Docker
        └── Spring Boot Application
```

Docker exposes the application through:

```text
Host Port: 8081
Container Port: 8080
```

---

## 🧪 Testing

Automated tests are executed through Maven and JUnit.

```bash
./mvnw test
```

Successful pipeline execution confirms that:

* Application compiles successfully
* Unit tests pass
* Application is packaged
* SonarQube analysis completes
* Docker image can be created
* Application can run inside a container

---

## ▶️ Run the Application Locally

### Clone the repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

### Enter the project

```bash
cd employee-cicd
```

### Build the application

```bash
./mvnw clean package
```

### Run the application

```bash
java -jar target/*.jar
```

The Spring Boot application runs on:

```text
http://localhost:8080
```

---

## 🐳 Run Using Docker

Build the Docker image:

```bash
docker build -t employee-cicd:1.0 .
```

Run the container:

```bash
docker run -d \
  --name employee-app \
  -p 8081:8080 \
  employee-cicd:1.0
```

Check the container:

```bash
docker ps
```

View logs:

```bash
docker logs employee-app
```

---

## 📊 CI/CD Benefits Demonstrated

This project demonstrates practical DevOps concepts including:

✅ Continuous Integration

✅ Automated Testing

✅ Automated Build

✅ Code Quality Analysis

✅ Containerization

✅ CI/CD Pipeline Automation

✅ GitHub Integration

✅ Jenkins Automation

✅ Docker Deployment

✅ AWS Cloud Deployment

---

## 🎯 Key Learning Outcomes

Through this project, I implemented an end-to-end CI/CD workflow and gained practical experience with:

* Jenkins Pipeline
* Jenkinsfile
* GitHub integration
* Maven build lifecycle
* JUnit testing
* SonarQube quality analysis
* Docker image creation
* Docker containers
* AWS EC2 deployment
* Linux server administration
* CI/CD automation

---

## 🚀 Future Improvements

Possible improvements to this project include:

* Add Jenkins webhook automation
* Add Docker image versioning
* Push images to Docker Hub or Amazon ECR
* Add automated deployment
* Add JaCoCo test coverage
* Add Prometheus and Grafana monitoring
* Add Kubernetes deployment
* Implement rollback strategy

---

## 👨‍💻 Project Author

**Gnanesh Ambati**

This project was created as a practical implementation of **DevOps and CI/CD concepts** using modern Java and cloud technologies.

---

## ⭐ Project Highlights

```text
GitHub
   ↓
Jenkins
   ↓
Maven
   ↓
JUnit
   ↓
SonarQube
   ↓
Docker
   ↓
AWS EC2
   ↓
Production-Ready Application
```

> 🚀 From source code to deployment — automated through a complete CI/CD pipeline.
