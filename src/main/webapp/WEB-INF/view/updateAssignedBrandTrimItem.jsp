<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<html>
<%@ page import="java.time.Year" %>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script type="text/javascript">
   

   </script>
  <title>Add New Item</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<%int counter=0; %>
<jsp:include page="adminoptions.jsp" />
<ca:form name="updateAssignedBrandTrimItemForm" id="updateAssignedBrandTrimItemForm" action="updateAssignedBrandTrimItemRequest"  modelAttribute = "unassignedItem" enctype="multipart/form-data">
<input type="hidden" name="result" id="result" value="${result}" />
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
    <td><ca:input  path="buttonConfiguration" type="text"  /></td>			  	
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
</table>

<h3>Current Assignment</h3>
<table id="table3" class="table table-striped" >
  <tr>
  <td>Brand</td>
  <td>Model</td>
  <td>Trim</td>
  <td>From Year</td>
  <td>To Year</td>
  <td>Update</td>
  <td>Delete</td>
  </tr>
  <tr>
  
  
   <c:forEach begin="0" end="${loopCount}" varStatus="loop" >
        <tr>
             <td id="brand${loop.index}">${brandList[loop.index]}</td>
            <td id="model${loop.index}">${modelList[loop.index]}</td>
            <td id="trim${loop.index}">${trimList[loop.index]}</td>
            <td id="fromYear${loop.index}">${fromYearList[loop.index]}</td>
            <td id="toYear${loop.index}">${toYearList[loop.index]}</td>
            <td><input id="updateButton${loop.index}" type="button" class="btn btn-default"  value="Update" /></td>
            <td><input  id="deleteButton${loop.index}"type="button" class="btn btn-default"  value="Delete" /></td>
            <%counter++; %>
    </c:forEach>
  </tr>
  
   <tr>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
 <td></td>
            <td><input  id="updateItem"type="button" class="btn btn-default"  value="Update Item"/></td>
  </tr>
  <tr>
  </table>
  
  
  <table id="table3" class="table table-striped" >
  <tr>
  	<td>Select Brand</td>
  	 <td> <select id="brand" name="brand" onchange="brandFilter()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${dropdownbrandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select> </td>
    
  </tr>
   <tr>
            <td>Select Model</td>
            <td> <select id="model" name="model" onchange="modelFilter()">
			</select></td>
           </tr>
  <tr>
            <td>Select Trim</td>
            <td><select id="trim" name="trim" >
			</select></td>
           </tr>
           <tr>
            <td>Select From Year</td>
            <td><select id="fromYear" name="fromYear" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
           </tr>
           <tr>
            <td>Select To Year</td>
            <td><select id="toYear" name="toYear" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
   	<tr><td></td>
         <td><input id="assignMore" type="button" class="btn btn-default"  value="Add More Assignment" /></td></tr>
         
  </table>
<ca:input type="hidden" path="scheduleTIme" value="${unassignedItem.sku}"/> <input type="hidden" name="buttonName" id="buttonName"  />
</ca:form>
<script type="text/javascript">
$('#assignMore').click(	
function() {
var result1='';
var e = document.getElementById("brand");
var strUser = e.options[e.selectedIndex].value;
result1=result1+strUser+'&&&';
var e = document.getElementById("model");
var strUser = e.options[e.selectedIndex].value;
result1=result1+strUser+'&&&';
var e = document.getElementById("trim");
var strUser = e.options[e.selectedIndex].value;
result1=result1+strUser+'&&&';
var e = document.getElementById("fromYear");
var strUser = e.options[e.selectedIndex].value;
result1=result1+strUser+'&&&';
var e = document.getElementById("toYear");
var strUser = e.options[e.selectedIndex].value;
result1=result1+strUser;
document.getElementById('result').value = result1;

document.getElementById('buttonName').value = 'assignMoreButton';
document.getElementById("updateAssignedBrandTrimItemForm").submit();



});	

$('#updateItem').click(	
function() {
var result1='';
var $table = $(this).closest('table');
$trs=$table.find('tr');
$.each($trs, function() 
	{  
	var $row = $(this).closest('tr');
	if (!(($(this).is(":first-child")) ))
	{
		$tds = $row.find('td');
		$.each($tds, function() 
		{
				result1 =result1+'&&&'+$(this).text();
		 
			
		});
		result1=result1+'###';
	}
	});
document.getElementById('result').value = result1;
document.getElementById('buttonName').value = 'assignCompleteButton';
document.getElementById("updateAssignedBrandTrimItemForm").submit();



});	
<%    
  for (int i=0;i<counter;i++)
  {
  out.println("$('#updateButton"+i+"').click(	function()  { var oldValue=$('#fromYear"+i+"').text(); var newValue = prompt('Enter New From Year', oldValue);  if (newValue != null) {   $('#fromYear"+i+"').html(newValue);} var oldValue=$('#toYear"+i+"').text(); var newValue = prompt('Enter New To Year', oldValue);  if (newValue != null) {   $('#toYear"+i+"').html(newValue);}});");
   out.println("$('#deleteButton"+i+"').click(	function() {var $row = $(this).closest('tr');  if (confirm('Do want to Delete this Item ?') == true) {  $row.remove(); } else {}  });");
  }
%>
function updateBrand(loopIndex)
{
	alert(loopIndex);
}
function deleteBrand()
{
var $row = $(this).closest('tr');  
if (confirm('Do want to Delete this Assignment ?') == true) 
{  $row.remove(); } 
else {}  
}
    	
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
 <script type="text/javascript">
    	var brandDetails=${brandMap};
    	var modelDetails=${modelMap};
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
			function modelFilter()
		{
		
		var categoryName=document.getElementById("model").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trim");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=modelDetails[categoryName];
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