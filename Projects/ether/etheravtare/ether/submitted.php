<?php	
include("objConnMainPage.php");
include("Include.php");
$question=$_POST['question'];
$answer=$_POST['answer'];
$subid=$_POST['subid'];
$points=$_POST['points'];
$isimage=$_POST['isimage'];
$query= "insert into  question SET  question = \"$question\", ans= '$answer' , subid= '$subid ',points='$points',isimage='$isimage' ";
$result=mysql_query($query) or die(mysql_error());
if($result)
{
echo "<script language=\"JavaScript\">location.replace(\"ether-main.php\");</script>" ;
}
else
{
echo "<center>Connection could not be made</center>";
}
mysql_close();
setcookie("tid");
?>