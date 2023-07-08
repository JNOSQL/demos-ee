# Apache TomEE MicroProfile


This project provides a starter kit for Apache TomEE Eclipse MicroProfile projects hosted on Platform.sh.  It includes a minimalist application skeleton that is intended for you to use as a starting point and modify for your own needs, along with the Platform.sh Config Reader to simplify accessing Platform.sh environment variables.

Apache TomEE is the Eclipse MicroProfile  implementation that uses several Apache Project flavors such as Apache Tomcat, Apache OpenWebBeans and so on.

## how to execute
To compile:
```shell
mvn clean package tomee:exec
```

To start: 

```shell
java -jar target/ROOT-exec.jar
```

## Using Docker


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. Run MongoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this command
    * `docker run -d --name mongodb-instance -p 27017:27017 mongo`

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)

## Execute

```shell 
curl --location --request POST 'http://localhost:8080/animals' \
--header 'Content-Type: application/json' \
--header 'Content-Type: application/javascript' \
--data-raw '{"name": "tomee"}'
```

```shell
curl --location --request GET 'http://localhost:8080/animals'
```

## References

* [Maven](https://maven.apache.org/)
* [Apache TomEE](https://tomee.apache.org/)
* [Eclipse MicroProfile](https://microprofile.io/)
