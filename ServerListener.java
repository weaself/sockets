package socketprogram;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable {

        String str;
        Socket fromClientSocket;
        PrintWriter pw;
        BufferedReader br;

        public ServerListener(Socket fromClientSocket) {
            this.fromClientSocket = fromClientSocket;
        }

        public void run() {
            try {
                pw = new PrintWriter(fromClientSocket.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
                pw.println("What's your name?");
                String hisName = br.readLine();

                //	System.out.println(br.readLine());
                boolean done = false;
                while (!done) {
                    str = br.readLine();
                    System.out.println(hisName + " says: " + str);
                    pw.println(hisName + " says: " + str);
                    if (str.equals(".bye")) done = false;
                }
                pw.close();
                br.close();
                fromClientSocket.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }

}
