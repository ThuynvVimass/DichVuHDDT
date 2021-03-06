package FPT;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.UriBuilder;

import FPT.object.HangHoa;
import FPT.object.Item;
import FPT.object.User;
import FPT.object.hoaDonHuy.ItemsHuy;
import FPT.table.ObjectTableHoaDonFPT;
import FPT.table.TableHoaDonFPT;
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

		Inv inv = new Inv();
		ThongTinDonVi thongTinDonVi = null;
		thongTinDonVi = getThongTinDonVi(objInput.maSoThueNguoiBan);
		if (thongTinDonVi != null) {
			objRoot.user.username = thongTinDonVi.maSoThue + "." + thongTinDonVi.user;
			objRoot.user.password = thongTinDonVi.password;

			inv.form = thongTinDonVi.mauSoHoaDon;		// m???u h??a ????n ???? ????ng k?? tr??n web
			inv.serial = thongTinDonVi.kyHieuHoaDon; 	// k?? hi???u h??a ????n ???? ????ng k??
		}
		if (objInput.sidHoaDon.equals("")) {
//			inv.sid = VimassCommon.generateSessionKey(15);
			result.result = "L???i: thi???u th??ng tin sidHoaDon";
			return new Gson().toJson(result);
		}
		else
			inv.sid = objInput.sidHoaDon;
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		if (objInput.loaiHoaDon.equals(FPTUltis.HOADON_GTGT)
		|| objInput.loaiHoaDon.equals("")) inv.type = FPTUltis.HOADON_GTGT;
			else inv.type = objInput.loaiHoaDon;
//		inv.form = "1"; 		// m???u h??a ????n ???? ????ng k?? tr??n web
//		inv.serial = "C22TAA"; 	// k?? hi???u h??a ????n ???? ????ng k??
		inv.seq =  ""; 			// s??? h??a ????n, kh??ng ??i???n v??o th?? s??? t??? t??ng
		inv.bcode =  ""; 		// m?? kh??ch h??ng, kh??ng b???t bu???c

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
		if (objInput.hinhThucThanhToan != null)
			inv.paym =  objInput.hinhThucThanhToan;
		inv.bacc =  objInput.soTaiKhoanNguoiMua;
		inv.bbank =  objInput.nganHangNguoiMua;
		inv.note =  objInput.ghiChu;

		inv.sum = 0;
		inv.vat = 0;
		ArrayList<Item> items = new ArrayList<>();
		for (HangHoa hangHoa: objInput.listHHDV){
			Item item_ = taoDuLieu_Item(hangHoa);
			items.add(item_);
			inv.vat += item_.vat;
			inv.sum += item_.amount;
		}
		inv.items = items;

		inv.curr =  objInput.tienTe;
		inv.exrt =  objInput.tiGia;
		inv.sumv =  inv.sum * inv.exrt;
		inv.vatv =  inv.vat  * inv.exrt;

		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney); // Khi s??? ti???n qu?? l???n, h??? th???ng s??? hi???n th??? d???ng a10^b ().
