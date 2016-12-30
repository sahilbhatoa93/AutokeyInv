<!DOCTYPE html>

<%@ page import="java.time.Year" %>
<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
  
	 $( function() {
	
  	$( "#SKU" ).autocomplete({
      source: ${skuList}
    });
   } );
  </script>

<title>Assign Inventory</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<form  action="assignInventoryRequest" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="itemCode">Enter SKU of the Item</label>
      <input type="text" class="form-control" name="sku"  id="SKU"
         placeholder="Enter SKU">
   </div>
  

 <div class="form-group" align="center">
 <p>OR</p>
  <label for="itemCode">Brand</label>
			<select id="brand" name="brand" onchange="brandFilter()" >
        	 <c:forEach items="${brandList}" var="brand">
        			<option value="${brand}">${brand}</option>
        		</c:forEach>
	</select>
	<br>
	 <label for="itemCode">Model</label>
			<select id="model" name="model"  onchange="modelFilter()" >
       
	</select>
	<br>
	 <label for="itemCode">Trim</label>
			<select id="trim" name="trim" >
        	
	</select>
	<br>
	 <label for="fromYear">From Year</label>
			<select id="fromYear" name="fromYear" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>	
   	</select>
	 <label for="toYear">From Year</label>
			<select id="toYear" name="toYear" >
        	<% for (int i=1980 ; i<= Year.now().getValue();i++ )
        	{
        	out.println("<option value='"+i+"'>"+i+"</option>");
        	}
        	 %>
        
	</select>
	</div>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
     <script type="text/javascript">
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
		
		
			function modelFilter()
		{
		var modelDetails=${modelMap};
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