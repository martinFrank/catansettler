FROM maven:3.6.1-jdk-8

RUN apt-get update apt-get install --no-install-recommends -y openjfx

RUN apt-get clean

RUN rm -f /var/lib/apt/lists

  RUN cp /usr/share/java/openjfx/jre/lib/ext/* /usr/lib/jvm/java-1.8.0-openjdk-amd64/lib

  RUN cp /usr/share/java/openjfx/lib/* /usr/lib/jvm/java-1.8.0-openjdk-amd64/lib

  RUN chmod 777 -R /usr/lib/jvm/java-1.8.0-openjdk-amd64