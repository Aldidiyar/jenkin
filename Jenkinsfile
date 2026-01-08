pipeline {
    agent any

    tools {
        jdk 'Java11'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}
