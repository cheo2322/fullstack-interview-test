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
4. Execute the gradle wrapper included into the repository to create the ```.jar```
which execute the back-end(it is not necessary to install Gradle to execute it)
``` 
> ./gradlew clean bootJar
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

## Front-end

Front-end part was developed using React. You should follow next steps to
start the front app:

1. Download and install nodejs LTS version > https://nodejs.org/
2. Open a new a terminal and go to the base path of the frontend project
```
> cd .../fullstack-interview-test/front-end
```
3. Use npm to install dependencies
```
> npm install
```
4. To start the project use npm too
```
> npm start
```
5. It automatically should appear the front-end running in default browser

![front](/assets/images/asset2.png)

6. For branches, commits and Git info go to the `Repository` tab in Navigation bar.

6.1. Use any existent username, repository and GitHub token.

![repo](/assets/images/asset3.png)

6.2. Username and repo are very easy to obtain, just go to your repo
and copy/paste it.

![username](/assets/images/asset4.png)

6.3. In order to obtain a GitHub token, just open your GitHub account
and go to Settings > Developer Settings > Personal access token >
Generate new token. In the `Note` section just give a nice name to your token
and then grant ALL permissions to your token (whether you don't do that, some
actions could fail) just clicking the blank boxes at the left.

![token](/assets/images/asset5png.PNG)

7. The rest is yours!