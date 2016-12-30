<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Change Location Search</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="changeLocationSearchRequest" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="itemCode">Scan/Enter Item Code whose Location is to be changed</label>
      <input type="text" class="form-control" name="itemCode" 
         placeholder="Enter Item Code">
   </div>
  

 <div class="form-group" align="center">
 <p>OR</p>
  <label for="itemCode">Select Inventory</label>
			<select id="invName" name="invName" >
        		<option value="${selectedInvCode}" selected>${selectedInvCode}</option>
   			 <c:forEach items="${senderInventoryList}" var="inventory">
        			<option value="${inventory}">${inventory}</option>
        		</c:forEach>
	</select>
	
	 <div class="form-group" align="center">
      <label for="sku">Enter SKU</label>
      <input type="text" class="form-control" name="sku" 
         placeholder="Enter SKU">
   </div>
			
     
    
      
         
   </div>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>