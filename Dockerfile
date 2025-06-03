FROM azul/zulu-openjdk:21-jre

WORKDIR /app

COPY target/ms-product-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]
