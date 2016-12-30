<!DOCTYPE html>


<html>
<head>
 	<link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Remove Sub Category</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchSubDeleteTable" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="categoryName">Select Category and SubCategory To Be Removed</label><br>
     <select id="categoryName" name="categoryName" onchange="categoryFilter()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select> <br><br>
			 <select id="subCategoryName" name="subCategoryName" >
			</select>
   </div>
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Delete SubCategory</button>
   </p>
   </form>
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