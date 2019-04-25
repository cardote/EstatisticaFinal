# EstatisticaFinal

## Docker

> Build

docker build -f Dockerfile -t docker-estatistica .

> Container 1

docker run --name App1 -p 8081:9090 docker-estatistica

> Container 2

docker run --name App2 -p 8082:9090 docker-estatistica

## Swagger

http://localhost:9090/swagger-ui.html
