FROM mysql/mysql-server:8.0.27

MAINTAINER Daimao(cktk@qq.com)

ENV TZ=Asia/Shanghai


RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


COPY ./legion.sql /docker-entrypoint-initdb.d
