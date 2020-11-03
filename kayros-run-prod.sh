docker run -d --rm --hostname rabbitmq -p 15672:15672 --name rabbitmq --network kayros-net rabbitmq:management-alpine
docker run -d --rm --name eureka-one -p 4001:4001 --network kayros-net -e spring_profiles_active=eureka-one eureka:1.2.0
echo [Disable] docker run -d --rm --name eureka-two -p 4002:4002 --network kayros-net -e spring_profiles_active=eureka-two eureka:1.2.0
echo [Disable] docker run -d --rm --name eureka-three -p 4003:4003 --network kayros-net -e spring_profiles_active=eureka-three eureka:1.2.0
docker run -d --rm --name authorization-server -p 9100:9100 --network kayros-net -e spring_profiles_active=prod authorization-server:1.2.0 
docker run -d --rm --name kayros-calendar -p 8081:8081 --network kayros-net -v "/opt/data:/workspace/data" -e spring_profiles_active=prod kairos-calendar:1.2.0 
docker run -d --rm --name kayros-clock -p 8082:8082 --network kayros-net -v "/opt/data:/workspace/data" -e spring_profiles_active=prod kairos-clock:1.2.0 
docker run -d --rm --name kayros-employee -p 8083:8083 --network kayros-net -v "/opt/data:/workspace/data" -e spring_profiles_active=prod kairos-employee:1.2.0
docker run -d --rm --name kayros-webapp -p 8080:8080 -p 8443:8443 --network kayros-net -v "/opt/data:/workspace/data" -e spring_profiles_active=prod kayros-webapp:1.2.0

