<?php
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"online_shop");

	// $upload_path='image/';
    // $server_ip = gethostbyname(gethostname());
	// $arp=`arp -a $ipAddress`;
	// echo substr($arp , 12 , 14);
	// $server_ip = substr($arp , 12 , 14);
    // $upload_url='http://'.$server_ip.'/4210EA/online_shop/Products_php/images/';

	

	// echo $arp;
    
	   $product_id=$_POST['product_id'];
	   $title=$_POST['title'];
	   $buyer=$_POST['buyer'];	 	   
	   $phone=$_POST['phone'];
       $location=$_POST['location'];
       $price=$_POST['price'];
	//    $filename="IMG".rand().".jpg";
	//    $fulelocation=$upload_url.$filename;
	//    file_put_contents("images/".$filename,base64_decode($img));

			$qry="INSERT INTO `orders` (`id`, `product_id`, `title`, `buyer`, `phone`,`location`,`price`)
			      VALUES (NULL, '$product_id', '$title', '$buyer', '$phone','$location','$price')";

			$res=mysqli_query($connection,$qry);
			
			if($res==true)
			 echo "Buy succeed！";
			else
			 echo "Faile!";
?>
