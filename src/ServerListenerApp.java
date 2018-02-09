
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListenerApp {
    ServerSocket serSocket;
    int portToListenOn = 8999;
    boolean keepRunning = true;
    ArrayList<Thread> connectionsList;

    public ServerListenerApp() {

        connectionsList = new ArrayList<Thread>();
        try {
            ServerSocket serSocket = new ServerSocket(portToListenOn);
            String addressOfServerSocket = serSocket.getInetAddress().toString();
            if (addressOfServerSocket.equals("0.0.0.0/0.0.0.0")) {
                addressOfServerSocket = "all local addresses.";
            }
            System.out.println("Server listening on: " + addressOfServerSocket);


            while (keepRunning) {
                Socket fromClientSocket = serSocket.accept();
                System.out.println("Address of connected client: " + fromClientSocket.getInetAddress().getHostAddress());
                Thread connectionFromClient = new Thread(new ServerListener(fromClientSocket));
                connectionsList.add(connectionFromClient);
        //        System.out.println("New thread with the client " + ServerListener.get);
                connectionFromClient.start();
            }
            }catch(IOException ioex){
                ioex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ServerListenerApp();
    }
}
