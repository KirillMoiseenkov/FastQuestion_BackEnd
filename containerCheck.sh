mvn install -DskipTests
docker-compose up --build
docker ps

docker stop fastquestion_dbpostgresql_1
docker stop fast-question

docker ps
