
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
        String clientsName = "";

        public ServerListener(Socket fromClientSocket) {
            this.fromClientSocket = fromClientSocket;
        }

        public String getClientsName() {
            return this.clientsName;
        }

        public void run() {
            try {
                pw = new PrintWriter(fromClientSocket.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
                pw.println("What's your name?");
                clientsName = br.readLine();
                System.out.println(clientsName + " connected.");

                //	System.out.println(br.readLine());
                boolean done = false;
                while (!done) {
                    while ((str = br.readLine()) != null) {
                        pw.println(clientsName + " says: " + str);
                        System.out.println(clientsName + " says: " + str);
                        if (str.equals(".bye")) done = false;
                    }
                }
                pw.close();
                br.close();
                System.out.println("Closing current thread.");
                fromClientSocket.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }

}
