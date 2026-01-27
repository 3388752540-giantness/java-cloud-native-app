# --- 第一阶段：构建 ---
# 使用阿里云同步的 Node 镜像
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/node:18-slim AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 ---
# 使用阿里云同步的 Nginx 镜像
FROM registry.cn-hangzhou.aliyuncs.com/central-mirror/nginx:stable-alpine AS production-stage

# 复制 nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf
# 拷贝静态文件
COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]