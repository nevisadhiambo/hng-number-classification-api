FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
COPY --from=build /target/hng-number-classification-api-0.0.1-SNAPSHOT.jar hng-number-classification-api.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","hng-number-classification-api.jar"]