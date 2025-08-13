FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/auth_service-0.0.1-SNAPSHOT.jar app/auth_service-0.0.1-SNAPSHOT.jar

EXPOSE 8082
CMD ["java", "-jar", "app/auth_service-0.0.1-SNAPSHOT.jar"]
