package vn.vimass.utils;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Data {

	
	
	public final static String LOG_FOLDER_PATH = "D://HDDT_LOG/";
	public final static String FOLDER_LOG = LOG_FOLDER_PATH + "LOG/";
	
	public final static String USER = "DEMO.SERVICE";
	public final static String PASSWORD = "123";
	
	public final static String FOLDER_XML_HOADON_CMC = "D://XML_CMC/";
	
	public final static String KEY_MD5 = "jslaowtwBavhhmx84rgfsvkd";

	public static void ghiLogRequest(String sNoiDung) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd_MM_yyyy");

		String giophutgiay = sdf.format(cal.getTime());
		String tenFile = "HDDT_" + sdfDate.format(cal.getTime()) + ".txt";
		FileManager.writeFile(FOLDER_LOG + tenFile, giophutgiay + "\t"
				+ sNoiDung + "\n", true);
	}
}
