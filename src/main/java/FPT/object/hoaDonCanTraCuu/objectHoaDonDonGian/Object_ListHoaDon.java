package FPT.object.hoaDonCanTraCuu.objectHoaDonDonGian;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Object_ListHoaDon {
	
	public ArrayList<Inv> listHoaDon;

	public Object_ListHoaDon(ArrayList<Inv> listHoaDon) {
		this.listHoaDon = listHoaDon;
	}
	
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}	
}
