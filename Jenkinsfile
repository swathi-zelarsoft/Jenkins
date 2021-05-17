pipeline {
    agent any
     options {
     disableConcurrentBuilds()
     }
    environment {
    Project_Name = "Roboshop"
    UBUNTU_SSH_CRED = credentials('UBUNTU-SSH')
    }
        stages{
            stage(one){
                steps {
                   sh "echo ${Project_Name}"
                   sh "env"
                }
            }
            stage(two){
            environment {
            Project_Name = "Todo"
                         }
               steps {
                        sh "echo ${Project_Name}"
                      }
                        }
        }
    }
