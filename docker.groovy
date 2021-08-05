folder('DC-Pipelines') {
    displayName('DC Pipelines')
    description('DC Pipelines')
}
def component = ["login", "todo","users","frontend"];
def count =(component.size()-1)
for (i in 0..count) {
    def j = component[i]
    pipelineJob("CI-Pipelines/${j}-ci") {
        configure { flowdefinition ->
            flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
                'triggers' {
                    'hudson.triggers.SCMTrigger' {
                        'spec'('*/4 * * * 1-5')
                        'ignorePostCommitHooks'(false)
                    }
                }
            }

            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'('https://github.com/swathi-zelarsoft/'+j+'.git')
                            'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')

                        }
                    }
                    'branches' {
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/tags/*')
                        }
                    }
                }


                'scriptPath'('Jenkinsfile-Docker')
                'lightweight'(true)
            }
        }

    }
}