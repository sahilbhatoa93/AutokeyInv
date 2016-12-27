<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Create Inventory</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="adminoptions.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form  action="seediscrepanciesRequest" modelAttribute="LogObject">
<br><br><br><br><br><br><br><br>
  
 <div class="form-group" align="center">
 
<label for="user">Select User</label>
  <br>
			 <select id="user" name="user" onchange="inventoryFilter()">
        		<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${loginIDList1}" var="name">
        		<c:if test="${name != selected}">
            		<option value="${name}">${name}</option>
        		</c:if>
        		</c:forEach>
		</select> <br>
  <label for="invFrom">Select Inventory</label>
  <br>
			<select id="invFrom" name="invFrom" >
        		
		</select>
			<br>  
         
   </div>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
	
   </form>
    <script type="text/javascript">
    
    var loginIDListStringDetails=${loginIDListString};
    function inventoryFilter()
		{
		var userName=document.getElementById("user").value;
		var select = document.getElementById("invFrom");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=loginIDListStringDetails[userName];
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