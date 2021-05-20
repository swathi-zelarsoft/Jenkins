def call(Map params = [:]) {
    // Start Default Arguments
    def args = [
            NEXUS_IP: '172.31.14.124',
    ]
    args << params

    // End Default + Required Arguments
    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }

        triggers {
            pollSCM('* * * * 1-5')
        }

        environment {
            COMPONENT = "${args.COMPONENT}"
            NEXUS_IP = "${args.NEXUS_IP}"
            PROJECT_NAME = "${args.PROJECT_NAME}"
            SLAVE_LABEL = "${args.SLAVE_LABEL}"
            APP_TYPE = "${args.APP_TYPE}"
        }
        stages {
            stage("Download dependencies") {
                when {
                    anyOf { environment name: 'APP_TYPE', value: 'NGINX' }
                            { environment name: 'APP_TYPE', value: 'JAVA' }
                }
                steps {
                    sh '''
              npm install
              '''
                }

            }
            stage("Making a build") {
                when { environment name: 'APP_TYPE', value: 'NGINX' }
                steps {
                    sh '''
                   npm run build
                   '''
                }

            }
            stage('Prepare Artifacts') {
                steps {
                    script{
                        prepare = new todo1()
                        prepare.make_artifacts ("${APP_TYPE}","${COMPONENT}")
                    }
                    sh '''
           ls
        '''
                }
            }
        }
    }
}