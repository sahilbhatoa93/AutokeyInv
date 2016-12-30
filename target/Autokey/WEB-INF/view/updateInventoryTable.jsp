<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script src="js/populate.php"></script>
<script type="text/javascript">

</script>

<title>Update</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="updateInventoryInfoForm" action="updateInventoryRequest"  modelAttribute = "inventory" >
<table class="table table-striped">
  <tr>
    <td>Inventory Code</td>
    <td><ca:input  path="inventoryCode" value="${inventory.inventoryCode}"  /></td>		
  </tr>
  <tr>
    <td>Inventory Name</td>
    <td><ca:input  path="inventoryName" value="${inventory.inventoryName}"/></td>		
  </tr>
  
      <tr>
    <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update"></td>		
  </tr>
  
</table>
<ca:input  path="id" value="${inventory.id}"  type="hidden"/>
</ca:form>

</body>

</html>