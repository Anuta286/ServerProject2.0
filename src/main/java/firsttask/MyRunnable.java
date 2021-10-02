package firsttask;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class MyRunnable implements Runnable {
    private final Socket serverClient;
    private final int clientNo;
    MyRunnable(Socket inSocket,int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }
    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "";
            String clientName;
            while(!clientMessage.equals("bye")) {
                clientMessage = inStream.readUTF();
                System.out.println("From Client " + clientNo + ": " + clientMessage);
                for(int i=0; i<clientMessage.split(" ").length; i++) {
                    if (clientMessage.split(" ")[i].equals("I'm")) {
                        clientName = clientMessage.split(" ")[i+1];
                        outStream.writeUTF("From Server: " +  "Hello, " + clientName);
                        break;
                    }
                }
                outStream.writeUTF("From Server: Ok, how are you?");
                outStream.flush();
            }
            inStream.close();
            outStream.close();
            serverClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Client " + clientNo + " exit!!");
        }
    }
}