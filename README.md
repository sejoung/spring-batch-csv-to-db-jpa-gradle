# 스프링 배치
스프링 배치로 csv 파일 DB에 저장

도커로 DB 실행

```
docker run -p 5432:5432 --name test -e POSTGRES_PASSWORD=test -d postgres 
```
db에 batch 관련 스키마 미생성 처리

# 참조
[postgres docker hub](https://hub.docker.com/_/postgres)
