package chat;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        SessionList list = new SessionList();
        try{
            ServerSocket server = new ServerSocket(8668);
            int counter=0;
            System.out.println(" >> Server Started!");
            while (true) {
                counter++;
                Socket serverClient = server.accept();
                System.out.println(" >> " + "Client" + counter + " started!");
                ChatSession session = new ChatSession(list, serverClient, counter);
                new Thread(session).start();
                list.add(session);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
