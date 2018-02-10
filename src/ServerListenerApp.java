
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListenerApp {
    ServerSocket serSocket;
    int portToListenOn = 8999;
    boolean keepRunning = true;
    ArrayList<ServerListener> connectionsList;
    ArrayList<String> messageCollector = new ArrayList<String>();

    public ServerListenerApp() {

        connectionsList = new ArrayList<ServerListener>();
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
                ServerListener connectionFromClient = new ServerListener(fromClientSocket);
                connectionsList.add(connectionFromClient);
        //        System.out.println("New thread with the client " + ServerListener.get);
                connectionFromClient.start();

                for(ServerListener serverListener : connectionsList) {
                    ArrayList<String> messages = serverListener.getMessageList();
                    for(String message : messages) {
                        serverListener.sendToMe(message);
                    }
                }
            }
            }catch(IOException ioex){
                ioex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ServerListenerApp();
    }
}
