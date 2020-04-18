package Entidades;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author Grupo 3
 */
public class Usuario {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    private String user;
    private String password;    

    public Usuario() {
        this.password = null;
        this.user = null;
    }


    public Usuario(String user, String password) {
        this.user = user;
        this.password = encrypt(password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encrytedPassword) {
        this.password = encrytedPassword;
    }
    
    public boolean changePassword(String oldP) {
        boolean actualizado = false;
        if(this.password.equals(encrypt(oldP))){
            actualizado = true;
        }
        return actualizado;
    }
    
/**
 * Método para la encriptación de la contraseña
 * @param value
 * @return 
 */
    private static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            System.out.println("Debe insertar la contraseña vieja " + ex);
        }
        return null;
    }

/**    
    public static String decrypt(String encrypted) {
    try {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
 
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
 
        return new String(original);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
 
    return null;
}
*/
}
