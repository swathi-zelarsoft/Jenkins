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
                        build.code_build("${APP_TYPE}","${COMPONENT}")
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
        }
    }
}
