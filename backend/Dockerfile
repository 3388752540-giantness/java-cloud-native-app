# --- 1. 编译阶段 (使用 Bitnami 提供的 Maven/JDK17) ---
FROM bitnami/java:17 AS build
# Bitnami 镜像默认是 root 权限，但为了稳妥我们指定下
USER root
WORKDIR /app
COPY . .
# Bitnami 预装了 Maven 环境
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# --- 2. 运行阶段 (使用极简 JRE 17) ---
FROM bitnami/java:17-runtime
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
