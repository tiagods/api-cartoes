# api-cartoes

# Script caso databse não seja criado

cd Cartoes
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=clientes teste
docker run -d -p 3306:3306 teste