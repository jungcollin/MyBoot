package kr.doo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class WhenYouGetResultJavaCode {

	
	private static String getDate() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime());
	}
	
	private String getPlain() {
		String plain = "HFG9000|" + getDate();
		System.out.printf("%s|", plain);
		return plain;
	}

	
	@Test
	public void getHmacSHA256() {
		
		String data = "HFG9000|" + getDate();
		data = "hanam|20170503133400";
		
		String hash = testSHA256(data);
		System.out.println(hash);
		
		String key = "4044cac130913f94a5d4979e0401500e";
		
		String hmac = doHMAC(data, key);
		System.out.println(hmac);
		
		try {
			System.out.println(URLEncoder.encode(hmac, "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
			System.out.println(URLEncoder.encode(doHMAC(getPlain(), key), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(doHMAC("944542050178560694342P1510100001", key));		
	}
	
	public String testSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
	
	public String doHMAC(String data, String key) {
		
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			
			byte[] rawHmac = mac.doFinal(data.getBytes());
			byte[] resultArray = Base64.encodeBase64(rawHmac);
			return new String(resultArray);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
