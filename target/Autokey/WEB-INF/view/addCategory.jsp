<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Category</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewCategoryform" action="addNewCategoryform"  modelAttribute = "Item" >
<table class="table table-striped">
  
  
  <tr>
    <td>Category Name</td>
    <td><input name="categoryName" type="text" required="required"/></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Category"></td>		
  </tr>
  
</table>
</ca:form>

</body>

</html>