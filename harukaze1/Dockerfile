FROM nginx
MAINTAINER "harukaze" 

RUN rm /etc/nginx/conf.d/default.conf

ADD conf.d/default.conf /etc/nginx/conf.d/

COPY dist/ /usr/share/nginx/harukaze