<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>My Inventory List</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />

<ca:form id="discrepanciesForm" name="discrepanciesForm" action="discrepanciesFormRequest" modelAttribute="Item"  >
<input type="hidden" name="result" id="result" value="${result}" />
<table class="table table-striped">
		 <tr>
          		<td>SKU</td>
			    <td>Inventory</td>
			    <td>Item Code</td>
			    <td>Brand</td>
			     <td>Model</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>Inventory No of Items</td>
			    <td>User No of Items</td>
			    <td>User</td>
			    <td>Date</td>
			     <td>Option</td>
 		 </tr>
 	  <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invFrom}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
             <td>${dataItem.model}</td>
            <td>${dataItem.category}</td>
            <td>${dataItem.subCategory}</td>
            <td>${dataItem.number}</td>
            <td>${dataItem.part1}</td>
            <td>${dataItem.user}</td>
            <td>${dataItem.date}</td>
            <td><input type="submit"  class="btn btn-default"  id="allowButton<%out.print(counter++);%>" name="allowButton<%out.print(counter-1); %>" value="Authorize"></input></td>
            
        </tr>
    	</c:forEach>
	
  
  </table>
  </ca:form>
<script type="text/javascript">

 <%    
  for (int i=0;i<counter;i++)
  {
  out.println("$('#allowButton"+i+"').click(	function() {var $row = $(this).closest('tr');  $tds = $row.find('td');  var result='';  $.each($tds, function() {   result =result+'&&&'+$(this).text();});document.getElementById('result').value = result;});");
  
  }
%>


</script>

</body>

</html>