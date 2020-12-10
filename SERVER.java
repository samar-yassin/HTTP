import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SERVER {

    public static void main(String[] args) {

        try {
            ServerSocket serversocket=new ServerSocket(3000);
            System.out.println("Server is now booted up and waiting for client connect");

            while(true) {
                Socket clientsocket =serversocket.accept();
                System.out.println("A new client ["+clientsocket+"]is contected to the server ");
                Thread client=new clientconnection(clientsocket);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Problem in IO of server socket");		}

    }

}



