package vn.vimass.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;



public class VimassCommon {
	
	public static String tinhToanCKS(String cmd, int length) {
		String cks = "";
		try
		{

			String hexString = VimassCommon.toHexadecimal(cmd);
			Data.ghiLogRequest("hexString:" + hexString);
			cks = VimassCommon.getCKSFromHEX(hexString, length);
			
			
			Data.ghiLogRequest("cks:" + cks);
		}
		catch (Exception e) {
			Data.ghiLogRequest("Loi tinhToanCKS :" + e.getMessage());
		}
		
		
		return cks;
	}
	public static String toHexadecimal(String text)
	{
		try
		{

		    byte[] myBytes = text.getBytes("UTF-8");

		    return DatatypeConverter.printHexBinary(myBytes);
		}
		catch (Exception e) {
			return "";
		}
	}
	public static String getCKSFromHEX(String hexString, int length) {
		int tong = 0;
		for(int i =0; i < hexString.length() / 2; i++)
		{
			String text = "" + hexString.charAt(i*2) + hexString.charAt(i*2 + 1);
			int kq = VimassCommon.hexToDecimal(text);
			
//			System.out.println(text + "->" + kq);
			tong += kq;
//			System.out.println("tong ->" + tong);
		}
		
		
		
		String kq = "" + tong;
		return getDataTheoFomatTBTTVer2(kq, "0", length);
	}
	

	public static int hexToDecimal(String hex) {
		try
		{
//			System.out.println(hex);
			return Integer.parseInt(hex,16); 
		}
		catch (Exception e) {
			return -1;
		}
		
	}

