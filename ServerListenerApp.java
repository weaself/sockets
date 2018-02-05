package socketprogram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerApp {
    ServerSocket serSocket;
    int portToListenOn = 8999;
    boolean keepRunning = true;

    public ServerListenerApp() {
        try {
            ServerSocket serSocket = new ServerSocket(portToListenOn);
            while (keepRunning) {
                Socket fromClientSocket = serSocket.accept();
                Thread firstServer = new Thread(new ServerListener(fromClientSocket));
                firstServer.start();
            }
            }catch(IOException ioex){
                System.out.println("ioex");
        }

    }

    public static void main(String[] args) {
        new ServerListenerApp();
    }
}
