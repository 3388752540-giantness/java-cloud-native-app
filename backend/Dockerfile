# --- 1. 编译阶段 ---
# 使用阿里云镜像替代官方镜像
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/maven:3.8.6-jdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- 2. 运行阶段 ---
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/openjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
