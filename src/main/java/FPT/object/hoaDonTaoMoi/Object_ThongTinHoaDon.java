package FPT.object.hoaDonTaoMoi;

import java.util.ArrayList;

import FPT.object.HangHoa;
import com.google.gson.Gson;

import FPT.object.Item;

public class Object_ThongTinHoaDon {

	public String maSoThueNguoiBan;
	public String maSoThueNguoiMua;
	public String loaiHoaDon = "";					// "01GTKT" là hoá đơn GTGT - mặc định
													// "02GTTT" là hoá đơn bán hàng
													// "07KPTQ" là hoá đơn khu phi thuế quan
													// "03XKNB" là hoá đơn vận chuyển nội bộ
													// "03XKNB" là hoá đơn vận chuyển nội bộ
	public String tenNguoiMua;
	public String soDienThoaiNguoiMua;
	public String emailNguoiMua;
	public String hinhThucThanhToan;				// null
	public String soTaiKhoanNguoiMua;				// null
	public String nganHangNguoiMua;					// null
	public String ghiChu;							// null
	public double tiGia = 1;						// null
	public String tienTe = "VND";					// null
	
	public ArrayList<HangHoa> listHHDV;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
