cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
cd ..
cd MovieCruiserServerApp
source ./env-variable.sh
mvn clean package
cd ..

