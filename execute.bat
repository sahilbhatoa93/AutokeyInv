cls
call mvn clean install 
call mvn eclipse:eclipse 
call mvn tomcat7:run -Denvfile="file:E:/AutoKeyinventorySoftwareProperties.properties"