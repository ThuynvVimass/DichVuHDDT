package FPT.object.hoaDonTaoMoi;

import com.google.gson.Gson;

import FPT.object.User;

public class Object_HDDT_FPT {
	public String lang = "";
    public User user = new User();
    public Inv inv = new Inv();  
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
