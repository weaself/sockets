package socketprogram;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket mySocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String ipAdd = "172.16.0.23";
        String strToWrite = null;
        int port = 8999;
        Scanner scn = new Scanner(System.in);
        boolean done = false;

        try {
            mySocket = new Socket(ipAdd, port);
            out = new PrintWriter(mySocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            System.out.println(in.readLine());
            String myName = scn.nextLine();
            out.println(myName);

            do {
                strToWrite = scn.nextLine();
                out.println(strToWrite);
                System.out.println(in.readLine());
                if (strToWrite.equals("bye")) done = true;
               // System.out.println(done);
            } while (!done);

        } catch (IOException io) {
            System.err.println(io);
        }

        mySocket.close();
    }

}
