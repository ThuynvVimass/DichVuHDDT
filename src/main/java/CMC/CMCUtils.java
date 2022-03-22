package CMC;

import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vn.vimass.utils.Data;
import VimassLib.util.VimassData;

public class CMCUtils {

	public final static String SERVICES_URL = new com.microsoft.webservices.DefaultServiceLocator().getDefaultServiceSoapAddress();
	
	public final static String SERVICES_USER = "DEMO.SERVICE";
	public final static String SERVICES_PASS = "123";

	public final static int KIEUPHATHANH_PHAT_HANH_MOI = 1;
	public final static int KIEUPHATHANH_SUA_THONG_TIN = 2;
	public final static int KIEUPHATHANH_DIEU_CHINH = 3;
	public final static int KIEUPHATHANH_THAY_THE = 4;

	

	public static String signMsg(String sSource, String path_pubkey) {

		byte[] bSource = null;

		byte[] bSign = null;

		String bSignBase64 = null;

		try {

			KeyStore keystore = KeyStore.getInstance("JKS");
			// KeyStore keystore =
			// KeyStore.getInstance(KeyStore.getDefaultType());

			// File keystoreFile = new File("D:\\RSA_KEY\\namakba.jks");

			File keystoreFile = new File(path_pubkey);

			// System.out.println("buoc 1");

			// nabkba là password luc tao keystore,NAB la alias

			keystore.load(new FileInputStream(keystoreFile),
					"nabkba".toCharArray());

			// System.out.println("buoc 2");

			KeyPair keyPair = getPrivateKey(keystore, "nab",
					"sbvkba".toCharArray());

			// System.out.println("buoc 3");

			PrivateKey privateKey = keyPair.getPrivate();

			// System.out.println("co private key");

			// 1.Lay ma nhi phan voi ma UTF-8 chuoi vao sSource

			bSource = sSource.getBytes("UTF-8");

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] encodedhash = digest.digest(bSource);

			// 2.Ky len chuoi sSource

			bSign = signing(encodedhash, privateKey);

			// 3.Chuyen doi chu ky dang nhi phan sang Base64

			Base64 base64 = new Base64();

			bSignBase64 = new String(base64.encode(bSign));

			// bSignBase64 = Base64.base64Encode(bSign);

			// System.out.println("[signMsg] bSignBase64:" + bSignBase64);

		} catch (Exception ex) {

			System.out.println("[" + getCurrentDateTimeNow() + "][signMSg]["

			+ ex.getMessage() + "]");

		}

