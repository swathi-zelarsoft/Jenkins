def make_artifacts(APP_TYPE, COMPONENT) {
    if(APP_TYPE == "NGINX") {
        command "zip -r ${COMPONENT}.zip dist node_modules"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "GO_LANG") {
        command "zip -r ${COMPONENT}.zip login-ci"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "JAVA") {
        command "cp target/*.jar ${COMPONENT}.jar && zip -r ${COMPONENT}.zip ${COMPONENT}.jar"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "NODEJS") {
        command "zip -r ${COMPONENT}.zip node_modules server.js"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
}

def code_build(APP_TYPE, COMPONENT) {
    if(APP_TYPE == "NGINX") {
        command "npm install && npm run build"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "GO_LANG") {
        command "go get github.com/dgrijalva/jwt-go && go get github.com/labstack && go get github.com/labstack/echo/middleware &&' +
                'go get github.com/labstack/gommon/log && go get github.com/openzipkin/zipkin-go && go get github.com/openzipkin/zipkin-go/middleware/http &&go get  github.com/openzipkin/zipkin-go/reporter/http && go build"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "JAVA") {
        command "mvn clean package"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "NODEJS") {
        command "npm install"
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
}

def nexus(COMPONENT) {
    command = "curl -f -v -u admin:admin123 --upload-file /home/ubuntu/workspace/CI-Pipelines/${COMPONENT}-ci/${COMPONENT}.zip http://${NEXUS_IP}:8081/repository/${COMPONENT}/${COMPONENT}.zip"
    def execute_state=sh(returnStdout: true, script: command)
}