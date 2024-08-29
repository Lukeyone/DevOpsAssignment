pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                echo "build"
                bat "javac PrimeFinder.java"
                bat "\"C:\\Program Files\\Java\\jdk-21\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"
                bat "dir"
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
