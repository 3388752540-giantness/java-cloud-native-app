# --- 第一阶段：构建 ---
FROM node:18-slim AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 ---
FROM nginx:stable-alpine AS production-stage

# 复制刚才写的 nginx 配置文件到镜像中 (覆盖默认配置)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 从构建阶段拷贝静态文件
COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]