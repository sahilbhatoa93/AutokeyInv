<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Sub Category</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewTrimForm" action="addNewTrimRequest"  modelAttribute = "Item" >
<table class="table table-striped">
  
  <tr>
    <td>Brand Name</td>
    <td><select id="brand" name="brand" onchange="brandFilter()">
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>		
  </tr>
  <tr>
    <td>Model Name</td>
    <td><select id="model" name="model" >
			</select></td>		
  </tr>
  
   <tr>
    <td>Trim Name</td>
    <td><input name="trim" type="text" /></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Trim"></td>		
  </tr>
  
</table>
</ca:form>
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
		
    
    </script>
</body>

</html>