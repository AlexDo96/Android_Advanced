<?
class DB_Functions
{
	private $db;

	
	function __construct()
	{
		require_once("DB_Connect.php");
		$this->db=new DB_Connect();
		$this->db->connect();
	}
	
	function __destruct()
	{
	}
	
	//luu user va database
	public function storeUser($name,$email,$password)
	{
		$sql="INSERT INTO user
			(name,email,password,create_date)VALUES 
			('$name', '$email', '$password', NOW())";
		$result=mysql_query($sql);
		if($result==true)
		{
			$id=mysql_insert_id();//id cuoi cung cung la dulieu vua them
			$result=mysql_query("select * from user where id='$id'");
			return mysql_fetch_array($result);
		}
		else
			return false;
	}
	
	//lay thong tin user dua vao email va password
	public function getUser($email,$password)
	{
		$sql="select * from user where email='$email' and password='$password'";
		$result=mysql_query($sql) or die (mysql_error());
		$rows=mysql_num_rows($result);//lay so hang
		if($rows>0) //neu co hang tuc la co user
		{
			$result=mysql_fetch_array($result);
			//echo "co user ne";
			return $result;
		}
		else //khong co user
		{
			//echo "khong co user";
			return false;
		}
	}
	
	//kiem tra email da co nguoi dung chua
	public function checkUser($email)
	{
		$sql="select * from user where email='$email'";
		$result=mysql_query($sql);
		$rows=mysql_num_rows($result);//lay so hang
		if($rows>0) 
			return true; //user da ton tai
		else
			return false; //chua co user nay
	}
}
?>
