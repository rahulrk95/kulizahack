<?php
require_once ('MysqliDb.php');
/* Extracts all details of particular question */

$pid = '20';//$_POST['pid'];

$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

$db->where ("pid", $pid);
$json = $db->JsonBuilder()->getOne("polls");
echo $json;

?>

