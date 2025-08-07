FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/department_service-0.0.1-SNAPSHOT.jar app/department_service-0.0.1-SNAPSHOT.jar

EXPOSE 8081
CMD ["java", "-jar", "app/department_service-0.0.1-SNAPSHOT.jar"]
