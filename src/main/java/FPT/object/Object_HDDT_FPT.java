package FPT.object;

import com.google.gson.Gson;

public class Object_HDDT_FPT {
	public String lang = "";
    public User user = new User();
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
