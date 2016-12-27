<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="js/jquery-1.9.1.js"></script>
  <script src="js/jquery-ui.js"></script>
<script type="text/javascript">
  
	 $( function() {
  	$( "#SKU" ).autocomplete({
      source: ${skuList}
    });
   } );
  </script>
 
  
<title>Receive Shipment</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<ca:form name="recieveshipmentform" action="recieveshipmentform"  modelAttribute = "TransferObject">
<table class="table table-striped">
  
  <tr>
    <td>Inventory Name</td>
    <td><select id="invCodeFrom" name="invCodeFrom" >
        		<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${senderInventoryList}" var="inventory">
        		<c:if test="${inventory != selected}">
            		<option value="${inventory}">${inventory}</option>
        		</c:if>
        		</c:forEach>
		</select></td>	
			<td></td>	
  </tr>
  <tr>
    <td>Select Shipper's Name</td>
    <td><select name="shipperName">
 			 <option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${shipperList}" var="shipper">
        		<c:if test="${shipper != selected}">
            		<option value="${shipper}">${shipper}</option>
        		</c:if>
        		</c:forEach>
			</select></td>	
			<td></td>	
  </tr>
  <tr>
    <td></td>
    <td>OR</td>	
			<td></td>	
  </tr>
  <tr>
    <td>Shipper Name</td>
    <td><input name="shipperName" type="text" /></td>	
    <td></td>	
  </tr>
   <tr>
    <td>Shipper Address</td>
    <td><input name="shipperAddress" type="text" /></td>	
    <td></td>	
  </tr>
   <tr>
    <td>Shipper Email</td>
    <td><input name="shipperEmail" type="text" /></td>	
    <td></td>	
  </tr>
  <tr>
    <td>Shipper Phone Number</td>
    <td><input name="shipperPhoneNumber" type="number" /></td>	
    <td></td>	
  </tr>
  <tr>
    <td>SKU</td>
    <td><input name="sku" type="text" id="SKU" value="${transferObject.sku}" required="required"/></td>
     <td><a href="searchbysku"><button class="btn btn-default" type="button">Search By SKU</button></a></td>		
  </tr>
   <tr>
   <td>Select Car Brand</td>
   <td>
   
  <select id="brand" name="brand" onchange="brandFilter()" >
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select>
   
  </td>	<td></td>	</tr>
   <tr>
   <td>Select Model</td>
   <td>
   
  <select id="model" name="model" >
        			</select>
   
  </td>	<td></td>	</tr>
  <tr>
    <td>Category</td>
    <td> 
    <select id="categoryName" name="categoryName"  onchange="categoryFilter()">
        <option value="${transferObject.categoryName}" selected>${transferObject.categoryName}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select>
    </td>	
			<td></td>	
  </tr>
  <tr>
    <td>Sub Category</td>
    <td> <select id="subCategoryName" name="subCategoryName" >
			
    		

			</select></td>	
			<td></td>
			<tr>
    <td>Number Of Items</td>
    <td><input  name="transferAmount" type="number"  required="required"/></td>		
    <td></td>
  </tr>	

    <tr>
    <td></td>
    <td><input type="submit"  class="btn btn-default" value="Shipment Received"></td>
    <td></td>		
  </tr>
  
</table>
</ca:form>
<script type="text/javascript">

	var categoryDetails=${categoryMap};
		var brandDetails=${brandMap};
	function categoryFilter()
		{
		var categoryName=document.getElementById("categoryName").value;
		var select = document.getElementById("subCategoryName");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=categoryDetails[categoryName];
		for (var i=0;i<options.length;i++)
		{
		var opt=options[i];
		var subCategoryName = opt.split(",");
		for (var j=0;j<subCategoryName.length;j++)
		{
			var e1=document.createElement('option');
			e1.textContent=subCategoryName[j];
			e1.value=subCategoryName[j];
			select.appendChild(e1);
		}
		}
		};
		 function brandFilter()
		{
		var categoryName=document.getElementById("brand").value;
		var select = document.getElementById("model");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=brandDetails[categoryName];
		for (var i=0;i<options.length;i++)
		{
		var opt=options[i];
		var subCategoryName = opt.split(",");
		for (var j=0;j<subCategoryName.length;j++)
		{
			var e1=document.createElement('option');
			e1.textContent=subCategoryName[j];
			e1.value=subCategoryName[j];
			select.appendChild(e1);
		}
		}
		};

</script>
</body>

</html>