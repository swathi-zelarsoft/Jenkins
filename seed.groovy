folder('CI-Pipelines') {
    displayName('CI Pipelines')
    description('CI Pipelines')
}
pipelineJob("CI-Pipelines/frontend-ci"){
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/swathi-zelarsoft/frontend.git')

                    } }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }


            'scriptPath'('jenkins')
            'lightweight'(true)
        }
    }

}
pipelineJob("CI-Pipelines/login-ci"){
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/swathi-zelarsoft/login.git')

                    } }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }


            'scriptPath'('jenkinsscript')
            'lightweight'(true)
        }
    }

}
pipelineJob("CI-Pipelines/users-ci"){
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/swathi-zelarsoft/users.git')

                    } }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }


            'scriptPath'('Jenkinsscript')
            'lightweight'(true)
        }
    }

}

pipelineJob("CI-Pipelines/todo-ci"){
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/swathi-zelarsoft/todo.git')

                    } }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }


            'scriptPath'('jenkinsscript')
            'lightweight'(true)
        }
    }

}