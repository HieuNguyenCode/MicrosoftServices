FROM openjdk:17-jdk

LABEL author="HieuNguyen"

WORKDIR /app

COPY target/eureka_server-0.0.1-SNAPSHOT.jar app/eureka_server-0.0.1-SNAPSHOT.jar

EXPOSE 8761
CMD ["java", "-jar", "app/eureka_server-0.0.1-SNAPSHOT.jar"]
