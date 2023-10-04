pipeline {
    agent any
    tools{
        jdk "JAVA_HOME"
        maven "MAVEN"
        }
   stages {
       // stage('Static code Analysis') {
            // steps {
            //    bat "mvn -D clean verify sonar:sonar -Dsonar.token=sqa_2b48e809e77b331e1bb0c70c5c3a16ddb839d527"                      

         // }
       // }
    
       stage('Test') {
            steps {
                bat "mvn -D clean test"                      

          }
        }
    }
    post {  
         success {  
            //def console_output = "${env.BUILD_URL}/console" 
            //env.ForEmailPlugin = env.WORKSPACE
            archiveArtifacts artifacts: 'test-outut/*html', followSymlinks: false
            //emailext attachmentsPattern: 'DataFiles/*', body: '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:Check console output at $BUILD_URL to view the results.''', compressLog: true, mimeType: 'text/plain', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'pravin.kumar@interactiveavenues.com'
           
            // mail bcc: '', body: "Details: ${env.JOB_NAME} Build Number: ${env.BUILD_NUMBER} Build: ${env.BUILD_URL} Console Output: ${env.BUILD_URL}/console", cc: '',attachment: 'test-output/*html', from: 'pravininteractive25@gmail.com', replyTo: '', subject: 'Failing success Test', to: 'pravin.kumar@interactiveavenues.com'
         } 
    }
 

}
