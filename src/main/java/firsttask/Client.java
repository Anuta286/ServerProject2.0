package firsttask;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("jtalks.org",8668);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "", serverMessage;
            System.out.println(" >> Connected!");
            while(!clientMessage.equals("bye")) {
                clientMessage = br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);
            }
            System.out.println("From Server: Bye!");
            outStream.close();
            outStream.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
