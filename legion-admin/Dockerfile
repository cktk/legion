FROM registry.cn-beijing.aliyuncs.com/coocco/basesky:8-jre

MAINTAINER leiyanfu@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /legion-admin

WORKDIR /legion-admin

EXPOSE 8888

ADD ./target/legion-admin-M1.jar ./

CMD sleep 6;java  $JAVA_OPTS -jar legion-admin-M1.jar
