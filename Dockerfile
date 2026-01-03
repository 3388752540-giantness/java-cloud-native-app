# 1. 编译阶段：换用目前最稳的镜像前缀
FROM hub.rat.dev/library/maven:3.8.5-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# 你的阿里云 Maven 加速代码（这段代码写得很好，完全没问题，保留）
RUN mkdir -p /root/.m2 &&     echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"     xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.1.0.xsd">     <mirrors>         <mirror>             <id>aliyunmaven</id>             <mirrorOf>*</mirrorOf>             <name>阿里云公共仓库</name>             <url>https://maven.aliyun.com/repository/public/</url>         </mirror>     </mirrors>     </settings>' > /root/.m2/settings.xml &&     mvn clean package -DskipTests

# 2. 运行阶段
FROM hub.rat.dev/library/openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
