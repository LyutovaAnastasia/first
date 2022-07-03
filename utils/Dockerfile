FROM postgres:latest
COPY scripts/init.sql /docker-entrypoint-initdb.d/
COPY scripts/postgres_localhost-2022_05_22_17_26_07-dump.sql /docker-entrypoint-initdb.d/

