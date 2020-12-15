package assignment2;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ServerClientThread extends Thread {
    Socket openSocket;
    int clientNo;

    ServerClientThread(Socket openSocket, int counter){
        this.openSocket = openSocket;
        clientNo = counter;
    }

    public void run()  {
        try {
                String request, response;
                Scanner in;
                PrintWriter out;

                in = new Scanner(openSocket.getInputStream());
                out = new PrintWriter(openSocket.getOutputStream(),true);

                while(in.hasNextLine()) {
                    request = in.nextLine();
                    if(request.equals("stop")) {
                        out.println("Good bye, client " + clientNo);
                        break;
                    }
                    else {
                        response = new StringBuilder(request).toString();
                        out.println(response);
                        System.out.println("Request: " + request + " Response: "+ response + " clientNo: " + clientNo);
                    }
                }
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}