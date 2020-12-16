package assignment3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class UDPClient {

    private static final int serverPort = 7777;
    public static String message;
    private static byte[] dataIn = new byte[148];
    private static DatagramPacket requestPacket;
    private static DatagramPacket responsePacket;
    private static DatagramSocket clientSocket;

    private static byte[] requestData;
    private static byte[] responseData;


    public static void main(String[] args) throws IOException {
        clientSocket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName("192.168.0.47");
        System.out.println(serverIP);

        Scanner scan = new Scanner(System.in);
        System.out.println("Press enter to send the image");

        while((message = scan.nextLine()) != null) {
            sendRequest(serverIP);
            receiveResponse();
        }
        clientSocket.close();
    }

    public static void byteArrayToImage(byte[] data) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
        if(bufferedImage == null){
            System.out.println("something went wrong with reading the image!");
        } else {
            ImageIO.write(bufferedImage, "png", new File("src/assignment3/output/output_" + Math.round(Math.random() * 1000000) + ".png"));
        }
    }

    public static void sendRequest(InetAddress serverIP) throws IOException {
        BufferedImage img = ImageIO.read(new File("src/assignment3/bulgaria.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        byte[] buffer = baos.toByteArray();
        requestData = buffer;
        requestPacket = new DatagramPacket(buffer, buffer.length, serverIP, serverPort);
        clientSocket.send(requestPacket);
    }

    public static void receiveResponse() throws IOException {
        responsePacket = new DatagramPacket(dataIn, dataIn.length);
        clientSocket.receive(responsePacket);
        responseData = responsePacket.getData();
        System.out.println(requestData == responseData); //Assuming this is where something goes wrong?
        System.out.println("requestData" + requestData.length);
        System.out.println("responseData" + responseData.length);
        byteArrayToImage(responseData);
    }
}

