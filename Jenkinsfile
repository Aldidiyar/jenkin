pipeline {
  agent any

  tools {
    jdk 'Java25'
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
        bat 'mvn clean test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
          archiveArtifacts artifacts: 'target/surefire-reports/**, target/allure-results/**'
        }
      }
    }

    stage('Generate Allure Report') {
      steps {
        bat 'mvn allure:report'
      }
    }

    stage('Publish Allure HTML') {
      steps {
        publishHTML(target: [
          reportDir: 'target/site/allure-maven-plugin',
          reportFiles: 'index.html',
          reportName: 'Allure Report'
        ])

        archiveArtifacts artifacts: 'target/site/allure-maven-plugin/**'
      }
    }

  }

  post {
    always {
      echo 'Pipeline finished'
    }
  }
}
