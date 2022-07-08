package Data;

import java.io.Serializable;

public class DataMessage implements Serializable {
	public String userSend;
	public String userTarget;
	public String message;
	public Boolean receive=false;
	/**
	 * Khoi tao message gui
	 * @param strUserSend Nguoi gui
	 * @param strUserTarger Nguoi nhan
	 * @param strmessage noi dung tin nhan
	 */
	public DataMessage(String strUserSend,String strUserTarger,String strmessage)
	{
		userSend=strUserSend;
		userTarget=strUserTarger;
		message=strmessage;
	}
	/**
	 * Khoi tao message nhan
	 * @param strUsernameSend Nguoi gui
	 * @param strMessage noi dung tin nhan
	 */
	public DataMessage(String strUsernameSend,String strMessage)
	{
		userSend=strUsernameSend;
		message=strMessage;
		receive=true;
		
	}

}
