FROM openjdk:17-oracle AS builder
RUN microdnf install findutils
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-oracle
RUN mkdir /service
WORKDIR /service
COPY --from=builder build/libs/*.jar /service/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/service/app.jar"]