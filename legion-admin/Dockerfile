FROM pig4cloud/java:8-jre

MAINTAINER leiyanfu@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /invoice

WORKDIR /invoice

EXPOSE 8555

ADD ./target/Invoice.jar ./

CMD sleep 12;java $JAVA_OPTS -jar Invoice.jar


