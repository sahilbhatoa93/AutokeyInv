<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Delete Transponder</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchDeleteTableTranponder" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="transponder">Select Transponder To Be deleted</label>
      <select id="categoryName" name="categoryName" onchange="categoryFilter()" >
       	<option selected="selected"></option>
   			 <c:forEach items="${categoryList}" var="category">
            		<option value="${category}">${category}</option>
       	</c:forEach>

			</select> <br><br>
			 <select id="subCategoryName" name="subCategoryName" onchange="subCategoryFilter()">
			</select>
<br>	<br>		
			   <select id="transponder" name="transponder"  >
      			</select>
   
   </div>

      
   
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
  <script type="text/javascript">
    	var categoryDetails=${categoryMap};
    	var subCategoryDetails=${subCategoryMap};
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
		
    function subCategoryFilter()
		{
		var categoryName=document.getElementById("subCategoryName").value;
		var select = document.getElementById("transponder");
		categoryName=categoryName.trim();
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=subCategoryDetails[categoryName];
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