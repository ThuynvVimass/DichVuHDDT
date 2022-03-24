package share.object;

import FPT.FPTFunc;
import FPT.object.hoaDonTaoMoi.Object_ThongTinHoaDon;
import com.google.gson.Gson;
import share.object.objectHoaDonDonGian.Root;
import vn.vimass.utils.Data;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;

@Path("/share")
@Produces("application/json;charset=utf-8")
public class ShareWebservice {

	@Inject
	private HttpServletRequest request;

	@POST
	@Path("/createInvoices")
	public String createInvoices(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());

		Root objInput = new Gson().fromJson(input, Root.class);
		Object_ThongTinHoaDon object_thongTinHoaDon = ObjectMapper.inputToRequestFPT(objInput);
		String duLieuSauKhiMapper = new Gson().toJson(object_thongTinHoaDon);

		return FPTFunc.lapHoaDon(duLieuSauKhiMapper);

	}
	
	@POST
	@Path("/apprs")
	public String apprs(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.kyDuyetHoaDon(input);

	}

	@POST
	@Path("/updateInvoices")
	public String updateInvoices(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.suaHoaDon(input);

	}

	@POST
	@Path("/cancelInvoices")
	public String cancelInvoices(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.huyHoaDon(input);

	}

	@POST
	@Path("/replaceInvoice")
	public String replaceInvoice(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.thayTheHoaDon(input);

	}
	
	@POST
	@Path("/adjustInvoice")
	public String adjustInvoice(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.dieuChinhHoaDon(input);

	}

	@POST
	@Path("/delInvoice")
	public String delInvoice(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.xoaHoaDonChuaCapSo(input);

	}
	
	@POST
	@Path("/regMerchant")
	public String regMerchant(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.dangKyThongTinDonVi(input);
		
	}
	
	@POST
	@Path("/updateMerchant")
	public String updateMerchant(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.thayDoiThongTinDonVi(input);
		
	}
	
	@GET
	@Path("/searchMerchant")
	public String searchMerchant(@QueryParam("maSoThue") String maSoThue) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.traCuuThongTinDonViInDB(maSoThue);
		
	}
	
	@POST
	@Path("/searchInvoice")
	public String searchInvoice(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.traCuuHoaDon(input);
		
	}

	@POST
	@Path("/searchInvoice2")
	public String searchInvoice2(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.traCuuHoaDon2(input);

	}

}
