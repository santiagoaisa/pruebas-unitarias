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

                                // Remueve 'origin/' del inicio si existe, preservando 'feature/sonarqube'
                                def branchName = env.GIT_BRANCH ? env.GIT_BRANCH.replaceFirst('^origin/', '') : 'main'

                                sh "./mvnw sonar:sonar -Dsonar.branch.name=${branchName}"
                            }

                       }
                    }
                }
    }
}