		return bSignBase64;

	}

	public static KeyPair getPrivateKey(KeyStore keystore, String alias,
			char[] password) {

		try {

			Key key = keystore.getKey(alias, password);

			if (key instanceof PrivateKey) {

				Certificate cert = keystore.getCertificate(alias);

				PublicKey publicKey = cert.getPublicKey();

				return new KeyPair(publicKey, (PrivateKey) key);

			}

		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	private static String getCurrentDateTimeNow() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(cal.getTime());

	}
	
	public static String getCurrentDateTimeNow_ddMMyyyy() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(cal.getTime());

	}

	private static final String ALGORITHM = "SHA256withRSA";

	private static byte[] signing(byte[] bSource, PrivateKey privateKey) {

		byte[] bSign = null;

		try {

			Signature sha1_rsa = Signature.getInstance(ALGORITHM);

			sha1_rsa.initSign(privateKey);

			sha1_rsa.update(bSource);

			bSign = sha1_rsa.sign();

		} catch (Exception ex) {

			System.out.println("[" + getCurrentDateTimeNow() + "][signing]["

			+ ex.getMessage() + "]");

		}

		return bSign;

	}

	public static String getBankCodeChuyenTienNhanh247_TK_Wooribank(
			String bankCodeVimass) {
		String bankCodeNamA = "";
		if (bankCodeVimass != null) {
			bankCodeVimass = bankCodeVimass.toUpperCase().trim();

			HashMap<String, String> hmMapping = new HashMap<>();

			// ===TK
			hmMapping.put("NAB", "970428");
			hmMapping.put("IVB", "970434");
			hmMapping.put("LPB", "970449");
			hmMapping.put("WOORI HCM", "970457");
			hmMapping.put("WOORI HN", "970457");
			hmMapping.put("IBK", "970455");
			hmMapping.put("SCB", "970429");
			hmMapping.put("SGB", "970400");
			hmMapping.put("ABB", "970425");
			hmMapping.put("EIB", "970431");
			hmMapping.put("NCB", "970419");
			hmMapping.put("TCB", "970407");
			hmMapping.put("EAB", "970406");
			hmMapping.put("GPB", "970408");
			
			
			hmMapping.put("OJB", "970414");
			hmMapping.put("CTG", "970415");
			hmMapping.put("ACB", "970416");
			
			
			hmMapping.put("MB", "970422");
			hmMapping.put("TPB", "970423");
			hmMapping.put("MSB", "970426");
			
			
			
			hmMapping.put("VIETBANK", "970433");
			hmMapping.put("HDB", "970437");
			hmMapping.put("BVB", "970438");
			
			
			
			hmMapping.put("VID", "970439");
			hmMapping.put("HLB", "970442");
			hmMapping.put("SHB", "970443");
			
			
			
			hmMapping.put("OCB", "970448");
			hmMapping.put("SEAB", "970440");
			hmMapping.put("BID", "970418");
			
			
			hmMapping.put("AGR", "970405");
			hmMapping.put("VPB", "970432");
			
			
			
			hmMapping.put("UOB", "970458");
			hmMapping.put("VAB", "970427");
			hmMapping.put("BAB", "970409");
			
			
			
			hmMapping.put("VCB", "970436");
			hmMapping.put("CIMB", "422589");

			
			
			

			hmMapping.put("STB", "970403");
			hmMapping.put("PVB", "970412");
			hmMapping.put("VRB", "970421");
			
			
			
			hmMapping.put("SHINHAN", "970424");
			hmMapping.put("PGB", "970430");
			hmMapping.put("VIB", "970441");
			hmMapping.put("KLB", "970452");
			hmMapping.put("VCCB", "970454");

			hmMapping.put("HSBC", "458761");
			hmMapping.put("SC", "970410");

			if (hmMapping.containsKey(bankCodeVimass)) {
				bankCodeNamA = hmMapping.get(bankCodeVimass);
			}
		}
		VimassData.ghiLogRequest("===getBankCodeChuyenTienNhanh247_TK_Wooribank:" + bankCodeNamA);
		
		return bankCodeNamA;
	}

	public static String getBankCodeChuyenTienNhanh247_THE_Wooribank(
			String bankCodeVimass) {
		String bankCodeNamA = "";
		if (bankCodeVimass != null) {
			bankCodeVimass = bankCodeVimass.toUpperCase();

			HashMap<String, String> hmMapping = new HashMap<>();

			hmMapping.put("NAB", "970428");
			hmMapping.put("IVB", "888999");
//			hmMapping.put("SCB", "157979");
			hmMapping.put("SCB", "970429");
			hmMapping.put("UOB", "970458");
//			hmMapping.put("VIB", "180906");
			hmMapping.put("VIB", "970441");
			hmMapping.put("STB", "452999");
			hmMapping.put("VCB", "970436");
			hmMapping.put("TCB", "888899");
			hmMapping.put("STB", "970403");
			hmMapping.put("EAB", "970406");
			hmMapping.put("GPB", "970408");
			hmMapping.put("BAB", "970409");
			hmMapping.put("OJB", "970414");
			hmMapping.put("CTG", "970415");
			hmMapping.put("ACB", "970416");
			hmMapping.put("MB", "970422");
			hmMapping.put("TPB", "970423");
			hmMapping.put("SHINHAN", "970424");
			hmMapping.put("HDB", "970437");
			hmMapping.put("BVB", "970438");
			hmMapping.put("VID", "970439");
			hmMapping.put("HLB", "970442");
			hmMapping.put("SHB", "970443");
			hmMapping.put("OCB", "970448");
			hmMapping.put("LPB", "970449");
			hmMapping.put("SEAB", "970468");
//			hmMapping.put("VPB", "981957");
			hmMapping.put("VPB", "970432");
			hmMapping.put("PVB", "970412");
			hmMapping.put("VAB", "970427");
			hmMapping.put("CIMB", "422589");
			hmMapping.put("SGB", "970400");
			hmMapping.put("AGR", "970405");
			hmMapping.put("COOPB", "970446");
			hmMapping.put("NCB", "970419");
			hmMapping.put("VRB", "970421");
			hmMapping.put("ABB", "970425");
			hmMapping.put("PGB", "970430");
			hmMapping.put("KLB", "970452");
			hmMapping.put("MSB", "970426");
//			hmMapping.put("BID", "970488");
			hmMapping.put("BID", "970418");
			hmMapping.put("WOORI HCM", "970457");
			hmMapping.put("WOORI HN", "970457");

			if (hmMapping.containsKey(bankCodeVimass)) {
				bankCodeNamA = hmMapping.get(bankCodeVimass);
			}
		}
		return bankCodeNamA;
	}
	public static String getMaCodeNabAPI(String kqGiaiMa) {
		String kq = "";
		{
			try
			{
				Document doc = Jsoup.parse(kqGiaiMa, "utf-8");
				Elements elements = doc.select("response");
				
				kq = elements.select("respcode").text();
			}
			catch(Exception e)
			{		
			}
		}
		return kq;
	}
	
	
	
	public static String getIdSaoKeNabAPI(String kqGiaiMa) {
		String kq = "";
		{
			try
			{
				Document doc = Jsoup.parse(kqGiaiMa, "utf-8");
				Elements elements = doc.select("response");
				
				kq = elements.select("trn_ref_no").text();
			}
			catch(Exception e)
			{		
			}
		}
		return kq;
	}


	public static String layTenChuTheTKNapas247(String kqGiaiMa) {
		String kq = "";
		{
			try
			{
				Data.ghiLogRequest("layTenChuTheTKNapas247:" + kqGiaiMa);
				Document doc = Jsoup.parse(kqGiaiMa, "utf-8");
				Elements elements = doc.select("response");
				
				String respcode = elements.select("respcode").text();
				if(respcode != null && respcode.equals("000"))
				{
					kq = elements.select("rec_name").text();
				}
			}
			catch(Exception e)
			{		
			}
		}
		return kq;
	}
	
	
	public static String layValueSoDu(String kqGiaiMa) {
		String kq = "";
		{
			try
			{
				Document doc = Jsoup.parse(kqGiaiMa, "utf-8");
				Elements elements = doc.select("response");
				
				String respcode = elements.select("respcode").text();
				if(respcode != null && respcode.equals("000"))
				{
					kq = elements.select("lcy_curr_balance").text();
				}
//				<response><header><respcode>000</respcode><respmsg></respmsg><banksignature>TTuin7Nq8VPxcNbvtMyHBhBtDgSlOGtLob/L2aeRQVcYzQgW+bpM/DW3Ovv7iaNlN+t6vsVjdbBLiCePwQmOiL6cGwfZUGe8/FkrKlF24HxrWDpgy6+Q7MTb8hk0/ImD8+yH7dZb/4lAh40Upmh22zKvGNYU1ppXFHJc5hJIbJiO8JYaM7e57kH/8Clqq2SQq7m09kKPr+y64mOHrkWmdJc6AKoguxDMO8Dlb6Owi2UDtcheyAFFE6pF6vNjmdQgzu5z+JdO8jWCwtGeBENBUcjMtsSENpeQn7OgjAY1MpvQdNBXx5beyd16Rss1iwGIKbQsrcBxReEoN05aqy+eCw==</banksignature></header><body><accountInformation><ac_desc>CTY TNHH DV NEN DI DONG VIET NAM</ac_desc><cust_ac_no>801014987666666</cust_ac_no><ccy>VND</ccy><open_date>02/03/2015</open_date><lcy_curr_balance>171562704</lcy_curr_balance><avl_balanace>171562704</avl_balanace><record_stat>O</record_stat></accountInformation></body></response>

			}
			catch(Exception e)
			{		
			}
		}
		return kq;
	}
	
	
//	public final static String URL = "jdbc:mysql://localhost:3306/mobilebank_db?useUnicode=yes&characterEncoding=UTF-8";
//	public final static String USER = "root";
//	public final static String PASS = "root";
//	public static Connection getConnect(String url, String user, String pass) {
//		try {
//			Connection connection = null;
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(url, user, pass);
//			return connection;
//		} catch (Exception e) {
//			System.out.println("Exception" + e.toString());
//			return null;
//		}
//	}
}
