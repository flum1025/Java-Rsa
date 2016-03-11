package rsasample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class RSASample {

	public static void main(String[] args) throws Exception {
		File keyFile = new File("/Users/flum/desktop/public_key.der");  //javaではpemファイルは扱えないため、derに変換して指定する。      
		byte[] encodedKey = new byte[(int)keyFile.length()];

		FileInputStream in = new FileInputStream(keyFile);
		in.read(encodedKey);
		in.close();
		  
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);

		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pubKey = kf.generatePublic(publicKeySpec);

		Cipher rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.ENCRYPT_MODE, pubKey);  
		String plainTest = "testplaintext";
		byte[] plainText = plainTest.getBytes();
		byte[] cipherText = rsa.doFinal(plainText);
		System.out.println(Base64.getEncoder().encodeToString(cipherText));
	}
}
