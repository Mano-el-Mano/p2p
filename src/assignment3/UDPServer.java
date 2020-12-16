package assignment3;

import java.io.IOException;
import java.net.*;

public class UDPServer
{
    private static final int serverPort = 7777;

    private static byte[] dataIn = new byte[148];
    private static DatagramPacket requestPacket;
    private static DatagramPacket responsePacket;
    private static DatagramSocket serverSocket;


    public static void main(String[] args) {
        try {
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            serverSocket = new DatagramSocket(serverPort);
            while(true)
            {
                System.out.println("Server " + serverIP + " running ...");
                receiveRequest();
            }
        }
        catch(Exception e) {
            System.out.println(" Connection fails: " + e);
        }
    }

    public static void receiveRequest() throws Exception {
        requestPacket = new DatagramPacket(dataIn, dataIn.length);
        serverSocket.receive(requestPacket);
        byte[] data = requestPacket.getData();
        sendResponse(data);
    }

    public static void sendResponse(byte[] image) throws IOException {
        responsePacket = new DatagramPacket(image, image.length, requestPacket.getAddress(), requestPacket.getPort());
        serverSocket.send(responsePacket);
    }
}
