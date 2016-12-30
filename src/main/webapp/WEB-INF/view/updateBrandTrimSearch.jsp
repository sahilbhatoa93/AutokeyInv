<!DOCTYPE html>

<%@ page import="java.time.Year" %>
<html>
<head>
 	
  
   <script src="js/jquery-1.9.1.js"></script>

   <script src="js/jquery-ui.js"></script>
<script type="text/javascript">
  
	 $( function() {
	
  	$( "#SKU" ).autocomplete({
      source: ${skuList}
    });
   } );
  </script>

<title>Assign Inventory</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="updateAssignedBrandTrimItemSearchRequest" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="sku">Enter SKU of the Item</label>
      <input type="text" class="form-control" name="sku"  id="SKU"
         placeholder="Enter SKU">
   </div>
  


	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
</body>
</html>