FROM azul/zulu-openjdk-alpine:17.0.0
ADD target/urlshortener.jar urlshortener.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "urlshortener.jar"]