package FPT.object.hoaDonCanTraCuu.objectResponse;

import com.google.gson.Gson;

public class AdjRoot_TC {
	
	public String des;
	public String idt;
	public String rdt;
	public String rea;
	public String ref;
	public String seq;
	public int typ = 0;
	public String form;
	public String serial;
	public int type_ref = 0;
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}