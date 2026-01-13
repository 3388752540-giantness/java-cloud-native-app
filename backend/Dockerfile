# 1. 编译阶段
# 使用 JDK 17 的 Maven 镜像
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# 运行打包
RUN mvn clean package -DskipTests

# 2. 运行阶段
# 使用 JRE 17 镜像（比 JDK 更小，更安全，适合生产环境）
FROM eclipse-temurin:17-jre
WORKDIR /app
# 拷贝编译结果
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
