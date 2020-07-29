FROM  openjdk:11
LABEL processor="1"
COPY target/processor-0.0.1-SNAPSHOT.jar /opt/app/
CMD java -jar  /opt/app/processor-0.0.1-SNAPSHOT.jar