//															 //	FPT kh??ng y??u c???u, n??n gi???i quy???t nhanh nh???t, b??? qua
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2; 			// 1 t??? qu???n l?? c???p s??? H??, 2 h??? th???ng c???p s??? h??a ????n t??ng d???n
		inv.type_ref =  1;		// 1 h??a ????n TT78
		inv.listnum =  ""; 		// b???ng k??
		inv.listdt =  ""; 		// list b???ng k??
		inv.sendtype =  1;  	// 1 chuy???n ?????n TTC lu??n, 2 chuy???n g???p
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
					// L??u th??ng tin ho?? ????n l???p th??nh c??ng v??o DB
					String kq = TableHoaDonFPT.taoDuLieu(r);
					if (kq!=""){
						result.msgCode = ErrorCode.SUCCESS;
						result.msgContent = ErrorCode.MES_SUCCESS;
					} else {
						Data.ghiLogRequest("saveToDB: " + "T???o ho?? ????n th??nh c??ng nh??ng l???i khi l??u d??? li???u v??o DB");
						result.msgContent = "T???o ho?? ????n th??nh c??ng nh??ng l???i khi l??u d??? li???u";
					}
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

		objRoot.user = getUserDonVi(objInput.maSoThueNguoiBan);

		objRoot.inv.sid = objInput.sidHoaDon;
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

		Inv inv = new Inv();
		ThongTinDonVi thongTinDonVi = null;
		thongTinDonVi = getThongTinDonVi(objInput.maSoThueNguoiBan);
		if (thongTinDonVi != null) {
			objRoot.user.username = thongTinDonVi.maSoThue + "." + thongTinDonVi.user;
			objRoot.user.password = thongTinDonVi.password;

			inv.form = thongTinDonVi.mauSoHoaDon;		// m???u h??a ????n ???? ????ng k?? tr??n web
			inv.serial = thongTinDonVi.kyHieuHoaDon; 	// k?? hi???u h??a ????n ???? ????ng k??
		}

		inv.sid = objInput.sidHoaDon;
		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
//		inv.form = "1"; 		// m???u h??a ????n ???? ????ng k?? tr??n web
//		inv.serial = "C22TAA"; 	// k?? hi???u h??a ????n ???? ????ng k??
		inv.seq =  ""; 			// s??? h??a ????n, kh??ng ??i???n v??o th?? s??? t??? t??ng
		inv.bcode =  ""; 		// m?? kh??ch h??ng, kh??ng b???t bu???c

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

		inv.sum = 0;
		inv.vat = 0;
		ArrayList<Item> items = new ArrayList<>();
		for (HangHoa hangHoa: objInput.listHHDV){
			Item item_ = taoDuLieu_Item(hangHoa);
			items.add(item_);
			inv.vat += item_.vat;
			inv.sum += item_.amount;
		}
		inv.items = items;

		inv.curr =  objInput.tienTe;
		inv.exrt =  objInput.tiGia;
		inv.sumv =  inv.sum * inv.exrt;
		inv.vatv =  inv.vat  * inv.exrt;

		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2; 			// 1 t??? qu???n l?? c???p s??? H??, 2 h??? th???ng c???p s??? h??a ????n t??ng d???n
		inv.type_ref =  1; 		// 1 h??a ????n TT78
		inv.listnum =  ""; 		// b???ng k??
		inv.listdt =  ""; 		// list b???ng k??
		inv.sendtype =  1;  	// 1 chuy???n ?????n TTC lu??n, 2 chuy???n g???p
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

				objRoot.user = getUserDonVi(objInput.maSoThueNguoiBan);

				WrongNotice wrongNotice = new WrongNotice();
				wrongNotice.stax = objInput.maSoThueNguoiBan;
				wrongNotice.noti_taxtype = objInput.loaiThongBaoHuy;
				wrongNotice.noti_taxnum = objInput.soThongBaoCQT;
				wrongNotice.noti_taxdt = objInput.ngayThongBaoCQT;
				wrongNotice.budget_relationid = objInput.maDonViPhuThuoc;
				wrongNotice.place = objInput.diaDanh;

				// L???y th??ng tin ho?? ????n mu???n hu??? th??ng qua sidHoaDon
				ObjectTableHoaDonFPT thongTinHoaDon = traCuuThongTinHoaDon (objInput.sidHoaDon, objRoot.user);
				if (thongTinHoaDon != null){
					wrongNotice.items = taoDuLieu_ThongTinHoaDonHuy (thongTinHoaDon, objInput.lyDo);
				} else {
					result.msgContent = "Kh??ng t??m th???y ho?? ????n v???i sidHoaDon: " + objInput.sidHoaDon;
					return new Gson().toJson(result);
				}

				objRoot.wrongnotice = wrongNotice;

				String json = new Gson().toJson(objRoot);
				Data.ghiLogRequest("input FPT: " + json);

				String response = PostBody.callService(FPTUltis.URL_HUY_HOADON, json);

				if(response.substring(0,3).equals("200"))
				{
					result.msgCode = ErrorCode.SUCCESS;
					result.msgContent = ErrorCode.MES_SUCCESS;
				}
					result.result = response.substring(3);
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

		ThongTinDonVi thongTinDonVi = null;
		Inv_ThayThe inv = new Inv_ThayThe();
		thongTinDonVi = getThongTinDonVi(objInput.maSoThueNguoiBan);
		if (thongTinDonVi != null) {
			objRoot.user.username = thongTinDonVi.maSoThue + "." + thongTinDonVi.user;
			objRoot.user.password = thongTinDonVi.password;

			inv.form = thongTinDonVi.mauSoHoaDon;		// m???u h??a ????n ???? ????ng k?? tr??n web
			inv.serial = thongTinDonVi.kyHieuHoaDon; 	// k?? hi???u h??a ????n ???? ????ng k??
		}

		Adj adj = new Adj();
		inv.adj = adj;
		inv.adj.rdt = objInput.ngayVanBanThoaThuan;
		inv.adj.rea = objInput.lyDoDieuChinh;
		inv.adj.ref = objInput.soVanBanThoaThuan;

		if (objInput.sidHoaDon.equals("")) {
			result.result = "L???i: thi???u th??ng tin sidHoaDon";
			return new Gson().toJson(result);
		}
		else
			inv.sid = objInput.sidHoaDon;
		if (objInput.sidHoaDonCu.equals("")) {
			result.result = "L???i: thi???u th??ng tin sidHoaDonCu";
			return new Gson().toJson(result);
		}
		// L???y th??ng tin ho?? ????n mu???n thay th??? th??ng qua sidHoaDonCu
		ObjectTableHoaDonFPT thongTinHoaDon = traCuuThongTinHoaDon (objInput.sidHoaDonCu, objRoot.user);
		if (thongTinHoaDon != null){
			inv.adj.seq = taoDuLieu_ThongTinHoaThayThe (thongTinHoaDon);
		} else {
			result.msgContent = "Kh??ng t??m th???y ho?? ????n v???i sidHoaDon: " + objInput.sidHoaDonCu;
			return new Gson().toJson(result);
		}

		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
