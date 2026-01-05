pipeline {
  agent any

  environment {
    IMAGE_NAME = "order-api:local"
  }

  stages {
    stage('Checkout') {
      steps {
        // If Jenkins job is configured to use SCM, this is often optional.
        checkout scm
      }
    }

    stage('Unit + Integration Tests') {
      steps {
        sh 'mvn -q -e clean test'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh "docker build -t ${IMAGE_NAME} ."
      }
    }

    stage('Deploy (docker compose)') {
      steps {
        // Use the repo’s compose file to start/update order-api (and dependencies).
        sh "docker compose up -d oracle-db"
        sh "docker compose up -d order-api"
      }
    }
  }

  post {
    always {
      sh 'docker ps || true'
    }
  }
}