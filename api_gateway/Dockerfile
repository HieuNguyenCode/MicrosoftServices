FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/api_gateway-0.0.1-SNAPSHOT.jar app/api_gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8762
CMD ["java", "-jar", "app/api_gateway-0.0.1-SNAPSHOT.jar"]
