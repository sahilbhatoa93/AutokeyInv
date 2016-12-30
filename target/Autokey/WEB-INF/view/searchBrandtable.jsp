<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Search Result</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="updateBrandRequest" action="updateBrandRequest"  modelAttribute = "item" >
<table class="table table-striped">
 
   <tr>
     <td>Brand Name</td>
    <td><ca:input  path="brand" value="${item.brand}"  /></td>
   
  </tr>
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update Brand"></td>		
  </tr>
  </table>
<ca:input  path="brandCode" value="${item.brandCode}"  type="hidden"/>
</ca:form>
</body>

</html>