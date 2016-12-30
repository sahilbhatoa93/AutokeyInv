<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
  
<title>Add Transponder</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form name="addNewTransponderform" action="addNewTransponderform"  modelAttribute = "Item" >
<table class="table table-striped">
   <tr>
    <td>Category Name</td>
    <td><select id="categoryName" name="categoryName" onchange="categoryFilter()">
    
   			 <c:forEach items="${categoryList}" var="category">
    
            		<option value="${category}">${category}</option>
    
        		
    		</c:forEach>

			</select></td>		
  </tr>
  <tr>
    <td>Sub Category Name</td>
    <td><select id="subCategoryName" name="subCategoryName" >
			</select></td>		
  </tr>
 
  
  
  <tr>
    <td>Transponder Name</td>
    <td><input name="transponder" type="text" required="required"/></td>		
  </tr>
 
   <tr>
   <td></td>
    <td><input type="submit"  class="btn btn-default" value="Add New Transponder"></td>		
  </tr>
  
</table>
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
</body>

</html>