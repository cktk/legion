FROM pig4cloud/java:8-jre

MAINTAINER cktk@qq.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /legion-admin-M1.jar

WORKDIR /legion-admin-M1

EXPOSE 8080

ADD ./target/legion-admin-M1.jar ./

CMD java $JAVA_OPTS -jar legion-admin-M1.jar
