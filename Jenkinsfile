pipeline {
  agent any

  stages {

    stage('Checkout') {
      steps {
        // Ensure clean workspace and fetch source
        deleteDir()
        checkout scm

        // Basic sanity check
        sh 'pwd && ls -la && test -f pom.xml'
      }
    }

    stage('Verify Docker Access') {
      steps {
        // Confirm Docker is available and accessible
        sh '''
          set -e
          whoami
          id
          ls -l /var/run/docker.sock
          docker version
          docker ps
        '''
      }
    }

    stage('Test (Maven Wrapper)') {
      steps {
        // Run unit tests using Maven Wrapper
        sh '''
          set -e
          chmod +x mvnw || true
          ./mvnw -q -e clean test
        '''
      }
    }

    stage('Build Images') {
      steps {
        // Build application Docker image
        sh '''
          set -e
          docker compose build order-api
        '''
      }
    }

    stage('Deploy') {
      steps {
        // Start database and application containers
        sh '''
          set -e
          docker compose up -d oracle-db
          docker compose up -d order-api
          docker ps
        '''
      }
    }
  }

  post {
    always {
      // Capture container status and recent logs for diagnostics
      sh 'docker ps || true'
      sh 'docker compose logs --tail 80 oracle-db || true'
      sh 'docker compose logs --tail 80 order-api || true'
    }
  }
}