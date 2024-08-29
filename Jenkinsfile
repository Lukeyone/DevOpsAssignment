pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                echo "build"
                bat "javac PrimeFinder.java"
                // Jar file command here because jar didn't work on its own, which is the file path of Java 21 jar.exe
                bat "cd lib && wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar"
                bat "\"C:\\Program Files\\Java\\jdk-21\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"
                bat "dir"
                echo "build done"
            }
        }
        stage('Test') { 
            steps {
                echo "Testing prime numbers up to 10"
                bat "java PrimeFinder 10"
                echo "Testing prime numbers up to 100"
                bat "java PrimeFinder 100"
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
