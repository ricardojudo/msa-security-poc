FROM openjdk:8-jre-alpine

RUN adduser -D wso2 && id wso2

COPY wso2is-5.5.0.zip /usr/
WORKDIR /usr

RUN unzip wso2is-5.5.0.zip
RUN chown -Rf wso2:wso2 wso2is-5.5.0 \ 
    && rm wso2is-5.5.0.zip \ 
    && chmod g+s wso2is-5.5.0

WORKDIR /usr/wso2is-5.5.0
RUN mkdir /var/wso2 && chown -Rf wso2:wso2 /var/wso2 && chmod g+s /var/wso2
USER wso2


CMD ["./bin/wso2server.sh"]