	public static String getDataTheoFomatTBTTVer2(String data, String noData, int maxLength) {

		String kq = "" + data;
		if(kq.length() < maxLength)
		{
			int delta = maxLength - kq.length();
			for(int i = 0; i < delta; i++)
			{
				kq = noData + kq;
			}
		}
		else
		{
			kq = kq.substring(0, maxLength);
		}
		return kq;
	}
	public static String taoNameAliasMacDinh(String nameAlias, String id) {
		String kq = nameAlias;
		try {
			if (nameAlias == null || nameAlias.trim().length() == 0 || nameAlias.contains("*")) {
				if (id != null) {
					id = id.replace(" ", "").trim();
					int sl = id.length();
					
					if (sl > 5) {
						kq = "";
						int startViTri = 3;
						kq = id.substring(0,3);
						if(id.startsWith("gg_") || id.startsWith("fb_") || id.startsWith("dn_"))
						{
							startViTri = 6;
							kq = id.substring(0,6);
						}
						for(int i = startViTri; i < sl - 2; i++)
						{
							kq +="*";
						}
						kq += id.substring(sl - 2);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
    public static String hexToAscii(String hexStr) {
	    StringBuilder output = new StringBuilder("");
	     
	    try
	    {
	    	hexStr = hexStr.replace(" ", "").trim();
		    for (int i = 0; i < hexStr.length(); i += 2) {
		        String str = hexStr.substring(i, i + 2);
		        output.append((char) Integer.parseInt(str, 16));
		    }	
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
	     
	    return output.toString();
	}

	 public static int hex2decimal(String s) {
         String digits = "0123456789ABCDEF";
         s = s.toUpperCase();
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = 16*val + d;
         }
         return val;
     }
	
	public static String getBitOS()
	{
		System.out.println("os: " + System.getProperty("os.arch") + "\n========\n");
		String arch = System.getenv("PROCESSOR_ARCHITECTURE");
		String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

		String realArch = arch != null && arch.endsWith("64")
		                  || wow64Arch != null && wow64Arch.endsWith("64")
		                      ? "64" : "32";
		System.out.println("realArch: " + realArch + "\n========\n");
		
		return realArch;
	}
	
	
	
	public static void delay(int minisec) {
		try {
			Thread.sleep(minisec);
		} catch (Exception e) {
		}
	}

	public static String getNumber(String input) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(input);
//		System.out.println("input:" + input);
		StringBuilder kq = new StringBuilder();
		kq.append("");
		while (m.find()) {
			// System.out.println("m.group():" + m.group());
			// int n = Integer.parseInt(m.group());
			kq.append(m.group());
		}
		if (kq.toString().length() > 0) {
			return kq.toString();
		}
		return "0";
	}
	
	
	public static String getNumberAndCharacterABCDEF(String input) {
		Pattern p = Pattern.compile("[0-9]+ABCDEF");
		Matcher m = p.matcher(input);
//		System.out.println("input:" + input);
		StringBuilder kq = new StringBuilder();
		kq.append("");
		while (m.find()) {
			// System.out.println("m.group():" + m.group());
			// int n = Integer.parseInt(m.group());
			kq.append(m.group());
		}
		if (kq.toString().length() > 0) {
			return kq.toString();
		}
		return "0";
	}

	public static String generateSessionKeyUpCase(int length) {
		String alphabet = new String("0123456789ABCDEF"); // 9
		int n = alphabet.length(); // 10

		String result = new String();
		Random r = new Random(); // 11

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;
	}

	public static String generateSessionKey(int length) {
		String alphabet = new String(
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();
		Random r = new Random(); // 11

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;
	}

	
	public static String getSoTienWithFomat(double amount) {
		df = new DecimalFormat("#");
		df.setMaximumFractionDigits(8);
		String kq = "0";
		try
		{
			kq = df.format(amount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String result = "";
		int nDem = 0;
		
		
		int indexPhanThapPhan = 0;
		if(kq.contains("."))
		{
			indexPhanThapPhan = kq.indexOf(".");
			result = "," + kq.substring(indexPhanThapPhan + 1);
			kq = kq.substring(0, indexPhanThapPhan);
//			System.out.println("phan nguyen:" + kq);
			
		}
	
		
		for(int i = kq.length() - 1; i >=0; i --)
		{
			result = kq.charAt(i) + result;
			nDem++;
			if(nDem % 3 == 0 && i != 0)
			{
				result = "." + result;	
			}
		}
		
		return result;
	}
	
	public static String generateSessionNumberKey(int length) {
		String alphabet = new String("0123456789"); // 9
		int n = alphabet.length(); // 10

		String result = new String();
		Random r = new Random(); // 11

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;
	}

	public static String deAccent(String str) {
		String nfdNormalizedString = Normalizer.normalize(str,
				Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	public static String boDauTiengViet(String sDuLieu) {
		if (sDuLieu != null) {

			sDuLieu = deAccent(sDuLieu);

			sDuLieu = sDuLieu.replaceAll("á|à|ả|ã|ạ|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ",
					"a");
			sDuLieu = sDuLieu.replaceAll("Á|À|Ả|Ã|Ạ|Ă|Ắ|Ằ|Ẳ|Ẵ|Ặ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ",
					"A");
			sDuLieu = sDuLieu.replaceAll("đ", "d");
			sDuLieu = sDuLieu.replaceAll("Đ", "D");
			sDuLieu = sDuLieu.replaceAll("é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ", "e");
			sDuLieu = sDuLieu.replaceAll("É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ", "E");
			sDuLieu = sDuLieu.replaceAll("í|ì|ỉ|ĩ|ị|Í|Ì|Ỉ|Ĩ|Ị", "i");
			sDuLieu = sDuLieu.replaceAll("Í|Ì|Ỉ|Ĩ|Ị", "I");
			sDuLieu = sDuLieu.replaceAll("ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ",
					"o");
			sDuLieu = sDuLieu.replaceAll("Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ",
					"O");
			sDuLieu = sDuLieu.replaceAll("ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự", "u");
			sDuLieu = sDuLieu.replaceAll("Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự", "U");
			sDuLieu = sDuLieu.replaceAll("ý|ỳ|ỷ|ỹ|ỵ", "y");
			sDuLieu = sDuLieu.replaceAll("Ý|Ỳ|Ỷ|Ỹ|Ỵ", "Y");
		} else {
			sDuLieu = "";
		}

		return sDuLieu;
	}

	public static String enCodeURL(String input) {
		try {
			return URLEncoder.encode(input, "utf-8");
		} catch (Exception e) {

		}
		return input;
	}

	public static String deCodeURL(String input) {
		try {
			return URLDecoder.decode(input, "utf-8");
		} catch (Exception e) {

		}
		return input;
	}

	private static DecimalFormat df;

	public static String getSoTien(double amount) {
		df = new DecimalFormat("#");
		df.setMaximumFractionDigits(8);
		String kq = "0";
		try {
			kq = df.format(amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	public static String slugify(String str) {
		if (str != null)
			return Normalizer
					.normalize(str.replace("Đ", "D").replace("đ", "d"),
							Normalizer.Form.NFD).replaceAll("[^\\w ]", "")
					.replace(" ", "-").toLowerCase();
		else
			return " ";
	}

	public static String getTimeddMMyyyy_HHmmssSSSSSS(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSSSSS");
		return dt1.format(timeShow);
	}
	
	public static String getTimeddMMyyyy_HHmmss(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dt1.format(timeShow);
	}
	
	public static String getTimeyyyyddMM_HHmmss(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dt1.format(timeShow);
	}
	

	public static String getTimeddMMyyyy(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		return dt1.format(timeShow);
	}
	
	public static String getTimeYYMMDD(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("yy/MM/dd");
		return dt1.format(timeShow);
	}
	
	public static String getTransDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,0);
		Date timeShow = cal.getTime();		

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
		String r_time = dt1.format(timeShow).replace("/", ""); 
		return r_time;
	}
	
	public static String getTimeDoiSoat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date timeShow = cal.getTime();		

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
		String r_time = dt1.format(timeShow).replace("/", ""); 
		return r_time;
	}

	public static long getTimeT3(long timeGiaoDich) {
		Date dateT3 = new Date(timeGiaoDich + (1000l * 60 * 60 * 24 * 3));
		dateT3.setHours(16);
		dateT3.setMinutes(0);
		dateT3.setSeconds(0);
		return dateT3.getTime();
	}
	
	public static String getTimeT0(String timeGiaoDich) {
		String time = "";
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date1 = df.parse(timeGiaoDich);
			
			Date date2 = new Date(date1.getTime() + (1000l * 60 * 60 * 24));
			time = df.format(date2);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return time;
	}
	
	
	
	public static String bamMD5(String input)
	{
		StringBuffer sb = new StringBuffer();
		try {
			input = input.trim();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (Exception e) {

		}
		return sb.toString();
	}
	
	
	
//	public static String sha256(String base) {
//	    try{
//	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
//	        StringBuffer hexString = new StringBuffer();
//
//	        for (int i = 0; i < hash.length; i++) {
//	            String hex = Integer.toHexString(0xff & hash[i]);
//	            if(hex.length() == 1) hexString.append('0');
//	            hexString.append(hex);
//	        }
//
//	        return hexString.toString();
//	    } catch(Exception ex){
//	       throw new RuntimeException(ex);
//	    }
//	}
	
	public static String sha256(String input) 
    {
		 return DigestUtils.sha256Hex(input.trim());
    } 


	public static String getCheckSumFile(String path) {
		String kq = "";
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		    FileInputStream fis = new FileInputStream(path);

		    byte[] dataBytes = new byte[1024];

		    int nread = 0;
		    while ((nread = fis.read(dataBytes)) != -1) {
		        md.update(dataBytes, 0, nread);
		    };
		    byte[] mdbytes = md.digest();
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < mdbytes.length; i++) {
		        sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		    kq = sb.toString();
		    System.out.println("Digest(in hex format):: " + kq);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return kq;
	}
	public static String checksum(File input) {
		
		

//		MD5("MD5"),
//	    SHA1("SHA1"),
//	    SHA256("SHA-256"),
//	    SHA512("SHA-512");
		
        try (InputStream in = new FileInputStream(input)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] block = new byte[4096];
            int length;
            while ((length = in.read(block)) > 0) {
                digest.update(block, 0, length);
            }
            return DatatypeConverter.printHexBinary(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	

	public static String setBase64(String input)
	{
		String kq = "";
		try
		{
			if(input != null)
			{
				byte[] encodedBytes = Base64.encodeBase64(input.getBytes("UTF-8"));
				kq = new String(encodedBytes);
			}	
		}
		catch(Exception e)
		{
			
		}
		return kq;
	}

	public static String getBase64(String input)
	{
		String kq = "";
		try
		{
			if(input != null)
			{
				byte[] decodedBytes = Base64.decodeBase64(input.getBytes("UTF-8"));
				kq = new String(decodedBytes);
			}	
		}
		catch(Exception e)
		{
			
		}
		return kq;
	}

	public static void sendCommand(String param) {

		try {
			String pathRun = "cmd /c start /B " + param + "\n";
			Runtime.getRuntime().exec(pathRun);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDataTheoFomatTBTT(String amount, int maxLength) {
		String kq = "" + amount;
		if(kq.length() < maxLength)
		{
			int delta = maxLength - kq.length();
			for(int i = 0; i < delta; i++)
			{
				kq = "0" + kq;
			}
		}
		return kq;
	}
	
	public static String getDataTheoFomatTBTT(String data, String noData, int maxLength) {
		String kq = "" + data;
		if(kq.length() < maxLength)
		{
			int delta = maxLength - kq.length();
			for(int i = 0; i < delta; i++)
			{
				kq = noData + kq;
			}
		}
		return kq;
	}

	public static String locData(String gm) {
		if(gm == null)
		{
			gm = "";
		}
		while(true)
		{
			if(gm.startsWith("0"))
			{
				gm = gm.substring(1, gm.length());
			}
			else
			{
				break;
			}
		}
		return gm;
	}


	public static String getSubVId(String vIdVatLy) {
		try
		{
			int index = vIdVatLy.indexOf("G") + 1;
			return vIdVatLy.substring(index, vIdVatLy.length());
		}
		catch(Exception e)
		{
			System.out.println("getSubVId:" + e.getMessage());
		}
		return "";
	}

	public static String getvID(String vIdVatLy) {
		try
		{
			int index = vIdVatLy.indexOf("G");
			String kq = vIdVatLy.substring(0,index);
			kq = VimassCommon.locData(kq);
			return kq;
		}
		catch(Exception e)
		{
			System.out.println("getvID:" + e.getMessage());
		}
		return "";
	}
	
	
	public static String getvIDChuan(String vIdVatLy) {
		try
		{
			vIdVatLy = vIdVatLy.toLowerCase();
			
			if(vIdVatLy.contains("v"))
			{
				int index = vIdVatLy.indexOf("v");
				String kq = vIdVatLy.substring(index);
				kq = VimassCommon.locData(kq);
				return kq;
			}
			else
			{
				String kq = VimassCommon.locData(vIdVatLy);
				return kq;
			}
		}
		catch(Exception e)
		{
			System.out.println("getvID:" + e.getMessage());
		}
		return "";
	}
	
	public static PublicKey getPublicKey(String base64PubKey) throws Exception
	{
		byte[] publicBytes = Base64.decodeBase64(base64PubKey.getBytes());
		java.security.spec.X509EncodedKeySpec keySpec = new java.security.spec.X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	public static boolean verify(PublicKey publicKey, String message, String signature) throws SignatureException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			InvalidKeyException {
		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initVerify(publicKey);
		sign.update(message.getBytes("UTF-8"));
		return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
	}	
	
	public static boolean verify_SHA1(PublicKey publicKey, String message, String signature) throws SignatureException,
		NoSuchAlgorithmException, UnsupportedEncodingException,
		InvalidKeyException {
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initVerify(publicKey);
		sign.update(message.getBytes("UTF-8"));
		return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
	}	

	
	public static String signData(String priKeyTest, String dataNeedSign)
    {
        try
        {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(getPrivateKey(priKeyTest));
            sign.update(dataNeedSign.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(sign.sign()), "UTF-8");
        }
        catch(Exception e)
        {

        }
        return "";
    }
	
	public static String signData_SHA1(String priKeyTest, String dataNeedSign)
    {
        try
        {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(getPrivateKey(priKeyTest));
            sign.update(dataNeedSign.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(sign.sign()), "UTF-8");
        }
        catch(Exception e)
        {

        }
        return "";
    }

		
    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;

        KeyFactory kf;
        try {
            kf = KeyFactory.getInstance("RSA");
            byte[] encodedPv = Base64.decodeBase64(base64PrivateKey.getBytes("utf-8"));
            PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
            privateKey = kf.generatePrivate(keySpecPv);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    public static String getVNDFormat(double money)
    {
        if(money>0)
        {
            DecimalFormat df;
            df = new DecimalFormat("#");
            df.setMaximumFractionDigits(8);
            String m = new StringBuilder(df.format(money)).reverse().toString();
            for(int i=0;i<m.length();i++)
            {
                if(i==3 || i ==7 || i == 11)
                {
                    m = new StringBuffer(m).insert(i, ".").toString();
                }
            }
            return new StringBuilder(m).reverse().toString();
        }
        else
        {
            return "0";
        }
    }
	
}
