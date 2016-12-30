<!DOCTYPE html>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  <title>Add New Item</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="updateUnassignedItemForm" action="updateUnassignedItemRequest"  modelAttribute = "unassignedItem" enctype="multipart/form-data">
<table class="table table-striped">
  
  <tr>
    <td>SKU (Stock Keeping Unit)</td>
    <td><ca:input path="sku" type="text" value="${unassignedItem.sku}" required="required"/></td>		
			 <td>Description</td>
    <td><ca:input  path="description" type="text"  value="${unassignedItem.description}"/></td>	
  </tr>
  <tr>
    <td>Emergency Key</td>
    <td><ca:input  path="emergencyKey" type="text"  value="${unassignedItem.emergencyKey}"/></td>
    <td>FCC-ID</td>
    <td><ca:input  path="ffcId" type="text" value="${unassignedItem.ffcId}" /></td>			
  </tr>
  <tr>
   <td>Battery Part Number</td>
    <td><ca:input  path="batteryPartNumber" type="text" value="${unassignedItem.batteryPartNumber}" /></td>	
			 <td>IC</td>
    <td><ca:input  path="iC" type="text"  value="${unassignedItem.iC}"/></td>	</tr>
    <tr>
    <td>Image</td>
    <td><img src="updateimageServletUnassignedItem?sku=${unassignedItem.sku}" height="800px" width="800px"/></td>		
  </tr>
 <tr>
<td>Image Upload</td>
    <td>  <input type="file" name="imageURL" accept="image/*" /></td>
 <td>Number Of Buttons</td>
    <td><ca:input  path="noOfButton" type="number" value="${unassignedItem.noOfButton}" /></td>
 
 </tr>
  <tr>
    
    <td>Category</td>
    <td><select id="categoryName" name="categoryName" onchange="categoryFilter()"> 
        <option value="${unassignedItem.categoryName}" selected>${unassignedItem.categoryName}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>	 		
  
     <td>Button Configuration</td>
    <td><ca:input  path="buttonConfiguration" type="text"  value="${unassignedItem.buttonConfiguration}"/></td>			  	
  </tr>
  <tr>
   <td>Sub Category</td>
    <td> <select id="subCategoryName" name="subCategoryName" onchange="transponderFilter()" >
    		<option selected="selected">${unassignedItem.subCategoryName}</option>
    </select></td>
  </tr>
 <tr>
  <td>Transponder</td>
    <td><input  id="transponder" name="transponder" value="${unassignedItem.transponder}" type="text" list='listid' />
    
     <datalist id='listid'>
   
</datalist>
   
    	
  </tr>
  
  <tr>
    	 <td>Product Notes</td>
    <td><ca:input  path="productNotes"  value="${unassignedItem.productNotes}" type="text" /></td>
    	
  </tr>

  
 
  <tr>
    	 <td></td>
    <td><input type="submit"  class="btn btn-default" value="Update Item"></td>
  </tr>
 
  <tr>
    
    	
		
			
 
  
</table>
<ca:input type="hidden" path="scheduleTIme" value="${unassignedItem.sku}"/>
</ca:form>
<script type="text/javascript">
    	
    	var categoryDetails=${categoryMap};
   
		
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
        <script type="text/javascript">
    function transponderFilter()
		{
var select = document.getElementById("subCategoryName");
var selectedSubCategory = select.options[select.selectedIndex].value;
if (selectedSubCategory.toLowerCase().indexOf("transponder") !== -1)
{

		var datalist=document.getElementById("listid");
		var transponderList=${transponderList};
		var transponderNames=transponderList.split(",")
		for (var j=0;j<transponderNames.length;j++)
		{
		var e1=document.createElement('option');
			e1.textContent=transponderNames[j].trim();
			e1.value=transponderNames[j].trim();
			datalist.appendChild(e1);
		}
}

}
</script>
</body>

</html>