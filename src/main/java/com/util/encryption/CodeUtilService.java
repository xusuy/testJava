package com.util.encryption;

import java.util.Arrays;
import java.util.List;


/**
 * <注意上线时切换密钥环境>
 * @author zhaofh 2016年7月6日
 *
 */
public class CodeUtilService {

	/**
	 * <服务端>
	 * 公钥加密，私钥签名
	 * @author zhaofh 2016年7月6日 上午9:47:23
	 * 
	 * @param param 报文明文
	 * @param aesKey AES_KEY
	 * @return 报文密文
	 */
	public static String encrpytService(String param,String aesKey){
		try {
			return encrpytService(param.getBytes("utf-8"),aesKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * <服务端>
	 * 私钥解密，公钥验签
	 * @author zhaofh 2016年7月6日 上午9:49:59
	 *
	 * @param param传输数据
	 * @return aesKey&&报文明文
	 */
	public static String[] decryptService(String param){
		try {
			if(param!=null&&!"".equals(param)){
				CommonParam c = new CommonParam("keys");
				return decryptService(param,c.getProperty("private.key.server"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String param = "Dtuz6uDtj8IZ3KBpv2RglihUNTmQNZhXFkEzG/PdWDMxtRLfvQgJsbde5Vc8Io289tU6J+R9YPCIjqvqs9nstPlnTx/fTla7EBaqb02K6Hlapg6sLYJ0tW37RenXoOolsUNqJx3AvNxSo7zOLExYZbO5db6gZEJxogRYlVv32jA1u9neXoR5uboL7QBrP3y9tdU1fzUDHuHBMMKid6msvAc8uCW5n8j5bEnwDelslf4t9Xr7HkbGzZJ+wayYLdIENB2/iDwODP7EsapbOYP+R0nMXv5aXDAt1sMrObMqSl8jBHZdkw7ixEhmbEZrbEBUA45h8v+0LzsxKc6dZRWvAg==";
		String privateKey = "MIIEpAIBAAKCAQEArggoz4kDb53vmvAA/ceqmTMiqq9rSDWnA7HNGnCDj+itw/kS\n" +
				"KdwclkZTMXPwfwU3zEnvznOz37NusL9wqkTUAZyfhanbgz6GQQgXSOGCKO/oh8Wx\n" +
				"eRXgLJid6RGYjqi49RxP4C6fhVVpyeo40qIY0CZ8zsUQK62wLYq9b04WpP+9qRnA\n" +
				"E9u98KsrpMTUxD5pQkz6pwS1sYLg0nZt9eHKLeBI5Jqk5ZR7A8De4KZ8kDGFKXQw\n" +
				"9cTsJhACYTIHY0TYDv2vwgck0XnBocOdUvlwfwISD5MZMNT1EK+VsVooPjzASLR4\n" +
				"Shgfohn9U1pQ4MdanpG5ZNFiRbgRHPmQN4IWzwIDAQABAoIBABstxQncUQAOf0wI\n" +
				"LW90zkzMsp5LdQq67lFiZDC736AK65BOhqqo6IIDi5yOicGclAFAjsYERdQq1p+d\n" +
				"1j+RLjbuOe+K/nA5xIfFdrV4GnbAOOVzV2sconz0fLzYdaXZ7cDB4iuVvG37T+Er\n" +
				"xiIHFK71Q8qa9hKMNpZ2M219mYW89OuJkUVH66uLGzTWcG4wqSjZbKxjUw5woe4N\n" +
				"KUf+jzQHDW45j9KNveAdBCfIv5TnwqXw0cSEY04lPFhV1T3GZXzAl1QqxUYegQ4C\n" +
				"BC9MLKIXnqmYKydjDYBkdMn/JS08BlHQHdXnwdP3HbsGtKciXE2JDP8FQFxyU59E\n" +
				"Yt/eRLkCgYEA27T+EhPRkAnpLIZ/H1odH03WKzirhFpHTORC1zn1kDWl4HNtUWtM\n" +
				"tPnVCXJmOy8gvfkXWgQyYODOjkGP5uIBlXoMBXlEeKA/YbDPHSjTHQqGfhc7GyJX\n" +
				"WFC8JQs3cCNsUDYWDJq8pi5nHRukCvwm8GxfJ6U687Uz8CmwzT3M7nUCgYEAysem\n" +
				"M76HKqNhZ9iFqH4WZlsmTtKsJap7LLaFpUMkjIuX/gIZFwuWU4Lu2nz16PHT4m9I\n" +
				"HJ6HKvw03DXKKDfvnNjgDfbElmjPixMR6QIL0JWeGdromCSYpz5p6xjxfaRGu1BD\n" +
				"Vb+s671ccPWxoPdveuG/2ZtQUCg/flTBsS90j7MCgYAaDTUPObBgrvwqHmyWcB4B\n" +
				"5z3c9CRKVlYcBPj0Alhe2C2qFqXAwMNgtjsPmrQKkoUEYbPmJZc/q4hyj82bZ/Me\n" +
				"IVGpmH6I+x9o2wlEAOI/kE7wRprolcLbov8cOV9CecDPJgqpRfPWlKv/ZJaoAswv\n" +
				"N/p0fgAmg7b75EWloI1bPQKBgQC3aepM91KvIGRkV5Zdlt6P6nY16ZxH3XHYOaec\n" +
				"maSfc0whNqiXDaYFF3MQMOMEtmeecmVZbZJ5MPJnFEgsJJwY1JmkR8X8/gRrmO4k\n" +
				"QfOJ0SrV3AlyQ/fHnk6TlVYo/MP1fV3KW7r+56KLkc/+9o/hJ/wtyavVNPJpRCSG\n" +
				"LZRSvQKBgQCFPhL3ytXEsnOFnnULVlDciUNwmTMhNZHNmAbo+96qo8fQNehBR1lg\n" +
				"YYqHH3dnupqeejMnB8lP+jKhnb2PonEF6nQalclJatRljbCOi2KbmkJqUSZgU9Fx\n" +
				"Z3eCsnjvh85vm/vOuXMD9EjNRoe7UIC7TswSxw1dWJn+oOf1vkZTPg==\n";
		try {
			if(param!=null&&!"".equals(param)){
				CommonParam c = new CommonParam("keys");
				String[] arraysStr = decryptService(param, privateKey);
				List<String> strList = Arrays.asList(arraysStr);
				System.out.println(strList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * <服务端>
     * aes加密
     * @author zhaofh 2016年7月6日 上午9:49:59
     * 
     * @param data       报文明文
     * @param aesKey     临时通信密钥
     * @return 报文密文
     */
    private static String encrpytService(byte[] data,String aesKey) throws Exception {
    	byte[] encData = AESCoder.encrypt(data, aesKey);
    	String outputStr = AESCoder.encryptBASE64(encData);
    	return outputStr;
    }
    
    /**
     * <服务端>
     * 数据解密，数据验签
     * @author zhaofh 2016年7月6日 上午9:49:59
     * 
     * @param data      报文密文
     * @param aesKey    临时通信密钥
     * @param srvPubKey 服务端公钥证书
     * @return aesKey&&报文明文
     */
    private static String[] decryptService(String data, String private_key_param) throws Exception {
    	String[] enc_data_seg = data.split("\\|");
    	byte[] encoded_aesKey =RSACoder.decryptBASE64(enc_data_seg[0]); 
        byte[] decoded_aesKey =RSACoder.decryptByPrivateKey(encoded_aesKey,private_key_param);
        String aesKey =new String(decoded_aesKey);
        byte[] encData = AESCoder.decrypt(AESCoder.decryptBASE64(enc_data_seg[1]),aesKey);
        String[] result = new String(encData,"utf-8").split("\\|");
        String public_key_sign ="";
        CommonParam c = new CommonParam("keys");
        if("0".equals(result[0])){
        	public_key_sign =c.getProperty("public.key.server.android");
        }else if("1".equals(result[0])){
        	public_key_sign =c.getProperty("public.key.server.ios");
        }else if("3".equals(result[0])){
        	public_key_sign =c.getProperty("public.key.server.net");
        }
        boolean status = RSACoder.verify(encData, public_key_sign, enc_data_seg[2]);
        if (status)
            return new String[]{aesKey,result[1]};
        else
            return null;
    }	
}
