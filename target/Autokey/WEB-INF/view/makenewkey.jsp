<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Make New Key</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>

<jsp:include page="employeeoptions.jsp" />
<h2>Scan the Barcode or Enter field values :</h2>
<ca:form name="newKeyEntryForm" action="newkeyentry" method="post"  onsubmit="getConditionNumber()" modelAttribute="Key">

<table class="table table-striped">


  <tr>
  <td>Item Code</td>
    <td><input type="text" name="itemCodePart1" id="itemCodePart1" ></td>
    <td><input type="text" name="itemCodePart2" id="itemCodePart2" ></td>		
    <td><input type="text" name="itemCodePart3" id="itemCodePart3" ></td>
    <td><input type="text" name="itemCodePart4" id="itemCodePart4" ></td>
    <td><input type="text" name="itemCodePart5" id="itemCodePart5" ></td>
  </tr>
   <tr>
<td></td>
<td> </td>
<td></td>
<td>OR</td>
<td></td>
<td></td>
</tr>
  <tr>
<td>Inventory Name</td>
<td> 
<select id="invCode" name="invCode" >
        		<option value="${selectedInvCode}" selected>${selectedInvCode}</option>
   			 <c:forEach items="${senderInventoryList}" var="inventory">
        			<option value="${inventory}">${inventory}</option>
        		</c:forEach>
	</select>
</td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
 <tr>
  <td>Category</td>
    <td> <select id="categoryNamePart1" name="categoryNamePart1" onchange="categoryFilterPart1()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
    <td> <select id="categoryNamePart2" name="categoryNamePart2" onchange="categoryFilterPart2()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>		
    <td> <select id="categoryNamePart3" name="categoryNamePart3" onchange="categoryFilterPart3()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
    <td> <select id="categoryNamePart4" name="categoryNamePart4" onchange="categoryFilterPart4()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        	</c:forEach>
				</select></td>
    <td> <select id="categoryNamePart5" name="categoryNamePart5" onchange="categoryFilterPart5()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        	</c:forEach>
				</select></td>
  </tr>
 <tr>
 <td>Sub Category</td>
    <td> <select id="subCategoryNamePart1" name="subCategoryNamePart1" >
			</select></td>
    <td><select id="subCategoryNamePart2" name="subCategoryNamePart2" >
			</select></td>		
    <td><select id="subCategoryNamePart3" name="subCategoryNamePart3" >
			</select></td>
    <td><select id="subCategoryNamePart4" name="subCategoryNamePart4" >
			</select></td>
    <td><select id="subCategoryNamePart5" name="subCategoryNamePart5" >
			</select></td>
  </tr>
   <tr>
   <td>Select Car Brand</td>
   <td><select id="brandPart1" name="brandPart1" onchange="brandFilterPart1()">
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
		
   <td><select id="brandPart2" name="brandPart2" onchange="brandFilterPart2()">
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
		
   <td><select id="brandPart3" name="brandPart3" onchange="brandFilterPart3()">
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			
   <td><select id="brandPart4" name="brandPart4" onchange="brandFilterPart4()">
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			 
   <td><select id="brandPart5" name="brandPart5" onchange="brandFilterPart5()">
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			</tr>
			<tr>
   <td>Select Car Model</td>
   <td><select id="modelPart1" name="modelPart1" >
		</select></td>
	<td><select id="modelPart2" name="modelPart2" >
		</select></td>
	<td><select id="modelPart3" name="modelPart3" >
		</select></td>
	<td><select id="modelPart4" name="modelPart4" >
		</select></td>
	<td><select id="modelPart5" name="modelPart5" >
		</select></td>
			</tr>
  <tr>
   <tr>
    <td></td>
    <td></td>		
    <td></td>
    <td></td>
    <td></td>
    <td> <input type="submit" class="btn btn-default" onclick="getConditionNumber()" value="Create New Key"></td>
  </tr>
    	
 
  
</table>
 
<br>
<input type="hidden" name="result" id="result" value="${result}">
</ca:form>
<script type="text/javascript">
var locationDetails=${locationMap};
		var categoryDetails=${categoryMap};
		var brandDetails=${brandMap};
function categoryFilterPart1()
		{
		var categoryName=document.getElementById("categoryNamePart1").value;
		var select = document.getElementById("subCategoryNamePart1");
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
		
function categoryFilterPart2()
		{
		var categoryName=document.getElementById("categoryNamePart2").value;
		var select = document.getElementById("subCategoryNamePart2");
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
function categoryFilterPart3()
		{
		var categoryName=document.getElementById("categoryNamePart3").value;
		var select = document.getElementById("subCategoryNamePart3");
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
function categoryFilterPart4()
		{
		var categoryName=document.getElementById("categoryNamePart4").value;
		var select = document.getElementById("subCategoryNamePart4");
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
function categoryFilterPart5()
		{
		var categoryName=document.getElementById("categoryNamePart5").value;
		var select = document.getElementById("subCategoryNamePart5");
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
		
function brandFilterPart1()
		{
		var categoryName=document.getElementById("brandPart1").value;
		var select = document.getElementById("modelPart1");
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

function brandFilterPart2()
		{
		var categoryName=document.getElementById("brandPart2").value;
		var select = document.getElementById("modelPart2");
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
		
function brandFilterPart3()
		{
		var categoryName=document.getElementById("brandPart3").value;
		var select = document.getElementById("modelPart3");
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
		
		
function brandFilterPart4()
		{
		var categoryName=document.getElementById("brandPart4").value;
		var select = document.getElementById("modelPart4");
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
		
		
function brandFilterPart5()
		{
		var categoryName=document.getElementById("brandPart5").value;
		var select = document.getElementById("modelPart5");
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
function getConditionNumber()
{
	
	var result="";
	var categoryName1=document.getElementById("categoryNamePart1");
	if ( categoryName1[categoryName1.selectedIndex].value != '' )
	{
	result=1;
	}
	var categoryName2=document.getElementById("categoryNamePart2");
	if (  categoryName2[categoryName2.selectedIndex].value != '' )
	{
	result=2;
	}
	var categoryName3=document.getElementById("categoryNamePart3");
	if ( categoryName3[categoryName3.selectedIndex].value != '' )
	{
	result=3;
	}
	var categoryName4=document.getElementById("categoryNamePart4");
	
	if (  categoryName4[categoryName4.selectedIndex].value != '' )
	{
	result=4;
	}
	var categoryName5=document.getElementById("categoryNamePart5");
	if ( categoryName5[categoryName5.selectedIndex].value != '' )
	{
	result=5;
	}
	
var itemCodePart1=document.getElementById("itemCodePart1").value;
	if ( itemCodePart1 != '' )
	{
	result=1;
	}
	var itemCodePart2=document.getElementById("itemCodePart2").value;
		if ( itemCodePart2 != '' )
	{
	result=2;
	}
	var itemCodePart3=document.getElementById("itemCodePart3").value;
		if ( itemCodePart3 != '' )
	{
	result=3;
	}
	var itemCodePart4=document.getElementById("categoryNamePart4").value;
		if ( itemCodePart4 != '' )
	{
	
	result=4;
	}
	var itemCodePart5=document.getElementById("itemCodePart5").value;
		if (itemCodePart5 != '' )
	{
	result=5;
	}
	document.getElementById('result').value = result;
}				
</script>

</body>
</html>