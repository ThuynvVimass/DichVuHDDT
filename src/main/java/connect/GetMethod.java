package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.client.utils.URLEncodedUtils;

import vn.vimass.utils.Data;

public class GetMethod {
	public static String getContent(String url) {
		try {
			Data.ghiLogRequest("GetMethod:" + url);
			URLEncodedUtils.parse(url, Charset.forName("UTF-8"));
			Client client = ClientBuilder.newClient();
			WebTarget cashOutTarget = client.target(url);
			Response response = cashOutTarget.request().get();
			String result = response.readEntity(String.class);
			Data.ghiLogRequest("GetMethod:" + result);
			return result;
		} catch (Exception e) {
			Data.ghiLogRequest("GetMethod: exception:" + e.getMessage());
		}
		return "";
	}

	

	public static String getContentURL(String urlInput) {
		URL url;

		try {

			// get URL content
			url = new URL(urlInput);
			URLConnection conn = url.openConnection();

			// conn.setRequestProperty("Cookie",
			// "PHPSESSID=r07k50tjvaha68d54en3p0k8q5; data_12345=%7B%221%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%222%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%223%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%224%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%225%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%7D; data_23450=%7B%222%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%223%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%224%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%225%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%220%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%7D; ver_34500=2; data_34500=%7B%223%22%3A%7B%22has%22%3A1%2C%22last%22%3A1%7D%2C%224%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%225%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%220%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%7D; data_62345=%7B%226%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%222%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%223%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%224%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%2C%225%22%3A%7B%22has%22%3A0%2C%22last%22%3A0%7D%7D; RCTS=4; hotel_recent=eyIxMDUwIjoxMDUwfQ%3D%3D; _ga=GA1.2.1834732838.1418025244; _gat=1");

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));

			String inputLine;

			StringBuilder sbinputLine = new StringBuilder();
			sbinputLine.append("");

			while ((inputLine = br.readLine()) != null) {
				sbinputLine.append(inputLine + "\n");
			}
			br.close();

			// System.out.println("Done");

			// ===========ghi file=====================================
			// String fileName = "D://testLayDuLieuWeb.html";
			// File file = new File(fileName);
			//
			// if (!file.exists()) {
			// file.createNewFile();
			// }
			// FileManager.ghiFile(fileName, sbinputLine.toString(), false);
			// =========================================================
			return sbinputLine.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
	
}
