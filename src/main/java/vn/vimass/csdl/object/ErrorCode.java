package vn.vimass.csdl.object;

public class ErrorCode {
	public final static int SUCCESS = 1;
	public final static String MES_SUCCESS = "Thành công";
	
	public final static int EXCEPTION = -1;
	public final static String MES_EXCEPTION = "Lỗi logic";
	
	public final static int NULL = -2;
	public final static String MES_NULL = "null";
	
	public final static int FALSE = 0;
	public final static String MES_FALSE = "Lỗi";
	
	public final static int PARAM_SATELESS = 2;
	public final static String MES_PARAM_SATELESS = "Thiếu tham số";
	
	public final static int ACC_EXIST = 3;
	public final static String MES_ACC_EXIST = "Tài khoản đã tồn tại";
	
	public final static int ACC_NOT_EXIST = 4;
	public final static String MES_ACC_NOT_EXIST = "Tài khoản chưa tồn tại";
	
	
	public final static int PASS_NOT_MATCH = 5;
	public final static String MES_PASS_NOT_MATCH = "Mật khẩu không khớp";
	
	public final static int OTP_NOT_MATCH = 6;
	public final static String MES_OTP_NOT_MATCH = "OTP không khớp";
	
	public final static int ACC_LOCK = 7;
	public final static String MES_ACC_LOCK = "Tài khoản bị khóa";
	public final static String MES_ACC_LOCK_FOR_TIME = "Tài khoản bị khóa! Vui lòng đăng nhập lại sau %s";
	
	public final static int OTP_EXPIRED = 8;
	public final static String MES_OTP_EXPIRED = "OTP hết hạn";
	
	public final static int OTP_HAD_BEEN_SENT = 9;
	public final static String MES_OTP_HAD_BEEN_SENT = "OTP vừa được gửi đi";
	
	public final static int ACC_IS_EDITTING = 10;
	public final static String MES_ACC_IS_EDITTING = "Tài khoản đang thay đổi, vui lòng thử lại sau";
	
	public final static int ACC_FROM_NOT_EXIST = 11;
	public final static String MES_ACC_FROM_NOT_EXIST = "Tài khoản chuyển tiền không tồn tại";
	
	public final static int ACC_FROM_IS_LOCKED = 12;
	public final static String MES_ACC_FROM_IS_LOCKED = "Tài khoản chuyển tiền đang bị khóa";
	
	public final static int ACC_FROM_IS_NOT_ENOUGHT_MONEY = 13;
	public final static String MES_ACC_FROM_IS_NOT_ENOUGHT_MONEY = "Tài khoản chuyển không đủ tiền";
	
	public final static int ACC_TO_NOT_EXIST = 14;
	public final static String MES_ACC_TO_NOT_EXIST = "Tài khoản nhận tiền không tồn tại!";
	
	
	public final static int ACC_TO_IS_LOCKED = 15;
	public final static String MES_ACC_TO_IS_LOCKED = "Tài khoản nhận tiền đang bị khóa";
	

	public final static int PHONE_EMPTY = 16;
	public final static String MES_PHONE_EMPTY = "Số điện thoại không được để trống";
	
	public final static int PHONE_INVALID = 17;
	public final static String MES_PHONE_INVALID = "Số điện thoại sai định dạng";
	
	public final static int OTP_EMPTY = 18;
	public final static String MES_OTP_EMPTY = "OTP không được để trống";
	
	public final static int OTP_INVALID = 19;
	public final static String MES_OTP_INVALID = "OTP sai định dạng";
	
	public final static int SERIAL_EMPTY = 20;
	public final static String MES_SERIAL_EMPTY = "Thiếu số serial number";
	
	public final static int SERIAL_INVALID = 21;
	public final static String MES_SERIAL_INVALID = "Số serial sai định dạng";
	
	public final static int TOKEN_TYPE_EMPTY = 22;
	public final static String MES_TOKEN_TYPE_EMPTY = "Thiếu loại token đang sử dụng";
	
	public final static int TOKEN_TYPE_INVALID = 23;
	public final static String MES_TOKEN_TYPE_INVALID = "Loại token đang sử dụng sai định dạng";
	
	
	public final static int ACC_NEED_LOGIN = 26;
	public final static String MES_ACC_NEED_LOGIN = "Vui lòng đăng nhập lại trước khi thực hiện chức năng này!";
	
	
	

