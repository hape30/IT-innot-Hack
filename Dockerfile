FROM maven:3.9.7-eclipse-temurin-21-alpine as builder
WORKDIR /
COPY java/src src
COPY java/pom.xml .
RUN mvn clean package -DskipTests

FROM bellsoft/liberica-runtime-container:jre-21-cds-slim-glibc as optimizer
WORKDIR /optimizer
ARG JAR_FILE=/target/webCommit-*.jar
COPY --from=builder ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM bellsoft/liberica-runtime-container:jre-21-cds-slim-glibc
WORKDIR /application
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser
EXPOSE 8080
COPY --from=optimizer /optimizer/extracted/dependencies/ ./
COPY --from=optimizer /optimizer/extracted/spring-boot-loader/ ./
COPY --from=optimizer /optimizer/extracted/snapshot-dependencies/ ./
COPY --from=optimizer /optimizer/extracted/application/ ./
ENTRYPOINT ["java", "-jar", "application.jar"]
