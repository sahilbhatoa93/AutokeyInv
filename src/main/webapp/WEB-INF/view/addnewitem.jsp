<!DOCTYPE html>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
function validateForm() {
    var x = document.forms["addNewItemform"]["rack"].value;
     var y = document.forms["addNewItemform"]["shelf"].value;
     var z = document.forms["addNewItemform"]["column"].value;
     var a = document.forms["addNewItemform"]["compartment"].value;
     var b = document.forms["addNewItemform"]["noOfItems"].value;
   
    if (x == null || x == "") {
        alert("Rack Code must be filled out");
        return false;
    }
    if (y == null || y == "") {
        alert("Shelf Code must be filled out");
        return false;
    }
     if (z == null || z == "") {
        alert("Column Code must be filled out");
        return false;
    }
     if (a == null || a == "") {
        alert("Compartment Code must be filled out");
        return false;
    }
     if (b == null || b == "") {
        alert("No Of Items  must be filled out");
        return false;
    }
}

</script>

<title>Add New Item</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewItemform" action="addNewItemform" onsubmit="return validateForm()" modelAttribute = "Item" enctype="multipart/form-data">
<table class="table table-striped">
  
  <tr>
    <td>SKU (Stock Keeping Unit)</td>
    <td><input name="sku" type="text" required="required"/></td>		
			 <td>Description</td>
    <td><input  name="description" type="text"  /></td>	
  </tr>
  <tr>
    <td>Emergency Key</td>
    <td><input  name="emergencyKey" type="text"  /></td>
    <td>FCC-ID</td>
    <td><input  name="ffcId" type="text"  /></td>			
  </tr>
  <tr>
   <td>Battery Part Number</td>
    <td><input  name="batteryPartNumber" type="text"  /></td>	
			 <td>IC</td>
    <td><input  name="iC" type="text"  /></td>	</tr>
 <tr>
<td>Image Upload</td>
    <td>  <input type="file" name="imageURL" accept="image/*" ></td>
 <td>Number Of Buttons</td>
    <td><input  name="noOfButton" type="number"  /></td>
 
 </tr>
  <tr>
    
    <td>Category</td>
    <td><select id="categoryName" name="categoryName" onchange="categoryFilter()"> 
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>	 		
  
     <td>Button Configuration</td>
    <td><input  name="buttonConfiguration" type="text"  /></td>			  	
  </tr>
  <tr>
   <td>Sub Category</td>
    <td> <select id="subCategoryName" name="subCategoryName" onchange="transponderFilter()"></select></td>
    	 <td>Product Notes</td>
    <td><input  name="productNotes"  type="text" /></td>
  </tr>
  <tr>
  <td>Transponder</td>
    <td><input  id="transponder" name="transponder" type="text" list='listid' />
    
     <datalist id='listid'>
   
</datalist>
   
    	</td>
  </tr>

  
 
  <tr>
    	 <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Item"></td>
  </tr>
 
  <tr>
    
    	
		
			
 
  
</table>
</ca:form>
<script type="text/javascript">
    	var brandDetails=${brandMap};
    	var categoryDetails=${categoryMap};
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