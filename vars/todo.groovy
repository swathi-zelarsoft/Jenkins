def call(Map params = [:]) {
    // Start Default Arguments
    def args = [
            NEXUS_IP               : '192.168.0.99',
    ]
    args << params

    // End Default + Required Arguments
    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }

        environment {
            COMPONENT     = "${args.COMPONENT}"
            NEXUS_IP      = "${args.NEXUS_IP}"
            PROJECT_NAME  = "${args.PROJECT_NAME}"
            SLAVE_LABEL   = "${args.SLAVE_LABEL}"
            APP_TYPE      = "${args.APP_TYPE}"
        }
    stages {
            stage("Download dependencies") {
                when {
                    anyOf { environment name: 'APP_TYPE', value: 'NGINX'
                            environment name: 'APP_TYPE', value: 'JAVA' }
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
                when { environment name: 'APP_TYPE', value: 'NGINX' }
                steps {
                    sh '''
        zip -r ${COMPONENT}.zip dist node_modules
        '''
                }
            }
            stage('Getting dependencies-LOGIN'){
                when { environment name: 'APP_TYPE', value: 'GO_LANG' }

                steps {
                    sh '''
        go get github.com/dgrijalva/jwt-go
        go get github.com/labstack/echo
        go get github.com/labstack/echo/middleware
        go get github.com/labstack/gommon/log
        go get github.com/openzipkin/zipkin-go
        go get github.com/openzipkin/zipkin-go/middleware/http
        go get  github.com/openzipkin/zipkin-go/reporter/http
        '''
                }
            }

            stage('Build the file'){
                when { environment name: 'APP_TYPE', value: 'GO_LANG' }
                steps {
                    sh '''
                 go build
                 '''
                } }
            stage('Prepare Artifacts-LOGIN'){
                when { environment name: 'APP_TYPE', value: 'GO_LANG' }
                steps {
                    sh '''
                       zip -r ${COMPONENT}.zip login-ci
                        '''
                }
            }
        stage('Download dependencies-USERS'){
            when { environment name: 'APP_TYPE', value: 'JAVA'}

            steps {
                sh '''  
        mvn clean package
        '''
            }
        }
        stage('Prepare Artifacts-USERS'){
            when { environment name: 'APP_TYPE', value: 'JAVA'}
            steps {
                sh '''
                cp target/*.jar ${COMPONENT}.jar
                zip -r ${COMPONENT}.zip ${COMPONENT}.jar
                '''
            }
        }
        stage('Prepare Artifacts-todo'){
            when { environment name: 'APP_TYPE', value: 'JAVA'}
            steps {
                sh '''
        zip -r ${COMPONENT}.zip node_modules server.js
        '''
            }
        }
            stage('upload artifacts') {
                steps {
                    sh '''
        curl -v -u admin:admin123 --upload-file /home/ubuntu/workspace/CI-Pipelines/${COMPONENT}-ci/${COMPONENT}.zip http://${NEXUS_IP}/repository/${COMPONENT}/${COMPONENT}.zip
        '''
                }
            }
        }
    }
}