# TeamView-J

This software built purely in Java that mimics the popular TeamViewer software. The software has two components: A server and a client. The idea is that the computer with server runs the server-side code and the client computer is able to get the IP address and display the screen of that computer in a multithreaded architecture, making use of socket programming to use it remotely.

### Prerequisites

### Steps

* Dowload zip or git clone the repository on your computer.

* The jar file called TeamViewer.jar is the CLIENT side of the program. *This is the computer that will fetch the screen of another user's computer and be able to work on it through the application.* The user who needs to share his/her screen for another person's use will have to run the MainServer.java file. This can be done in the terminal or in some IDE:

Compile the code after moving to the working directory:

```
javac MainServer.java
```

Run the program (Start the server):

```
java MainServer
```

* Now open the jar file and click on 'Detect Helper System'. The person's IP address should come in the table below. *You can find your computer's IP address [here](http://www.tp-link.in/faq-838.html)*

* Click on that IP address and you will be able to get the screen and use it smoothly!

### Built With

** Java **
** Swing Framework **
** NetBeans IDE 8.1 **
