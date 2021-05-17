pipeline {
    agent any
    environment {
    Project_Name = "Roboshop"
    }
        stages{
            stage(one){
                steps {
                   sh "echo ${Project_Name}"
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
