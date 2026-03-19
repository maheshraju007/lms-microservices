pipeline {
    agent any

    environment {
        MAVEN_HOME = 'c:\\Users\\chara\\mahesh\\fsProj\\maven\\apache-maven-3.9.6'
        PATH = "${MAVEN_HOME}\\bin;${PATH}"
        SERVICES_DIR = 'c:\\Users\\chara\\mahesh\\fsProj\\lms-microservices'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/maheshraju007/lms-microservices.git'
            }
        }

        stage('Build Auth Service') {
            steps {
                dir("${SERVICES_DIR}\\auth-service") {
                    bat 'mvn clean package -DskipTests=false'
                }
            }
            post {
                always {
                    junit testResults: "${SERVICES_DIR}\\auth-service\\target\\surefire-reports\\*.xml", allowEmptyResults: true
                }
            }
        }

        stage('Build Course Service') {
            steps {
                dir("${SERVICES_DIR}\\course-service") {
                    bat 'mvn clean package -DskipTests=false'
                }
            }
            post {
                always {
                    junit testResults: "${SERVICES_DIR}\\course-service\\target\\surefire-reports\\*.xml", allowEmptyResults: true
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir("${SERVICES_DIR}\\api-gateway") {
                    bat 'mvn clean package -DskipTests=false'
                }
            }
            post {
                always {
                    junit testResults: "${SERVICES_DIR}\\api-gateway\\target\\surefire-reports\\*.xml", allowEmptyResults: true
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success { echo 'All microservices built successfully!' }
        failure  { echo 'Build failed!' }
    }
}
