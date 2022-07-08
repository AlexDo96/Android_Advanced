<?
	require_once 'include/DB_Functions.php';
	$db=new DB_Functions();
	$json=array();
	
	if(isset($_POST['tag'])&& $_POST['tag']!='')
	{
		$tag=$_POST['tag'];
		
		$json=array("tag"=>$tag,"thanhcong"=>0,"loi"=>0);	
	
		if($tag=='getallfiledownload')
		{
			getallfiledownload($json,$db);
		}
		else 
		{
			echo "yeu cau khong hop le";
		}
	}

	function getallfiledownload($json,$db)
	{
		$result=$db->getAllFileDownLoad();

		if(mysql_num_rows($result)>0)//co san pham
		{
			$json["thanhcong"]=1;
			$json["filedownload"]=array(); //mang con
			
			//duyet tat ca san pham dua vao json
			while($row=mysql_fetch_array($result))
			{
				$filedownload=array();
				$filedownload["id"]=$row["id"];
				$filedownload["ten"]=$row["ten"];
				$filedownload["duongdan"]=$row["duongdan"];
				$filedownload["mota"]=$row["mota"];
				
				//dua san pham vao mang
				array_push($json["filedownload"],$filedownload);
			}
		}
		else //khong co san pham
		{
			$json["thanhcong"]=0;
			$json["thongbao"]="khong co file download";
		}
		echo json_encode($json);
		
	}
?>