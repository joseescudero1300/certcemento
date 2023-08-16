package bo.gob.aduana.system;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Seguridad {

    // CONFIGURACION LLAVE PRIVADA AN Y PUBLICA RUAT
    static String key_private = "";

    static String key_public = "";
    PrivateKey privateKey = null;
    PublicKey publicKey = null;

    public Seguridad() throws Exception {
        System.out.println("adeudo");
    }

    public Seguridad(String v_properties) throws Exception {

        loadConfig(v_properties);

        File f = new File(key_private);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        privateKey = kf.generatePrivate(spec);

        f = new File(key_public);
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec1 = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf1 = KeyFactory.getInstance("RSA");
        publicKey = kf1.generatePublic(spec1);

    }

    public String decrypt(String msg) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, this.publicKey);

        byte[] raw = Base64.decode(msg);
        byte[] stringBytes = cipher.doFinal(raw);

        return new String(stringBytes, "UTF8");
    }


    public String encrypt(String msg) throws Exception {
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(1, this.privateKey);
        byte[] doFinal = rsa.doFinal(msg.getBytes("UTF8"));
        String m = new String(Base64.encode(doFinal), "UTF-8");

        return m;
    }


    private static void loadConfig(String v_properties) throws Exception {
        Properties config = new Properties();
        config.load(new FileInputStream(v_properties));
        key_private = config.getProperty("keystore-private");

        key_public = config.getProperty("keystore-public");
    }
}
