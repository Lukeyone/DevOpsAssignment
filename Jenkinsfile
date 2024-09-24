pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                bat "java --version"
                echo "Building PrimeFinder"
                bat "javac PrimeFinder.java"
                
                // Check if the lib directory exists and create it if it does not
                bat "if not exist lib mkdir lib"
                
                // Download the JUnit jar file to the lib directory
                bat 'powershell -Command "Invoke-WebRequest -Uri https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar -OutFile lib\\junit-platform-console-standalone-1.7.0-all.jar"'
                
                // Create a JAR file for PrimeFinder
                bat "\"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"
                
                echo "Building PrimeFinderTest"
                // Compile the JUnit test class with the current directory and lib in the classpath
                bat "javac -cp .;lib\\junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java"
                
                bat "dir"
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
        stage('Code Analysis') {
            // agent { label 'linux'}
            steps {
                withSonarQubeEnv(installationnName: 'sq1') {
                    sh './mvnw clean sonar:sonar'
                }
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deploying PrimeFinder for up to 1 million"
                bat "java PrimeFinder 1000000"
            }
        }
        stage('Release') { 
            steps {
                echo "Release"
            }
        }
    }
}
