FROM 3.5.0-jdk-8-onbuild
COPY . /usr/src/server
RUN mvn spring-boot:run