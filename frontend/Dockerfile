# --- 第一阶段：构建 (使用 Bitnami Node 18) ---
FROM bitnami/node:18 AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 (使用 Bitnami Nginx) ---
FROM bitnami/nginx:latest AS production-stage
# Bitnami 的 Nginx 路径与官方略有不同，这是它的标准路径
COPY --from=build-stage /app/dist /opt/bitnami/nginx/html
EXPOSE 8080
# Bitnami 镜像默认使用 8080 端口，非 root 运行更安全