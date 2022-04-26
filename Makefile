.mvn.DEFAULT_GOAL := install

install:
	mvnw clean install
package:
	mwnw clean package
update:
	mvnw liquibase:update
database:
	docker run --name postgres-docker -e POSTGRES_PASSWORD=qwerty -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres
drop:
	docker rm -f $$(docker ps -qa); docker volume rm $$(docker volume ls -q);
init:
	export PGPASSWORD=qwerty && psql -h 192.168.99.100 -p 5432 -U postgres -w -f sql/init.sql
config:
	scp server-app/target/server-app-00.001.00-SNAPSHOT.jar root@81.163.28.113:./
deploy:
	nohup java -Xmx32m -Xss256k -jar server-app-00.001.00-SNAPSHOT.jar > log.txt
docker-build:
	docker-compose build
docker-up:
	docker-compose up
run:
	mvn spring-boot:run
swagger:
	curl http://localhost:8080/v3/api-docs.yaml -o swagger.yaml
