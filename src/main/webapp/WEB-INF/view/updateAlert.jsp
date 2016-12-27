<!DOCTYPE html>



<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Update Search</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="updatesearchAlertRequest" modelAttribute="Alert">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="alertName">Select Alert Name To Be Updated</label>
      <select name="alertName">
			<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${alertList}" var="alertName">
        		<c:if test="${alertName != selected}">
            		<option value="${alertName}">${alertName}</option>
        		</c:if>
        		</c:forEach>
		
			</select>
   </div>
<br>
 	<p align="center">
   <button type="submit" class="btn btn-default">Search</button>
   </p>
   </form>
    
</body>
</html>