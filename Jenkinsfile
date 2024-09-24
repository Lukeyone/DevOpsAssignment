pipeline {
    agent any

    tools {
        maven 'maven-3' // Ensure this matches the Maven installation name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tooling') {
            steps {
                script {
                    bat 'docker --version'
                    bat 'mvn --version'
                    bat 'curl --version'
                    bat 'jq --version'
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building with Maven'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running Tests with Maven'
                bat 'mvn test'
            }
        }

        stage('Code Analysis') {
            steps {
                echo 'Performing Code Analysis with SonarQube'
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Building Docker image'
                    bat 'docker build -t primefinder-image .'

                    echo 'Running Docker container in test environment'
                    bat 'docker run -d --name primefinder_test primefinder-image'
                }
            }
        }

        stage('Release') {
            steps {
                echo 'Releasing the application...'
                // Add your release steps here
            }
        }

        stage('Monitoring and Alerting') {
            steps {
                echo 'Setting up monitoring and alerting...'
                // Add monitoring and alerting setup steps here
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace'
            cleanWs()
            // Stop and remove Docker containers
            bat 'docker stop primefinder_test || true'
            bat 'docker rm primefinder_test || true'
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
