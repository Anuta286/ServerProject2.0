package firsttask;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket server=new ServerSocket(8668);
            int counter=0;
            System.out.println(" >> Server Started!");
            while(true){
                counter++;
                Socket serverClient=server.accept();
                System.out.println(" >> " + "Client" + counter + " started!");
                Thread sct = new Thread(new MyRunnable(serverClient,counter));
                sct.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
