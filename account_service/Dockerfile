FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/account_service_0.0.1_SNAPSHOT.jar app/account_service_0.0.1_SNAPSHOT.jar

EXPOSE 8081
CMD ["java", "-jar", "app/account_service_0.0.1_SNAPSHOT.jar"]