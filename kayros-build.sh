mvn -f micro-core clean install -Dmaven.test.skip=true
mvn -f data-inquire clean install -Dmaven.test.skip=true
mvn -f authorization-server clean install -Dmaven.test.skip=true
mvn -f eureka clean install -Dmaven.test.skip=true
mvn -f kayros-calendar clean install -Dmaven.test.skip=true
mvn -f kayros-clock clean install -Dmaven.test.skip=true
mvn -f kayros-employee clean install -Dmaven.test.skip=true
mvn -f kayros-webapp clean install -Dmaven.test.skip=true
