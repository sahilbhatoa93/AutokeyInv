<?php
$servername = "localhost";
$username = "root";
$password = "sahil";

// Create connection
$conn = new mysqli($servername, $username, $password);

$i=0; 
$q=mysql_query('select DISTINCT sku from inventory;');

 while($row=mysql_fetch_array($q)){

  echo "myarray[".$i."]='".$row['data']."';";
      $i++;

 }
?>