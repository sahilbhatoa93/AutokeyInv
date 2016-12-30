<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

<title>Update</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<ca:form name="updateKeyInfoForm" action="updateKeyInfoFormEmployee"  modelAttribute = "updatesearchresulttable" enctype="multipart/form-data">
<table class="table table-striped">
  <tr>
    <td>Item Code</td>
    <td><ca:input  path="itemCode" value="${updatesearchresulttable.itemCode}"  readonly="true"/></td>		
  </tr>
  <tr>
    <td>Inventory Name</td>
    <td><ca:input  path="invName" value="${updatesearchresulttable.invName}" readonly="true"/></td>		
  </tr>
  
   <tr>
    <td>SKU (Stock Keeping Unit)</td>
    <td><ca:input path="sku" value="${updatesearchresulttable.sku}"  readonly="true" /></td>		
  </tr>
  <tr>
   <td>Select Car Brand</td>
   <td><select id="brand"  onchange="brandFilter()"  disabled="disabled">
        <option value="${selectedbrand}" selected>${selectedbrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			</tr>
			<tr>	
    <td>Select Model</td>
    <td>	 <select id="model"  disabled="disabled" >
    		<option selected="selected">${updatesearchresulttable.model}</option>
    		</select></td>		
  </tr>
		<tr>	
    <td>Rack</td>
    <td><ca:input path="rack" value="${updatesearchresulttable.rack}" readonly="true"   /></td>		
  </tr>
  <tr>
    <td>Shelf</td>
    <td><ca:input path="shelf" value="${updatesearchresulttable.shelf}" readonly="true"   /></td>		
  </tr>
  <tr>
    <td>Column</td>
    <td><ca:input path="column" value="${updatesearchresulttable.column}" readonly="true" /></td>		
  </tr>
  <tr>
    <td>Compartment</td>
    <td><ca:input  path="compartment" value="${updatesearchresulttable.compartment}" readonly="true"/></td>		
  </tr>
  <tr>
    <td>Number Remaining</td>
    <td><ca:input  path="noOfItems" value="${updatesearchresulttable.noOfItems}"  /></td>		
  </tr>
 <tr>
    <td>Category</td>
    <td><select id="categoryName"  onchange="categoryFilter()"  disabled="disabled"> 
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>		
  </tr>
  <tr>
    <td>Sub Category </td>
    <td><select id="subCategoryName"   disabled="disabled">
    	<option selected="selected">${updatesearchresulttable.subCategoryName}</option>
			</select></td>		
  </tr>
   <tr>
    <td>Image</td>
    <td><img src="updateimageServlet?itemCode=${searchResult.itemCode}" height="800px" width="800px"/></td>		
  </tr>
 
   <tr>
    <td>Description</td>
    <td><ca:input  path="description" value="${updatesearchresulttable.description}"  readonly="true" /></td>		
  </tr>		
    <tr>
    <td>FFC-ID</td>
    <td><ca:input  path="ffcId" value="${updatesearchresulttable.ffcId}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>IC</td>
    <td><ca:input  path="iC" value="${updatesearchresulttable.iC}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>Number Of Buttons</td>
    <td><ca:input  path="noOfButton" value="${updatesearchresulttable.noOfButton}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>Button Configuration</td>
    <td><ca:input  path="buttonConfiguration" value="${updatesearchresulttable.buttonConfiguration}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>Emergency Key</td>
    <td><ca:input  path="emergencyKey" value="${updatesearchresulttable.emergencyKey}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>Battery Part Number</td>
    <td><ca:input  path="batteryPartNumber" value="${updatesearchresulttable.batteryPartNumber}"  readonly="true" /></td>		
  </tr>	
  <tr>
    <td>Product Notes</td>
    <td><ca:input  path="productNotes" value="${updatesearchresulttable.productNotes}"  readonly="true" /></td>		
  </tr>
  <tr>
    <td>Restock Limit</td>
    <td><ca:input  path="restockLimit" value="${updatesearchresulttable.restockLimit}"   readonly="true"/></td>		
  </tr>	
    <tr>
    <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update"></td>		
  </tr>
  
</table>
<ca:input type="hidden" path="brand" value="${selectedbrand}" />
<ca:input type="hidden" path="model" value="${updatesearchresulttable.model}" />
<ca:input type="hidden" path="categoryName" value="${selectedCategory}" />
<ca:input type="hidden" path="subCategoryName" value="${updatesearchresulttable.subCategoryName}" />
</ca:form>
 <script type="text/javascript">
    	var brandDetails=${brandMap};
    	var categoryDetails=${categoryMap};
    	var brandDetails=${brandMap};
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
			
    
    </script>
</body>

</html>