package vn.vimass.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;












import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Common {
	public static void delay(int minisec)
	{
		try
		{
			Thread.sleep(minisec);
		}
		catch(Exception e)
		{
			
		}
	}
	public static String getTimeyyyyMMDDCachHHmmss(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd HHmmss");
		return dt1.format(timeShow);
	}


	public static String getDataTheoFomatTBTTVer2(String data, String noData, int maxLength) 
	{
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
	
	
	public static String xuLyNoiDungGiaoDichKhongencode(String input)
	{
		try
		{
			input = input.replace(",", " ")

					.replace(">", " ")
					.replace("<", " ")
					.replace("_", " ")
					.replace("-", " ")
					.replace("*", " ")
					.replace("#", " ")
					.replace("$", " ")
					.replace("%", " ")
					.replace("^", " ")
					.replace("&", " ")
					.replace("/", " ")
					.replace("(", " ")
					.replace(")", " ")
					.replace("!", " ")
					.replace("`", " ")
					.replace("~", " ")
					.replace(":", " ")
					.replace("×", " ")
					.replace("=", " ")
					.replace("\r\n", " ")
					.replace("\n", " ")
					.replace("\t", " ")
					.trim()
					;
			input = FileManager.boDauTiengViet(input);
		}
		catch(Exception e)
		{
			
		}
		return input;
	}
	
	public static String boDauTiengViet(String sDuLieu)
	{
		if (sDuLieu != null)
		{
			sDuLieu = sDuLieu.replaceAll("á|à|ả|ã|ạ|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ|Á|À|Ả|Ã|Ạ|Ă|Ắ|Ằ|Ẳ|Ẵ|Ặ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ", "a");
			sDuLieu = sDuLieu.replaceAll("đ|Đ", "d");
			sDuLieu = sDuLieu.replaceAll("é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ|É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ", "e");
			sDuLieu = sDuLieu.replaceAll("í|ì|ỉ|ĩ|ị|Í|Ì|Ỉ|Ĩ|Ị", "i");
			sDuLieu = sDuLieu.replaceAll("ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ|Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ", "o");
			sDuLieu = sDuLieu.replaceAll("ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự|Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự", "u");
			sDuLieu = sDuLieu.replaceAll("ý|ỳ|ỷ|ỹ|ỵ|Ý|Ỳ|Ỷ|Ỹ|Ỵ", "y");
		}
		
		return sDuLieu;
	}
	
	public static String enCodeData(String input)
	{
		try
		{
			input = URLEncoder.encode(input, "utf-8");					
		}
		catch(Exception e)
		{
		}
		return input;
	}
	
	public static String deCodeData(String input)
	{
		try
		{
			input = URLDecoder.decode(input, "utf-8");					
		}
		catch(Exception e)
		{
		}
		return input;
	}
	
	public static String generateSessionKeyLowestCase(int length) {
		String alphabet = new String(
				"0123456789abcdefghijklmnopqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();
		Random r = new Random(); // 11

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // 13

		return result;
	}
	
	public static String getTimeCoGach(long time) {
		Date timeShow = new Date(time);
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dt1.format(timeShow);
	}
	

	
	public static void addTextWatermarkQRDown(final String text,
			final File sourceImageFile, final File destImageFile) {

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
				try {
					BufferedImage sourceImage = ImageIO.read(sourceImageFile);
					Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

					// initializes necessary graphic properties
					AlphaComposite alphaChannel = AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.9f);
					g2d.setComposite(alphaChannel);
					g2d.setColor(Color.BLUE);
					g2d.setStroke(new BasicStroke(10));
					g2d.setFont(new Font("Arial", Font.BOLD, 90));
					FontMetrics fontMetrics = g2d.getFontMetrics();
					Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

					// calculates the coordinate where the String is painted
					int centerX = (sourceImage.getWidth() - (int) rect
							.getWidth()) / 2;
					int centerY = sourceImage.getHeight() - 30;

					// paints the textual watermark
					g2d.drawString(text, centerX, centerY);

					ImageIO.write(sourceImage, "png", destImageFile);
					g2d.dispose();

				} catch (IOException ex) {
					System.err.println(ex);
				}
//			}
//		}).start();

	}
	

	public static void addTextWatermarkQRTren(String text,
			final File sourceImageFile, final File destImageFile) {

		
		int centerYLength15 = 88;
		int fontLength15 = 90;
		String fontName = Font.SANS_SERIF;
//		String fontName = "Arial";
		
		
		
				try {
					
					if(text != null)
					{
						if(text.length() > 35)
						{
							text = text.substring(0,35);
						}
						if(text.length() > 18 && text.length() <= 26)
						{
							centerYLength15 = 80;
							fontLength15 = 53;								
						}
						if(text.length() > 26 && text.length() <= 35)
						{
							centerYLength15 = 82;
							fontLength15 = 35;								
						}
					}
					
					BufferedImage sourceImage = ImageIO.read(sourceImageFile);
					Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

					// initializes necessary graphic properties
					AlphaComposite alphaChannel = AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.9f);
					g2d.setComposite(alphaChannel);
					g2d.setColor(Color.BLUE);
					g2d.setStroke(new BasicStroke(10));
					g2d.setFont(new Font(fontName, Font.BOLD, fontLength15));
					FontMetrics fontMetrics = g2d.getFontMetrics();
					Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

					// calculates the coordinate where the String is painted
					int centerX = (sourceImage.getWidth() - (int) rect
							.getWidth()) / 2;
					int centerY = centerYLength15;

					// paints the textual watermark
					g2d.drawString(text, centerX, centerY);

					ImageIO.write(sourceImage, "png", destImageFile);
					g2d.dispose();

				} catch (IOException ex) {
					System.err.println(ex);
				}

	}
	
	
	

	public static void addTextWatermark90Phai(String text,
			final File sourceImageFile, final File destImageFile) {

		int centerYLength15 = 50;
		int fontLength15 = 90;
		

		String fontName = Font.SANS_SERIF;
//		String fontName = "Arial";
		
				try {
					
					if(text != null)
					{
						if(text.length() > 35)
						{
							text = text.substring(0,35);
						}
						if(text.length() > 18 && text.length() <= 26)
						{
							centerYLength15 = 49;
							fontLength15 = 53;								
						}
						if(text.length() > 26 && text.length() <= 35)
						{
							centerYLength15 = 45;
							fontLength15 = 35;								
						}
					}
					

		
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

			// initializes necessary graphic properties
			AlphaComposite alphaChannel = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.9f);
			g2d.setComposite(alphaChannel);
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(10));
			g2d.setFont(new Font(fontName, Font.BOLD, fontLength15));
			
			
			AffineTransform at = new AffineTransform();
			at.rotate(Math.PI / 2);
			 
			g2d.setTransform(at);
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

			// calculates the coordinate where the String is painted
			int centerX = (sourceImage.getWidth() - (int) rect
					.getWidth()) / 2;
			int centerY = -1110;

			// paints the textual watermark
			g2d.drawString(text, centerX, centerY);

			ImageIO.write(sourceImage, "png", destImageFile);
			g2d.dispose();

		} catch (IOException ex) {
			System.err.println(ex);
		}

	}


	public static void addTextWatermark90Trai(String text,
			final File sourceImageFile, final File destImageFile) {


		int centerYLength15 = 50;
		int fontLength15 = 90;
		

		String fontName = Font.SANS_SERIF;
//		String fontName = "Arial";
		
				try {
					
					if(text != null)
					{
						if(text.length() > 35)
						{
							text = text.substring(0,35);
						}
						if(text.length() > 18 && text.length() <= 26)
						{
							centerYLength15 = 49;
							fontLength15 = 53;								
						}
						if(text.length() > 26 && text.length() <= 35)
						{
							centerYLength15 = 45;
							fontLength15 = 35;								
						}
					}
					
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

			// initializes necessary graphic properties
			AlphaComposite alphaChannel = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.9f);
			g2d.setComposite(alphaChannel);
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(10));
			g2d.setFont(new Font(fontName, Font.BOLD, fontLength15));
			
			
			AffineTransform at = new AffineTransform();
			at.rotate(- Math.PI / 2);
			 
			g2d.setTransform(at);
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

			// calculates the coordinate where the String is painted
