import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CLIENT {

    public static void main(String[] args) {

        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket clientsocket=new Socket(ip,3000);
            DataInputStream input=new DataInputStream(clientsocket.getInputStream());
            DataOutputStream output=new DataOutputStream(clientsocket.getOutputStream());

            String isconnected=input.readUTF();

            String username = new String(args[0]);
            output.writeUTF(username);
            String pass = new String(args[1]);
            output.writeUTF(pass);
            String GetPost = new String(args[2]);
            output.writeUTF(GetPost);
            String host = new String(args[3]);
            output.writeUTF(host);
            String path = new String(args[4]);
            output.writeUTF(path);
            String version = new String(args[5]);
            output.writeUTF(version);
            String login = new String(input.readUTF());
            System.out.println(login);
            if(args[3].equalsIgnoreCase("server")) {
                System.out.println();
                String found = new String(input.readUTF());
                if (found.equalsIgnoreCase(" 200 OK")) {
                    System.out.println("HTTP Version : " + input.readUTF() + found);
                    System.out.println();
                    if(args[2].equalsIgnoreCase("get")){
                        System.out.println("Username : "+input.readUTF());
                        System.out.println("Password : "+input.readUTF());
                        System.out.println();
                    }
                    while (true) {
                        String line = input.readUTF();
                        if (line.equalsIgnoreCase("finished")) break;
                        System.out.println(line);

                    }
                    System.out.println();
                    java.util.Date date = new java.util.Date();
                    System.out.println("Date : " + date);
                }
                else System.out.println(found);
            }


        }
        catch(UnknownHostException e) {}

        catch (IOException e) {

            e.printStackTrace();
        }


    }

}



