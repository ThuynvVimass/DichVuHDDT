package FPT.object.hoaDonTaoMoi;

import java.util.ArrayList;

import com.google.gson.Gson;

import FPT.object.Item;

public class Object_ThongTinHoaDon {

	public String maSoThueNguoiBan;
	public String maSoThueNguoiMua;
	public String loaiHoaDon;
	public String tenNguoiMua;
	public String soDienThoaiNguoiMua;
	public String emailNguoiMua;
	public String hinhThucThanhToan;
	public String soTaiKhoanNguoiMua;
	public String nganHangNguoiMua;
	public String ghiChu;
	public double tiGia = 1;
	public double tongTienChuaThueNguyenTe;
	public double tongTienThueNguyenTe;
	
	public ArrayList<Item> items;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
