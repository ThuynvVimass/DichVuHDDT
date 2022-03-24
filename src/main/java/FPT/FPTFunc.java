package FPT;

import java.util.Date;

import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import CMC.CMCFunc;
import CMC.Table.TableDonVi;
import CMC.object.ObjectTraCuuThongTinDoanhNghiep;
import CMC.object.ThongTin;
import CMC.object.ThongTinDonVi;
import FPT.object.hoaDonCanKyDuyet.Object_HoaDonCanKyDuyet;
import FPT.object.hoaDonCanKyDuyet.Object_HoaDonCanKyDuyet_FPT;
import FPT.object.hoaDonCanTraCuu.Object_TraCuuHoaDon;
import FPT.object.hoaDonCanTraCuu.objectResponse.Response_TC;
import FPT.object.hoaDonChinhSua.Object_ChinhSuaHoaDon;
import FPT.object.hoaDonChuaCapSo.Object_XoaHoaDonChuaCapSo;
import FPT.object.hoaDonChuaCapSo.Object_XoaHoaDonChuaCapSo_FPT;
import FPT.object.hoaDonHuy.Object_HuyHoaDon;
import FPT.object.hoaDonHuy.Object_HuyHoaDon_FPT;
import FPT.object.hoaDonHuy.WrongNotice;
import FPT.object.hoaDonTaoMoi.Inv;
import FPT.object.hoaDonTaoMoi.Object_HDDT_FPT;
import FPT.object.hoaDonTaoMoi.Object_ThongTinHoaDon;
import FPT.object.hoaDonThayThe.Adj;
import FPT.object.hoaDonThayThe.Inv_ThayThe;
import FPT.object.hoaDonThayThe.Object_ThayTheHoaDon;
import FPT.object.hoaDonThayThe.Object_ThayTheHoaDon_FPT;
import FPT.objectResponse.Root;
import FPT.objectResponse.Root_TT;
import connect.ConnectUsingGet;
import connect.PostBody;
import share.object.ObjectMapper;
import vn.vimass.csdl.object.ErrorCode;
import vn.vimass.csdl.object.ObjectMessageResult;
import vn.vimass.utils.Data;
import vn.vimass.utils.VimassCommon;
import vn.vimass.utils.gson.DoubleTypeAdapter;
import vn.vimass.utils.gson.IntegerTypeAdapter;

public class FPTFunc {

