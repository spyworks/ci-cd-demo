pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        deleteDir()
        checkout scm
        sh 'pwd && ls -la && test -f pom.xml'
      }
    }

    stage('Verify Docker Access') {
      steps {
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
        sh '''
          set -e
          chmod +x mvnw || true
          ./mvnw -q -e clean test
        '''
      }
    }

    stage('Build Images') {
      steps {
        sh '''
          set -e
          docker compose build order-api
        '''
      }
    }

    stage('Deploy') {
      steps {
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
      sh 'docker ps || true'
      sh 'docker logs --tail 80 oracle-db || true'
      sh 'docker logs --tail 80 order-api || true'
    }
  }
}