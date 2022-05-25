<?php
include "connection.php";
	$upload_path='images/';
    $upload_url='4210EA/online_shop/Products_php/images/';

	

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
	   file_put_contents($upload_path.$filename,base64_decode($img));

			$qry="INSERT INTO `products` (`id`, `title`, `describe`, `price`, `photo`,`owner`,`phone`)
			      VALUES (NULL, '$title', '$describe', '$price', '$fulelocation','$owner','$phone')";

			$res=mysqli_query($connection,$qry);
			
			if($res==true)
			 echo "File Uploaded Successfully";
			else
			 echo "Could not upload File";
?>


