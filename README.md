# TeamView-J

The desktop application mimics TeamViewer's core idea primarily. But it does so completely in Java. Built using socket programming and a multithread architecture, this repo hold both client side of the app and the server side. 

### Prerequisites

Needs JRE (or JDK) installed

### Steps

Use command line and type the following

```
git clone https://github.com/parthkhanna150/TeamView-J.git
```
Or simply download zip. 


The person with the parent laptop i.e. the server-side (who wants to share his/her screen) first needs to type the following command in the terminal:

```
javac MainServer.java

java MainServer
```

The repo consists of one JAR file which is to be used in client-side. Type this to run it: 
```
java -jar ClientSide.jar
```
Alternatively, in the dist folder, you will find a JAR file which you can double-click and open.

Thereafter you can send messages across computers from the client and use the parent laptop’s screen. Enjoy.

### Used

* Java
* Swing
