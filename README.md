# spring-boot-yaus ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
Spring Boot native app demo (Yet Another URL Shortener with Redis).

- Spring Boot version: 3.0.0 (RC2)
- Java version: 17
- Build tool: Maven


## build
Run `mvn -Pnative spring-boot:build-image`

## run
`docker-compose up`

## References
- https://docs.spring.io/spring-boot/docs/3.0.0-RC2/reference/html/native-image.html#native-image.introducing-graalvm-native-images
- https://docs.spring.io/spring-boot/docs/3.0.0-RC2/reference/html/native-image.html#native-image.developing-your-first-application.buildpacks.maven
