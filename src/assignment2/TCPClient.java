package assignment2;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {

    public static int PORT = 6666;
    public static String request, response;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName(null), PORT);

        System.out.println("Opened socket on: " + socket);

            try (Scanner cin = new Scanner(System.in);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 Scanner in = new Scanner(socket.getInputStream());) {
                System.out.println("Send a message or \"stop\" to close connection");

                while ((request = cin.nextLine()) != null) {
                    out.println(request);
                    response = in.nextLine();
                    System.out.println("response " + response);
                }

            } catch (UnknownHostException e) {
                System.err.println("No host" + InetAddress.getByName(null));
                System.exit(1);
            } catch (IOException e) {
                System.err.println("No I/O connection to " + InetAddress.getByName(null));
                System.exit(1);
            } finally {
                socket.close();
                System.out.println("Client closing...");
            }
        }
    }