<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Reason</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewReasonform" action="addNewReasonform"  modelAttribute = "Item" >
<table class="table table-striped">
  
  
  <tr>
    <td>Reason Name</td>
    <td><input name="reason" type="text" required="required" /></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Reason"></td>		
  </tr>
  
</table>
</ca:form>

</body>

</html>