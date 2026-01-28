# --- 第一阶段：构建 ---
# 改用你自己的私有基础镜像（国内拉取，无视 429）
FROM crpi-isruoknymwjt6jux.cn-hangzhou.personal.cr.aliyuncs.com/dja-repo/node-18-slim:latest AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 ---
# 改用你自己的私有基础镜像
FROM crpi-isruoknymwjt6jux.cn-hangzhou.personal.cr.aliyuncs.com/dja-repo/nginx-alpine:latest AS production-stage

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]