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
<ca:form name="addNewModelform" action="addNewModelRequest"  modelAttribute = "Item" >
<table class="table table-striped">
  
  <tr>
    <td>Brand Name</td>
    <td><select id="brand" name="brand" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>		
  </tr>
  <tr>
    <td>Model Name</td>
    <td><input name="model" type="text" /></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Model"></td>		
  </tr>
  
</table>
</ca:form>

</body>

</html>