//		inv.form = "1"; 		// m???u h??a ????n ???? ????ng k?? tr??n web
//		inv.serial = "C22TAA"; 	// k?? hi???u h??a ????n ???? ????ng k??
		inv.seq =  ""; 			// s??? h??a ????n, kh??ng ??i???n v??o th?? s??? t??? t??ng
		inv.bcode =  ""; 		// m?? kh??ch h??ng, kh??ng b???t bu???c

		try {
			ThongTin thongTinNguoiMua = new ThongTin();
			ObjectTraCuuThongTinDoanhNghiep input_tracuu = new ObjectTraCuuThongTinDoanhNghiep();
			input_tracuu.maSoThue = objInput.maSoThueNguoiMua;
			input_tracuu.md5String = VimassCommon.bamMD5(objInput.maSoThueNguoiMua+"vsrbgjO6nV7M2SO");
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

		inv.sum = 0;
		inv.vat = 0;
		ArrayList<Item> items = new ArrayList<>();
		for (HangHoa hangHoa: objInput.listHHDV){
			Item item_ = taoDuLieu_Item(hangHoa);
			items.add(item_);
			inv.vat += item_.vat;
			inv.sum += item_.amount;
		}
		inv.items = items;

		inv.curr =  objInput.tienTe;
		inv.exrt =  objInput.tiGia;
		inv.sumv =  inv.sum * inv.exrt;
		inv.vatv =  inv.vat  * inv.exrt;

		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2;			// 1 t??? qu???n l?? c???p s??? H??, 2 h??? th???ng c???p s??? h??a ????n t??ng d???n
		inv.type_ref =  1; 		// 1 h??a ????n TT78
		inv.listnum =  ""; 		// b???ng k??
		inv.listdt =  ""; 		// list b???ng k??
		inv.sendtype =  1;  	// 1 chuy???n ?????n TTC lu??n, 2 chuy???n g???p
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

		ThongTinDonVi thongTinDonVi = null;
		Inv_ThayThe inv = new Inv_ThayThe();
		thongTinDonVi = getThongTinDonVi(objInput.maSoThueNguoiBan);
		if (thongTinDonVi != null) {
			objRoot.user.username = thongTinDonVi.maSoThue + "." + thongTinDonVi.user;
			objRoot.user.password = thongTinDonVi.password;

			inv.form = thongTinDonVi.mauSoHoaDon;		// m???u h??a ????n ???? ????ng k?? tr??n web
			inv.serial = thongTinDonVi.kyHieuHoaDon; 	// k?? hi???u h??a ????n ???? ????ng k??
		}

		Adj adj = new Adj();
		inv.adj = adj;
		inv.adj.rdt = objInput.ngayVanBanThoaThuan;
		inv.adj.rea = objInput.lyDoDieuChinh;
		inv.adj.ref = objInput.soVanBanThoaThuan;

		if (objInput.sidHoaDon.equals("")) {
			result.result = "L???i: thi???u th??ng tin sidHoaDon";
			return new Gson().toJson(result);
		}
		else
			inv.sid = objInput.sidHoaDon;

		// L???y th??ng tin ho?? ????n mu???n ??i???u ch???nh th??ng qua sidHoaDonCu
		ObjectTableHoaDonFPT thongTinHoaDon = traCuuThongTinHoaDon (objInput.sidHoaDonCu, objRoot.user);
		if (thongTinHoaDon != null){
			inv.adj.seq = taoDuLieu_ThongTinHoaThayThe (thongTinHoaDon);
		} else {
			result.msgContent = "Kh??ng t??m th???y ho?? ????n v???i sidHoaDon: " + objInput.sidHoaDonCu;
			return new Gson().toJson(result);
		}

		inv.idt = VimassCommon.getTimeyyyyddMM_HHmmss(new Date().getTime());
		inv.type = FPTUltis.HOADON_GTGT;
//		inv.form = "1"; 		// m???u h??a ????n ???? ????ng k?? tr??n web
//		inv.serial = "C22TAA"; 	// k?? hi???u h??a ????n ???? ????ng k??
		inv.seq =  ""; 			// s??? h??a ????n, kh??ng ??i???n v??o th?? s??? t??? t??ng
		inv.bcode =  ""; 		// m?? kh??ch h??ng, kh??ng b???t bu???c

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

		inv.sum = 0;
		inv.vat = 0;
		ArrayList<Item> items = new ArrayList<>();
		for (HangHoa hangHoa: objInput.listHHDV){
			Item item_ = taoDuLieu_Item(hangHoa);
			items.add(item_);
			inv.vat += item_.vat;
			inv.sum += item_.amount;
		}
		inv.items = items;

		inv.curr =  objInput.tienTe;
		inv.exrt =  objInput.tiGia;
		inv.sumv =  inv.sum * inv.exrt;
		inv.vatv =  inv.vat  * inv.exrt;

		double totalMoney = inv.sumv + inv.vatv;
//		inv.word =  FPTUltis.convertMoneyToWord(totalMoney);
		inv.total =  totalMoney;
		inv.totalv =  totalMoney * inv.exrt;
		inv.tradeamount =  0;
		inv.discount =  0;
		inv.aun =  2;			// 1 t??? qu???n l?? c???p s??? H??, 2 h??? th???ng c???p s??? h??a ????n t??ng d???n
		inv.type_ref =  1; 		// 1 h??a ????n TT78
		inv.listnum =  ""; 		// b???ng k??
		inv.listdt =  ""; 		// list b???ng k??
		inv.sendtype =  1;  	// 1 chuy???n ?????n TTC lu??n, 2 chuy???n g???p
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
	// Kh??ng test ??c. L???i: "504 Gateway Time-out" tr??n api ch??nh c???a FPT
	{
		Data.ghiLogRequest("================ xoaHoaDonChuaCapSo() ================");
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;

		Object_XoaHoaDonChuaCapSo objInput = new Gson().fromJson(input, Object_XoaHoaDonChuaCapSo.class);

		Object_XoaHoaDonChuaCapSo_FPT objRoot = new Object_XoaHoaDonChuaCapSo_FPT();
		objRoot.lang = "vn";

		objRoot.user = getUserDonVi(objInput.maSoThueNguoiBan);

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

	public static String traCuuHoaDonChiTiet(String input)
	{
		Data.ghiLogRequest("================ traCuuHoaDon() ================");
		Data.ghiLogRequest("input " + input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;

		Object_TraCuuHoaDon objInput = new Gson().fromJson(input, Object_TraCuuHoaDon.class);

		User user = getUserDonVi(objInput.maSoThueNguoiBan);
		objInput.username = user.username;
		objInput.password = user.password;

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
//			Data.ghiLogRequest("L???i : " + e);			
//			result.result = e.getMessage();
//		}	

		return new Gson().toJson(result);
	}

	public static String traCuuHoaDonChiTiet2(String input)
	{
		Data.ghiLogRequest("================ traCuuHoaDon2() ================");
		Data.ghiLogRequest("input " + input);
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;

		Object_TraCuuHoaDon objInput = new Gson().fromJson(input, Object_TraCuuHoaDon.class);

		User user = getUserDonVi(objInput.maSoThueNguoiBan);
		objInput.username = user.username;
		objInput.password = user.password;

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
//			Data.ghiLogRequest("L???i : " + e);
//			result.result = e.getMessage();
//		}

		return new Gson().toJson(result);
	}

	private static Item taoDuLieu_Item(HangHoa hangHoa) {
		if (hangHoa.thueSuat.equals("")) hangHoa.thueSuat = "0";
		Item item_ = new Item();
		item_.line = hangHoa.sTT;
		item_.type = hangHoa.hinhThucHangHoa;
		item_.vrt = hangHoa.thueSuat;
		item_.code = hangHoa.maHangHoa;
		item_.name = hangHoa.ten;
		item_.unit = hangHoa.donViTinh;
		item_.price = hangHoa.donGia;
		item_.quantity = hangHoa.soLuong;
		item_.perdiscount = hangHoa.tyLeChietKhau;

		if (hangHoa.donGia == 0 && hangHoa.soLuong == 0){
			item_.amount = hangHoa.thanhTien;
			item_.amtdiscount = hangHoa.soTienChietKhau;
		} else {
			item_.amount = Math.round(hangHoa.donGia * hangHoa.soLuong * (100 - hangHoa.tyLeChietKhau) / 100);
			item_.amtdiscount = Math.round(hangHoa.donGia * hangHoa.soLuong * hangHoa.tyLeChietKhau / 100);
		}
		int thueXuat = Integer.parseInt(hangHoa.thueSuat);
		if (thueXuat <= 0) thueXuat = 0;
		item_.vat = Math.round( item_.amount * thueXuat / 100);
		item_.total = item_.amount + item_.vat;
		return item_;
	}

	private static ThongTinDonVi getThongTinDonVi(String maSoThueNguoiBan) {
		ThongTinDonVi donvi = new ThongTinDonVi();
		try {
			donvi = TableDonVi.getThongTinDonVi(maSoThueNguoiBan);
			Data.ghiLogRequest("donvi: " + donvi);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		return donvi;
	}

	private static User getUserDonVi(String maSoThueNguoiBan) {
		User user = new User();
		try {
			ThongTinDonVi donvi = TableDonVi.getThongTinDonVi(maSoThueNguoiBan);
			user.username = donvi.maSoThue + "." + donvi.user;
			user.password = donvi.password;
			Data.ghiLogRequest("user: " + user.username);
		}catch (Exception e) {
			Data.ghiLogRequest("getThongTinDonVi Exception= " + e.getMessage());
		}
		return user;
	}

	private static ArrayList<ItemsHuy> taoDuLieu_ThongTinHoaDonHuy(ObjectTableHoaDonFPT thongTinHoaDon, String lyDo) {
		ItemsHuy itemsHuy = new ItemsHuy();
		itemsHuy.form = thongTinHoaDon.mauSoHoaDon;
		itemsHuy.serial = thongTinHoaDon.kiHieuHoaDon;
		itemsHuy.seq = thongTinHoaDon.soHoaDon;
		itemsHuy.idt = thongTinHoaDon.thoiGianFPT;
		itemsHuy.type_ref = 1;
		itemsHuy.noti_type = "1";
		itemsHuy.rea = lyDo;

		ArrayList<ItemsHuy> items = new ArrayList<>();
		items.add(itemsHuy);
		return items;
	}

	private static ObjectTableHoaDonFPT traCuuHoaDonTuFPT(User user, String sidHoaDon)
	{
		Data.ghiLogRequest("================ traCuuHoaDon3() ================");
		Data.ghiLogRequest("input: sidHoaDon " + sidHoaDon + ", user " + new Gson().toJson(user));
		ObjectMessageResult result = new ObjectMessageResult();
		result.msgCode = ErrorCode.FALSE;
		result.msgContent = ErrorCode.MES_FALSE;
		ObjectTableHoaDonFPT objectTableHoaDonFPT = null;

		String dinhDangMuonTraVe = "json";
		String url = FPTUltis.URL_TRACUU_HOADON;

		url = UriBuilder.fromUri(url).queryParam("type", dinhDangMuonTraVe)
					  .queryParam("stax", user.username.split("\\.")[0])
					  .queryParam("sid", sidHoaDon)

					  .build().toString();

		Data.ghiLogRequest("url Get : " + url);
		String response = ConnectUsingGet.getContentWithHeader(url, user.username, user.password);
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
			objectTableHoaDonFPT = new ObjectTableHoaDonFPT();
			objectTableHoaDonFPT.sidHoaDon = sidHoaDon;
			objectTableHoaDonFPT.kiHieuHoaDon = r.listHD.get(0).doc.serial;
			objectTableHoaDonFPT.mauSoHoaDon = r.listHD.get(0).doc.form;
			objectTableHoaDonFPT.soHoaDon = r.listHD.get(0).doc.seq;
			objectTableHoaDonFPT.thoiGianFPT = r.listHD.get(0).doc.idt;

//				Object_ListHoaDon hddg = Main.objectResponseToObjectHoaDonDonGian(r);
//				ArrayList<Doc_TC> ar = new Gson().fromJson(response, Doc_TC.class);
			result.msgCode = ErrorCode.SUCCESS;
			result.msgContent = ErrorCode.MES_SUCCESS;
			result.result = r;
		}
		return objectTableHoaDonFPT;
	}

	private static ObjectTableHoaDonFPT traCuuThongTinHoaDon (String sidHoaDon, User user){
		// L???y th??ng tin ho?? ????n mu???n hu??? t??? DB - th??ng qua sidHoaDon
		ObjectTableHoaDonFPT thongTinHoaDon = TableHoaDonFPT.layDuLieu(sidHoaDon);
		if (thongTinHoaDon == null){
			// N???u kh??ng th???y th?? l???y th??ng tin t??? DB, t??m ki???m qua FPT
			thongTinHoaDon = traCuuHoaDonTuFPT(user, sidHoaDon );
		}
		return thongTinHoaDon;
	}

	private static String taoDuLieu_ThongTinHoaThayThe(ObjectTableHoaDonFPT thongTinHoaDon) {
		return  thongTinHoaDon.mauSoHoaDon + "-" + thongTinHoaDon.kiHieuHoaDon + "-" + thongTinHoaDon.soHoaDon;
	}


}