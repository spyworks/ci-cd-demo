pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Test (Maven container)') {
      steps {
        sh '''
          docker run --rm \
            -v "$PWD":/workspace \
            -w /workspace \
            maven:3.9.8-eclipse-temurin-21 \
            mvn -q -e clean test
        '''
      }
    }

    stage('Build order-api image') {
      steps {
        sh 'docker build -t order-api:local .'
      }
    }

    stage('Deploy') {
      steps {
        sh 'docker compose up -d oracle-db'
        sh 'docker compose up -d order-api'
      }
    }
  }

  post {
    always {
      sh 'docker ps || true'
    }
  }
}