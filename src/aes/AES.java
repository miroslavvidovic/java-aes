package aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 * 
 * SImple implementation of the AES alghoritm.
 * 
 * @author  miroslav
 * @version 1.0
 * @since   2017-10-04
 */
public class AES {
    /**
     * This method is used to encrypt a string with AES (advanced encryption standard) alghoritm.
     *
     * @param key        the key for the encryption
     * @param initVector the initialization vector
     * @param value      the value that will be encrypted
     * @return           the encrypted value
     */
    public static String encrypt(String key, String initVector, String value) {

        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return DatatypeConverter.printBase64Binary(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * This method is used to decrypt a string with AES (advanced encryption standard) alghoritm.
     *
     * @param key        the key for the decryption
     * @param initVector the initialization vector
     * @param value      the value that will be decrypted
     * @return           the decrypted value
     */
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}