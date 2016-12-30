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
<ca:form name="updateTransponderRequest" action="updateTransponderRequest"  modelAttribute = "item" >
<table class="table table-striped">
 
   <tr>
     <td>Transponder Name</td>
    <td><ca:input  path="transponder" value="${item.transponder}"  /></td>
   
  </tr>
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update Transponder"></td>		
  </tr>
  </table>
<ca:input  path="itemCode" value="${item.itemCode}"  type="hidden"/>
</ca:form>
</body>

</html>