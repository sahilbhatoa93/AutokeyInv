<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Brand</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewBrandform" action="addNewBrandRequest"  modelAttribute = "Item" >
<table class="table table-striped">
  
  
  <tr>
    <td>Brand Name</td>
    <td><input name="brand" type="text" required="required"/></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Brand"></td>		
  </tr>
  
</table>
</ca:form>

</body>

</html>