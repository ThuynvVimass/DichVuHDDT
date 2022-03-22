package CMC;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import CMC.object.objectLayTTinMSKHHDon.ObjectLayTTinMSKHHDon;
import org.apache.axis.utils.BeanUtils;
import org.apache.commons.codec.binary.Base64;

import org.apache.zookeeper.server.quorum.SendAckRequestProcessor;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.jsoup.Jsoup;

import vn.vimass.csdl.object.ErrorCode;
import vn.vimass.csdl.object.ObjectMessageResult;

import vn.vimass.utils.Common;
import vn.vimass.utils.Data;
import vn.vimass.utils.FileManager;

import VimassLib.util.VimassCommon;
import VimassLib.util.VimassData;
import connect.GetMethod;
import connect.PostBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.webservices.DLHDon;
import com.microsoft.webservices.DSCKS;
import com.microsoft.webservices.DefaultServiceLocator;
import com.microsoft.webservices.DefaultServiceSoap;
import com.microsoft.webservices.DefaultServiceSoapProxy;
import com.microsoft.webservices.HDon;
import com.microsoft.webservices.HHDVu;
import com.microsoft.webservices.LTSuat;
import com.microsoft.webservices.NBan;
import com.microsoft.webservices.NDHDon;
import com.microsoft.webservices.NMua;
import com.microsoft.webservices.Signature;
import com.microsoft.webservices.TTCQT;
import com.microsoft.webservices.TTChung;
import com.microsoft.webservices.TTin;
import com.microsoft.webservices.TToan;

import CMC.Table.TableDonVi;
import CMC.Table.TableHoaDon;
import CMC.object.ObjectHangHoaDichVu;
import CMC.object.ObjectHoaDon;
import CMC.object.ObjectKetQuaHoaDon;
import CMC.object.ObjectThongTinHoaDon;
import CMC.object.ObjectTraCuuThongTinDoanhNghiep;
import CMC.object.ThongTin;
import CMC.object.ThongTinDonVi;
import CMC.object.objectApiKyHDon.ObjectKeyHoaDon;


public class CMCFunc {

	private static final String TAG = "CMCFunc";
	
