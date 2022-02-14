<?php
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"online_shop");

	$upload_path='image/';
    // $server_ip = gethostbyname(gethostname());
	$arp=`arp -a`;

	$server_ip = substr($arp , 69 , 15);
    $upload_url='http://'.$server_ip.'/4210EA/online_shop/Products_php/images/';

	

	// echo $arp;
	// echo $upload_url;
    
	   $title=$_POST['title'];
	   $describe=$_POST['describe'];
	   $price=$_POST['price'];	 	   
	   $img=$_POST['upload'];
	   $owner=$_POST['owner'];
	   $phone=$_POST['phone'];
	   $filename="IMG".rand().".jpg";
	   $fulelocation=$upload_url.$filename;
	   file_put_contents("images/".$filename,base64_decode($img));

			$qry="INSERT INTO `products` (`id`, `title`, `describe`, `price`, `photo`,`owner`,`phone`)
			      VALUES (NULL, '$title', '$describe', '$price', '$fulelocation','$owner','$phone')";

			$res=mysqli_query($connection,$qry);
			
			if($res==true)
			 echo "File Uploaded Successfully";
			else
			 echo "Could not upload File";
?>


