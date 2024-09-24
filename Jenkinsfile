pipeline {
    agent any
    stages {
        stage("verify tooling") {
            steps {
                bat """
                docker info
                docker --version
                docker-compose --version
                curl --version
                jq --version
                """
            }
        }
        stage('Build') { 
            steps {
                bat "java --version"
                echo "Building PrimeFinder"
                bat "javac PrimeFinder.java"
                
                bat "if not exist lib mkdir lib"
                
                bat 'powershell -Command "Invoke-WebRequest -Uri https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar -OutFile lib\\junit-platform-console-standalone-1.7.0-all.jar"'
                
                bat "\"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\jar\" cvf PrimeFinder.jar PrimeFinder.class"
                
                echo "Building PrimeFinderTest"
                bat "javac -cp .;lib\\junit-platform-console-standalone-1.7.0-all.jar PrimeFinderTest.java"
                
                bat "dir"
                echo "Build done"
            }
        }
        stage('Test') { 
            steps {
                echo "Running JUnit Tests"
                bat "java -jar lib\\junit-platform-console-standalone-1.7.0-all.jar --class-path . --scan-class-path"
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis'
            }
        }
        stage('Deploy') { 
            agent { dockerfile true }
            steps {
                echo "checking node"
                bat 'node --version'
                echo "Start container"
                bat 'docker-compose up -d --no-color --wait'
                bat 'docker-compose ps'
                bat 'curl http://localhost:3000/param?query=demo | jq'
                echo "Deploying PrimeFinder for up to 1 million"
                bat "java PrimeFinder 1000000"
            }
            post {
                always {
                    bat 'docker-compose down --remove-orphans -v'
                    bat 'docker-compose ps'
                }
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