//			int centerX = -1 * ((int) rect.getHeight() * 11);
			
			
			
			
			int centerX =((sourceImage.getHeight() - (int) rect.getWidth()) / 2) - 1200;
			
			System.out.println("sourceImage.getHeight() left:" + sourceImage.getHeight());
			System.out.println("rect.getHeight() left:" + rect.getHeight());
			System.out.println("rect.getWidth() left:" + rect.getWidth());
			
			System.out.println("centerX left:" + centerX);
			
			int centerY = 82;

			// paints the textual watermark
			g2d.drawString(text, centerX, centerY);

			ImageIO.write(sourceImage, "png", destImageFile);
			g2d.dispose();

		} catch (IOException ex) {
			System.err.println(ex);
		}

	}
	

	public static void createQRCode(String qrCodeData, String filePath)
			throws WriterException, IOException {
		
		int qrCodeheight = 1200;
		int qrCodewidth = 1200;
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		
		
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String getBase64Image(String filePathFinal) {

		String kq = "";
		
		try
		{

			FileInputStream fin = new FileInputStream(new File(filePathFinal));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int count = 0;

			while ((count = fin.read(buffer)) != -1) {
				baos.write(buffer, 0, count);
			}

			byte[] fileContent = baos.toByteArray();

			// all chars in encoded are guaranteed to be 7-bit ASCII
		
			kq = encodeImage(fileContent);
			
			
	
		}
		catch(Exception e)
		{
			
		}
				return kq;
		
	}

	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	
	

	public static void addImageWatermark(String watermarkPath, String type, String sourcePath, String destinationPath) throws IOException {
		
		
		File source = new File(sourcePath);
		File watermark = new File(watermarkPath);
		File destination = new File(destinationPath);
		
        BufferedImage image = ImageIO.read(source);
        
        BufferedImage imageWatermarkChuan = ImageIO.read(watermark);
        
        
        int chiSoResize = imageWatermarkChuan.getHeight() * 180 / 456;
        
        
        
        BufferedImage overlay = resize(ImageIO.read(watermark), chiSoResize, chiSoResize);
        // determine image type and handle correct transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // initializes necessary graphic properties
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        w.setComposite(alphaChannel);

        // calculates the coordinate where the String is painted
        int centerX = image.getWidth() / 2 - chiSoResize/2;
        int centerY = image.getHeight() / 2 - chiSoResize/2;
        

        // add text watermark to the image
        w.drawImage(overlay, centerX, centerY, null);
        ImageIO.write(watermarked, type, destination);
        w.dispose();
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
