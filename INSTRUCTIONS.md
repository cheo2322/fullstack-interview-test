#Instructions to run the app

## Back-end

Back-end has been developed in Spring Boot framework based on
Java (I have token this technology because at present is one I
use the most).

There way to run this part of the project goes following next steps:

1. Install JDK 11 in your machine. Java works with the Java Virtual
Machine, thus it is agnostic to Operative System in which you are working.
You could find JDK installers in Oracle official website: 
https://www.oracle.com/java/technologies/downloads/#java11-windows
. It doesn't matter if you have a superior version of Java, but having
a lower version may cause troubles.
2. Clone the repository (use SSH if you prefer)
```
> git clone https://github.com/cheo2322/fullstack-interview-test.git
```
3. Open a terminal and go to the base path of the project, it will take us
to the default branch: ```features/full-stack-interview``` (this branch was selected
as default just by logistic)
```
> cd .../fullstack-interview-test
```
4. Execute the gradle wrapper included into the repository to create the .jar
which execute the back-end(it is not necessary to install Gradle to execute it)
``` 
> ./gradlew bootJar
```
After execute it, a file named
````
full-stack-interview-0.0.1-SNAPSHOT.jar
````
will appear into ```/build/libs``` generated folder

![jar](/assets/images/asset1.PNG)

5. Finally, with terminal go into the folder containing the .jar and type
````
> java -jar full-stack-interview-0.0.1-SNAPSHOT.jar
````
To execute it is mandatory to have port ```8080``` available.