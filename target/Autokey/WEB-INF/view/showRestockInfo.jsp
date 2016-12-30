<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>Reorder Inventory List</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />

<ca:form id="discrepanciesForm" name="discrepanciesForm" action="discrepanciesFormRequest" modelAttribute="Item"  >

<table class="table table-striped">
		 <tr>
          		<td>SKU</td>
			    <td>Brand</td>
			     <td>Model</td>
			    <td>Category</td>
			    <td>Sub Category</td>
			    <td>FCC ID</td>
			    <td>Description</td>
			    <td>Button Configuration</td>
			    <td>Battery Part Number</td>
			    <td>No of Items to Order</td>
			   
 		 </tr>
 	  <c:forEach items="${result}" var="dataItem">
        <tr>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.brand}</td>
             <td>${dataItem.model}</td>
            <td>${dataItem.categoryName}</td>
            <td>${dataItem.subCategoryName}</td>
            <td>${dataItem.ffcId}</td>
            <td>${dataItem.description}</td>
            <td>${dataItem.buttonConfiguration}</td>
            <td>${dataItem.batteryPartNumber}</td>
            <td>${dataItem.noOfItems}</td>
        </tr>
    	</c:forEach>
	<!--  <tr>
          		<td></td>
			    <td></td>
			     <td></td>
			    <td></td>
			    <td></td>
			    <td></td>
			    <td></td>
			    <td></td>
			    <td></td>
			    <td><input type="button" class="btn btn-default" id="printButton" value="Print PDF"></td>
			   
 		 </tr> -->
  
  </table>
  </ca:form>
<script type="text/javascript">
function callPrint()
		{
		
		var obj = {};
		obj['data'] = ${result};
		$.ajax({
            type: "GET",
            url: 'printTransferLabel',
            contentType:  "application/json; charset=utf-8",
            data: obj,
            dataType: "text",
            success: function(result) { alert(result); }
			
            });
		
		}; 

</script>

</body>

</html>