<?php 
include("connect.inc.php");
session_start();
if($_SESSION['tid']!=''&&$_SESSION['cid']!='')
{
$tid=$_SESSION['tid'];
$cid=$_SESSION['cid'];
$sql="select * from team where tid='".$tid."'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
if($row)
 {
	$_SESSION['pid']=$row['current_pid'];
	echo ("Total Score = ".$row['tscore']);
}
$pid=$_SESSION['pid'];
 $sql2="select * from status where pid=$pid and tid=$tid";
$result2=mysql_query($sql2);
$row2=mysql_fetch_array($result2);

echo("<br/>You are on Planet no. = ".$pid);
echo("<br/>Planet score = ".$row2['p_score']."<br/>");
$sql="select * from questionair where pid=$pid and cid=$cid";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
echo('<form id="form1" name="form1" method="post" action="sol.php">');

for($i=1;$i<=6;$i++)
{
$quest[$i]=$row['q'.$i];
$sql1="select * from question where q_id=$quest[$i]";
$result1=mysql_query($sql1);
$row1=mysql_fetch_array($result1);
if($row2[0]%10==0)
echo("Question ".$i.": ".$row1[1]."<br/><br/> Points = ".$row1[3].'<br/><input type="text" name="ans'.$i.'"/><br/><br/>');
$row2[0]=$row2[0]/10;
}
echo ('<input name="" type="submit" /></form>');
if($row2[1]>=40) echo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='jumpup.php'>Jumpup</a><br/>");
if($row2[1]>=20) echo("<a href='jumpleft.php'>Jumpleft</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='jumpright.php'>Jumpright</a>");

}
?>