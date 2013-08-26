
REFERENCES ------------------------------------------------------------------------------------

http://software.sawano.se/2012/03/combining-json-and-xml-in-restful-web.html
http://azagorneanu.blogspot.com/2011/09/crud-restful-services-using-spring.html


COMMANDS --------------------------------------------------------------------------------------

curl -H "Accept:application/json" -X GET http://localhost:8080/userservice/users/
curl -H "Accept:application/xml" -X GET http://localhost:8080/userservice/users/

curl -H "Accept:application/json" -X GET http://localhost:8080/userservice/users/1
curl -H "Accept:application/xml" -X GET http://localhost:8080/userservice/users/1

curl -X DELETE http://localhost:8080/userservice/users/2

curl -H "Accept:application/xml" -H "Content-Type:application/xml" -X POST http://localhost:8080/userservice/users/ -d "<user><id>2</id><name>John Doe</name></user>"
curl -X DELETE http://localhost:8080/userservice/users/2
curl -H "Accept:application/json" -H "Content-Type:application/json" -X POST http://localhost:8080/userservice/users/ -d '{"id":2,"name":"John Doe"}'

curl -H "Content-Type:application/xml" -X PUT http://localhost:8080/userservice/users/ -d "<user><id>2</id><name>NEW John Doe</name></user>"
curl -X DELETE http://localhost:8080/userservice/users/2
curl -H "Content-Type:application/json" -X PUT http://localhost:8080/userservice/users/ -d '{"id":2,"name":"NEW John Doe"}'
