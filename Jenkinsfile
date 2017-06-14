pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        parallel(
          "build": {
            sh 'mvn clean'
            
          },
          "test parallel": {
            sh 'echo \'parallel?\''
            
          }
        )
      }
    }
    stage('wait') {
      steps {
        input 'Proceed?'
      }
    }
  }
}