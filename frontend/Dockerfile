# --- 第一阶段：构建 ---
# 将 FROM node:18-slim 改为阿里云的镜像地址
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/node:18-slim AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 ---
# 将 FROM nginx:stable-alpine 改为阿里云的镜像地址
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/nginx:stable-alpine AS production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]