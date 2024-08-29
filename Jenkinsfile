pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                javac HelloWorld.java
                java HelloWorld
            }
        }
    }
}
