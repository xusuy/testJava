package com.util.encryption;

import org.bouncycastle.util.encoders.Base64;


/**
 * @author zhaofh 2016年7月6日
 *
 */
public abstract class Coder {

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decode(key.getBytes());
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return new String(Base64.encode(key));
	}
}
