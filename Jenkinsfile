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
            stage(two) {
            enviroment  {
                           Project_Name = "Todo"
                           }
                         steps {
                                sh "echo ${Project_Name}"
                                }
                        }
        }
    }
