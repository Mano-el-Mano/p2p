package assignment1;

import java.io.*;
import java.net.Socket;

public class WhoIsClient {
    private static String whoIsServer;
    private static String domain;
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("please provide two arguments to the program!\n" +
                    "1. the whois server example: 'whois.networksolutions.com'\n" +
                    "2. the domain to search for within the whois registrar example: 'mindtherags.dk'");
            return;
        }

        whoIsServer = args[0];
        domain = args[1];

        int port = 43;
        try (Socket socket = new Socket(whoIsServer, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(domain);
            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}