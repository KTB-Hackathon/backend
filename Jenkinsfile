pipeline {
    agent any

    environment {
        REPO = 'KTB-Hackathon/backend'
        ECR_REPO = '9905418374604.dkr.ecr.ap-northeast-2.amazonaws.com/ktbhackback'
        ECR_CREDENTIALS_ID = 'ecr:ap-northeast-2:ecr_credentials_id'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "https://github.com/${REPO}.git"
            }
        }

        stage('Check JAR File') {
            steps {
                script {
                    sh 'ls -l build/libs/'
                }
            }
        }

        stage('Build Gradle') {
                    steps {
                        withGradle {
                            sh './gradlew build'
                          }
                    }
                }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${ECR_REPO}:latest")
                }
            }
        }

        stage('Push to ECR') {
            steps {
                script {
                    docker.withRegistry("https://${ECR_REPO}", "${ECR_CREDENTIALS_ID}") {
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy on Local Server') {
            steps {
                script {
                    // Jenkins 자격증명을 사용하여 Docker 로그인
                    docker.withRegistry("https://${ECR_REPO}", "${ECR_CREDENTIALS_ID}") {
                        //기존에 도커 컨테이너 삭제
//                         sh "docker rm -f todayfin-fe"

                        // ECR에서 이미지 pull
                        sh "docker pull ${ECR_REPO}:latest"

                        // 도커 컨테이너 실행
                        sh "docker run -d --name ktbhackback -p 80:8080 ${ECR_REPO}:latest"
                    }
                }
            }
        }
    }
}