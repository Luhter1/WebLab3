# sudo docker compose up --build -d
ant build
cp ./app/build/dist/server.war wildfly/standalone/deployments/ROOT.war
wildfly/bin/standalone.sh