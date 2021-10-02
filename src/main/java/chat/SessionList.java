package chat;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SessionList {
    public final List<ChatSession> allSessions = new CopyOnWriteArrayList<>();
    public final List<Integer> allKeys = new CopyOnWriteArrayList<>();

    public void giveKey(int key) {
        allKeys.add(key);
    }

    public void add(ChatSession s) {
        allSessions.add(s);
    }

    public void send(ChatSession sender, String message) throws IOException {
        for (ChatSession s: allSessions) {
            if (!sender.equals(s)) {
                s.outStream.writeInt(Helper.encrypt(message, allKeys.get(allSessions.indexOf(s))).length);
                s.outStream.flush();
                s.outStream.write(Helper.encrypt(message, allKeys.get(allSessions.indexOf(s))));
                s.outStream.flush();
            }
        }
    }
}
