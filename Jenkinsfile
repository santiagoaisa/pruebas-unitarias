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
        stage('SonarQube') {
                    steps {
                       withSonarQubeEnv('sonarqube-jenkis'){
                       sh './mvnw sonar:sonar -Dsonar.branch.name=test'
                       }
                    }
                }
    }
}
