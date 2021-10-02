package chat;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8668);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "";

            int b = 2 + (int) (Math.random()*3);
            int x = Integer.parseInt(inStream.readUTF());
            int x_a = Integer.parseInt(inStream.readUTF());
            outStream.writeUTF(Integer.toString(((int) (Math.pow(x, b))%2)));
            int key = ((int) Math.pow(x_a, b))%2;

            Thread thread = new Thread(new ReadingServer(inStream, key));
            thread.start();
            System.out.println(">> Connected!");
            while(!clientMessage.equals("bye")) {
                clientMessage = br.readLine();
                outStream.writeInt(Helper.encrypt(clientMessage, key).length);
                outStream.flush();
                outStream.write(Helper.encrypt(clientMessage, key));
                outStream.flush();
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
