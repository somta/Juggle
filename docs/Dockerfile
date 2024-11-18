FROM nginx:stable-alpine

COPY ./docs/.vuepress/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]