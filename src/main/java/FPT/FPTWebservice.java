package FPT;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import vn.vimass.utils.Data;

@Path("/fpt")
@Produces("application/json;charset=utf-8")
public class FPTWebservice {

	@Inject
	private HttpServletRequest request;
	
	@GET
	@Path("/testfpt")
	public String test2020() {

		if (request != null) {
			Data.ghiLogRequest("IP: " + request.getRemoteAddr());
			return request.getRemoteAddr();
		}
		return "Loi";
	}

	@POST
	@Path("/createInvoices")
	public String createInvoices(String input) {

		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return FPTFunc.lapHoaDon(input);

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
}
