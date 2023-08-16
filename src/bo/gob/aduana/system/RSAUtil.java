package bo.gob.aduana.system;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import org.apache.struts.action.ActionMessage;

import org.bouncycastle.util.encoders.Base64;

public class RSAUtil {

    private static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJZx0BwFtx6+kYSpa9AQlcqs5+AvXL0wEkDAmSmwTFBfw1Nxa8y88e2sH3Mjj0tMAV4naqvQe2np6zvcY5mJeR0CAwEAAQ==";
    private static String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAlnHQHAW3Hr6RhKlr0BCVyqzn4C9cvTASQMCZKbBMUF/DU3FrzLzx7awfcyOPS0wBXidqq9B7aenrO9xjmYl5HQIDAQABAkEAhwOHD871QyslLxhYwDZSJ7y6r8/xe3urtd7i1a35HtsiAGMTtlw3HZsSmhzSYpHnNeMKEXKGCmrCV6fYdf8CAQIhAMgwKJwovvytx2JE1Uon1wbOFFr+MLF9Wz2pJCHvhiadAiEAwGNa1J9ZG9Ygy7IzoTrSFAE8yApeEhPvu+nJ+b3u1IECIQCbGKyt5+eobp7WJMw3CszOieU9ZkiliqChEiVbYW7HBQIgBG4FOGIW5iovBSGsSKkXDzjcEmsSsW3eFkCS0vpJloECIHc2RcQfiMfpmCmWMv0gEnHJP0lz3KIMj0PQ/w3Ryn1P";

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        String ruta = "/u03/oracle/user_projects/data/certificacion/app.properties";
        try {
           /* String encryptedString = new String(Base64.encode(encrypt("Dhiraj is the author", publicKey)), "UTF-8");//Base64..encodeToString(encrypt("Dhiraj is the author", publicKey));
            System.out.println(encryptedString);
            encryptedString="pngVhI3AGYyeVfXbQdzJoiHfTSAqPac6SfQ7pp1RUwh8H8y1zCMQ7ctndBiNrZH18xW230SqbLXloUi+vd5Qiw==";
            String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
            System.out.println(decryptedString);*/
           Seguridad seg=null;
           try {
               seg= new Seguridad(ruta);
           } catch (Exception e) {
            
           }
              String      textoEncryptado =seg.encrypt("AN-CERT-20-2020");
          //  System.out.println("http://anbsw01.aduana.gob.bo:7601/adeudo/VerificacionCert.do?id="+textoEncryptado);
          //System.out.println("http://deslogic01.aduana.gob.bo:7011/adeudo/VerificacionCert.do?id="+textoEncryptado);
            System.out.println("http://10.0.2.78:7101/cert_cemento/VerificacionCert.do?id="+textoEncryptado);
                                 //  System.out.print(seg.decrypt(textoEncryptado));
            //http://10.0.2.78:7101/cert_cemento/
          //ruta        http://anbsw01.aduana.gob.bo:7601/adeudo/VerificacionCert.do?id=            
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
        }
        catch (Exception e) {
                }

    }
}