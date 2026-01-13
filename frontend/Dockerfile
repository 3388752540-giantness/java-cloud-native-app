# --- 第一阶段：构建 (Build Stage) ---
# 使用 Node.js 18 官方镜像作为构建环境
FROM node:18-slim AS build-stage

# 设置容器内的工作目录
WORKDIR /app

# 先拷贝 package.json 和 lock 文件，安装依赖
# 这样如果代码变动但依赖没变，可以利用 Docker 缓存加速构建
COPY package*.json ./
RUN npm install

# 拷贝所有源代码并执行生产环境打包
COPY . .
RUN npm run build

# --- 第二阶段：运行 (Production Stage) ---
# 我们不需要 Node.js 环境来运行静态网页，改用极其轻量级的 Nginx
FROM nginx:stable-alpine AS production-stage

# 从构建阶段拷贝打包好的静态文件 (dist 目录) 到 Nginx 的默认展示目录
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 暴露 80 端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]