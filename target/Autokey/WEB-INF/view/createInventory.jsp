<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Create Inventory</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="createInventoryRequest" modelAttribute="Inventory">
<table class="table table-striped">
 <tr>
    <td>Enter Inventory Code</td>
    <td> <input type="text" class="form-control" name="inventoryCode" required="required"></td>		
  </tr>
  <tr>
    <td>Enter Inventory Name</td>
    <td> <input type="text" class="form-control" name="inventoryName" required="required"></td>		
  </tr>
 
   <tr>
    <td></td>
    <td><button type="submit" class="btn btn-default">Submit</button></td>		
  </tr>
       </table>      
  
	
   </form>
    
</body>
</html>