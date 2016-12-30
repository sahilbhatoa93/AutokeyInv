<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Remove Reason</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchDeleteReason" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="reason">Select Reason To Be Deleted</label>
      <select id="reason" name="reason">
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${reasonList}" var="reason">
        		<c:if test="${reason != selected}">
            		<option value="${reason}">${reason}</option>
        		</c:if>
        		
    		</c:forEach>

			</select>
   </div>
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Delete Reason</button>
   </p>
   </form>
    
</body>
</html>