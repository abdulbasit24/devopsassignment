pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'abdulbasit7/week3part1:1'
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', credentialsId: 'github-ass', url: 'https://github.com/abdulbasit24/devopsassignment'
                }
            }
        }
        stage('Build') {
            steps {
                bat '"C:\.Net & Java Software\apache-maven-3.6.3\bin\mvn" clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    bat "echo #Basit@Docker | docker login -u abdulbasit7 --password-stdin"
                    bat "docker push ${DOCKER_IMAGE}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def proc = "docker run -d --name my-app-container -p 8080:8080 ${DOCKER_IMAGE}".execute()
                    proc.waitFor() // Wait for the process to finish
                    echo "Container ID: ${proc.text}"
                    
                    // Check container status and logs
                    bat "docker ps"
                    bat "docker logs my-app-container"
                }
            }
        }
    }
}
