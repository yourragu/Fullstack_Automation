pipeline {
  agent any
  stages {
    stage('Dev code pull') {
      steps {
        echo 'dev code pull'
      }
    }

    stage('dev maven build') {
      steps {
        echo 'dev maven build'
      }
    }

    stage('QA deploy') {
      steps {
        echo 'QA Test'
        echo 'API Test'
      }
    }

    stage('QA Certification') {
      steps {
        echo 'Certify QA'
      }
    }

    stage('Deploy UAT') {
      steps {
        echo 'Deploy UAT'
      }
    }

    stage('Manual Prod Deploy') {
      steps {
        echo 'Deploy Prod'
      }
    }

  }
}