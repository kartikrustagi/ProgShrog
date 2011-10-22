<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
</head>

<body>

<?php 

if($_POST['tid']!=''&&is_numeric($_POST['tid']))
{
session_start();
$tid=$_POST['tid'];
include("connect.inc.php");
$sql="select * from team where tid='".$tid."'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
if($row)
{

 $_SESSION['tid']=$tid;
 $_SESSION['pid']=$row['current_pid'];
 $_SESSION['cid']=$row['cid'];
?>
<script>window.location="planet.php";</script>
<?php
}
else echo("Wrong Team Id"); 
}
else {
?>
<form id="form1" name="form1" method="post" action="index.php">
  Team Id: 
  <input type="text" name="tid" />
  <input name="" type="submit" />
</form>
<?php } ?>
</body>
</html>