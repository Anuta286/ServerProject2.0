package chat;
import java.io.DataInputStream;
import java.io.IOException;

public class ReadingServer implements Runnable {
    DataInputStream inputStream;
    int key;
    ReadingServer(DataInputStream inputStream, int key) {
        this.inputStream = inputStream;
        this.key = key;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int length = inputStream.readInt();
                if(length>0) {
                    byte[] message = new byte[length];
                    inputStream.readFully(message, 0, message.length);
                    System.out.println(Helper.decrypt(message, key));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
