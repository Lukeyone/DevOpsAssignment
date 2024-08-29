pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Building"
                sh 'javac PrimeFinder.java'
            }
        }
        stage('Test') {
            steps {
                echo "testing if code works"
                sh 'java PrimeFinder 10'
            }
        }
        stage('Deploy') {
            steps {
                echo "deploy to staging environment requires no tools for this process"
            }
        }
        stage('Release') {
            steps {
                echo "if everything is successful up to this point, we deploy the code to production"
            }
        }
    }
}
