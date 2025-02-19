## Angular Frontend -- Nginx server version

FROM node:alpine AS builder
COPY ./medical-platform-frontend ./medical-platform-frontend
WORKDIR ./medical-platform-frontend
RUN npm install && \
    npm run build --prod

FROM nginx:alpine
COPY --from=builder /medical-platform-frontend/dist/medical-platform-frontend/ /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf