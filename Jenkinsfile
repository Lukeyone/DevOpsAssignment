pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh "java --version"
                echo "Building PrimeFinder"
                sh "javac PrimeFinder.java"
                
                // Check if the lib directory exists and create it if it does not
                sh "mkdir -p lib"
                
                // Download the JUnit jar file to the lib directory
                sh 'curl -o lib/junit-platform-console-standalone-1.7.0-all.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar'
                
                // Create a JAR file for PrimeFinder
                sh "jar cvf PrimeFinder.jar PrimeFinder.class"
                
                echo "Building PrimeFinderTest"
                // Compile the JUnit test class with the current directory and lib in the classpath
                sh "javac -cp .:lib/junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java"
                
                sh "ls -la"
                echo "Build done"
            }
        }
        stage('Test') { 
            steps {
                echo "Running JUnit Tests"
                // Run the JUnit tests using the standalone JUnit platform console
                sh "java -jar lib/junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path"
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis'
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deploying PrimeFinder for up to 1 million"
                sh "java PrimeFinder 1000000"
            }
        }
        stage('Release') { 
            steps {
                echo "Release"
            }
        }
        stage('Monitoring and Alerting') { 
            steps {
                echo "Monitoring and Alerting"
            }
        }
    }
}
