# alice-bob

#### This project demonstrates a simple implementation of encryption and decryption using AES in CBC mode and RSA encryption/decryption for the key. It consists of three classes in Java: Alice, Bob, and Main.

 - **Alice class:** This class handles the encryption of a message using AES in CBC mode and RSA encryption for the key. It generates an AES key and encrypts the message, then encrypts the AES key using the RSA public key.
 - **Bob class:** This class handles the decryption of the encrypted message and the encrypted key. It decrypts the AES key using the RSA private key and then decrypts the message using the decrypted AES key and the CBC mode.
 - **Main class:** This class tests the encryption and decryption process. It generates an RSA key pair, creates instances of Alice and Bob using the public and private keys, respectively, and demonstrates the encryption and decryption process.