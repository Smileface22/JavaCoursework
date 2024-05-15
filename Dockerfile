FROM openjdk:17

WORKDIR /app

COPY . /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/build/libs/butcher_shop-0.0.1-SNAPSHOT.jar"]