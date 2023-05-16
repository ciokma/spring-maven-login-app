
// The name you want to give your Spring Boot application
// Each resource related to your app will be given this name
appName = "spring-login"

pipeline {
    // Use the 'maven' Jenkins agent image which is provided with OpenShift 
    agent any
    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub-cred')
	    	NEXUSDOCKER_CREDENTIALS=credentials('nexusdocker-cred')
	}
    stages {
    
        stage('Construir aplicación') {
          steps {
            // Puedes elegir Maven o Gradle según tu proyecto
            sh 'mvn clean package'
          }
        }
        stage('Crear imagen Docker') {
            steps {
              sh 'docker build -t ciokma/spring-login:latest .'
              sh 'docker image save -o ciokma-spring-login-latest.tar ciokma/spring-login:latest'
              
            }
        }
        stage('Upload Artifact to Nexus') {
	  steps {
   		   nexusArtifactUploader artifacts: [[artifactId: '01-spring-login',
					       classifier: '',
					       file: 'target/spring-login-0.0.1-SNAPSHOT.jar',
					       type: 'jar']],
		    credentialsId: 'nexus-credentials',
		    groupId: 'spring-login',
		    nexusUrl: '3.95.38.34:8081',
		    nexusVersion: 'nexus3',
		    protocol: 'http',
		    repository: 'spring-ms-app',
		    version: "${currentBuild.number}"		  
	  }
       }
        stage('Build and Push Docker Image to Nexus') {
	  steps {
		  sh 'docker login -u $NEXUSDOCKER_CREDENTIALS_USR -p $NEXUSDOCKER_CREDENTIALS_PSW ec2-54-209-208-49.compute-1.amazonaws.com:8085'
		  sh "docker build . -t ec2-54-209-208-49.compute-1.amazonaws.com:8085/spring-login:${currentBuild.number}"
		  sh "docker push ec2-54-209-208-49.compute-1.amazonaws.com:8085/spring-login:${currentBuild.number}"
		  
	  }
       }
       stage('Login to dockerhub') {

		steps {
			sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
		}
	}

	stage('Push to dockerhub') {

		steps {
			sh 'docker push ciokma/spring-login:latest'
		}
	}
        
        stage('Desplegar en OpenShift') {
            steps {
              sh '''
		    oc login --token=kMpcdg7ThlPQo5tkaC-9gDEXI7F_AO-6l8BLiWt7wXQ --server=https://ec2-54-224-88-191.compute-1.amazonaws.com:8443 --insecure-skip-tls-verify
		    oc delete all -l app=spring-login
		    oc new-app ciokma/spring-login:latest --name spring-login
		  '''
		   sh "#oc new-app ec2-54-209-208-49.compute-1.amazonaws.com:8085/springboot:${currentBuild.number} --name spring-login"
		   sh 'oc expose svc/spring-login --name=spring-login'
                  
             }
        }
    }
}
