pipeline {
    agent any

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
                    bat 'docker-compose --version'
                    bat 'curl --version'
                    bat 'jq --version'
                    bat 'sonar-scanner --version'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Building PrimeFinder'
                    bat 'javac PrimeFinder.java'

                    // Ensure the lib directory exists
                    bat 'if not exist lib mkdir lib'

                    // Download the JUnit standalone jar
                    bat 'powershell -Command "Invoke-WebRequest -Uri https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar -OutFile lib\\junit-platform-console-standalone-1.7.0-all.jar"'

                    // Create a JAR file for PrimeFinder
                    bat '"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\jar" cvf PrimeFinder.jar PrimeFinder.class'

                    echo 'Building PrimeFinderTest'
                    // Compile the JUnit test class with the current directory and lib in the classpath
                    bat 'javac -cp .;lib\\junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit Tests'
                // Run the JUnit tests using the standalone JUnit platform console
                bat 'java -jar lib\\junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path'
            }
        }

        stage('Code Analysis') {
            steps {
                echo 'Performing Code Analysis with SonarQube'
                withSonarQubeEnv('sq1') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Building Docker image'
                    bat 'docker build -t primefinder-image -f Dockerfile .'

                    echo 'Inspecting the Docker image'
                    bat 'docker inspect primefinder-image'

                    echo 'Running Docker container and executing PrimeFinder up to 1,000,000'
                    bat '''
                    docker run -d --name primefinder_container primefinder-image
                    '''
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

            // Stop and remove Docker containers, with Windows-compatible logic
            bat 'docker stop primefinder_container || echo "Container already stopped"'
            bat 'docker rm primefinder_container || echo "Container not found"'
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
