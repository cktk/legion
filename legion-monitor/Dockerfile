FROM pig4cloud/java:8-jre

MAINTAINER cktk@qq.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms256m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /legion-monitor-M1.jar

WORKDIR /legion-monitor-M1

EXPOSE 10086

ADD ./target/legion-monitor-M1.jar ./

CMD java $JAVA_OPTS -jar legion-monitor-M1.jar
