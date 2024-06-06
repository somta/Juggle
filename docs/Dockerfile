FROM nginx:stable-alpine

# 移除默认的 Nginx 配置文件
#RUN rm /etc/nginx/conf.d/default.conf

# 把你的 Nginx 配置文件复制进去
#COPY nginx.conf /etc/nginx/conf.d

# 把 Docusaurus 生成的静态文件复制到 Nginx 的 HTML 目录
COPY ./build /usr/share/nginx/html

# 暴露 80 端口
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]