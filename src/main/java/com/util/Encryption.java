package com.util;


import org.junit.jupiter.api.Test;

import java.security.MessageDigest;

public class Encryption {

	/****
	 * MD5 加密
	 * 
	 * @param plainText
	 *            加密内容
	 * @param length
	 *            长度
	 * @return
	 */
	public static String Md5(String plainText, int length) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (length == 32) {
				// 32位加密
				result = buf.toString();
			} else if (length == 16) {
				// 16位加密
				result = buf.toString().substring(8, 24);
			} else {
				return "";
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 生成混淆码
	 * 
	 * @param args
	 * @return
	 */
	public static String getAppKey(Object[] args) {
		try {
			StringBuffer sb = new StringBuffer();
			String Key = "ywkj2014";
			String str = "";
			if (args.length <= 0) {
				sb.append(Key);
				str = sb.toString();
			} else {
				for (int i = 0; i < args.length; i++) {
					sb.append(String.valueOf(args[i]));
				}
				str = sb.toString();
				if (str.length() <= 2) {
					str = str + Key;
				} else {
					str = str.substring(0, 2) + Key + str.substring(2);
				}
			}
			String md5Key = Md5(str, 32);
			System.out.println(md5Key);
			return md5Key;
		} catch (Exception e) {
		}
		return "";
	}

	@Test
	public void test() {
		String str = "";
		getAppKey(new String[] {str});
	}

}
