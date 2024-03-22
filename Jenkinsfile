pipeline{
    agent any
    
    stages{
        stage("Build"){
            steps{
                echo("Build the project")
            }
        }
        
        stage("Deply to Dev"){
            steps{
                echo("Deploying the code in DEV env")
            }
        }
        
        stage("Deply to QA"){
            steps{
                echo("Deploying the code in QA env")
            }
        }
        
        stage("Running regression test cases in QA"){
            steps{
                echo("Running the regression automation test cases in QA env")
            }
        }
        
        stage("Deply to UAT"){
            steps{
                echo("Deploying the code in UAT env")
            }
        }
        
        stage("Running Sanity test cases in UAT"){
            steps{
                echo("Running the sanity automation test cases in UAT env")
            }
        }
        
        stage("Deply to PROD"){
            steps{
                echo("Deploying the code in PROD env")
            }
        }
    }
}