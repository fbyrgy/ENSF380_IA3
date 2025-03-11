# Use JDK 11
FROM openjdk:11-jdk-slim

WORKDIR /app

COPY edu /app/edu

COPY lib /app/lib

COPY GenderOptions.txt /app/

RUN javac -cp ".:/app/lib/*" edu/ucalgary/oop/*.java

CMD ["sh", "-c", "java -cp '.:/app/lib/*' edu.ucalgary.oop.UserInterface central"]
