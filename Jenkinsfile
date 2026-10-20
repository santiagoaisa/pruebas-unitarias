pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh 'java --version'
                sh 'chmod +x mvnw'
                sh './mvnw clean test'
                junit 'target/surefire-reports/*.xml'
                jacoco()
            }
        }
    }
}
