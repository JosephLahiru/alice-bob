import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Bob {
    private PrivateKey privateKey;
    private Cipher aesCipher;
    private Cipher rsaCipher;

    public Bob(PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.privateKey = privateKey;
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    public SecretKey decryptKey(byte[] encryptedAesKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedAesKeyBytes = rsaCipher.doFinal(encryptedAesKey);
        SecretKey decryptedAesKey = new SecretKeySpec(decryptedAesKeyBytes, "AES");
        return decryptedAesKey;
    }

    public String decrypt(byte[] encryptedMessage, SecretKey aesKey, byte[] initVector) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(initVector));
        byte[] decryptedMessageBytes = aesCipher.doFinal(encryptedMessage);
        String decryptedMessage = new String(decryptedMessageBytes);
        return decryptedMessage;
    }
}
