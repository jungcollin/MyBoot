package kr.doo;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class WhenYouGetResultJavaCode {

	
	@Test
	public void getHmacSHA256() {
		
		String data = "HFG9000|" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime());
		
		String hash = testSHA256(data);
		System.out.println(hash);
		
		String key = "HFG9000";
		
		String hmac = doHMAC(data, key);
		System.out.println(hmac);
		
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
		
		Mac mac = null;
		
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			
			byte[] rawHmac = mac.doFinal(data.getBytes());
			
			return URLEncoder.encode(new String(rawHmac), "UTF-8");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
