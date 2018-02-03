import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerListener {
    public static void main(String[] args)  {
        int portToListenOn = 8999;
        String str;

        try {
            Socket clientSocket = createServerSocket(portToListenOn);
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
            clientSocket.close();
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public static Socket createServerSocket(int portToListenOn) {
        try {
            ServerSocket serSocket = new ServerSocket(portToListenOn);
            System.out.println("Waiting for a connection on: " + portToListenOn);
            Socket fromClientSocket = serSocket.accept();
            System.out.println(fromClientSocket.toString());
            return fromClientSocket;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
