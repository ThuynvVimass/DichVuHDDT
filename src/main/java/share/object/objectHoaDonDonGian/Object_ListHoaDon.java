package share.object.objectHoaDonDonGian;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Object_ListHoaDon {
	
	public ArrayList<Root> listHoaDon;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
