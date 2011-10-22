<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr"><head>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
 
<link rel="stylesheet" href="fisubsilver.css" type="text/css">

<script type="text/javascript">
_uacct = "UA-954824-1";
urchinTracker();
</script>
<SCRIPT LANGUAGE="JavaScript">
<! >
<! >
<!-- Begin
function checkrequired(which) {
var pass=true;
if (document.images) {
for (i=0;i<which.length;i++) {
var tempobj=which.elements[i];
if (tempobj.name.substring(0,8)=="required") {
if (((tempobj.type=="text"||tempobj.type=="textarea")&&
tempobj.value=='')||(tempobj.type.toString().charAt(0)=="s"&&
tempobj.selectedIndex==0)) {
pass=false;
break;
         }
      }
   }
}
if (!pass) {
shortFieldName=tempobj.name.substring(8,30).toUpperCase();
alert("Please make sure the "+shortFieldName+" field was properly completed.");
return false;
}
else
return true;
}
//  End -->
</script>
</head><body>

<table class="bodyline" border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody><tr>
<td>
<table class="topbkg" border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody><tr> 

</tr>
</tbody></table>
<table border="0" cellpadding="10" cellspacing="0" width="100%">
<tbody><tr>
<td><form action="catsubmit.php" method="post" onsubmit="return checkrequired(this)">
  <table class="forumline" border="0" cellpadding="3" cellspacing="1" width="100%">
<tbody>
<tr>
<td width="100%" height="22" class="row2"><span class="maintitle">QuestionareSelecting page</span></td>
</tr>
</tbody></table>
<br>
<table class="forumline" border="0" cellpadding="3" cellspacing="1" width="100%">
<tbody>
<tr>
  <td width="38%" class="row1"><span class="explaintitle">PLANETS ID </span> *</td>
  <td width="62%" class="row2">
  <?php
  $var=chr(ord("a")-1+$_POST['pid']);         
  echo" <label>  <input name=\"pid\" type=\"text\" value=\"  $var\" readonly=\"true\" />  </label></td>";
  
		 
?>
</td>
</tr>
<tr>
<td class="row1"><span class="explaintitle">CID</span></td>
<td class="row2"><label>
  <?php
  echo "cat";
$var=$_POST['cid'];      
   echo" <label>  <input name=\"cid\" type=\"text\" value=\"$var\" readonly=\"true\" />  </label></td>";   
		 
?>
</label></td>
</tr>
<tr>
   <?php
  include('objConnMainPage.php');
  $subid=$_POST['q1'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q1\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
   <?php
  include('objConnMainPage.php');
  $subid=$_POST['q2'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q2\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>

<tr>
    <?php
  include('objConnMainPage.php');
  $subid=$_POST['q3'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q3\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
    <?php
  include('objConnMainPage.php');
  $subid=$_POST['q4'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q4\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>

    <?php
  include('objConnMainPage.php');
  $subid=$_POST['q5'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q5\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
    <?php
  include('objConnMainPage.php');
  $subid=$_POST['q6'];
  echo"<td class=\"row1\"><span class=\"explaintitle\">question for subcat $subid </span></td>";
  echo "<td class=\"row2\"><label>";
  $dept_query="select * from question where subid='$subid'" ;
  $dept_result=mysql_query($dept_query) or die(mysql_error());
         echo"<select name=\"q6\">";
	    $num_res=mysql_num_rows($dept_result);
        for($i=1;$i<=$num_res-1;$i++)
		{
		$dept_row=mysql_fetch_array($dept_result);
      echo "<option value=\"",$i,"\">",$dept_row[q_id],"</option>";
	  };
    $dept_row=mysql_fetch_array($dept_result);
	echo"<option value=\"",$i,"\" selected=\"selected\">", $dept_row[q_id],"</option>";

echo"</select>";
?>
  </label></td>
</tr>
<tr>
<td class="cat" colspan="2"><div align="center">
  <input name="submit" value="Submit" class="mainoption" type="submit">
  &nbsp;&nbsp;
  <input value="Reset" name="reset" class="button" type="reset">
</div></td>
</tr>
</tbody></table>
<br>


</form></td>
</tr>
</tbody></table>
</td></tr></tbody></table></body></html>