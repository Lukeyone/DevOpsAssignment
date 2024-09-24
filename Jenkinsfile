pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "setting up maven"
                git url: 'https://github.com/Lukeyone/DevOpsAssignment'
                
                withMaven(maven: 'maven-399') {
                    bat 'mvn clean verify'
                }
                
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

        stage('Code Analysis') {
            steps {
                echo "Running SpotBugs Code Analysis"
                // Wrap SpotBugs execution in try-catch block to avoid breaking the pipeline on failure
                script {
                    try {
                        withMaven(maven: 'maven-399') {
                            bat 'mvn com.github.spotbugs:spotbugs-maven-plugin:4.2.0:spotbugs'
                        }
                    } catch (Exception e) {
                        // Log the error but continue execution
                        echo "SpotBugs failed: ${e.message}"
                        writeFile file: 'spotbugs_error.log', text: "SpotBugs failure: ${e.message}\n"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo "Running JUnit Tests"
                // Run the JUnit tests using the standalone JUnit platform console
                bat "java -jar lib\\junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path"
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    bat 'mvn clean sonar:sonar'
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
    post {
        always {
            echo "Pipeline finished"
        }
    }
}
