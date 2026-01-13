# --- 1. 编译阶段 (使用亚马逊提供的 Maven/Java17 镜像) ---
FROM public.ecr.aws/docker/library/maven:3.8.6-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --- 2. 运行阶段 (使用亚马逊提供的精简 Java17 镜像) ---
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
WORKDIR /app
# 再次提醒：请确认 pom.xml 里的 artifactId 和版本号是否对应生成这个 jar 包名字
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
