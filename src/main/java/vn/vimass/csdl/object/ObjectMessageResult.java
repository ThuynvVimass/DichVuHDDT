package vn.vimass.csdl.object;

public class ObjectMessageResult {

	public Integer msgCode;
	public String msgContent;
	public Object result;
	public int total = 0;
	public int total2 = 0;
	
	public Integer getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(Integer msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Object getResultObject() {
		return result;
	}
	public void setResultObject(Object resultObject) {
		this.result = resultObject;
	}
}
