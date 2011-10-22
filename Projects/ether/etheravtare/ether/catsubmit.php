<?php	
include("objConnMainPage.php");
include("Include.php");
$q1=$_POST['q1'];
$q2=$_POST['q2'];
$q3=$_POST['q3'];
$q4=$_POST['q4'];
$q5=$_POST['q5'];
$q6=$_POST['q6'];
$pid=$_POST['pid'];
$cid=$_POST['cid'];
$query= "insert into  questionair SET q1 = \"$q1\",q2 = \"$q2\",q3 = \"$q3\",q4 = \"$q4\",q5 = \"$q5\",q6 = \"$q6\",pid='$pid',cid='$cid'";
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