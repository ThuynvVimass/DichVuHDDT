package CMC;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import vn.vimass.csdl.object.ErrorCode;
import vn.vimass.csdl.object.ObjectMessageResult;
import vn.vimass.utils.Data;

@Path("/cmc")
@Produces("application/json;charset=utf-8")
public class CMCWebservice {
	
	@Inject
	private HttpServletRequest request;

	@Path("/testcmc")
	@GET
	public String test2020() {
				
		if(request != null)
		{
			Data.ghiLogRequest("IP:" + request.getRemoteAddr());
			return request.getRemoteAddr();
		}
		return "Loi";
	}

	@POST
	@Path("/apprsInvoices")
	public String apprsInvoices(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.lapVaKyDuyetHoaDon(input);		
	}
	
	@POST
	@Path("/createInvoices")
	public String createInvoices(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.lapHoaDon(input);		
	}
	
	@POST
	@Path("/apprs")
	public String apprs(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.kyDuyetHoaDon (input);		
	}
	
	@POST
	@Path("/printInvoices")
	public String printInvoices(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.inHoaDon (input);		
	}
	
	@POST
	@Path("/canceInvoices")
	public String canceInvoices(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.huyHoaDon (input);		
	}

	@POST
	@Path("/getDHDPH")
	public String getDHDPH(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.layDaiHoaDonPhatHanhSuDung(input);
	}

	@POST
	@Path("/searchCompany")
	public String searchCompany(String input) {
		Data.ghiLogRequest("IP:" + request.getRemoteAddr());
		return CMCFunc.getThongTinDonViBangMST(input);		
	}
	
	
//	@Path("/getInquiryBIDV")
//	@GET
//	public String getInquiryBIDV(@QueryParam("transId") String transId) {
//				
//		Data.ghiLogRequest("----------getInquiryBIDV-----------"); 
//		
//		return CMCFunc.getInquiryBIDV(transId);
//	}
	
	
}
