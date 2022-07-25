<?php
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"online_shop");

    
	   $title=$_POST['title'];
	   $describe=$_POST['describe'];
	   $price=$_POST['price'];	 	   
	   $img=$_POST['photo'];
	   $owner=$_POST['owner'];
	   $phone=$_POST['phone'];
	   $email=$_POST['email'];
	//    $filename="IMG".rand().".jpg";
	//    $fulelocation=$upload_url.$filename;
	//    file_put_contents("images/".$filename,base64_decode($img));

			$qry="INSERT INTO `shopping_carts` (`id`, `title`, `describe`, `price`, `photo`,`owner`,`phone`,`email`)
			      VALUES (NULL, '$title', '$describe', '$price', '$img','$owner','$phone','$email')";

			$res=mysqli_query($connection,$qry);
			
			if($res==true)
			 echo "Already add to cart.";
			else
			 echo "Add to cart faile!";
?>