	public final static int ACC_NOT_ADMIN = 27;
	public final static String MES_ACC_NOT_ADMIN = "Bạn không phải admin!";
	
	public final static int FUNC_COMMING_SOON = 28;
	public final static String MES_FUNC_COMMING_SOON = "Chức năng đang phát triển!";
	
	public final static int PHONE_AUTHENTICATE_NOT_REGISTER = 29;
	public final static String MES_PHONE_AUTHENTICATE_NOT_REGISTER = "Bạn chưa đăng ký số điện thoại để nhận OTP!";
	
	public final static int EMAIL_NOT_REGISTER = 30;
	public final static String MES_EMAIL_NOT_REGISTER = "Bạn chưa đăng ký email để nhận OTP!";
	
	
	public final static int OTP_WILL_SEND_TO_PHONE = 31;
	public final static String MES_OTP_WILL_SEND_TO_PHONE = "OTP vừa được gửi về tin nhắn!";
	
	public final static int OTP_WILL_SEND_TO_EMAIL = 32;
	public final static String MES_OTP_WILL_SEND_TO_EMAIL = "OTP vừa được gửi về email!";
	
	public final static int DA_DANG_KY_TOKEN = 33;
	public final static String MES_DA_DANG_KY_TOKEN = "Tài khoản đã đăng kí SoftToken. Vui lòng xác thực thông qua 6 số Token!";
	

	public final static int YEU_CAU_KHONG_HOP_LE = 34;
	public final static String MES_YEU_CAU_KHONG_HOP_LE = "Yêu cầu không hợp lệ!";
	

	public final static int TAI_KHOAN_KHONG_HOP_LE = 34;
	public final static String MES_TAI_KHOAN_KHONG_HOP_LE = "Tài khoản không hợp lệ!";
	

	public final static int SO_THE_KHONG_HOP_LE = 34;
	public final static String MES_SO_THE_KHONG_HOP_LE = "Số thẻ không hợp lệ!";
	

	public final static int HAN_MUC_DA_TON_TAI = 35;
	public final static String MES_HAN_MUC_DA_TON_TAI = "Hạn mức đã tồn tại!";
	
	public final static int HAN_MUC_KHONG_TON_TAI = 36;
	public final static String MES_HAN_MUC_KHONG_TON_TAI = "Hạn mức không tồn tại!";
	
	public final static int HAN_MUC_THEO_LAN_NGAY_THANG_KHONG_PHU_HOP = 37;
	public final static String MES_HAN_MUC_THEO_LAN_NGAY_THANG_KHONG_PHU_HOP = "Hạn mức mỗi lần chuyển phải bé hơn hạn mức trong 1 ngày và hạn mức trong một ngày phải bé hơn hạn mức trong 1 tháng!";
	
	public final static int TAI_KHOAN_BI_KHOA_10P = 38;
	public final static String MES_TAI_KHOAN_BI_KHOA_10P = "Tài khoản bị khóa 10 phút!";

	public final static int TAI_KHOAN_BI_KHOA_30P = 39;
	public final static String MES_TAI_KHOAN_BI_KHOA_30P = "Tài khoản bị khóa 30 phút!";

	public final static int TAI_KHOAN_BI_KHOA_1TIENG = 40;
	public final static String MES_TAI_KHOAN_BI_KHOA_1TIENG = "Tài khoản bị khóa 1 tiếng!";
	
	public final static int TAI_KHOAN_BI_KHOA_1NGAY = 41;
	public final static String MES_TAI_KHOAN_BI_KHOA_1NGAY = "Tài khoản bị khóa 1 ngày!";
	
	public final static int YEU_CAU_HET_HIEU_LUC = 42;
	public final static String MES_YEU_CAU_HET_HIEU_LUC = "Yêu cầu hết hiệu lực!";
	
	public final static int VCB_SO_TIEN_CHUYEN_KHONG_HOP_LE = 43;
	public final static String MES_VCB_SO_TIEN_CHUYEN_KHONG_HOP_LE = "Số tiền chuyển phải lớn hơn 1000 vnđ!";
	
	public static ObjectMessageResult getObjectMessageResult(int code, String mess)
	{
		ObjectMessageResult obj = new ObjectMessageResult();
		obj.msgCode = code;
		obj.msgContent = mess;
		return obj;		
	}
	
}
