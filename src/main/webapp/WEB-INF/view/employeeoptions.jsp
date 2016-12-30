<!DOCTYPE html>
<html>
<head>
   <title>Options</title>
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <script src="js/jquery-ui.js"></script>
   <script src="js/bootstrap.min.js"></script>
</head>
<style>
li a, .dropbtn {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: red;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}</style>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>


<ul class="nav nav-tabs">
   <li class="active"><a href="#">Options</a></li>
   <li class="dropdown">
      
      
         <%-- <li><a href="makermain?myObject=${oj.logger}">Add</a></li> --%>
          
          <li><a href="inventoryAlert">My Inventory Alert</a></li>
         <li class="divider"></li>
         <li><a href="makenewkey">Make New Key</a></li>
         <li class="divider"></li>
         <li class="dropdown">
  		 <a href="#" class="dropbtn">Search</a>
    	<div class="dropdown-content">
     	 	<a href="searchbybrand">By Brand</a>
     		 <a href="searchbycategory">By Category</a>
      		<a href="searchbysubcategory">By Sub Category</a>
      		<a href="searchbysku">By SKU</a>
      		<a href="searchbydescription">By Description</a>
      		<a href="searchbyitemCode">By Item Code</a>
      	</div>
 		 </li>
      <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Item</a>
    	<div class="dropdown-content">
     	
     		
      		<a href="updatesearch">Update</a>
      		
      	</div>
 		 </li>
        
            <li class="divider"></li>
         
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Transfer Stocks</a>
    	<div class="dropdown-content">
    	<a href="pendingStockTransfer">Pending Transfer</a>
     	 	<a href="transferStocksIn">Transfer In</a>
     		 <a href="transferChoice">Transfer Out</a>
      		
    	</div>
 		 </li>
          <li class="divider"></li>
         <li><a href="receiveshipments">Receive Shipments</a></li>
          <li class="divider"></li>
         <li><a href="updatePassword">Update Password</a></li>
         <li class="divider"></li>
         <li><a href="logout">Log Out</a></li>
          <li class="divider"></li>
         <li><a href="#">Current User: ${name}</a></li>
        
      

   
</ul>





</body>
</html>
