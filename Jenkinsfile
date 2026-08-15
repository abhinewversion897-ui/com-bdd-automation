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

            // Cucumber Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-report',
                reportFiles: 'index.html',
                reportName: 'Cucumber HTML Report',
                reportTitles: 'Cucumber Report'
            ])

            // Extent Report
            publishHTML([
                allowMissing: false,
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
        }
    }
}