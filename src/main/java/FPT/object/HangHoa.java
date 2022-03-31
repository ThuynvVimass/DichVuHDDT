package FPT.object;

import com.google.gson.Gson;

public class HangHoa {
    public int sTT;                             // null
    public String hinhThucHangHoa;              // null
    public String thueSuat = "0";               // not null
    public String maHangHoa = "";               // null
    public String ten = "";                     // not null
    public String donViTinh = "";               // null
    public double donGia = 0;                   // not null
    public int soLuong = 0;                     // not null
    public int tyLeChietKhau = 0;               // null
    public double soTienChietKhau = 0;          // null
    public double thanhTien = 0;                // null, truyền khi không truyền gia, soLuong
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}