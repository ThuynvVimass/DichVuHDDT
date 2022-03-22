package CMC.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectHangHoaDichVu {

	public int TChat;

	public int STT;

	public java.lang.String ma;

	public java.lang.String ten;

	public java.lang.String DVTinh;

	public java.math.BigDecimal SLuong;

	public java.math.BigDecimal DGia;

	public java.math.BigDecimal TLCKhau;

	public java.math.BigDecimal STCKhau;

	public java.math.BigDecimal thTien;

	public java.lang.String TSuat;

	public java.lang.String TSGiam;

	public java.math.BigDecimal TThue;

	public java.math.BigDecimal tgTien;
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}
}
