pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                echo "build"
                bat "javac HelloWorld.java"
                bat "java HelloWorld"
            }
        }
    }
}
