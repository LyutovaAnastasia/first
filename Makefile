.mvn.DEFAULT_GOAL := install

install:
	mvnw clean install
update:
	mvnw liquibase:update
database:
	docker run --name postgres-docker -e POSTGRES_PASSWORD=example -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres
drop:
	docker rm -f $$(docker ps -qa); docker volume rm $$(docker volume ls -q);
init:
	export PGPASSWORD=qwerty && psql -h localhost -p 5432 -U postgres -w -f utils/init.sql