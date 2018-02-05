package socketprogram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerApp {
    ServerSocket serSocket;
    Socket fromClientSocket;
    int portToListenOn = 8999;
    boolean keepRunning = true;

    public ServerListenerApp() {
        try {
            ServerSocket serSocket = new ServerSocket(portToListenOn);
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }

        while(keepRunning) {
            try {
                fromClientSocket = serSocket.accept();

            }catch (IOException ioex) {
                System.out.println(fromClientSocket.toString());}

            Thread firstServer = new Thread(new ServerListener(fromClientSocket));
            firstServer.start();
        }

    }

    public static void main(String[] args) {
        new ServerListenerApp();
    }
}
