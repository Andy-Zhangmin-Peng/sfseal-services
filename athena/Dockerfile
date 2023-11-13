FROM openjdk:8-jdk-alpine

ENV SPRING_PROFILES_ACTIVE JWTAuthCore,local

VOLUME /tmp
ARG DEPENDENCY=/build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-cp","app:app/lib/*","com.wanyun.sfseal.athena.Application"]
