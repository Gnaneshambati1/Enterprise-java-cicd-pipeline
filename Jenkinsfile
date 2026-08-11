pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh './mvnw clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh './mvnw sonar:sonar -Dsonar.projectKey=employee-cicd'
                }
            }
        }

        stage('Package') {
            steps {
                sh './mvnw package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'sudo docker build -t employee-cicd:1.0 .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'sudo docker rm -f employee-app || true'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                    sudo docker run -d \
                    --name employee-app \
                    -p 8081:8081 \
                    employee-cicd:1.0
                '''
            }
        }

        stage('Verify Application') {
            steps {
                sh 'sleep 10'
                sh 'curl -f http://localhost:8081/employees'
            }
        }
    }

    post {
        success {
            echo 'Employee CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}
