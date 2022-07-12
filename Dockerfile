FROM ibmjava:jre
RUN mkdir /opt/app
COPY target/MonitoreoMascotas-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/MonitoreoMascotas-0.0.1-SNAPSHOT.jar"]