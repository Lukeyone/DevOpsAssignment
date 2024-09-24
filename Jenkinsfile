pipeline {
    agent any

    environment {
        WORKSPACE_DIR = '/c/Users/lachl/AppData/Local/Jenkins/.jenkins/workspace/Task6.2HD@2'
    }

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
                    // Verify Docker and other tooling versions
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
                // Placeholder for static analysis tools (e.g., SonarQube or Checkstyle)
            }
        }

        stage('Docker Build and Deploy') {
            steps {
                script {
                    echo 'Building Docker image'
                    
                    // Build Docker image using a Linux-style path
                    bat 'docker build -t primefinder-image -f Dockerfile .'
                    
                    // Inspect the Docker image to ensure it's built
                    bat 'docker inspect primefinder-image'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    echo 'Running Docker container'

                    // Adjusting the path for Docker volume mounting (Windows to Linux path)
                    def dockerRunCmd = """
                    docker run -d -v /c/Users/lachl/AppData/Local/Jenkins/.jenkins/workspace/Task6.2HD@2:/workspace \
                    -w /workspace primefinder-image
                    """
                    
                    bat dockerRunCmd
                }
            }
        }

        stage('Release') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo 'Releasing the application...'
                // Add any release steps needed here (e.g., deployment to a server or artifact repository)
            }
        }

        stage('Monitoring and Alerting') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo 'Setting up monitoring and alerting...'
                // Add steps for setting up monitoring and alerting (e.g., Prometheus, Grafana)
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
