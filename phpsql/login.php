<?php
//Authentication script
require_once ('MysqliDb.php');

$email_id =  "rolet.fernandes@gmail.com";         //$POST_['email_id'];
$password =	  "oliver" ;	   //$POST_['password'];

$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

$db->where ("email_id", $email_id);
$user = $db->getOne ("users");

if($user['password'] == $password)
	echo "authenticated";
else
	echo "wrong username or password";

?>