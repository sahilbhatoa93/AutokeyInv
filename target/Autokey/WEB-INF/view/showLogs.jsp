<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>My Inventory List</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />

<table class="table table-striped">
<c:choose>
    <c:when test="${condition=='SignIn/SignOut Activity'}">
    	<tr>
			    <td>User</td>
			    <td>Date</td>
			    <td>Sign In</td>
			    <td>Sign Out</td>
		</tr>
 	  <c:forEach items="${attendanceMap}" var="dataItem">
        <tr>
        	<td><c:out value="${dataItem.value[2]}"/></td>
        	 <td>${dataItem.key}</td>
        	 <td><c:out value="${dataItem.value[0]}"/></td>	
   			 <td><c:out value="${dataItem.value[1]}"/></td>
         </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Password Change Activity'}">
          <tr>
			     <td>User</td>
			    <td>Date</td>
			   
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='New Key'}">
          <tr>
          		<td>Generation ID</td>
			    <td>SKU</td>
			    <td>Inventory Name</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			      <td>User</td>
			    <td>Date</td>
		</tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
         	<td>${dataItem.keyGenerationID}</td>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
            
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Item'}">
          <tr>
			    <td>SKU</td>
			    <td>Inventory Name</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>Activity</td>
			      <td>User</td>
			    <td>Date</td>
			    
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
             <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
           </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Initiated Transfers'}">
          <tr>
          		<td>Transfer ID</td>
			    <td>SKU</td>
			    <td>From Inv</td>
			    <td>To Inv</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>Number Transferred</td>
			    <td>Reason</td>
			    <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.transferID}</td>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.invTo}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
            <td>${dataItem.number}</td>
            <td>${dataItem.reason}</td>
            <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
            
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Completed Transfer'}">
         <tr>
          		<td>Transfer ID</td>
			    <td>SKU</td>
			    <td>From Inv</td>
			    <td>To Inv</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>Number Transferred</td>
			    <td>Reason</td>
			    <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
       <tr>
        	<td>${dataItem.transferID}</td>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.invTo}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
            <td>${dataItem.number}</td>
            <td>${dataItem.reason}</td>
            <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
            
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Recieve Shipment'}">
          <tr>
			    <td>SKU</td>
			    <td>Inventory Name</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>No. of Buttons</td>
			    <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
			     <td>Shipper Name</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
            <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
             <td>${dataItem.shipperName}</td>
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Technician'}">
          <tr>
			    <td>Technician</td>
			  <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.technician}</td>
            <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
          
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Category'}">
          <tr>
			    
			    <td>Category</td>
			   <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	
            <td>${dataItem.category}</td>
             <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
          
        </tr>
    	</c:forEach>
	</c:when>    
    <c:when test="${condition=='Sub Category'}">
          <tr>
			    
			    <td>Category</td>
			    <td>Sub Category</td>
			   <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
             <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
          
        </tr>
    	</c:forEach>
	</c:when>    
	  
    <c:when test="${condition=='Brand'}">
          <tr>
			    
			    <td>Brand</td>
			   <td>Activity</td>
			    <td>User</td>
			    <td>Date</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.brand}</td>
             <td>${dataItem.activity}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
          
        </tr>
    	</c:forEach>
	</c:when>    
</c:choose>
  </table>


</body>

</html>