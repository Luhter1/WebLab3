FROM bitnami/wildfly:latest

COPY ./app/build/libs/server.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war


EXPOSE 8080 9990

RUN /opt/bitnami/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]