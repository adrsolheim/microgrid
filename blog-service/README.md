# Dockerized
To prevent the *fat jar approach* of rebuilding the entire docker image on any changes to the code we make use of **layered jars** (see `BOOT-INF/layers.idx`).
More layers can be added if for example some company-wide library is used which changes often and you don't want to rebuild all dependencies every time.

Extract jar layers into a directory structure
```
java -Djarmode=layertools -jar target/docker-spring-boot-0.0.1.jar extract
```
![Source: Baeldung](https://www.baeldung.com/wp-content/uploads/2020/11/spring-boot-layers.jpg)
### `Testcontainers`
- Runs a real DB inside of a container and this provide 100% database compatibility (compared to H2 which is faster)