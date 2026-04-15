FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/shopping-cart-1.0.jar app.jar

ENV LANG=C.UTF-8

CMD ["java", "-jar", "app.jar"]