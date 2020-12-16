# Mini Project 1: Web Services and API

## Assignment#1

Assignment 1 we had to provide a program with some arguemnts that should send a request to user defined whois servers in order to get available/unavailable domains within their registry. We implemented this over CLI arguments. We have provided some output in the CLI for example input in order to get the expected output.
![](./images/whois.png)

## Assignment#2

Assignment 2 were a bit more challenging. We had to configure a TCP server to run multiple connections with multiple threads. In order to run the program (atleast with intellij) one would have to configure the running options of the service in order to be able to run multiple instances of it. Luckily this is fairly easy to work around. We apologize for the image ordering, it was more difficult than expected to control in markdown. Also we acknowledge that the cut of client number might seem suspicious. 

![](./images/config.png)
![](./images/server_started)
![](./images/ping1.png)
![](./images/pong1.png)
![](./images/ping2.png)
![](./images/pong2.png)
## Assignment#3

In this assignment we had to configure a UDP client and a UDP server in order to be able to send images back and forth. In order to prove that the program successfully does this, we save the images on the client when it receives the images in src/assignemnt3/output folder. It chooses an image of the bulgarian flag by default. We had issues in regards to providing arguments in order to stop the socket from the client, which was originally included in the project since we send the image as byte arrays. This would probably have been solvable through UDP headers, but we agreed that it would be beyond the scope of the assignment. 

![](./images/udp_ready.png)
![](./images/saved_image.png)

Overall this assignment has been really fun, and we definitely had quite a lot of "aha" moments. Especially in regards to the UDP protocol.
