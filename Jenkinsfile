pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'abdulbasit7/week3part1:1'
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from SCM
                script {
                    git branch: 'main', git credentialsId: 'github-ass', url: 'https://github.com/abdulbasit24/devopsassignment'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'  // Use 'sh' instead of 'bat' for cross-platform compatibility
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    // Login to Docker registry and push image
                    sh "echo '#Basit@Docker' | docker login -u abdulbasit7 --password-stdin"
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Deploy Docker container
                    sh '''
                        docker run -d --name my-app-container -p 8080:8080 ${DOCKER_IMAGE}
                        docker ps
                        docker logs my-app-container
                    '''
                }
            }
        }
    }
}
