# 1. 编译阶段：直接从官方 Docker Hub 拉取
FROM maven:3.8.5-jdk-11 AS build
WORKDIR /app
# 复制源码
COPY . .
# 编译打包（在海外机器上，由于能直连 Maven 中央仓库，下载速度极快，无需阿里私服）
RUN mvn clean package -DskipTests

# 2. 运行阶段：直接从官方 Docker Hub 拉取
FROM openjdk:11-jre-slim
WORKDIR /app
# 拷贝编译结果
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
