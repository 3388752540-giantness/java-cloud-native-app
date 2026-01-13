# --- 第一阶段：构建 (使用亚马逊提供的 Node 18) ---
FROM public.ecr.aws/docker/library/node:18-slim AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# --- 第二阶段：运行 (使用亚马逊提供的 Nginx) ---
FROM public.ecr.aws/docker/library/nginx:alpine AS production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]