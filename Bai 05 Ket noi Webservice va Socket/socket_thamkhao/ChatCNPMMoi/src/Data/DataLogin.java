package Data;

import java.io.Serializable;

public class DataLogin implements Serializable {

	public String username;
	public String password;
	public Boolean ThanhCong=false;
	public DataLogin(String strUsername,String strPassword) {
		username=strUsername;
		password=strPassword;
	}
}
