<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Search</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<form  action="searchResultTable" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="itemCode">Scan/Enter Item Code To Be Searched</label>
      <input type="text" class="form-control" name="itemCode" 
         placeholder="Enter Item Code">
   </div>
<br>
 <div class="form-group" align="center">
 <p>OR</p>
  <label for="itemCode">Select Inventory</label>
			 <select name="invCode">
				 <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${invList}" var="invName">
        		<c:if test="${invName != selected}">
            		<option value="${invName}">${invName}</option>
        		</c:if>
        		
    		</c:forEach>
			</select>
			<br>
     
     <input type="text" class="form-control" name="rack" 
         placeholder="Enter Rack">
         <br>
    
      <input type="text" class="form-control" name="shelf" 
         placeholder="Enter Shelf">
         <br>
      
      <input type="text" class="form-control" name="column" 
         placeholder="Enter Column">
         <br>
     
      <input type="text" class="form-control" name="compartment" 
         placeholder="Enter Compartment">
         
   </div>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>