	public static String lapVaKyDuyetHoaDon(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;
		
		Data.ghiLogRequest("ApiPHanhHDon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			
			ObjectThongTinHoaDon objHD = new Gson().fromJson(input, ObjectThongTinHoaDon.class);
			
			ThongTinDonVi nBan = TableDonVi.getThongTinDonVi(objHD.MSTNBan);
			objHD.NBan = nBan;
			
			ThongTin nMua = getThongTinDonViBangMST2(objHD.MSTNMua);	
			objHD.NMua = mapToThongTinDonVi (nMua);

			HDon Hdon[] = taoDuLieu_HDon(objHD);
			Data.ghiLogRequest("taoDuLieu_HDon: " + new Gson().toJson(Hdon[0]));
			String ketQuaPhatHanhHoaDon = def.apiPHanhHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, Hdon);
			Data.ghiLogRequest("------------apiPHanhHDon---------------- ");
			ObjectKetQuaHoaDon ketQuaPhatHanh = phanTichKetQua(ketQuaPhatHanhHoaDon,"apiPHanhHDon");
			
			if(ketQuaPhatHanh!=null)
			{				
				if(ketQuaPhatHanh.errorCode.equals("200"))
				{															
					String kyHoaDon_response = def.apiKyHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, ketQuaPhatHanh.idHoaDon);
					Data.ghiLogRequest("------------apiKyHDon---------------- ");
					ObjectKetQuaHoaDon ketQuaKyHoaDon = phanTichKetQua(kyHoaDon_response,"apiKyHDon");
					if(ketQuaKyHoaDon.errorCode.equals("200"))
					{																				
						Data.ghiLogRequest("apiKyHDon: " +ketQuaKyHoaDon.errorCode + " " + ketQuaKyHoaDon.thongTinThem);
						String xmlDuLieuSauKy = vn.vimass.utils.VimassCommon.getBase64(ketQuaKyHoaDon.thongTinThem);
						// Luu du lieu
						String XML_FILENAME = Data.FOLDER_XML_HOADON_CMC + "/" + ketQuaPhatHanh.mauSoHoaDon.replace("/", ".") + "_"+ketQuaPhatHanh.SoHoaDon;
						Data.ghiLogRequest("XML_FILENAME: " +XML_FILENAME);
						FileManager.writeFile(XML_FILENAME + ".xml", xmlDuLieuSauKy, false);
						vn.vimass.utils.VimassCommon.delay(500);
						if(FileManager.checkFileExist(XML_FILENAME + ".xml"))
						{
							Data.ghiLogRequest("apiInHoadon: Luu file XML " + XML_FILENAME + " thanh cong!");
							String inHoaDon_response = def.apiInHoadon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, ketQuaPhatHanh.idHoaDon);
							Data.ghiLogRequest("------------apiInHoadon---------------- ");
							ObjectKetQuaHoaDon ketQuaInHoaDon = phanTichKetQua(inHoaDon_response,"apiInHoadon");
							if(ketQuaInHoaDon.errorCode.equals("200"))
							{
								Data.ghiLogRequest("apiInHoadon:kq " +ketQuaInHoaDon.errorCode + " " + ketQuaInHoaDon.thongTinThem);
								// Luu du lieu hoa don	
								String soHoaDonInput = ketQuaPhatHanh.SoHoaDon;
								String maHoaDonInput = ketQuaPhatHanh.maTraCuuHoaDon;								
								String maSoDuThuongInput = randomInt()+ketQuaPhatHanh.SoHoaDon;
								String keyHoaDon = ketQuaPhatHanh.idHoaDon;
								String coQuanThueInput = objHD.NBan.coQuanThue;
								String linkPDFInput = ketQuaInHoaDon.thongTinThem;
								String linkXMLInput = XML_FILENAME;
								String kq = TableHoaDon.taoDuLieu(objHD,keyHoaDon, soHoaDonInput, maHoaDonInput, maSoDuThuongInput, coQuanThueInput, linkPDFInput);
								if(kq!="")
								{
									ketQua.result = ketQuaPhatHanh;
									ketQua.msgCode = ErrorCode.SUCCESS;
									ketQua.msgContent = ErrorCode.MES_SUCCESS;
									guiEmailHoaDon(objHD.NBan.tenDoanhNghiep,objHD.NMua.tenDoanhNghiep, soHoaDonInput, maHoaDonInput, maSoDuThuongInput, linkPDFInput, linkXMLInput,objHD.EmailNMua);
								}
							}
							else {
								ketQua.result = ketQuaPhatHanh.thongTinThem;
								Data.ghiLogRequest("ketQuaInHoaDon: code!=200 " +ketQuaInHoaDon.errorCode + " " + ketQuaInHoaDon.thongTinThem);
							}
						}						
						else {
							ketQua.result = "apiInHoadon: Luu file XML Loi ";
							Data.ghiLogRequest("apiInHoadon: Luu file XML Loi ");
						}
					}
					else {
						ketQua.result = ketQuaPhatHanh.thongTinThem;
						Data.ghiLogRequest("ketQuaKyHoaDon:code!=200 " +ketQuaKyHoaDon.errorCode + " " + ketQuaKyHoaDon.thongTinThem);
					}
				}
				else {
					ketQuaPhatHanhHoaDon = ketQuaPhatHanh.thongTinThem;
					ketQua.result = ketQuaPhatHanh.thongTinThem;
					Data.ghiLogRequest("ApiPHanhHDon:code!=200 " +ketQuaPhatHanh.errorCode + " " + ketQuaPhatHanh.thongTinThem);
				}
				
			}
			else {
				ketQuaPhatHanhHoaDon = "Loi";
				Data.ghiLogRequest("ApiPHanhHDon: Loi");	
			}
		} catch (Exception e) {
			
			Data.ghiLogRequest("ApiPHanhHDon: Exception " + e.getMessage());
		}		
		return new Gson().toJson(ketQua);
	}	
	

	public static String lapHoaDon(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;
		
		Data.ghiLogRequest("ApiPHanhHDon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			ObjectThongTinHoaDon objHD = new Gson().fromJson(input, ObjectThongTinHoaDon.class);
			
			ThongTinDonVi nBan = TableDonVi.getThongTinDonVi(objHD.MSTNBan);
			objHD.NBan = nBan;
			
			ThongTin nMua = getThongTinDonViBangMST2(objHD.MSTNMua);	
			objHD.NMua = mapToThongTinDonVi (nMua);

			HDon Hdon[] = taoDuLieu_HDon(objHD);
			Data.ghiLogRequest("taoDuLieu_HDon: " + new Gson().toJson(Hdon[0]));
			String ketQuaPhatHanhHoaDon = def.apiPHanhHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, Hdon);
			Data.ghiLogRequest("------------apiPHanhHDon---------------- ");
			ObjectKetQuaHoaDon ketQuaPhatHanh = phanTichKetQua(ketQuaPhatHanhHoaDon,"apiPHanhHDon");

			if(ketQuaPhatHanh.errorCode.equals("200"))
			{
				ketQua.msgCode = ErrorCode.SUCCESS;
				ketQua.msgContent = ErrorCode.MES_SUCCESS;
				ketQua.result = ketQuaPhatHanh;
			} else 
			{
				ketQua.result = ketQuaPhatHanh.thongTinThem;
			}									
			
		}
		catch (Exception e) {
			
			Data.ghiLogRequest("ApiPHanhHDon: Exception " + e.getMessage());
		}		
		return new Gson().toJson(ketQua);
	}	
	
	
	public static String kyDuyetHoaDon(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;
		
		Data.ghiLogRequest("ApiKyHDon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			ObjectKeyHoaDon objKeyHD = new Gson().fromJson(input, ObjectKeyHoaDon.class);
			
			String ketQuaPhatHanhHoaDon = def.apiKyHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, objKeyHD.b_KeyHDon);
			Data.ghiLogRequest("------------apiKyHDon---------------- ");
			ObjectKetQuaHoaDon ketQuaPhanTich = phanTichKetQua(ketQuaPhatHanhHoaDon,"apiKyHDon");

			if(ketQuaPhanTich.errorCode.equals("200"))
			{
				ketQua.msgCode = ErrorCode.SUCCESS;
				ketQua.msgContent = ErrorCode.MES_SUCCESS;
				ketQua.result = ketQuaPhanTich;
				
				Data.ghiLogRequest("apiKyHDon: " +ketQuaPhanTich.errorCode + " " + ketQuaPhanTich.thongTinThem);
				String xmlDuLieuSauKy = vn.vimass.utils.VimassCommon.getBase64(ketQuaPhanTich.thongTinThem);
				// Luu du lieu
				String XML_FILENAME = Data.FOLDER_XML_HOADON_CMC + "/" + ketQuaPhanTich.mauSoHoaDon.replace("/", ".") + "_"+ketQuaPhanTich.SoHoaDon;
				Data.ghiLogRequest("XML_FILENAME: " +XML_FILENAME);
				FileManager.writeFile(XML_FILENAME + ".xml", xmlDuLieuSauKy, false);
				vn.vimass.utils.VimassCommon.delay(500);
				if(FileManager.checkFileExist(XML_FILENAME + ".xml"))
				{
					Data.ghiLogRequest("apiInHoadon: Luu file XML " + XML_FILENAME + " thanh cong!");
				}
				else {
					ketQua.result = "apiInHoadon: Luu file XML Loi ";
					Data.ghiLogRequest("apiInHoadon: Luu file XML Loi ");
				}
			} 
			else {
				ketQua.result = ketQuaPhanTich.thongTinThem;
				Data.ghiLogRequest("ketQuaKyHoaDon:code!=200 " +ketQuaPhanTich.errorCode + " " + ketQuaPhanTich.thongTinThem);
			}				
		}
		catch (Exception e) {
			
			Data.ghiLogRequest("ApiKyHDon: Exception " + e.getMessage());
		}		
		return new Gson().toJson(ketQua);
	}
	
	
	public static String inHoaDon(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;
		
		Data.ghiLogRequest("ApiInHoadon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			ObjectKeyHoaDon objKeyHD = new Gson().fromJson(input, ObjectKeyHoaDon.class);
			
			String inHoaDon_response = def.apiInHoadon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, objKeyHD.b_KeyHDon);
			Data.ghiLogRequest("------------apiInHoadon---------------- ");
			ObjectKetQuaHoaDon ketQuaInHoaDon = phanTichKetQua(inHoaDon_response,"apiInHoadon");
			if(ketQuaInHoaDon.errorCode.equals("200"))
			{
				Data.ghiLogRequest("apiInHoadon:kq " +ketQuaInHoaDon.errorCode + " " + ketQuaInHoaDon.thongTinThem);
				// Luu du lieu hoa don	
				String soHoaDonInput = ketQuaInHoaDon.SoHoaDon;
				String maHoaDonInput = ketQuaInHoaDon.maTraCuuHoaDon;								
				String maSoDuThuongInput = randomInt()+ketQuaInHoaDon.SoHoaDon;
				String keyHoaDon = ketQuaInHoaDon.idHoaDon;
//				String coQuanThueInput = objHD.NBan.coQuanThue;
				String linkPDFInput = ketQuaInHoaDon.thongTinThem;
//				String linkXMLInput = XML_FILENAME;
//				String kq = TableHoaDon.taoDuLieu(objHD,keyHoaDon, soHoaDonInput, maHoaDonInput, maSoDuThuongInput, coQuanThueInput, linkPDFInput);
//				if(kq!="")
				{
					ketQua.result = ketQuaInHoaDon;
					ketQua.msgCode = ErrorCode.SUCCESS;
					ketQua.msgContent = ErrorCode.MES_SUCCESS;
//					guiEmailHoaDon(objHD.NBan.tenDoanhNghiep,objHD.NMua.tenDoanhNghiep, soHoaDonInput, maHoaDonInput, maSoDuThuongInput, linkPDFInput, linkXMLInput,objHD.EmailNMua);
				}
			}
			else {
				ketQua.result = ketQuaInHoaDon.thongTinThem;
				Data.ghiLogRequest("ketQuaInHoaDon: code!=200 " +ketQuaInHoaDon.errorCode + " " + ketQuaInHoaDon.thongTinThem);
			}								
			
		}
		catch (Exception e) {
			
			Data.ghiLogRequest("ApiPHanhHDon: Exception " + e.getMessage());
		}		
		return new Gson().toJson(ketQua);
	}
	
	public static String huyHoaDon(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;

		Data.ghiLogRequest("ApiHuyHDon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			ObjectKeyHoaDon objKeyHD = new Gson().fromJson(input, ObjectKeyHoaDon.class);
			
			String ketQuaHuyHoaDon = def.apiHuyHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, objKeyHD.b_KeyHDon);
			Data.ghiLogRequest("------------apiHuyHDon---------------- ");
			ObjectKetQuaHoaDon ketQuaPhanTich = phanTichKetQua(ketQuaHuyHoaDon,"apiHuyHDon");

			if(ketQuaPhanTich.errorCode.equals("200"))
			{
				ketQua.msgCode = ErrorCode.SUCCESS;
				ketQua.msgContent = ErrorCode.MES_SUCCESS;
				ketQua.result = ketQuaPhanTich.thongTinThem;
			} 
			else {
				ketQua.result = ketQuaPhanTich.thongTinThem;
				Data.ghiLogRequest("ketQuaHuyHoaDon:code!=200 " +ketQuaPhanTich.errorCode + " " + ketQuaPhanTich.thongTinThem);
			}				
		}
		catch (Exception e) {
			
			Data.ghiLogRequest("ApiHuyHDon: Exception " + e.getMessage());
		}		
		return new Gson().toJson(ketQua);
	}

	public static String layDaiHoaDonPhatHanhSuDung(String input)
	{
		ObjectMessageResult ketQua = new ObjectMessageResult();
		ketQua.msgCode = ErrorCode.FALSE;
		ketQua.msgContent = ErrorCode.MES_FALSE;

		Data.ghiLogRequest("ApiLayTTinMSKHHDon: input " + input);
		DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
		try {
			ObjectLayTTinMSKHHDon objKeyHD = new Gson().fromJson(input, ObjectLayTTinMSKHHDon.class);
			String b_loai = objKeyHD.loaiHoaDon;
			double b_ngayph = 20220321;
//			objKeyHD.ngayPhatHanh;

			String ketQuaLayTTinMSKHHDon = def.apiLayTTinMSKHHDon(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, b_loai, b_ngayph);
			Data.ghiLogRequest("------------apiLayTTinMSKHHDon---------------- ");
			ObjectKetQuaHoaDon ketQuaPhanTich = phanTichKetQua(ketQuaLayTTinMSKHHDon,"apiLayTTinMSKHHDon");

			if(ketQuaPhanTich.errorCode.equals("200"))
			{
				ketQua.msgCode = ErrorCode.SUCCESS;
				ketQua.msgContent = ErrorCode.MES_SUCCESS;
				ketQua.result = ketQuaPhanTich.thongTinThem;
			}
			else {
				ketQua.result = ketQuaPhanTich.thongTinThem;
				Data.ghiLogRequest("ketQuaHuyHoaDon:code!=200 " +ketQuaPhanTich.errorCode + " " + ketQuaPhanTich.thongTinThem);
			}
		}
		catch (Exception e) {

			Data.ghiLogRequest("ApiHuyHDon: Exception " + e.getMessage());
		}
		return new Gson().toJson(ketQua);
	}

	private static ThongTinDonVi mapToThongTinDonVi(ThongTin thongTin) {
		ThongTinDonVi nMua = new ThongTinDonVi();
		
		nMua.maSoThue = thongTin.ma_so_thue;
		nMua.tenDoanhNghiep = thongTin.ten_cty;
		nMua.diaChi = thongTin.ten_cty;
		nMua.coQuanThue = thongTin.cqthuecap_tinh;		
		nMua.coQuanThueCapCuc = thongTin.cqthue_ql;

		return nMua;
	}


	private static String guiEmailHoaDon(String NBan,String NMua,String soHoaDonInput,String maHoaDonInput,String maSoDuThuongInput,String linkPDFInput,String linkXMLInput,String emailKH)
	{
		String kq = "";
		try {
//			String url = "http://210.245.8.17:1076/TraCuuQRThanhToan/services/tracuuqr/ipnHoaDon" 
//					+ "?NBan=" + NBan
//					+ "?NMua=" + NMua
//					+ "&soHoaDon=" + soHoaDonInput
//					+ "&maHoaDon="+maHoaDonInput
//					+ "&maSoDuThuong="+maSoDuThuongInput
//					+ "&linkPDF="+linkPDFInput
//					+ "&linkXML=" + linkXMLInput
//					+ "&emailKH=" + emailKH
//					+ "&checkSum=" + vn.vimass.utils.VimassCommon.bamMD5(Data.KEY_MD5+soHoaDonInput+maSoDuThuongInput);
//			kq = GetMethod.getContent(url);
			
			ObjectHoaDon objPost = new ObjectHoaDon();
			objPost.checkSum = vn.vimass.utils.VimassCommon.bamMD5(Data.KEY_MD5+soHoaDonInput+maSoDuThuongInput);
			objPost.emailKH = emailKH;
			objPost.linkPDF = linkPDFInput;
			objPost.linkXML = linkXMLInput;
			objPost.maHoaDon = maHoaDonInput;
			objPost.maSoDuThuong = maSoDuThuongInput;
			objPost.NBan = NBan;
			objPost.NMua = NMua;
			objPost.soHoaDon = soHoaDonInput;
			String url = "http://210.245.8.17:1076/TraCuuQRThanhToan/services/tracuuqr/emailHoaDon";
			String json = new Gson().toJson(objPost);
			kq = PostBody.callServiceHTTP(url, json);
			if(kq!="")
			{
				Data.ghiLogRequest("guiEmailHoaDon: ThanhCong " + kq);
			}
			
		}catch (Exception e) {
			Data.ghiLogRequest("guiEmailHoaDon: Loi " + soHoaDonInput);
		}
		
		return kq;
	}
	
	
	private static String ranDomString()
	{
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();

	    return generatedString;
	}	
	
	private static int randomInt()
	{
		int min = 0;
		int max = 50000000;
		
		int rd = new Random().nextInt(max + min) - min;
		return rd;
	}
	
	private static ObjectKetQuaHoaDon phanTichKetQua(String xmlKQ,String api) {
		ObjectKetQuaHoaDon objKQ = null;
		
		if(xmlKQ!=null && xmlKQ != "")
		{
			Data.ghiLogRequest("phanTichKetQua: " + api);
			Data.ghiLogRequest("phanTichKetQua:ChuoiCanPhanTich " + xmlKQ);
			objKQ = new ObjectKetQuaHoaDon();
			try 
			{				
				if(api.equals("apiPHanhHDon"))
				{
					if (xmlKQ.indexOf("<?xml") != -1) 
					{
						org.jsoup.nodes.Document document = Jsoup.parse(xmlKQ);
	//					String xml_Key = document.select("KEY").html();
						String xml_Kq = document.select("KQ").html();				
						if(xml_Kq.contains("#"))
						{
							Data.ghiLogRequest("xmlKq:  " + xml_Kq);
							String[] temp = xml_Kq.split("#");
							if(temp[0].equals("200"))
							{						
								// thanh cong
								objKQ.errorCode = temp[0];
								objKQ.idHoaDon = temp[1];
								
								String thongTinHD = temp[2];
								if(thongTinHD!=null&&thongTinHD!="")
								{
									String temp1 = thongTinHD.replace(".", "vmok");
									String[] temp2 = temp1.split("vmok");
									objKQ.mauSoHoaDon = temp2[0];
									objKQ.kyHieuHoaDon = temp2[1];
									objKQ.SoHoaDon = temp2[2];
								}
								objKQ.maTraCuuHoaDon = temp[3];					
							}						
							else {
								objKQ.errorCode = temp[0];
								objKQ.thongTinThem = xml_Kq.substring(4);
							}
						}	
					} else 
					{
						String[] temp = xmlKQ.split("#");
						objKQ.errorCode = temp[0];
						objKQ.thongTinThem = xmlKQ.substring(4);
					}
				}
				else if (api.equals("apiLayTTinMSKHHDon"))
				{
					String[] temp = xmlKQ.split("#");
					if(temp[0].equals("200"))
					{
						String xml_Kq = temp[1];
						Data.ghiLogRequest("xmlKq:  " + xml_Kq);
						org.jsoup.nodes.Document document = Jsoup.parse(xml_Kq);
						String MaMSHDon = document.select("MaMSHDon").html();
						String MaKHHDon = document.select("MaKHHDon").html();
						String SoBD = document.select("SoBD").html();
						String SoHT = document.select("SoHT").html();
						String SoKT = document.select("SoKT").html();

					} else
					{
						objKQ.errorCode = temp[0];
						objKQ.thongTinThem = temp[1];
					}
				} else
				{					
					if(xmlKQ.contains("#"))
					{						
						String[] temp = xmlKQ.split("#");
						objKQ.errorCode = temp[0];
						objKQ.thongTinThem = xmlKQ.substring(4);	
					}
				}
				Data.ghiLogRequest("KetQuaPhanTich:  " + new Gson().toJson(objKQ));					
			}catch (Exception e) {
				Data.ghiLogRequest("phanTichKetQuaPhatHanh: Exception " + e.getMessage());
			}
			
		}
		
		return objKQ;
	}
	
	private static ObjectKetQuaHoaDon phanTichKetQua2(String xmlKQ,String api) {
		ObjectKetQuaHoaDon objKQ = null;
		
		if(xmlKQ!=null && xmlKQ != "")
		{
			Data.ghiLogRequest("phanTichKetQua: " + api);
			Data.ghiLogRequest("phanTichKetQua:ChuoiCanPhanTich " + xmlKQ);
			objKQ = new ObjectKetQuaHoaDon();
			try 
			{	
				if (xmlKQ.indexOf("<?xml") != -1) 
				{
					if (api.equals("apiPHanhHDon"))
					{
						org.jsoup.nodes.Document document = Jsoup.parse(xmlKQ);
	//					String xml_Key = document.select("KEY").html();
						String xml_Kq = document.select("KQ").html();
	
						if(xml_Kq.contains("#"))
						{
							Data.ghiLogRequest("xmlKq:  " + xml_Kq);
							String[] temp = xml_Kq.split("#");	
							if(temp[0].equals("200"))
							{						
								// Thanh cong
								objKQ.errorCode = temp[0];
								objKQ.idHoaDon = temp[1];
								
								String thongTinHD = temp[2];
								if(thongTinHD!=null&&thongTinHD!="")
								{
									String temp1 = thongTinHD.replace(".", "vmok");
									String[] temp2 = temp1.split("vmok");
									objKQ.mauSoHoaDon = temp2[0];
									objKQ.kyHieuHoaDon = temp2[1];
									objKQ.SoHoaDon = temp2[2];
								}
								objKQ.maTraCuuHoaDon = temp[3];					
							}						
							else {
								objKQ.errorCode = temp[0];
								objKQ.thongTinThem = xml_Kq.substring(4);
							}
						}																			
					}
					else
					{					
						if(xmlKQ.contains("#"))
						{						
							String[] temp = xmlKQ.split("#");
							objKQ.errorCode = temp[0];
							objKQ.thongTinThem = xmlKQ.substring(4);
	
							Data.ghiLogRequest("KetQuaPhanTich:  " + new Gson().toJson(objKQ));		
							Data.ghiLogRequest("KetQuaPhanTich:  " + objKQ.errorCode);		
							Data.ghiLogRequest("KetQuaPhanTich:  " + objKQ.thongTinThem);	
						}
					}	
				} else {
					if(xmlKQ.contains("#"))
					{						
						String[] temp = xmlKQ.split("#");
						objKQ.errorCode = temp[0];
						objKQ.thongTinThem = xmlKQ.substring(4);
					}
				}
			}catch (Exception e) {
				Data.ghiLogRequest("phanTichKetQuaPhatHanh: Exception " + e.getMessage());
			}
			
		}
		
		return objKQ;
	}

	private static HDon[] taoDuLieu_HDon(ObjectThongTinHoaDon objHD) {
		
		String key = vn.vimass.utils.VimassCommon.generateSessionKey(15);		
		String keyOld = objHD.KeyOld;
		BigDecimal id = new BigDecimal(new Random().nextInt() & Integer.MAX_VALUE);				
		
		HDon param[] = new HDon[1];
		try {
			TTChung TTChung = taoDuLieu_TTChung(objHD);
			System.out.println("taoDuLieu_HDon:TTChung 1" + new Gson().toJson(TTChung));
			NDHDon NDHDon = taoDuLieu_NDHDon(objHD);
			System.out.println("taoDuLieu_HDon: NDHDon 1" + new Gson().toJson(NDHDon));
			TTin TTKhac[] = taoDuLieu_TTKhac();						
			System.out.println("taoDuLieu_HDon: TTKhac 1" + new Gson().toJson(TTKhac));
			
			DLHDon DLHDon = new DLHDon(key, keyOld, id, TTChung, NDHDon, TTKhac);
										
	        Signature TCT = new Signature(""); 
	        TTCQT TTCQT = taoDuLieu_TTCQT();
	        
	        Signature NBan = new Signature(""); 
	        Signature NMua = new Signature("");        
	        Signature CCKSKhac = new Signature(""); 
	        DSCKS DSCKS = new DSCKS(NBan, NMua, TCT, CCKSKhac);
			
			
			param[0] = new HDon(DLHDon, TTCQT, DSCKS);
			
		}catch (Exception e) {
			System.out.println("taoDuLieu_HDon:Exception " + e.getMessage());
			
		}
		System.out.println("taoDuLieu_HDon: Thanh cong! " + new Gson().toJson(param));	
		return param;
	}
	
 	private static TTCQT taoDuLieu_TTCQT() { 		
 		String MCCQT = "";
        String MSTNBan = "";
        String KHMSHDon = "";
        String KHHDon = "";
        String SHDon = "";
        String TDLap = "";
        Signature TCT = new Signature("");
		TTCQT param = new TTCQT(MCCQT, MSTNBan, KHMSHDon, KHHDon, SHDon, TDLap, TCT);
		return param;
	}

	private static TTin[] taoDuLieu_TTKhac() {
 		
		TTin tin[] = new TTin[1];
		try {
			String DLieu = "";
	 		String KDLieu = "";
	 		String TTruong = "";
			
			tin[0] = new TTin(TTruong, KDLieu, DLieu);
	 		System.out.println("taoDuLieu_TTKhac: tin " + new Gson().toJson(tin[0]));
	 		
		}catch (Exception e) {
			System.out.println("taoDuLieu_TTKhac: tinException " + e.getMessage());
		}
 		return tin;
	}

	private static NDHDon taoDuLieu_NDHDon(ObjectThongTinHoaDon obj)
	{
		NDHDon param = null;
		try {
			NBan NBan = taoDuLieu_NBan(obj);
			System.out.println("taoDuLieu_NDHDon: NBan OK");
	        NMua NMua = taoDuLieu_NMua(obj);
	        System.out.println("taoDuLieu_NDHDon: NMua OK");
	        if(obj.listHHDV!=null)
	        {
	        	HHDVu DSHHDVu[] = taoDuLieu_HHDVu(obj.listHHDV);
	        	 System.out.println("taoDuLieu_NDHDon: HHDVu OK");
		        TToan TToan = taoDuLieu_TToan(); 
		        System.out.println("taoDuLieu_NDHDon: TToan OK");
		        param = new NDHDon(NBan, NMua, DSHHDVu, TToan);
	        }
	        else {
	        	System.out.println("taoDuLieu_NDHDon: listHHDV null");
			}
	        
			
		}catch (Exception e) {

			System.out.println("taoDuLieu_NDHDon:Exception " + e.getMessage());
		}
		System.out.println("taoDuLieu_NDHDon: Thanh cong! " + new Gson().toJson(param));
		
		return param;
	}

	private static TToan taoDuLieu_TToan() {
		
		BigDecimal tgTCThue = BigDecimal.ZERO;
        BigDecimal tgTThue = BigDecimal.ZERO;
        BigDecimal tgTHHDVu = BigDecimal.ZERO;
        BigDecimal TLPhi = BigDecimal.ZERO;
        BigDecimal TPhi = BigDecimal.ZERO;
        BigDecimal TTCKTMai = BigDecimal.ZERO;
        BigDecimal tgTTTBSo = BigDecimal.ZERO;
        String tgTTTBChu = "";
        LTSuat THTTLTSuat[] = taoDuLieu_LTSuat();
        TTin TTKhac[] = taoDuLieu_TTKhac();
		
		TToan param = new TToan(tgTCThue, tgTThue, tgTHHDVu, TLPhi, TPhi, TTCKTMai, tgTTTBSo, tgTTTBChu, THTTLTSuat, TTKhac);
		return param;
	}

	private static LTSuat[] taoDuLieu_LTSuat() {
		String TSuat = "";
        BigDecimal TThue = BigDecimal.ZERO;
        BigDecimal thTien = BigDecimal.ZERO;
        
		LTSuat param[] = new LTSuat[1];
		param[0] = new LTSuat(TSuat, TThue, thTien);
		return param;
	}

	private static HHDVu[] taoDuLieu_HHDVu(List<ObjectHangHoaDichVu> lisHangHoa) {
		int soLuongHangHoa = lisHangHoa.size();
		 System.out.println("soLuongHangHoa: " + soLuongHangHoa);
		 HHDVu param[] = new HHDVu[soLuongHangHoa];
		 System.out.println("param: " + param.length);
		 
		 int TChat = 1;
         int STT = 1;
         String ma = "";
         String ten = "";
         String DVTinh = "";
         BigDecimal SLuong = BigDecimal.ZERO;
         BigDecimal DGia = BigDecimal.ZERO;
         BigDecimal TLCKhau = BigDecimal.ZERO;
         BigDecimal STCKhau = BigDecimal.ZERO;
         BigDecimal thTien = BigDecimal.ZERO;
         String TSuat = "";
         String TSGiam = ""; 
         BigDecimal TThue = BigDecimal.ZERO;
         BigDecimal tgTien = BigDecimal.ZERO;
         try {
        	 System.out.println("taoDuLieu_TTKhac: nghi lam");
        	  TTin TTKhac[] = taoDuLieu_TTKhac();
	         System.out.println("taoDuLieu_TTKhac: OK");
			 for(int i=0;i<soLuongHangHoa;i++)
			 {
				 TChat = lisHangHoa.get(i).TChat;
		         STT = lisHangHoa.get(i).STT;
		         ma = lisHangHoa.get(i).ma;
		         ten = lisHangHoa.get(i).ten;
		         DVTinh = lisHangHoa.get(i).DVTinh;
		         SLuong = lisHangHoa.get(i).SLuong;
		         DGia = lisHangHoa.get(i).DGia;
		         TLCKhau = lisHangHoa.get(i).TLCKhau;
		         STCKhau = lisHangHoa.get(i).STCKhau;
		         thTien = lisHangHoa.get(i).thTien;
		         TSuat = lisHangHoa.get(i).TSuat;
		         TSGiam = lisHangHoa.get(i).TSGiam;
		         TThue = lisHangHoa.get(i).TThue;
		         tgTien = lisHangHoa.get(i).tgTien;
		         TTKhac = taoDuLieu_TTKhac();
		         	       
		 		 param[i] = new HHDVu(TChat, STT, ma, ten, DVTinh, SLuong, DGia, TLCKhau, STCKhau, thTien, TSuat, TSGiam, TThue, tgTien, TTKhac);
		 		 
		 		System.out.println("taoDuLieu_HHDVu: " + new Gson().toJson(param[i]));
			 }
         }catch (Exception e) {
        	 System.out.println("taoDuLieu_HHDVu: Exception " + e.getMessage());
		}
        
		 System.out.println("taoDuLieu_HHDVu: OK");
		return param;
	}

	private static NMua taoDuLieu_NMua(ObjectThongTinHoaDon obj) {		
		 String ten = obj.NMua.tenDoanhNghiep;
         String MST = obj.NMua.maSoThue;
         String DChi = obj.NMua.diaChi;
         TTin TTKhac[] = taoDuLieu_TTKhac();
		NMua param = new NMua(ten, MST, DChi, TTKhac);
		return param;
	}

	private static NBan taoDuLieu_NBan(ObjectThongTinHoaDon obj) {
		String ten = obj.NBan.tenDoanhNghiep;
        String MST = obj.NBan.maSoThue;
        String DChi = obj.NBan.diaChi;
		NBan param = new NBan(ten, MST, DChi);
		return param;
	}

	private static TTChung taoDuLieu_TTChung(ObjectThongTinHoaDon obj)
	{
		TTChung param = null;	
		try {
			 System.out.println("taoDuLieu_TTChung: ObjectThongTinHoaDon : " + obj.kieuPhatHanh);
			String PBan = "";
	        String THDon = "";
	        String KHMSHDon = obj.NBan.maSoHoaDon;
	        if(KHMSHDon.equals("01GTKT"))
	        {
	        	THDon = "Hóa đơn giá trị gia tăng";
	        }
	        String KHHDon = obj.NBan.kyHieuHoaDon;
	        int soHoaDonHienTai = 0;    
	        System.out.println("taoDuLieu_TTChung: soHoaDonHienTai : " + soHoaDonHienTai);
	        String SHDon = "";
	        if(soHoaDonHienTai>0)
	        {        	
	        	SHDon = getSoHoaDonMoi(soHoaDonHienTai,obj.NBan.maxHD);
	        	 
	        }
	        else {			
	    		SHDon = "00000001";
	    	}
	        System.out.println("taoDuLieu_TTChung: soHoaDonTiepTheo : " + SHDon);
	        String TDLap = CMCUtils.getCurrentDateTimeNow_ddMMyyyy();
	        String TChat = "";
	        if(obj.kieuPhatHanh == 1)
	        {
	        	TChat = "G";
	        }
	        else if(obj.kieuPhatHanh == 2) {
				TChat = "U";
			}
	        else if(obj.kieuPhatHanh == 3) {
				TChat = "D";
			}
	        else if(obj.kieuPhatHanh == 4) {
				TChat = "T";
			}
	        String DVTTe = "VND";
	        double TGia = 1;
	        String HTHDBTThe = "";
	        String TDLHDBTThe = "";
	        String KHMSHDBTThe = "";
	        String KHHDBTThe = "";
	        String SHDBTThe = "";
	        TTin TTKhac[] = taoDuLieu_TTKhac();		
	        System.out.println("taoDuLieu_TTChung: TTChung : " + new Gson().toJson(TTKhac));
			param = new TTChung(PBan, THDon, KHMSHDon, KHHDon, SHDon, TDLap, TChat, DVTTe, TGia, HTHDBTThe, TDLHDBTThe, KHMSHDBTThe, KHHDBTThe, SHDBTThe, TTKhac);
			
		}catch (Exception e) {
			System.out.println("taoDuLieu_TTChung: Exception: " + e.getMessage());
		}
		System.out.println("taoDuLieu_TTChung: Thanh cong!");
				
		return param;
	}
		
	private static String getSoHoaDonMoi(int soHoaDonHienTai,int maxHoaDon)
	{
		int soHoaDonTiepTheo = 0;
		String prefix = "";	
		if(soHoaDonHienTai<=maxHoaDon)
		{
			soHoaDonTiepTheo = soHoaDonHienTai + 1;
			int chenhLech = 8 - (String.valueOf(soHoaDonTiepTheo).length());
			for(int i = 0;i<chenhLech;i++)
			{
				prefix += "0";
			}
		}
		return prefix+soHoaDonTiepTheo;		
	}
	
	private static ThongTin getThongTinDonViBangMST2(String maSoThue)
	{
		Data.ghiLogRequest("getThongTinDonViBangMST2: " + maSoThue);
		try {
			DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
			String kq = def.apiLayTTinMst(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, maSoThue);
			if(kq.startsWith("200"))
			{
				String json = kq.split("#")[1];
				Data.ghiLogRequest("json " + json);
				ThongTin thongTin = new Gson().fromJson(json, ThongTin.class);
				return thongTin;
			}
			else {
				Data.ghiLogRequest("getThongTinDonViBangMST2: khong tim thay mst "+ maSoThue );
				return null;
			}
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonViBangMST2: loi khi tim mst" + e.getMessage());
		}	
		Data.ghiLogRequest("getThongTinDonViBangMST2 res: " );
		return null;
	}
	
	public static String getThongTinDonViBangMST(String input)
	{
		ObjectTraCuuThongTinDoanhNghiep objInput = new Gson().fromJson(input, ObjectTraCuuThongTinDoanhNghiep.class);
		Data.ghiLogRequest("getThongTinDonViBangMST: " + objInput.maSoThue);
		Data.ghiLogRequest("getThongTinDonViBangMST MD5 : " + objInput.md5String);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		String pass_server = "vsrbgjO6nV7M2SO";
		String md5String_server = vn.vimass.utils.VimassCommon.bamMD5(objInput.maSoThue+pass_server);
		if(md5String_server.equals(objInput.md5String))
		{
			try {
				DefaultServiceSoapProxy def = new DefaultServiceSoapProxy();
				String kq = def.apiLayTTinMst(CMCUtils.SERVICES_USER, CMCUtils.SERVICES_PASS, objInput.maSoThue);
				if(kq.startsWith("200"))
				{
					String json = kq.split("#")[1];

					Data.ghiLogRequest("json " + json);
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					ThongTin tt = new Gson().fromJson(json, ThongTin.class);
					result.result = tt;
				}
				else {
					result.msgContent = kq.split("#")[1];
				}
			}catch (Exception e) {
				Data.ghiLogRequest("getThongTinDonViBangMST " + e.getMessage());
			}
		}
		else {
			result.msgContent = ErrorCode.MES_YEU_CAU_KHONG_HOP_LE;
		}
		Data.ghiLogRequest("getThongTinDonViBangMST res: " + new Gson().toJson(result));
		return new Gson().toJson(result);
	}
	
}
