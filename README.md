# post-reader
Reads posts from jsonplaceholder.typicode.com and displays them on a web-page.

Requirements
1. The system should have Maven 3.6, or later,  installed.
2. The system should have Java JDK 11 or later installed.
3. The JAVA_HOME environment variable should be set and javac should be on the classpath.

To execute, get the source code from https://github.com/vj3000/post-reader.git

Steps
1. git clone https://github.com/vj3000/post-reader.git
2. cd ../post-reader   //Change to the directory where the pom.xml file is located.
3. mvn clean install   //Compile and install application

To run the application
mvn spring-boot:run

To view the posts open a browser and goto http://localhost:8080/

