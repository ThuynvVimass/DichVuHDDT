package CMC.object;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		String input = "{\r\n"
				+ "    \"kieuPhatHanh\": 0,\r\n"
				+ "    \"listHHDV\": [\r\n"
				+ "        {\r\n"
				+ "            \"STT\": 1,\r\n"
				+ "            \"Ten\": \"Điện thoại Samsung Galaxy A52\",\r\n"
				+ "            \"DVTinh\": \"cai\",\r\n"
				+ "            \"SLuong\": 1,\r\n"
				+ "            \"DGia\": 7200000,\r\n"
				+ "            \"TLCKhau\": 0,\r\n"
				+ "            \"STCKhau\": 0,\r\n"
				+ "            \"ThTien\": 7200000,\r\n"
				+ "            \"TSuat\": \"\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"MSTNBan\": \"0105906169\",\r\n"
				+ "    \"MSTNMua\": \"0105906169-001\",\r\n"
				+ "    \"EmailNMua\": \"thuynv.vimass@gmail.com\"\r\n"
				+ "    \r\n"
				+ "}";
		ObjectThongTinHoaDon objHD = new Gson().fromJson(input, ObjectThongTinHoaDon.class);

//		System.out.println(objHD.toString());
		System.out.println("012345".substring(4));
	}

}
