<?php
class DB_Connect{
	//ham tao
	function __construct()
	{
		
	}
	
	//ham huy
	function __destruct()
	{
		
	}
//ket noi toi database
	function connect()
	{
		require_once "config.php";
		$con=mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
		mysql_select_db(DB_DATABASE,$con);
		return $con;
	}
	//dong ket noi
	function close()
	{
		mysql_close();
	}

	
}
?>