	public static String lapHoaDon(String input)
	{		
		Data.ghiLogRequest("================ lapHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_ThongTinHoaDon objInput = new Gson().fromJson(input, Object_ThongTinHoaDon.class);
		
		Object_HDDT_FPT objRoot = new Object_HDDT_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		
		
		Inv inv = new Inv();
		inv.sid = VimassCommon.generateSessionKey(15);
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
		inv.form = "1"; 		// mẫu hóa đơn đã đăng ký trên web
		inv.serial = "C22TAA"; 	// ký hiệu hóa đơn đã đăng ký
		inv.seq =  ""; 			// số hóa đơn, không điền vào thì số tự tăng
		inv.bcode =  ""; 		// mã khách hàng, không bắt buộc
		
		try {
			ThongTin thongTinNguoiMua = new ThongTin();
			ObjectTraCuuThongTinDoanhNghiep input_tracuu = new ObjectTraCuuThongTinDoanhNghiep();
			input_tracuu.maSoThue = objInput.maSoThueNguoiMua;
			input_tracuu.md5String = vn.vimass.utils.VimassCommon.bamMD5(objInput.maSoThueNguoiMua+"vsrbgjO6nV7M2SO");
			String jsonNguoiMua = CMCFunc.getThongTinDonViBangMST(new Gson().toJson(input_tracuu));
			if(jsonNguoiMua!=null&&jsonNguoiMua!="")
			{
				Data.ghiLogRequest("jsonNguoiMua: " + jsonNguoiMua);
				ObjectMessageResult res = new ObjectMessageResult();
				res = new Gson().fromJson(jsonNguoiMua, ObjectMessageResult.class);
				if(res!=null&&res.msgCode == 1)
				{
					String json =  new Gson().toJson(res.result);
					Data.ghiLogRequest("json donvi: " + json);
					thongTinNguoiMua = new Gson().fromJson(json, ThongTin.class);
					inv.bname =  thongTinNguoiMua.ten_cty;
					inv.baddr =  thongTinNguoiMua.dia_chi;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonViBangMST Exception: " + e.getMessage());
		}
		
				
		inv.buyer =  objInput.tenNguoiMua;
		inv.btax =  objInput.maSoThueNguoiMua;		
		inv.btel =  objInput.soDienThoaiNguoiMua;
		inv.bmail = objInput.emailNguoiMua;
		inv.paym =  objInput.hinhThucThanhToan;
		inv.curr =  "VND";
		inv.bacc =  objInput.soTaiKhoanNguoiMua;
		inv.bbank =  objInput.nganHangNguoiMua;
		inv.note =  objInput.ghiChu;
		
		if (objInput.tiGia == 0) objInput.tiGia = 1;
		inv.exrt =  objInput.tiGia;
		inv.sum =  objInput.tongTienChuaThueNguyenTe;
		inv.sumv =  objInput.tongTienChuaThueNguyenTe * inv.exrt;
		inv.vat =  objInput.tongTienThueNguyenTe;
		inv.vatv =  objInput.tongTienThueNguyenTe * inv.exrt;
		
		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney); // Khi số tiền quá lớn, hệ thống sẽ hiển thị dạng a10^b ().
//															 //////	FPT không yêu cầu, nên giải quyết nhanh nhất, bỏ qua
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;	
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2; 			// 1 tự quản lý cấp số HĐ, 2 hệ thống cấp số hóa đơn tăng dần
		inv.sign =  -1; 		// hóa đơn xuất hoàn trả
		inv.type_ref =  1;		// 1 hóa đơn TT78
		inv.listnum =  ""; 		// bảng kê
		inv.listdt =  ""; 		// list bảng kê
		inv.sendtype =  1;  	// 1 chuyển đến TTC luôn, 2 chuyển gộp  
		inv.items = objInput.items;
		inv.stax = objInput.maSoThueNguoiBan;
		
		objRoot.inv = inv;
						
		String url = FPTUltis.URL_LAP_HOADON;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		String response = PostBody.callService(url, json);
		Data.ghiLogRequest("output FPT: " + response);
		try {
			if (response.substring(0,3).equals("200")) {
				if(FPTUltis.isValidJSON(response.substring(3)))
				{
					Root r = new Gson().fromJson(response.substring(3), Root.class);				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = r;			
				} else {
					result.result = response.substring(3);
				}	
			} else {
				result.result = response.substring(3);
			}
		}catch (Exception e) {			
			result.result = e.getMessage();
		}	
		
		return new Gson().toJson(result);
	}

	public static String kyDuyetHoaDon(String input)
	{		
		Data.ghiLogRequest("================ kyDuyetHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_HoaDonCanKyDuyet objInput = new Gson().fromJson(input, Object_HoaDonCanKyDuyet.class);
		
		Object_HoaDonCanKyDuyet_FPT objRoot = new Object_HoaDonCanKyDuyet_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		
//		Inv_Duyet inv = new Inv_Duyet ();
//		objRoot.inv = inv;
		objRoot.inv.sid = objInput.keyHoaDon;
		objRoot.inv.stax = objInput.maSoThueNguoiBan;
						
		String url = FPTUltis.URL_KYDUYET_HOADON;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		String response = PostBody.callService(url, json);
		Data.ghiLogRequest("output FPT: " + response);
		try {
			if (response.substring(0,3).equals("200")) {
				if(FPTUltis.isValidJSON(response.substring(3)))
				{
					Root r = new Gson().fromJson(response.substring(3), Root.class);				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = r;			
				} else {
					result.result = response.substring(3);
				}	
			} else {
				result.result = response.substring(3);
			}
		}catch (Exception e) {			
			result.result = e.getMessage();
		}	
		
		return new Gson().toJson(result);
	}
	
	public static String suaHoaDon(String input)
	{		
		Data.ghiLogRequest("================ suaHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_ChinhSuaHoaDon objInput = new Gson().fromJson(input, Object_ChinhSuaHoaDon.class);
		
		Object_HDDT_FPT objRoot = new Object_HDDT_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		
		Inv inv = new Inv();
		inv.sid = objInput.sidHoaDon;
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
		inv.form = "1"; 		// mẫu hóa đơn đã đăng ký trên web
		inv.serial = "C22TAA"; 	// ký hiệu hóa đơn đã đăng ký
		inv.seq =  ""; 			// số hóa đơn, không điền vào thì số tự tăng
		inv.bcode =  ""; 		// mã khách hàng, không bắt buộc
		
		try {
			ThongTin thongTinNguoiMua = new ThongTin();
			ObjectTraCuuThongTinDoanhNghiep input_tracuu = new ObjectTraCuuThongTinDoanhNghiep();
			input_tracuu.maSoThue = objInput.maSoThueNguoiMua;
			input_tracuu.md5String = vn.vimass.utils.VimassCommon.bamMD5(objInput.maSoThueNguoiMua+"vsrbgjO6nV7M2SO");
			String jsonNguoiBan = CMCFunc.getThongTinDonViBangMST(new Gson().toJson(input_tracuu));
			if(jsonNguoiBan!=null&&jsonNguoiBan!="")
			{
				Data.ghiLogRequest("jsonNguoiBan: " + jsonNguoiBan);
				ObjectMessageResult res = new ObjectMessageResult();
				res = new Gson().fromJson(jsonNguoiBan, ObjectMessageResult.class);
				if(res!=null&&res.msgCode == 1)
				{
					String json =  new Gson().toJson(res.result);
					Data.ghiLogRequest("json donvi: " + json);
					thongTinNguoiMua = new Gson().fromJson(json, ThongTin.class);
					inv.bname =  thongTinNguoiMua.ten_cty;
					inv.baddr =  thongTinNguoiMua.dia_chi;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonViBangMST Exception: " + e.getMessage());
		}
			
		inv.buyer =  objInput.tenNguoiMua;
		inv.btax =  objInput.maSoThueNguoiMua;		
		inv.btel =  objInput.soDienThoaiNguoiMua;
		inv.bmail = objInput.emailNguoiMua;
		inv.paym =  objInput.hinhThucThanhToan;
		inv.curr =  "VND";
		inv.bacc =  objInput.soTaiKhoanNguoiMua;
		inv.bbank =  objInput.nganHangNguoiMua;
		inv.note =  objInput.ghiChu;

		if (objInput.tiGia == 0) objInput.tiGia = 1;
		inv.exrt =  objInput.tiGia;
		inv.sum =  objInput.tongTienChuaThueNguyenTe;
		inv.sumv =  objInput.tongTienChuaThueNguyenTe * inv.exrt;
		inv.vat =  objInput.tongTienThueNguyenTe;
		inv.vatv =  objInput.tongTienThueNguyenTe * inv.exrt;
		
		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2; 			// 1 tự quản lý cấp số HĐ, 2 hệ thống cấp số hóa đơn tăng dần
		inv.sign =  -1; 		// hóa đơn xuất hoàn trả
		inv.type_ref =  1; 		// 1 hóa đơn TT78
		inv.listnum =  ""; 		// bảng kê
		inv.listdt =  ""; 		// list bảng kê
		inv.sendtype =  1;  	// 1 chuyển đến TTC luôn, 2 chuyển gộp  
		inv.items = objInput.items;
		inv.stax = objInput.maSoThueNguoiBan;
		
		objRoot.inv = inv;
						
		String url = FPTUltis.URL_SUA_HOADON;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		
		String response = PostBody.callService(url, json);
		try {
			if (response.substring(0,3).equals("200")) {
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;			
			}
			result.result = response.substring(3);	
		}catch (Exception e) {			
			result.result = e.getMessage();
		}
		
		return new Gson().toJson(result);
	}

	public static String huyHoaDon(String input)
	{
		Data.ghiLogRequest("================ huyHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		
		if(input!=null&&input!="")
		{
			try
			{
				Object_HuyHoaDon objInput = new Gson().fromJson(input, Object_HuyHoaDon.class);
				
				Object_HuyHoaDon_FPT objRoot = new Object_HuyHoaDon_FPT();
				objRoot.lang = "vn";
				
				try {
					ThongTinDonVi donvi = new ThongTinDonVi(); 
					donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
					
					objRoot.user.username = donvi.user;
					objRoot.user.password = donvi.password;
					
					Data.ghiLogRequest("donvi: " + objRoot.user.username);
				}catch (Exception e) {
					Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
				}
				
				WrongNotice wrongNotice = new WrongNotice();
				wrongNotice.stax = objInput.maSoThueNguoiBan;
				wrongNotice.noti_taxtype = objInput.loaiThongBaoHuy;
				wrongNotice.noti_taxnum = objInput.soThongBaoCQT;
				wrongNotice.budget_relationid = objInput.maDonViPhuThuoc;
				wrongNotice.place = objInput.diaDanh;							
				wrongNotice.items = objInput.items;
				
				objRoot.wrongnotice = wrongNotice;
				
				String json = new Gson().toJson(objRoot);
				Data.ghiLogRequest("input FPT: " + json);
				
				String response = PostBody.callService(FPTUltis.URL_HUY_HOADON, json);
				
				if(response.substring(0,3).equals("200"))
				{
					Root r = new Gson().fromJson(response.substring(3), Root.class);				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = r;			
				} else {
					result.result = response.substring(3);
				}			
							
			} catch (Exception e) {
				Data.ghiLogRequest("huyHoaDon FPT: " + e.getMessage());
			}
		}
	
		return new Gson().toJson(result);
	}
	
	public static String thayTheHoaDon(String input)
	{		
		Data.ghiLogRequest("================ thayTheHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_ThayTheHoaDon objInput = new Gson().fromJson(input, Object_ThayTheHoaDon.class);
		
		Object_ThayTheHoaDon_FPT objRoot = new Object_ThayTheHoaDon_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		
		Inv_ThayThe inv = new Inv_ThayThe();
		Adj adj = new Adj();
		inv.adj = adj;
		inv.adj.rdt = objInput.ngayVanBanThoaThuan;
		inv.adj.rea = objInput.lyDoDieuChinh;
		inv.adj.ref = objInput.soVanBanThoaThuan;
		inv.adj.seq = objInput.thongTinHoaHoaDon;
		
		inv.sid = VimassCommon.generateSessionKey(15);
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
		inv.form = "1"; 		// mẫu hóa đơn đã đăng ký trên web
		inv.serial = "C22TAA"; 	// ký hiệu hóa đơn đã đăng ký
		inv.seq =  ""; 			// số hóa đơn, không điền vào thì số tự tăng
		inv.bcode =  ""; 		// mã khách hàng, không bắt buộc
		
		try {
			ThongTin thongTinNguoiMua = new ThongTin();
			ObjectTraCuuThongTinDoanhNghiep input_tracuu = new ObjectTraCuuThongTinDoanhNghiep();
			input_tracuu.maSoThue = objInput.maSoThueNguoiMua;
			input_tracuu.md5String = vn.vimass.utils.VimassCommon.bamMD5(objInput.maSoThueNguoiMua+"vsrbgjO6nV7M2SO");
			String jsonNguoiBan = CMCFunc.getThongTinDonViBangMST(new Gson().toJson(input_tracuu));
			if(jsonNguoiBan!=null&&jsonNguoiBan!="")
			{
				Data.ghiLogRequest("jsonNguoiBan: " + jsonNguoiBan);
				ObjectMessageResult res = new ObjectMessageResult();
				res = new Gson().fromJson(jsonNguoiBan, ObjectMessageResult.class);
				if(res!=null&&res.msgCode == 1)
				{
					String json =  new Gson().toJson(res.result);
					Data.ghiLogRequest("json donvi: " + json);
					thongTinNguoiMua = new Gson().fromJson(json, ThongTin.class);
					inv.bname =  thongTinNguoiMua.ten_cty;
					inv.baddr =  thongTinNguoiMua.dia_chi;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonViBangMST Exception: " + e.getMessage());
		}
				
		inv.buyer =  objInput.tenNguoiMua;
		inv.btax =  objInput.maSoThueNguoiMua;		
		inv.btel =  objInput.soDienThoaiNguoiMua;
		inv.bmail = objInput.emailNguoiMua;
		inv.paym =  objInput.hinhThucThanhToan;
		inv.curr =  "VND";
		inv.bacc =  objInput.soTaiKhoanNguoiMua;
		inv.bbank =  objInput.nganHangNguoiMua;
		inv.note =  objInput.ghiChu;

		if (objInput.tiGia == 0) objInput.tiGia = 1;
		inv.exrt =  objInput.tiGia;
		inv.sum =  objInput.tongTienChuaThueNguyenTe;
		inv.sumv =  objInput.tongTienChuaThueNguyenTe * inv.exrt;
		inv.vat =  objInput.tongTienThueNguyenTe;
		inv.vatv =  objInput.tongTienThueNguyenTe * inv.exrt;
		
		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2;			// 1 tự quản lý cấp số HĐ, 2 hệ thống cấp số hóa đơn tăng dần
		inv.sign =  -1; 		// hóa đơn xuất hoàn trả
		inv.type_ref =  1; 		// 1 hóa đơn TT78
		inv.listnum =  ""; 		// bảng kê
		inv.listdt =  ""; 		// list bảng kê
		inv.sendtype =  1;  	// 1 chuyển đến TTC luôn, 2 chuyển gộp  
		inv.items = objInput.items;
		inv.stax = objInput.maSoThueNguoiBan;
		
		objRoot.inv = inv;
						
		String url = FPTUltis.URL_THAYTHE_HOADON;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		String response = PostBody.callService(url, json);
		
		try {
			if (response.substring(0,3).equals("200")) {
				if(FPTUltis.isValidJSON(response.substring(3)))
				{
					Root_TT r = new Gson().fromJson(response.substring(3), Root_TT.class);				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = r;			
				} else {
					result.result = response.substring(3);
				}	
			} else {
				result.result = response.substring(3);
			}
		} catch (Exception e) {			
			result.result = e.getMessage();
		}	
		
		return new Gson().toJson(result);
	}
	
	public static String dieuChinhHoaDon(String input)
	{		
		Data.ghiLogRequest("================ dieuChinhHoaDon() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_ThayTheHoaDon objInput = new Gson().fromJson(input, Object_ThayTheHoaDon.class);
		
		Object_ThayTheHoaDon_FPT objRoot = new Object_ThayTheHoaDon_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		
		Inv_ThayThe inv = new Inv_ThayThe();
		Adj adj = new Adj();
		inv.adj = adj;
		inv.adj.rdt = objInput.ngayVanBanThoaThuan;
		inv.adj.rea = objInput.lyDoDieuChinh;
		inv.adj.ref = objInput.soVanBanThoaThuan;
		inv.adj.seq = objInput.thongTinHoaHoaDon;
		
		inv.sid = VimassCommon.generateSessionKey(15);
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
		inv.form = "1"; 		// mẫu hóa đơn đã đăng ký trên web
		inv.serial = "C22TAA"; 	// ký hiệu hóa đơn đã đăng ký
		inv.seq =  ""; 			// số hóa đơn, không điền vào thì số tự tăng
		inv.bcode =  ""; 		// mã khách hàng, không bắt buộc
		
		try {
			ThongTin thongTinNguoiMua = new ThongTin();
			ObjectTraCuuThongTinDoanhNghiep input_tracuu = new ObjectTraCuuThongTinDoanhNghiep();
			input_tracuu.maSoThue = objInput.maSoThueNguoiMua;
			input_tracuu.md5String = vn.vimass.utils.VimassCommon.bamMD5(objInput.maSoThueNguoiMua+"vsrbgjO6nV7M2SO");
			String jsonNguoiBan = CMCFunc.getThongTinDonViBangMST(new Gson().toJson(input_tracuu));
			if(jsonNguoiBan!=null&&jsonNguoiBan!="")
			{
				Data.ghiLogRequest("jsonNguoiBan: " + jsonNguoiBan);
				ObjectMessageResult res = new ObjectMessageResult();
				res = new Gson().fromJson(jsonNguoiBan, ObjectMessageResult.class);
				if(res!=null&&res.msgCode == 1)
				{
					String json =  new Gson().toJson(res.result);
					Data.ghiLogRequest("json donvi: " + json);
					thongTinNguoiMua = new Gson().fromJson(json, ThongTin.class);
					inv.bname =  thongTinNguoiMua.ten_cty;
					inv.baddr =  thongTinNguoiMua.dia_chi;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonViBangMST Exception: " + e.getMessage());
		}
				
		inv.buyer =  objInput.tenNguoiMua;
		inv.btax =  objInput.maSoThueNguoiMua;		
		inv.btel =  objInput.soDienThoaiNguoiMua;
		inv.bmail = objInput.emailNguoiMua;
		inv.paym =  objInput.hinhThucThanhToan;
		inv.curr =  "VND";
		inv.bacc =  objInput.soTaiKhoanNguoiMua;
		inv.bbank =  objInput.nganHangNguoiMua;
		inv.note =  objInput.ghiChu;

		if (objInput.tiGia == 0) objInput.tiGia = 1;
		inv.exrt =  objInput.tiGia;
		inv.sum =  objInput.tongTienChuaThueNguyenTe;
		inv.sumv =  objInput.tongTienChuaThueNguyenTe * inv.exrt;
		inv.vat =  objInput.tongTienThueNguyenTe;
		inv.vatv =  objInput.tongTienThueNguyenTe * inv.exrt;
		
		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2;			// 1 tự quản lý cấp số HĐ, 2 hệ thống cấp số hóa đơn tăng dần
		inv.sign =  -1; 		// hóa đơn xuất hoàn trả
		inv.type_ref =  1; 		// 1 hóa đơn TT78
		inv.listnum =  ""; 		// bảng kê
		inv.listdt =  ""; 		// list bảng kê
		inv.sendtype =  1;  	// 1 chuyển đến TTC luôn, 2 chuyển gộp  
		inv.items = objInput.items;
		inv.stax = objInput.maSoThueNguoiBan;
		
		objRoot.inv = inv;
						
		String url = FPTUltis.URL_DIEUCHINH_HOADON;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		String response = PostBody.callService(url, json);
		
		try {
			if (response.substring(0,3).equals("200")) {
				if(FPTUltis.isValidJSON(response.substring(3)))
				{
					Root_TT r = new Gson().fromJson(response.substring(3), Root_TT.class);				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = r;			
				} else {
					result.result = response.substring(3);
				}	
			} else {
				result.result = response.substring(3);
			}
		} catch (Exception e) {			
			result.result = e.getMessage();
		}	
		
		return new Gson().toJson(result);
	}
	
	
	public static String xoaHoaDonChuaCapSo(String input)
	// Không test đc. Lỗi: "504 Gateway Time-out" trên api chính của FPT
	{		
		Data.ghiLogRequest("================ xoaHoaDonChuaCapSo() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_XoaHoaDonChuaCapSo objInput = new Gson().fromJson(input, Object_XoaHoaDonChuaCapSo.class);
		
		Object_XoaHoaDonChuaCapSo_FPT objRoot = new Object_XoaHoaDonChuaCapSo_FPT();
		objRoot.lang = "vn";
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objRoot.user.username = donvi.user;
			objRoot.user.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objRoot.user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}

		objRoot.sid = objInput.keyHoaDon;
		objRoot.stax = objInput.maSoThueNguoiBan;
		
		String url = FPTUltis.URL_XOA_HOADON_CHUACAPSO;
		String json = new Gson().toJson(objRoot);
		Data.ghiLogRequest("input FPT: " + json);
		String response = PostBody.callService(url, json);
		
		try {
			if (response.substring(0,3).equals("200")) {				
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
					result.result = response.substring(3);				
			} else {
				result.result = response.substring(3);
			}
		} catch (Exception e) {			
			result.result = e.getMessage();
		}	
		
		return new Gson().toJson(result);
	}

	public static String dangKyThongTinDonVi(String input) {
		
		Data.ghiLogRequest("================ dangKythongTinDonVi() ================"+ input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		
		try {
			ThongTinDonVi obj_input = new Gson().fromJson(input, ThongTinDonVi.class);
			if(obj_input!=null)
			{
				if(obj_input.maSoThue!=null||obj_input.coQuanThue!=null)
				{
					String mst = TableDonVi.taoDuLieuDonVi(obj_input);
					if(mst!=null&&mst.equals(obj_input.maSoThue))
					{
						result.msgCode = ErrorCode.SUCCESS;
						result.msgContent = ErrorCode.MES_SUCCESS;	
					}
					result.result = mst;
				}
				else {
					result.msgCode = ErrorCode.PARAM_SATELESS;
					result.msgContent = ErrorCode.MES_PARAM_SATELESS;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("dangKythongTinDonVi Exception : "+ e.getMessage());
		}
								
		return new Gson().toJson(result);
	}

	public static String thayDoiThongTinDonVi(String input) {
		
		Data.ghiLogRequest("================ thayDoithongTinDonVi() ================"+ input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		
		try {
			ThongTinDonVi obj_input = new Gson().fromJson(input, ThongTinDonVi.class);
			if(obj_input!=null)
			{
				if(obj_input.maSoThue!=null||obj_input.coQuanThue!=null)
				{
					String mst = TableDonVi.suaDuLieuDonVi(obj_input);
					if(mst!=null&&mst.equals(obj_input.maSoThue))
					{
						result.msgCode = ErrorCode.SUCCESS;
						result.msgContent = ErrorCode.MES_SUCCESS;	
					}
					result.result = mst;
				}
				else {
					result.msgCode = ErrorCode.PARAM_SATELESS;
					result.msgContent = ErrorCode.MES_PARAM_SATELESS;
				}
			}
		}catch (Exception e) {
			Data.ghiLogRequest("thayDoithongTinDonVi Exception : "+ e.getMessage());
		}
								
		return new Gson().toJson(result);
	}
	
	public static String traCuuThongTinDonViInDB(String maSoThue) {
		
		Data.ghiLogRequest("================ traCuuthongTinDonViInDB() ================"+ maSoThue);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		
		try {
			
			if(maSoThue!=null)
			{
				ThongTinDonVi donVi = TableDonVi.getThongTinDonVi(maSoThue);
				result.msgCode = ErrorCode.SUCCESS;
				result.msgContent = ErrorCode.MES_SUCCESS;	

//				res = new Gson().fromJson(jsonNguoiBan, ObjectMessageResult.class);
				result.result = donVi;
			}
			else {
				result.msgCode = ErrorCode.PARAM_SATELESS;
				result.msgContent = ErrorCode.MES_PARAM_SATELESS;
			
			}
		}catch (Exception e) {
			Data.ghiLogRequest("traCuuthongTinDonVi Exception : "+ e.getMessage());
		}
								
		return new Gson().toJson(result);
	}
	
	public static String traCuuHoaDon(String input)
	{		
		Data.ghiLogRequest("================ traCuuHoaDon() ================");
		Data.ghiLogRequest("input " + input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
				
		Object_TraCuuHoaDon objInput = new Gson().fromJson(input, Object_TraCuuHoaDon.class);
		
		try {
			ThongTinDonVi donvi = new ThongTinDonVi(); 
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);	
			
			objInput.username = donvi.user;
			objInput.password = donvi.password;
			
			Data.ghiLogRequest("donvi: " + objInput.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		if (objInput.dinhDangMuonTraVe.equals(""))objInput.dinhDangMuonTraVe = "json";				
		String url = FPTUltis.URL_TRACUU_HOADON;

		url = UriBuilder.fromUri(url).queryParam("type", objInput.dinhDangMuonTraVe)
				.queryParam("stax", objInput.maSoThueNguoiBan)
				.queryParam("sid", objInput.sidHoaDon)
				.queryParam("fd", objInput.tuNgay)
				.queryParam("td", objInput.denNgay)
				.queryParam("form", objInput.mauSoHoaDon)
				.queryParam("serial", objInput.kyHieuHoaDon)
				.queryParam("seq", objInput.soHoaDon)
				.queryParam("btax", objInput.maSoThueNguoiMua)
				.queryParam("status", objInput.trangThai)
				
				.build().toString();
				
		Data.ghiLogRequest("url Get : " + url);

//		try {
			String response = ConnectUsingGet.getContentWithHeader(url, objInput.username, objInput.password);	
			response = response.replace("//", "");
			if (response.indexOf("[")== -1) {
				result.result = response;
			} else {
				response = "{\"listHD\":" + response + "}";
				Data.ghiLogRequest(" FPT: " + response);
				Gson gson = new GsonBuilder()
									.registerTypeAdapter(double.class, new DoubleTypeAdapter())
									.registerTypeAdapter(int.class, new IntegerTypeAdapter())
									.create();
				Response_TC r = gson.fromJson(response, Response_TC.class);	

				
//				Object_ListHoaDon hddg = Main.objectResponseToObjectHoaDonDonGian(r);
//				ArrayList<Doc_TC> ar = new Gson().fromJson(response, Doc_TC.class);					
				result.msgCode = ErrorCode.SUCCESS;
				result.msgContent = ErrorCode.MES_SUCCESS;
				result.result = r;	
			}		
			
//		}catch (Exception e) {
//			Data.ghiLogRequest("Lỗi : " + e);			
//			result.result = e.getMessage();
//		}	
		
		return new Gson().toJson(result);
	}

	public static String traCuuHoaDon2(String input)
	{
		Data.ghiLogRequest("================ traCuuHoaDon() ================");
		Data.ghiLogRequest("input " + input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;

		Object_TraCuuHoaDon objInput = new Gson().fromJson(input, Object_TraCuuHoaDon.class);

		try {
			ThongTinDonVi donvi = new ThongTinDonVi();
			donvi = TableDonVi.getThongTinDonVi(objInput.maSoThueNguoiBan);

			objInput.username = donvi.user;
			objInput.password = donvi.password;

			Data.ghiLogRequest("donvi: " + objInput.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		if (objInput.dinhDangMuonTraVe.equals(""))objInput.dinhDangMuonTraVe = "json";
		String url = FPTUltis.URL_TRACUU_HOADON;

		url = UriBuilder.fromUri(url).queryParam("type", objInput.dinhDangMuonTraVe)
				.queryParam("stax", objInput.maSoThueNguoiBan)
				.queryParam("sid", objInput.sidHoaDon)
				.queryParam("fd", objInput.tuNgay)
				.queryParam("td", objInput.denNgay)
				.queryParam("form", objInput.mauSoHoaDon)
				.queryParam("serial", objInput.kyHieuHoaDon)
				.queryParam("seq", objInput.soHoaDon)
				.queryParam("btax", objInput.maSoThueNguoiMua)
				.queryParam("status", objInput.trangThai)

				.build().toString();

		Data.ghiLogRequest("url Get : " + url);

//		try {
		String response = ConnectUsingGet.getContentWithHeader(url, objInput.username, objInput.password);
		response = response.replace("//", "");
		if (response.indexOf("[")== -1) {
			result.result = response;
		} else {
			response = "{\"listHD\":" + response + "}";
			Data.ghiLogRequest(" FPT: " + response);
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(double.class, new DoubleTypeAdapter())
					.registerTypeAdapter(int.class, new IntegerTypeAdapter())
					.create();
			Response_TC r = gson.fromJson(response, Response_TC.class);


//				Object_ListHoaDon hddg = Main.objectResponseToObjectHoaDonDonGian(r);
//				ArrayList<Doc_TC> ar = new Gson().fromJson(response, Doc_TC.class);
			result.msgCode = ErrorCode.SUCCESS;
			result.msgContent = ErrorCode.MES_SUCCESS;
			result.result = ObjectMapper.responseFPT_toObject_ListHoaDon(r);
		}

//		}catch (Exception e) {
//			Data.ghiLogRequest("Lỗi : " + e);
//			result.result = e.getMessage();
//		}

		return new Gson().toJson(result);
	}
}