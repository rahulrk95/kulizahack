<?php
require_once ('MysqliDb.php');

$branch = "CSE"//$_POST['branch'];

$db = new MysqliDb ('localhost', 'root', 'hack123', 'colgpolls');

$pids = $db->get($branch,7);
//$json = $db->JsonBuilder()->get("users");
//return $json;
foreach ($pids as $pid) 
{
	print_r($pid);
	$db->where ("pid", $pids);
	//$user = $db->getOne("polls");
	$json = $db->JsonBuilder()->get("polls");
	echo $json;
    
}
?>