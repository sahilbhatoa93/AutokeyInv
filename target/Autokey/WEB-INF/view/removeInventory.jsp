<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Remove</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="removeInventoryRequest" modelAttribute="Inventory">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="inventoryName">Scan/Enter Inventory Name To Be Deleted</label>
      <select id="inventoryName" name="inventoryName" >
        	 <c:forEach items="${inventoryList}" var="inventory">
        			<option value="${inventory}">${inventory}</option>
        		</c:forEach>
		</select>
   </div>
<br>
 	<p align="center">
   <button type="submit" class="btn btn-default">Delete</button>
   </p>
   </form>
    
</body>
</html>