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
                       sh 'env | sort'
                       withSonarQubeEnv('sonarqube-jenkis'){
                            script{
                                def branchName = GIT_BRANCH.split('/').last()
                                sh "./mvnw sonar:sonar -Dsonar.branch.name=${branchName}"
                            }

                       }
                    }
                }
    }
}
