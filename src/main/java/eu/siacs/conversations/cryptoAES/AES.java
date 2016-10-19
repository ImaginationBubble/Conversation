package eu.siacs.conversations.cryptoAES;


import android.util.Base64;
import android.util.Log;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by dmitriyzaretskiy on 11.10.16.
 */

public class AES {

    public static SecretKeySpec keySpec;
    // public static final Type BYTE_TYPE = new TypeToken<byte[]>() {
    //}.getType();
    private static final int KEY_SIZE = 128;
    public static byte[] keyInBytes;
   // public static Gson gsonParser = new Gson();

    public static void generateKey(char[] password, byte[] salt) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password, salt, 65536, KEY_SIZE);
            SecretKey tempKey = keyFactory.generateSecret(spec);
            keySpec = new SecretKeySpec(tempKey.getEncoded(), "AES");
            keyInBytes = keySpec.getEncoded();
            Log.d("TAG", Arrays.toString(keySpec.getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptAES(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    public static String decryptAES(String message) throws Exception {
        byte[] messageInBytes = Base64.decode(message,Base64.NO_WRAP);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        String reslt_message = new String(cipher.doFinal(messageInBytes));
        return reslt_message;
    }

    public static SecretKeySpec getKey(){
        return keySpec;
    }

    public static void setKey(SecretKeySpec _keySpec){
        keySpec = _keySpec;
    }

//
//    public static void setKey(String key){
//        keyInBytes = gsonParser.fromJson(key, BYTE_TYPE);
//        keySpec = new SecretKeySpec( keyInBytes, "AES");
//    }
}
