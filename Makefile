.mvn.DEFAULT_GOAL := install

install:
	mvnw clean install
update:
	mvnw liquibase:update
database:
	docker run --name postgres-docker -e POSTGRES_PASSWORD=qwerty -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres
drop:
	docker rm -f $$(docker ps -qa); docker volume rm $$(docker volume ls -q);
init:
	export PGPASSWORD=qwerty && psql -h 192.168.99.100 -p 5432 -U postgres -w -f sql/init.sql
deploy:
	scp server-app/target/server-app-00.001.00-SNAPSHOT.jar root@81.163.28.113:./
run:
	nohup java -Xmx32m -Xss256k -jar server-app-00.001.00-SNAPSHOT.jar > log.txt
docker:
	docker -v

