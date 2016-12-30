<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Delete Category</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchDeleteTable" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="categoryName">Enter Category To Be Deleted</label>
      <select id="categoryName" name="categoryName">
      	 <c:forEach items="${categoryList}" var="category">
            		<option value="${category}">${category}</option>
      	</c:forEach>
	</select>
   </div>
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Delete Category</button>
   </p>
   </form>
    
</body>
</html>