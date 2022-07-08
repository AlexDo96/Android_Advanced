package Data;

public class User {
	
	private String username,password;
	public User(String strUserName,String strPassWord)
	{
		username=strUserName;
		password=strPassWord;
	}
	public String getUserName()
	{
	   return username;
	}
	public void setUserName(String strUsername)
	{
		this.username=strUsername;
	}
	public String getPassWord()
	{
	   return password;
	}
	public void setPassWord(String strPassWord)
	{
		this.password=strPassWord;
	}

}
