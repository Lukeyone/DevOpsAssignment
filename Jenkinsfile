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
            steps {
                script {
                    echo 'Building Docker image'
                    bat 'docker build -t primefinder-image -f Dockerfile .'

                    echo 'Inspecting the Docker image'
                    bat 'docker inspect primefinder-image'

                    echo 'Running Docker container and executing PrimeFinder up to 1,000,000'
                    // Capturing the output of the PrimeFinder execution
                    def result = bat(
                        script: '''
                        docker run --rm -v /c/Users/lachl/AppData/Local/Jenkins/.jenkins/workspace/Task6.2HD:/workspace \
                        -w /workspace primefinder-image \
                        java -cp . PrimeFinder 1000000
                        ''',
                        returnStdout: true
                    )

                    // Displaying the result in the Jenkins console
                    echo "PrimeFinder results: ${result}"

                    // Checking if the result contains the expected output and marking the deployment as successful
                    if (result.contains("Total number of primes")) {
                        echo 'Deployment successful!'
                    } else {
                        error('Deployment failed! PrimeFinder did not run successfully.')
                    }
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
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
