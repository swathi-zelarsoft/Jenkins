def make_artifacts(APP_TYPE, COMPONENT) {
    if (APP_TYPE == "NGINX") {
        command 'zip -r ${COMPONENT}.zip dist node_modules'
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "GO_LANG") {
        command 'zip -r ${COMPONENT}.zip login-ci'
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "JAVA") {
        command 'cp target/*.jar ${COMPONENT}.jar && zip -r ${COMPONENT}.zip ${COMPONENT}.jar'
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
    elseif(APP_TYPE == "NODEJS") {
        command '  zip -r ${COMPONENT}.zip node_modules server.js'
        def execute_com = sh(returnStdout: true, script: command)
        print execute_com
    }
}