# spring-boot-yaus ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
Spring Boot native app demo (Yet Another URL Shortener with Redis).

- Spring Boot version: 3.0.0 (RC2)
- Java version: 17
- Build tool: Maven

Disclaimer: the demo app is an over-simplified URL shortener for the purpose of testing HashiCorp Nomad's Horizontal Application Scaling capabilities with a Spring Native app as scaling target (an app that has some footprint and is not a simple "Hello World" case). You can read all about it on my Dzone article [Auto-Scaling a Spring Boot Native App With Nomad](https://dzone.com/articles/auto-scaling-a-spring-native-app-with-nomad). If you are interested in building an actual URL shortener clone, check System Design approaches like [this one](https://dev.to/karanpratapsingh/system-design-url-shortener-10i5).

## build
Run `mvn -Pnative spring-boot:build-image`

This will take some time :hourglass_flowing_sand: since we are building a GraalVM Native Image.

## run
`docker-compose up`

## References
- https://docs.spring.io/spring-boot/docs/3.0.0-RC2/reference/html/native-image.html#native-image.introducing-graalvm-native-images
- https://docs.spring.io/spring-boot/docs/3.0.0-RC2/reference/html/native-image.html#native-image.developing-your-first-application.buildpacks.maven
