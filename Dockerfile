FROM openjdk:8
EXPOSE 8080
ADD target/job-portal-crud-kube.jar job-portal-crud-kube.jar
ENTRYPOINT ["java","-jar","/job-portal-crud-kube.jar"]