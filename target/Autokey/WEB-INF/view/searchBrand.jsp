<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Search Brand</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchBrandTable" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="brand">Select Brand Name To Be Updated</label>
     <select id="brand" name="brand" >
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select>
   </div>
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>