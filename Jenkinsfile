pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                echo "Building PrimeFinder"
                bat "javac PrimeFinder.java"
                bat "mkdir lib"
                bat "cd lib && wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar"
                bat "\"C:\\Program Files\\Java\\jdk-21\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"
                echo "Building PrimeFinderTest"
                // Compile the JUnit test class
                bat "javac -cp lib\\junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java"
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
