pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3.9.14'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'exit /b 1'
            }
        }

        stage('Check Reports') {
            steps {
                bat '''
                echo ===== TARGET DIRECTORY =====
                dir target

                echo ===== CUCUMBER HTML =====
                dir target\\CucumberReports.html

                echo ===== EXTENT REPORT =====
                dir reports\\ExtentReport.html
                '''
            }
        }
    }

    post {

    always {

        echo 'Test execution completed.'

        publishHTML([
            allowMissing: true,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'target',
            reportFiles: 'CucumberReports.html',
            reportName: 'Cucumber HTML Report',
            reportTitles: 'Cucumber Report'
        ])

        publishHTML([
            allowMissing: true,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'reports',
            reportFiles: 'ExtentReport.html',
            reportName: 'Extent Report',
            reportTitles: 'Extent Report'
        ])
    }

    success {
        echo 'Automation tests PASSED.'
    }

    failure {
        echo 'Automation tests FAILED.'

        mail(
            to: 'abhinewversion897@gmail.com',
            subject: "Jenkins FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
Jenkins Automation Test Failed.

Job: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Status: ${currentBuild.currentResult}

Please check Jenkins console output and reports.

Jenkins URL:
${env.BUILD_URL}
"""
        )
    }
}