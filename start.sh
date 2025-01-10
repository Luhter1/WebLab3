# gradle build
# mv ./app/build/libs/server.war ./wildfly/standalone/deployments/server.war
# ./wildfly/bin/standalone.sh

gradle build
sudo docker-compose up --build -d