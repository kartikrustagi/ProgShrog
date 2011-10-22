<?php
# FileName="Connection_php_mysql.htm"
# Type="MYSQL"
# HTTP="true"
$hostname_objConnMainPage = "localhost";
$database_objConnMainPage = "ether";
$username_objConnMainPage = "root";
$password_objConnMainPage = "";
$objConnMainPage = mysql_connect($hostname_objConnMainPage, $username_objConnMainPage, $password_objConnMainPage) or die(mysql_error()); 
$objselectdatabase=mysql_select_db($database_objConnMainPage,$objConnMainPage) or die(mysql_error());
?>