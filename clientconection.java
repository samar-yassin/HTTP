import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class clientconnection extends Thread {
    private Socket clientSocket;

    public clientconnection(Socket clientsocket) {
        this.clientSocket = clientsocket;
    }

    public void run() {
        try {
            String usernames[]={"samar","alaa","marim","mona","hend"};
            String passes[]={"samar123","alaa123","marim123","mona123","hend123"};
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            output.writeUTF("connected.");
            //reading file from user input
            Scanner user = new Scanner(System.in);
            String inputFileName;

            String username = new String(input.readUTF());
            String pass = new String(input.readUTF());
            String GetPost = new String(input.readUTF());
            String host = new String(input.readUTF());
            String path = new String(input.readUTF());
            String version = new String(input.readUTF());
            boolean available =false;
            for(int x=0 ; x <usernames.length; x++){
                if(usernames[x].equalsIgnoreCase(username)&&passes[x].equalsIgnoreCase(pass)){
                    available = true;
                    break;
                }
            }
            if(available == true) {
                output.writeUTF("accepted credentials");
                if (host.equalsIgnoreCase("server")) {
                    String found;
                    boolean exists = (new File(path)).exists();
                    if (exists) {
                        found =new String(" 200 OK");
                        output.writeUTF(found);
                        output.writeUTF(version);
                        if(GetPost.equalsIgnoreCase("get")){
                            output.writeUTF(username);
                            output.writeUTF(pass);
                        }
                        File file = new File(path);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String st;
                        while ((st = br.readLine()) != null)
                            output.writeUTF(st);
                        output.writeUTF("finished");

                    } else {
                        found =new String("400 error (wrong path)");
                        output.writeUTF(found);
                    }

                } else {
                    String url = host + path;
                    Runtime rt = Runtime.getRuntime();
                    System.out.println(url);
                    rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
                }
            }
            else output.writeUTF("Sorry , username or password is incorrect");


        } catch (IOException e) {
            System.out.println("Problem in IO of server socket");
        }
    }
}
