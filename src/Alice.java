import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class Alice {
    private SecretKey aesKey;
    private Cipher aesCipher;
    private Cipher rsaCipher;
    private byte[] initVector;
    private PublicKey publicKey;

    public Alice(PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.publicKey = publicKey;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        aesKey = keyGenerator.generateKey();
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        initVector = new byte[16];
        new SecureRandom().nextBytes(initVector);
    }

    public byte[] encrypt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(initVector));
        byte[] encryptedMessage = aesCipher.doFinal(message.getBytes());
        return encryptedMessage;
    }

    public byte[] encryptKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedAesKey = rsaCipher.doFinal(aesKey.getEncoded());
        return encryptedAesKey;
    }

    public byte[] getInitVector() {
        return initVector;
    }
}
