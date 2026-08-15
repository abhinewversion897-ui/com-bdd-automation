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
                bat 'mvn test'
            }
        }
    }

    post {

        always {
            echo 'Test execution completed.'

            // Publish Cucumber HTML report
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'CucumberReports.html',
                reportName: 'Cucumber HTML Report'
            ])

            // Publish Extent HTML report
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }

        success {
            echo 'Automation tests PASSED.'
        }

        failure {
            echo 'Automation tests FAILED.'
        }
    }
}