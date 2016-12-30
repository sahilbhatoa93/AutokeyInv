<!DOCTYPE html>
<%@ page import="java.time.Year" %>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>Brand/Trim Assignment</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />
<ca:form id="itemAssignmentForm" name="itemAssignmentForm" action="trimModelItemAssignmentFormRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table id="table1" class="table table-striped" >
  <tr>
  	<td>SKU</td>
  	
    <td>Category</td>
    <td>Sub Category</td>
   
    <td>Description</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
  
  </tr>
   <tr>
            <td>${result.sku}</td>
           
            <td>${result.categoryName}</td>
            <td>${result.subCategoryName}</td>
           
            <td>${result.description}</td>
            <td>${result.iC}</td>
          	<td>${result.noOfButton}</td>
            <td>${result.buttonConfiguration}</td>
            
        </tr>
  
  </table>
  <br>
  <br>
  <div>
  
   
<table id="table3" class="table table-striped" >
  <tr>
  	<td>Select Brand</td>
  	 <td> <select id="brand" name="brand" onchange="brandFilter()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${brandList}" var="brand">
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
         
  </table>
   <br><br>
   <table id="table2" class="table table-striped" >
  <tr>
  	<td> Brand</td>
  	<td> Model</td>
  	<td> Trim</td>
  	<td> From Year</td>
  	<td> To Year</td>	
  </tr>
   <tr>
    <c:forEach items="${dataList}" var="dataItem">
        <tr>
            <td>${dataItem.brand}</td>
            <td>${dataItem.model}</td>
            <td>${dataItem.trim}</td>
            <td>${dataItem.fromYear}</td>
            <td>${dataItem.toYear}</td>
    </c:forEach>           </tr>
    
      
             <tr>
             
            <td><input type="button" class="btn btn-default"  id="assignMoreButton"  value="Assign More"/></td>
            <td><input type="button" class="btn btn-default"  id="assignCompleteButton"  value="Assignment Complete"/></td>
           </tr>
           </table>
           
          
  

 </div>
 <input type="hidden" name="result" id="result" value="${result1}" />
 <input type="hidden" name="sku" id="sku" value="${result.sku}" />
 <input type="hidden" name="buttonName" id="buttonName"  />
</ca:form>
<script type="text/javascript">
$('#assignMoreButton').click(	
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
	}
	});
document.getElementById('result').value = result;
document.getElementById('buttonName').value = 'assignMoreButton';
document.getElementById("itemAssignmentForm").submit();



});	



$('#assignCompleteButton').click(	
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
	}
	});
document.getElementById('result').value = result;
document.getElementById('buttonName').value = 'assignCompleteButton';
document.getElementById("itemAssignmentForm").submit();



});	

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