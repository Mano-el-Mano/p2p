package assignment3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class UDPClient
{
    // Client needs to know server identification, <IP:port>
    private static final int serverPort = 7777;

    // buffers for the messages
    public static String message;
    private static byte[] dataIn = new byte[256];
    private static byte[] dataOut = new byte[256];

    // In UDP messages are encapsulated in packages and sent over sockets
    private static DatagramPacket requestPacket;
    private static DatagramPacket responsePacket;
    private static DatagramSocket clientSocket;

    public static void main(String[] args) throws IOException
    {
        clientSocket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName("192.168.0.47");
        System.out.println(serverIP);

        Scanner scan = new Scanner(System.in);
        System.out.println("Type message: ");

        while((message = scan.nextLine()) != null)
        {
            sendRequest(serverIP);
            receiveResponse();
        }
        clientSocket.close();
    }

    public static void byteArrayToImage(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("src/assignment3/output/output_" + Math.round(Math.random() * 1000000) + ".jpg") );
        System.out.println("image created");
    }

    public static void sendRequest(InetAddress serverIP) throws IOException
    {
        BufferedImage img = ImageIO.read(new File("src/assignment3/bulgaria.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        baos.flush();
        byte[] buffer = baos.toByteArray();
        byteArrayToImage(buffer);
    }

    public static void receiveResponse() throws IOException
    {
        responsePacket = new DatagramPacket(dataIn, dataIn.length);
        clientSocket.receive(responsePacket);
        String message = new String(responsePacket.getData(), 0, responsePacket.getLength());
        System.out.println("Response from Server: " + message.getBytes());
    }
}

