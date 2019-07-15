job('NodeJS Docker example') {
    scm {
            git('git://github.com/ganeshans1/my-node-app-docker.git') {  node -> // is hudson.plugins.git.GitSCM
                node / gitConfigName('DSL User')
                node / gitConfigEmail('ganesh90438@gmail.com')
            }
        }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ganeshans1/node-js-example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}