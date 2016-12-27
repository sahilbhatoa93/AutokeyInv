<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Sub Category</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewSubCategoryform" action="addNewSubCategoryform"  modelAttribute = "Item" >
<table class="table table-striped">
  
  <tr>
    <td>Category Name</td>
    <td><select id="categoryName" name="categoryName" >
    
   			 <c:forEach items="${categoryList}" var="category">
    
            		<option value="${category}">${category}</option>
    
        		
    		</c:forEach>

			</select></td>		
  </tr>
  <tr>
    <td>Sub Category Name</td>
    <td><input name="subCategoryName" type="text" required="required" /></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New SubCategory"></td>		
  </tr>
  
</table>
</ca:form>

</body>

</html>