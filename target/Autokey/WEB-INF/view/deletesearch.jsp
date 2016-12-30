<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Delete Item</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="deleteItemRequest" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="itemCode">Scan/Enter Item Code To Be Deleted</label>
      <input type="text" class="form-control" name="itemCode" 
         placeholder="Enter Item Code">
   </div>
<br>
 <div class="form-group" align="center">
 <p>OR</p>
  <label for="invCode">Select Inventory</label>
			 <select name="invName">
				 <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${invList}" var="invName">
        		<c:if test="${invName != selected}">
            		<option value="${invName}">${invName}</option>
        		</c:if>
        		
    		</c:forEach>
			
			</select>
			<br>
     
     <label for="sku">Enter SKU To Be Updated</label>
      <input type="text" class="form-control" name="sku"  
         placeholder="Enter SKU">
         <br>
    
      
         <br>
      
      
         
     
      
         
   </div>
	<p align="center">
   <button type="submit" class="btn btn-default">Delete</button>
   </p>
   </form>
    
</body>
</html>