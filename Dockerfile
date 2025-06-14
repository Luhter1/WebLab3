ARG JAVA_VERSION=17
ARG BUILD_TOOL_ARGS=""

FROM openjdk:${JAVA_VERSION}-jdk-slim AS builder

RUN apt-get update && apt-get install -y ant && apt-get install -y mpg123 && apt-get install -y python3

WORKDIR /server

COPY . /server

RUN ant -Djdk.args="${BUILD_TOOL_ARGS}" build

FROM bitnami/wildfly:latest

COPY --from=builder /server/app/build/dist/server.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080 9990

RUN /opt/bitnami/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]