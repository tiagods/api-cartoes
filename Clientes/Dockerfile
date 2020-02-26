FROM mysql:5.7
ENV MYSQL_DATABASE clientes
ENV MYSQL_ROOT_PASSWORD=root

COPY init-database.js /docker-entrypoint-initdb.d/
EXPOSE 3306