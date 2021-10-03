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

    stage('QA Test') {
      parallel {
        stage('QA Test') {
          steps {
            echo 'API Test'
          }
        }

        stage('QA Certify UI') {
          steps {
            echo 'Certify QA'
          }
        }

        stage('QA Certify API') {
          steps {
            echo 'API Certify'
          }
        }

      }
    }

    stage('Deploy UAT') {
      steps {
        echo 'Deploy UAT'
        input 'Do you want to certify UAT?'
      }
    }

    stage('Manual Prod Deploy') {
      when {
        branch 'master'
      }
      steps {
        echo 'Deploy Prod'
      }
    }

  }
}