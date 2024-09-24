pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                bat "java --version"
                echo "Building PrimeFinder"
                bat "javac PrimeFinder.java"

                // Create a JAR file for PrimeFinder
                bat "\"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"

                echo "Build done"
            }
        }
        stage('Test') { 
            steps {
                echo "Running JUnit Tests"
                // Run the JUnit tests using the standalone JUnit platform console
                bat "java -jar lib\\junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path"
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deploying PrimeFinder to Test Environment"
                // Simulate deployment by copying the JAR to a 'test_environment' directory
                bat "if not exist test_environment mkdir test_environment"
                bat "copy PrimeFinder.jar test_environment\\PrimeFinder.jar"

                // Navigate to the test_environment directory
                dir('test_environment') {
                    echo "Running PrimeFinder in Test Environment"
                    // Run the application with a test parameter
                    bat "java -jar PrimeFinder.jar 1000"
                }
            }
        }
    }
}
