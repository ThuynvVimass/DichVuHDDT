package FPT.object.hoaDonCanTraCuu.objectResponse;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Response_TC {

    public ArrayList<Root_TC> listHD = new  ArrayList<Root_TC>();		// list Hoá đơn kết quả
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
    
}
