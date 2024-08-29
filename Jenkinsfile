pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Github Commit Received. Building in Process"
                echo "building code with Java maven. The reason for this is because of increased performance and project code building"
            }
        }
        stage('Test') {
            steps {
                echo "in the testing stage, I am using the JUnit tester. The reason for this is to test the code function and integrations to ensure the application is working as expected. Demo project here"
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
