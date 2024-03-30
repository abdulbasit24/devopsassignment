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
                bat '"C:/.Net & Java Software/apache-maven-3.6.3/bin/mvn" clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat '''
                    wsl /usr/bin/docker build -t %DOCKER_IMAGE% .
                '''
            }
        }
        stage('Push Docker Image') {
            steps {
                bat '''
                    echo #Basit@Docker | wsl /usr/bin/docker login -u abdulbasit7 --password-stdin
                    wsl /usr/bin/docker push %DOCKER_IMAGE%
                '''
            }
        }
        stage('Deploy') {
            steps {
                bat '''
                    export DOCKER_HOST=unix:///var/run/docker.sock
                    wsl /usr/bin/docker run -d --name my-app-container -p 8080:8080 %DOCKER_IMAGE%
                    wsl /usr/bin/docker ps
                    wsl /usr/bin/docker logs my-app-container
                '''
                }
            }
        }
    }
}
