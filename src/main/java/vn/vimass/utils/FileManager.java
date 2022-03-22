package vn.vimass.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class FileManager {


	public static String readFile(String path) {
		String text = "";
		int read, N = 1024 * 1024;
		char[] buffer = new char[N];

		try {
			File f = new File(path);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			while (true) {
				read = br.read(buffer, 0, N);
				text += new String(buffer, 0, read);

				if (read < N) {
					break;
				}
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return text;
	}
	
	public static ArrayList<String> readFileDoiSoat(String path) {
		String text = "";
		ArrayList<String> listLine = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Data.ghiLogRequest("----------readFileDoiSoat: doc file doi soat " + path);
		try {
			File f = new File(path);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			while ((text = br.readLine()) != null) {				
				builder.append(text);
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			Data.ghiLogRequest("----------readFileDoiSoat: Exception " + ex.getMessage());
		}
		
		 Document doc = convertStringToXMLDocument( builder.toString() );
		 
		 String duLieuDoiSoat = "";
		 if(doc!=null)
		 {
			 Data.ghiLogRequest("----------readFileDoiSoat: convert ok");
			 try {
				 if(doc.getElementsByTagName("ERROR_CODE").item(0).getFirstChild().getNodeValue().equals("000"))
				 {	
					 String fileContentTagData = doc.getElementsByTagName("FILE_CONTENT").item(0).getFirstChild().getNodeValue();
					 Data.ghiLogRequest("----------fileContentTagData: " + fileContentTagData.length());
					  byte[] dulieuBase64 = Base64.decodeBase64(fileContentTagData);
					  duLieuDoiSoat = new String(dulieuBase64);
					  String[] temp = duLieuDoiSoat.split("\n");
					  for(String str : temp)
					  {
						  listLine.add(str);
						 // System.out.println("----------duLieuDoiSoat: " + str);
					  }
					  Data.ghiLogRequest("----------readFileDoiSoat: loc du lieu doi soat thanh cong");
				 }
				 else
				 {
					 Data.ghiLogRequest("----------readFileDoiSoat: ERROR_CODE " + doc.getElementsByTagName("ERROR_CODE").item(0).getFirstChild().getNodeValue());
					 duLieuDoiSoat = "ERROR_DESC:"+ doc.getElementsByTagName("ERROR_DESC").item(0).getFirstChild().getNodeValue();
					 listLine.add(duLieuDoiSoat);
				 }
			 }catch (Exception e) {
				 Data.ghiLogRequest("----------readFileDoiSoat:" + e.getMessage());
			}
			 
		 }
		 else
		 {
			 Data.ghiLogRequest("----------readFileDoiSoat: file doi soat loi!");
		 }			
		
		return listLine;
	}
	
	private static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            Data.ghiLogRequest("----------convertStringToXMLDocument: " + e.getMessage());
        }
        return null;
    }

	public static void writeFile(String path, String content, boolean append) {
		BufferedWriter writer = null;
		try {
			File f = new File(path);
			writer = new BufferedWriter(new FileWriter(f, append));
//			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, append), "UTF-8"));
			writer.write(content);
		
		} catch (Exception e) {
			Data.ghiLogRequest("----------writeFile XML: " + e.getMessage());

		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}

	}

	public static byte[] getBinaryDataFromFile(String path) {
		File file = new File(path);
		FileInputStream fin = null;
		byte fileContent[] = null;
		try {
			fin = new FileInputStream(file);
			fileContent = new byte[(int) file.length()];
			fin.read(fileContent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
		return fileContent;
	}

	public static void xoaToanBoFileCuaThuMuc(String input) {
		try
		{
			
			File dir = new File(input);
			FileFilter fileFilter = new WildcardFileFilter("*");
			File[] files = dir.listFiles(fileFilter);
			for(int i = 0; i < files.length; i++)
			{
				try
				{
					files[i].delete();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}

	public static int getTotalFileInFolder(String input) {
		try
		{
			File dir = new File(input);
			FileFilter fileFilter = new WildcardFileFilter("*.*");
			File[] files = dir.listFiles(fileFilter);
			return files.length;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	


	public static boolean checkFileExist(String input) {
		try
		{
			File f = new File(input);
			if(f.exists())
			{
				return true;
			}
		}
		catch(Exception e)
		{
			
		}
		return false;
	}
	public static String boDauTiengViet(String sDuLieu) {
		if (sDuLieu != null) {
			sDuLieu = sDuLieu
					.replaceAll(
							"á|à|ả|ã|ạ|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ|Á|À|Ả|Ã|Ạ|Ă|Ắ|Ằ|Ẳ|Ẵ|Ặ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ",
							"a");
			sDuLieu = sDuLieu.replaceAll("đ|Đ", "d");
			sDuLieu = sDuLieu.replaceAll(
					"é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ|É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ", "e");
			sDuLieu = sDuLieu.replaceAll("í|ì|ỉ|ĩ|ị|Í|Ì|Ỉ|Ĩ|Ị", "i");
			sDuLieu = sDuLieu
					.replaceAll(
							"ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ|Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ",
							"o");
			sDuLieu = sDuLieu.replaceAll(
					"ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự|Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự", "u");
			sDuLieu = sDuLieu.replaceAll("ý|ỳ|ỷ|ỹ|ỵ|Ý|Ỳ|Ỷ|Ỹ|Ỵ", "y");
		}

		return sDuLieu;
	}
	

	public static void xoaFile(String input) {
		try
		{
			File file = new File(input);
			file.delete();
		}
		catch(Exception e)
		{
			
		}
	}
	

	public static String enCodeURL(String input)
	{
		try
		{
			return URLEncoder.encode(input, "utf-8");
		}
		catch(Exception e)
		{
			
		}
		return input;
	}
	
	public static String deCodeURL(String input)
	{
		try
		{
			return URLDecoder.decode(input, "utf-8");
		}
		catch(Exception e)
		{
			
		}
		return input;
	}

}
