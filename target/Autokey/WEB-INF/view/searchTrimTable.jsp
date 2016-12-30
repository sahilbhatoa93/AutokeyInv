<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Item"%>
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
<ca:form name="updateTrimRequest" action="updateTrimRequest"  modelAttribute = "item" >
<table class="table table-striped">
 
   <tr>
     <td>Trim Name</td>
    <td><ca:input  path="trim" value="${item.trim}"  /></td>
   
  </tr>
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update Model"></td>		
  </tr>
  </table>
  <ca:input  path="brand" value="${item.brand}"  type="hidden"/>
<ca:input  path="itemCode" value="${item.trim}"  type="hidden"/>
</ca:form>
</body>

</html>