pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from the Git repository
                checkout scm
            }
        }

        stage('Verify Tooling') {
            steps {
                script {
                    bat 'docker info'
                    bat 'docker --version'
                    bat 'docker-compose --version'
                    bat 'curl --version'
                    bat 'jq --version'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Building PrimeFinder'
                    bat 'javac PrimeFinder.java'
                    bat 'if not exist lib mkdir lib'
                    bat 'powershell -Command "Invoke-WebRequest -Uri https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar -OutFile lib\\junit-platform-console-standalone-1.7.0-all.jar"'
                    bat '"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\jar" cvf PrimeFinder.jar PrimeFinder.class'
                    echo 'Building PrimeFinderTest'
                    bat 'javac -cp .;lib\\junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit Tests'
                bat 'java -jar lib\\junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path'
            }
        }

        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis'
                // Add code analysis steps here, e.g., static analysis tools
            }
        }

        stage('Deploy') {
            agent {
                label 'docker' // Specify agent with Docker installed
            }
            steps {
                script {
                    echo 'Deploying with Docker'
                    
                    // Checkout the latest code
                    checkout scm

                    // Build Docker image and run container
                    def image = docker.build("67fae38cfd7b1c720e9787f9406061e1fa259600", "-f Dockerfile .")

                    // Use Linux-style path for Windows paths
                    image.inside('-w /c/Users/lachl/AppData/Local/Jenkins/.jenkins/workspace/Task6.2HD@2/') {
                        bat 'docker inspect -f . "67fae38cfd7b1c720e9787f9406061e1fa259600"'
                    }
                }
            }
        }

        stage('Release') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo 'Releasing the application...'
                // Add your release steps here
            }
        }

        stage('Monitoring and Alerting') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
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
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
