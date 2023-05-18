import java.security.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Alice encrypts the message
        Alice alice = new Alice(publicKey);
        String message = "Hello, this is a secret message!";
        byte[] encryptedMessage = alice.encrypt(message);
        byte[] encryptedAesKey = alice.encryptKey();
        byte[] initVector = alice.getInitVector();

        // Bob decrypts the message
        Bob bob = new Bob(privateKey);
        SecretKey decryptedAesKey = bob.decryptKey(encryptedAesKey);
        String decryptedMessage = bob.decrypt(encryptedMessage, decryptedAesKey, initVector);

        System.out.println("Original message: " + message);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
