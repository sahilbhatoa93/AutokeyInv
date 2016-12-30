<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.time.Year" %>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Make New Key</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <td> <select id="subCategoryNamePart1" name="subCategoryNamePart1" onchange="transponderFilter1()" >
			</select></td>
    <td><select id="subCategoryNamePart2" name="subCategoryNamePart2" onchange="transponderFilter2()">
			</select></td>		
    <td><select id="subCategoryNamePart3" name="subCategoryNamePart3" onchange="transponderFilter3()">
			</select></td>
    <td><select id="subCategoryNamePart4" name="subCategoryNamePart4" onchange="transponderFilter4()">
			</select></td>
    <td><select id="subCategoryNamePart5" name="subCategoryNamePart5" onchange="transponderFilter5()">
			</select></td>
  </tr>
  
  <tr>
  <td>Transponder</td>
    <td><input type="text" name="transponderPart1" id="transponderPart1"  list="listid"/>  <datalist id='listid'>
</datalist>
   </td>
    <td><input type="text" name="transponderPart2" id="transponderPart2" list='listid'/></td>		
    <td><input type="text" name="transponderPart3" id="transponderPart3" list='listid'></td>
    <td><input type="text" name="transponderPart4" id="transponderPart4" list='listid'></td>
    <td><input type="text" name="transponderPart5" id="transponderPart5" list='listid'></td>
  </tr>
   <tr>
  
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
   <td><select id="modelPart1" name="modelPart1" onchange="modelFilter1()" >
		</select></td>
	<td><select id="modelPart2" name="modelPart2" onchange="modelFilter2()">
		</select></td>
	<td><select id="modelPart3" name="modelPart3" onchange="modelFilter3()">
		</select></td>
	<td><select id="modelPart4" name="modelPart4" onchange="modelFilter4()">
		</select></td>
	<td><select id="modelPart5" name="modelPart5" onchange="modelFilter5()">
		</select></td>
			</tr>
	<tr>
	<td>Select Car Trim</td>
   <td><select id="trimPart1" name="trimPart1" ></select></td>
		
   <td><select id="trimPart2" name="trimPart2" ></select></td>
		
   <td><select id="trimPart3" name="trimPart3" ></select></td>
			
   <td><select id="trimPart4" name="trimPart4" ></select></td>
			 
   <td><select id="trimPart5" name="trimPart5" ></select></td>
			</tr>
	
	<tr>
	<td>From Year</td>
   <td><select id="fromYearPart1" name="fromYearPart1" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
		
   <td><select id="fromYearPart2" name="fromYearPart2" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
		
   <td><select id="fromYearPart3" name="fromYearPart3" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
			
   <td><select id="fromYearPart4" name="fromYearPart4" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
			 
   <td><select id="fromYearPart5" name="fromYearPart5" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
			</tr>
	
	<tr>
	<td>To Year</td>
   <td><select id="toYearPart1" name="toYearPart1" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
		
   <td><select id="toYearPart2" name="toYearPart2" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
		
   <td><select id="toYearPart3" name="toYearPart3" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
			
   <td><select id="toYearPart4" name="toYearPart4" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select></td>
			 
   <td><select id="toYearPart5" name="toYearPart5" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
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
			var modelDetails=${modelMap};
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
   <script type="text/javascript">
    function transponderFilter1()
		{
var select = document.getElementById("subCategoryNamePart1");
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


  function transponderFilter2()
		{
var select = document.getElementById("subCategoryNamePart1");
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

  function transponderFilter3()
		{
var select = document.getElementById("subCategoryName3");
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

  function transponderFilter4()
		{
var select = document.getElementById("subCategoryName4");
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

  function transponderFilter5()
		{
var select = document.getElementById("subCategoryName5");
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
	function modelFilter1()
		{
		
		var categoryName=document.getElementById("modelPart1").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trimPart1");
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
    
    	function modelFilter2()
		{
		
		var categoryName=document.getElementById("modelPart2").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trimPart2");
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
    
    	function modelFilter3()
		{
		
		var categoryName=document.getElementById("modelPart3").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trimPart3");
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
    
    	function modelFilter4()
		{
		
		var categoryName=document.getElementById("modelPart4").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trimPart4");
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
    
    	function modelFilter5()
		{
		
		var categoryName=document.getElementById("modelPart5").value;
		categoryName=categoryName.trim();
		var select = document.getElementById("trimPart5");
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