<?php
    if(isset($_POST['email']) && isset($_POST['password'])){
        require_once "connection.php";
        require_once "validate.php";
        $email = validate($_POST['email']);
        $password = validate($_POST['password']);
        $sql = "SELECT * FROM users where email = '$email' and password='" . md5($password) . "'";
        $result = $connection->query($sql);
        if($result->num_rows > 0){
            echo "success";
        }else{
            echo "failure";
        }
    }
?>