//pipeline {
 //   agent any
//     options {
// disableConcurrentBuilds()
//     }
//    environment {
//    Project_Name = "Roboshop"
//    UBUNTU_SSH_CRED = credentials('UBUNTU-SSH')
//    }
  //  triggers { cron('H */4 * * 1-5') }
    //tools{ maven 'mvn-3.6'}
//    parameters {
 //        string(name: 'COMPONENT', defaultValue: '', description: 'Which Component?')
//         text(name: 'COMMENT', defaultValue: '', description: 'Write Comment about the Job why are you running it.')
//         booleanParam(name: 'FORCE_DEPLOY', defaultValue: true, description: 'Check this for Force Deployment')
//         choice(name: 'ENV', choices: ['dev', 'qa', 'prod'], description: 'Pick an Environment')
//         password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
//       }

//        stages{
//            stage(one){
//            when {
//                       branch 'production'
//                      }
//                steps {
 //                  sh "echo ${Project_Name}"
 //                  sh "env"
 //               }
//            }
 //           stage(two){
  //          environment {
//            Project_Name = "Todo"
//                         }
//               steps {
 //                       sh "echo ${Project_Name}"
//                      }
 //                       }
 //       }
//    }

pipeline{
agent any
 stages{
 stage(Parallel steps){
 parallel{
 stage(one){
 steps {
 sh 'sleep 100'}}
 stage(two){
  steps {
  sh 'sleep 90'}}
  stage(three){
    steps {
    sh 'sleep 80'}}
 }
}}}