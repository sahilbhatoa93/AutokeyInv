<!DOCTYPE html>
<html>
<head>
   <title>Admin Options</title>
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
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
   <li class="active"><a href="#">Admin Options	</a></li>
   <li class="dropdown">
      
      
         <%-- <li><a href="makermain?myObject=${oj.logger}">Add</a></li> --%>
         <li class="dropdown">
  		 <a href="#" class="dropbtn">User</a>
    	<div class="dropdown-content">
     	 	<a href="addTechnician">Add</a>
     		 <a href="updateTechnician">Update</a>
      		<a href="removeTechnician">Remove</a>
    	</div>
 		 </li>
         <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Category</a>
    	<div class="dropdown-content">
     	 	<a href="addCategory">Add</a>
     		 <a href="searchCategory">Update</a>
      		<a href="searchDeleteCategoryTable">Remove</a>
    	</div>
 		 </li>
         <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Sub Category</a>
    	<div class="dropdown-content">
     	 	<a href="addSubCategory">Add</a>
     		 <a href="searchSubCategory">Update</a>
      		<a href="searchDeleteSubCategoryTable">Remove</a>
    	</div>
 		 </li>
         <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Brand</a>
    	<div class="dropdown-content">
     	 	<a href="addBrand">Add</a>
     		 <a href="searchBrand">Update</a>
      		<a href="searchDeleteBrand">Remove</a>
    	</div>
 		 </li>
 		  <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Model</a>
    	<div class="dropdown-content">
     	 	<a href="addModel">Add</a>
     		 <a href="searchModel">Update</a>
      		<a href="searchDeleteModel">Remove</a>
    	</div>
 		 </li>
 		  <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Trim</a>
    	<div class="dropdown-content">
     	 	<a href="addTrim">Add</a>
     		 <a href="searchTrim">Update</a>
      		<a href="searchDeleteTrim">Remove</a>
    	</div>
 		 </li>
         <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Item</a>
    	<div class="dropdown-content">
     	 	 <a href="addnewitem">Add New Item</a>
     	 	 <a href="assignModelTrimSearch">Assign Model & Trim</a>
     		<a href="updatesearchadmin">Update Item</a>
      		<a href="deleteItem">Delete Item</a>
      		<a href="changeLocation">Change Item Location</a>
      		<a href="showUnassignedItems">See Unassigned Items</a>
    	</div>
 		 </li>
         <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Inventory</a>
    	<div class="dropdown-content">
     	 	<a href="createInventory">Create</a>
     		 <a href="updateInventory">Update</a>
      		<a href="removeInventory">Remove</a>
    	</div>
 		 </li>
 		  <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Reason</a>
    	<div class="dropdown-content">
     	 	<a href="addReason">Create</a>
     		 <a href="searchReason">Update</a>
      		<a href="searchDeleteReasonTable">Remove</a>
    	</div>
 		 </li>
 		  <li class="divider"></li>
          <li class="dropdown">
  		 <a href="#" class="dropbtn">Alerts</a>
    	<div class="dropdown-content">
     	 	<a href="createAlerts">Create</a>
     		 <a href="updateAlert">Update</a>
      		<a href="removeAlerts">Remove</a>
    	</div>
 		 </li>
 		 
            
              <li class="divider"></li>
         <li><a href="seeLog">Logs</a></li>
         <li class="divider"></li>
         <li><a href="seediscrepancies">Discrepancies</a></li>
              <li class="divider"></li>
         <li><a href="getReorderInfo">Reorder Info</a></li>
              <li class="divider"></li>
         <li><a href="logout">LogOut</a></li>
   
</ul>


<h3>${finalResult}</h3>

</body>
</html>
