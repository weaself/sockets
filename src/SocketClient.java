
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    Socket mySocket;
    PrintWriter out;
    BufferedReader in;
    String ipAdd = "localhost";
    String strToWrite = null;
    int port = 8999;
    Scanner scn = new Scanner(System.in);
    boolean done = false;

    public SocketClient() {

        try {
            mySocket = new Socket(ipAdd, port);
            out = new PrintWriter(mySocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            System.out.println(in.readLine());
            String myName = scn.nextLine();
            out.println(myName);

            listenAndWriteInput();


        } catch (IOException io) {
            System.err.println(io);
        }
    }

    public static void main(String[] args) throws IOException {
        new SocketClient();
    }

    public void listenAndWriteInput() {
        do {
            try {
                System.out.print("Your message: ");
                strToWrite = scn.nextLine();
                out.println(strToWrite);
                System.out.println(in.readLine());

                if (strToWrite.equals("bye")) done = true;
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
            // System.out.println(done);
        } while (!done);
        try {
            out.close();
            mySocket.close();
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }
    }
}