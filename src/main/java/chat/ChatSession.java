package chat;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatSession implements Runnable {
    private final SessionList sessionList;
    private final Socket serverClient;
    private final int clientNo;
    public DataOutputStream outStream;

    int x = 1 + (int) (Math.random()*8);
    int a = 2 + (int) (Math.random()*3);

    ChatSession(SessionList list, Socket inSocket,int counter) {
        sessionList = list;
        serverClient = inSocket;
        clientNo = counter;
    }

    @Override
    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "";

            outStream.writeUTF(Integer.toString(x));
            outStream.writeUTF(Integer.toString(((int) (Math.pow(x, a))%2)));
            int x_b = Integer.parseInt(inStream.readUTF());
            int key =  ((int) Math.pow(x_b, a))%2;
            sessionList.giveKey(key);

            while(!clientMessage.equals("bye")) {
                int length = inStream.readInt();                                                     //
                if(length>0) {                                                                       //
                    byte[] message = new byte[length];                                               //
                    inStream.readFully(message, 0, message.length);                              //
                    clientMessage = Helper.decrypt(message, key);                                    //
                }                                                                                    //
                System.out.println("From Client " + clientNo + ": " + clientMessage + "\n");
                sessionList.send(this, "From Client " + clientNo + ": " + clientMessage);
            }
            inStream.close();
            serverClient.close();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Client " + clientNo + " exit!!");
        }

    }
}
