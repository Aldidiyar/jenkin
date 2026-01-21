pipeline {
  agent any

  tools {
    jdk 'Java25'
    maven 'Maven3'
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Test') {
      steps {
        bat 'mvn -B clean test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'

          archiveArtifacts artifacts: 'target/surefire-reports/**, target/allure-results/**', fingerprint: false
        }
      }
    }

    stage('Generate Allure HTML') {
      steps {
        bat 'mvn -B allure:report'
      }
    }

    stage('Publish Allure') {
      steps {
        publishHTML(target: [
          reportDir: 'target/site/allure-maven-plugin',
          reportFiles: 'index.html',
          reportName: 'Allure Report',
          keepAll: true,
          alwaysLinkToLastBuild: true,
          allowMissing: false
        ])

        archiveArtifacts artifacts: 'target/site/allure-maven-plugin/**', fingerprint: false
      }
    }
  }

  post {
    always { echo 'Pipeline finished' }
  }
}
