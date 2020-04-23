package com.util.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;


/**
 * AES安全编码组件
 * @author 《java加密与解密的艺术 梁栋》
 * 			zhaofh 2016-07-06	根据项目需求引用并优化
 * @version 1.0
 * @since 1.0
 */
public abstract class AESCoder extends Coder {
	/**
	 * ALGORITHM 算法 <br>
	 * AES   key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
	 */
	public static final String ALGORITHM = "AES";
	 private static String ivParameter = "0102030405060708";//偏移量,可自行修改
	 private static BouncyCastleProvider BC =new BouncyCastleProvider();
	/**
	 * 转换密钥<br>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		 SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(AESCoder.decryptBASE64(key));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding",BC);
//		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, k,iv);

		return cipher.doFinal(data);
	}
	
	/*****
	 * 微信小程序解密用户信息
	 * @author Tuyu  2017-01-12 14:25:01
	 * @param data
	 * @param key
	 * @param ivStr
	 * @return
	 * @throws Exception
	 */
	public static String cxDecrypt(byte[] data, String key,String ivStr) throws Exception {
		Key k = toKey(AESCoder.decryptBASE64(key));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding",BC);
		 IvParameterSpec iv = new IvParameterSpec(AESCoder.decryptBASE64(ivStr));
		cipher.init(Cipher.DECRYPT_MODE, k,iv);
		return new String(cipher.doFinal(data),"utf-8");
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding",BC);
		 IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, k,iv);

		return cipher.doFinal(data);
	}

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		kg.init(128,random);
		SecretKey secretKey = kg.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	
}
