package assignment2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static final int PORT = 6666;
    public static ServerSocket server = null;

    public static ServerSocket configureServer() throws  IOException {
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server ip: " + serverIP);
        return new ServerSocket(PORT);
    }

    public static void main(String[] args) throws IOException
    {
        server = configureServer();
        while(true) {
                int counter = 0;
                while (true) {
                    counter++;
                    Socket openSocket = server.accept();  //hangs and waits for request to come. Hence it gives client numbers in correct order
                    System.out.println(" >> " + "Client No:" + counter + " started!");
                    ServerClientThread sct = new ServerClientThread(openSocket, counter); //creates server client worker
                    sct.start();
                }
        }

    }
}