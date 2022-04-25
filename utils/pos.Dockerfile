FROM postgres:latest
COPY scripts/init.sql /docker-entrypoint-initdb.d/
COPY scripts/postgres_localhost-2022_04_01_19_43_12-dump.sql /docker-entrypoint-initdb.d/

