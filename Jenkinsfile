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
      sauce('sauceuser'){
        sauceconnect(useGeneratedTunnelIdentifier: false, verboseLogging: true) {
            sh 'mvn test'
            //junit '**/junitreports/*.xml'
            step([$class: 'SauceOnDemandTestPublisher'])
        }
      }
     }
      post {
	      success {
	      sh """
	      curl --location --request POST ‘https://devopsinteg1.service-now.com/api/sn_devops/v1/devops/tool/test?toolId=826b10a0db8124104b59f7541d961919’ --header ‘Accept: application/json’ \
	      --header ‘Authorization: Basic ZGV2b3BzLmludGVncmF0aW9uLnVzZXI6ZGV2b3Bz’ \
	      --header ‘Content-Type: application/json’ \
	      --data-raw \‘{ 
	      	"original_payload":"${env.JOB_NAME}-${env.STAGE_NAME}-${env.BUILD_NUMBER}",
         	"pipeline":"${env.JOB_NAME}",
         	"stageName":"${env.STAGE_NAME}",
         	"buildNumber":"${env.BUILD_NUMBER}"
		}’\
	    	"""
    	      }
      }
    }
  }
}
