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
      sauce('sauceuser-np'){
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
