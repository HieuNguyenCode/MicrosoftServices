FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/admin_service-0.0.1-SNAPSHOT.jar app/admin_service-0.0.1-SNAPSHOT.jar

EXPOSE 8083
CMD ["java", "-jar", "app/admin_service-0.0.1-SNAPSHOT.jar"]
