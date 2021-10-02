package chat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Helper {
    public static byte[] encrypt(String message, int key) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] res = new byte[bytes.length];
        for (int i=0; i< bytes.length; i++) {
            res[i] = (byte) (bytes[i]^key);
        }
        return res;
    }
    public static String decrypt(byte[] bytes, int key) throws UnsupportedEncodingException {
        byte[] result = new byte[bytes.length];
        for (int i=0; i< bytes.length; i++) {
            result[i] = (byte) (bytes[i] ^ key);
        }
        return new String(result, "UTF-8");

    }
}
