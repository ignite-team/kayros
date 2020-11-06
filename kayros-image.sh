mvn -f eureka clean spring-boot:build-image -Dmaven.test.skip=true
mvn -f authorization-server clean spring-boot:build-image -Dmaven.test.skip=true
mvn -f kayros-calendar clean spring-boot:build-image -Dmaven.test.skip=true
mvn -f kayros-clock clean spring-boot:build-image -Dmaven.test.skip=true
mvn -f kayros-employee clean spring-boot:build-image -Dmaven.test.skip=true
mvn -f kayros-webapp clean spring-boot:build-image -Dmaven.test.skip=true

