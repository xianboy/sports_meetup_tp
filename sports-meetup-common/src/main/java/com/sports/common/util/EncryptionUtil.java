package com.sports.common.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionUtil {
	  private static Key key;  
	    private static String KEY_STR="mykey";  
	      
	    static{  
	        try  
	        {  
	            KeyGenerator generator = KeyGenerator.getInstance("DES");  
	            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");  
	            secureRandom.setSeed(KEY_STR.getBytes());  
	            generator.init(secureRandom);  
	            key = generator.generateKey();  
	            generator=null;  
	        }  
	        catch (Exception e)  
	        {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	      
	    /** 
	     * 瀵瑰瓧绗︿覆杩涜鍔犲瘑锛岃繑鍥濨ASE64鐨勫姞瀵嗗瓧绗︿覆 
	     * <鍔熻兘璇︾粏鎻忚堪> 
	     * @param str 
	     * @return 
	     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳] 
	     */  
	    public static String getEncryptString(String str){  
	        BASE64Encoder base64Encoder = new BASE64Encoder();  
	        System.out.println(key);  
	        try  
	        {  
	            byte[] strBytes = str.getBytes("UTF-8");  
	            Cipher cipher = Cipher.getInstance("DES");  
	            cipher.init(Cipher.ENCRYPT_MODE, key);  
	            byte[] encryptStrBytes = cipher.doFinal(strBytes);  
	            return base64Encoder.encode(encryptStrBytes);  
	        }  
	        catch (Exception e)  
	        {  
	            throw new RuntimeException(e);  
	        }  
	          
	    }  
	      
	    /** 
	     * 瀵笲ASE64鍔犲瘑瀛楃涓茶繘琛岃В瀵� 
	     * <鍔熻兘璇︾粏鎻忚堪> 
	     * @param str 
	     * @return 
	     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳] 
	     */  
	    public static String getDecryptString(String str){  
	        BASE64Decoder base64Decoder = new BASE64Decoder();  
	        try  
	        {  
	            byte[] strBytes = base64Decoder.decodeBuffer(str);  
	            Cipher cipher = Cipher.getInstance("DES");  
	            cipher.init(Cipher.DECRYPT_MODE, key);  
	            byte[] encryptStrBytes = cipher.doFinal(strBytes);  
	            return new String(encryptStrBytes,"UTF-8");  
	        }  
	        catch (Exception e)  
	        {  
	            throw new RuntimeException(e);  
	        }  
	          
	    }
	    
}
