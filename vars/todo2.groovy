def call(Map params = [:]) {
    // Start Default Arguments
    def args = [
            NEXUS_IP               : '192.168.0.69',
    ]
    args << params

    // End Default + Required Arguments
    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }

        triggers {
            pollSCM('*/2 * * * 1-5')
        }

        environment {
            COMPONENT = "${args.COMPONENT}"
            NEXUS_IP = "${args.NEXUS_IP}"
            PROJECT_NAME = "${args.PROJECT_NAME}"
            SLAVE_LABEL = "${args.SLAVE_LABEL}"
            APP_TYPE = "${args.APP_TYPE}"
        }
        stages {
            stage('code build and install dependencies') {
                steps {
                    script {
                        build = new todo1()
                        build.code_build ("${APP_TYPE}","${COMPONENT}")
                    }
                }
            }

            stage('Prepare Artifacts') {
                steps {
                    script {
                        prepare = new todo1()
                        prepare.make_artifacts("${APP_TYPE}","${COMPONENT}")
                    }
                }
            }
            stage('Upload Artifacts') {
                steps {
                    script {
                        upload = new todo1()
                        upload.nexus(COMPONENT)
                    }
                }
            }
            stage('Deploy to Dev Env') {
                steps {
                    script {
                        get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
                        env.get_branch_exec=sh(returnStdout: true, script: get_branch)
                    }
                    build job: 'Deployment-pipeline', parameters: [string(name: 'ENV', value: 'dev'), string(name: 'COMPONENT', value: "${COMPONENT}"), string(name: 'VERSION', value: "${get_branch_exec}")]
                }
            }


        }
    }
}
