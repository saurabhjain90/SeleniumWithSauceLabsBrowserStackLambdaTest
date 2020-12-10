pipeline {
 agent none
 tools {
    	maven 'Maven'
 }
 stages {
    stage("Checkout"){
     agent any
     steps{
       sh 'echo checkout'
       checkout scm
     }
    }
    stage("Tests") {
     agent any
     steps {
      sauce('04a12a99-9003-4f07-9094-1af9831072ab'){
        sauceconnect(useGeneratedTunnelIdentifier: false, verboseLogging: true) {
            sh 'mvn test'
            //junit '**/junitreports/*.xml'
            step([$class: 'SauceOnDemandTestPublisher'])
        }
      }
     }
    }
  }
}
