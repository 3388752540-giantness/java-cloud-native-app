# 1. 编译阶段
# 使用阿里云公开镜像，稳定且快
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/maven:3.8.5-jdk-11 AS build
WORKDIR /app
COPY . .
# 执行打包
RUN mvn clean package -DskipTests

# 2. 运行阶段
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
