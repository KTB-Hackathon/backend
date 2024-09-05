pipeline {
    agent any

    environment {
        REPO = 'KTB-Hackathon/backend'
        DOCKER_HUB_REPO = 'jonum12312/ktbhackback'  // Docker Hub 저장소
        DOCKER_HUB_CREDENTIALS_ID = 'dockerhub'     // Jenkins에 저장된 Docker Hub 자격 증명
    }

    stages {
        // 1. Git Checkout
        stage('Checkout') {
            steps {
                git branch: 'main', url: "https://github.com/${REPO}.git"
            }
        }

        // 2. Check JAR File
        stage('Check JAR File') {
            steps {
                script {
                    sh 'ls -l build/libs/'
                }
            }
        }

        // 3. Gradle Build
        stage('Build Gradle') {
            steps {
                withGradle {
                    sh './gradlew build'
                }
            }
        }

        // 4. Docker 이미지 빌드
        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드
                    dockerImage = docker.build("${DOCKER_HUB_REPO}:${BUILD_NUMBER}")
                }
            }
        }

        // 5. Docker Hub에 푸시
        stage('Push to Docker Hub') {
            steps {
                script {
                    // Jenkins에 저장된 Docker Hub 자격 증명 사용
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_HUB_CREDENTIALS_ID}") {
                        dockerImage.push('latest')  // 태그를 'latest'로 푸시
                        dockerImage.push("${BUILD_NUMBER}")  // 빌드 번호로도 태그를 푸시
                    }
                }
            }
        }

        // 6. Docker Hub에서 로컬 서버로 배포
        stage('Deploy on Local Server') {
            steps {
                script {
                    // 로컬 서버에서 기존 컨테이너 제거
                    sh "docker rm -f ktbhackback || true"

                    // Docker Hub에서 최신 이미지 pull
                    sh "docker pull ${DOCKER_HUB_REPO}:latest"

                    // 컨테이너 실행 (로컬 포트 80 -> 컨테이너 8080)
                    sh "docker run -d --name ktbhackback -p 8888:8080 ${DOCKER_HUB_REPO}:latest"
                }
            }
        }
    }
}
