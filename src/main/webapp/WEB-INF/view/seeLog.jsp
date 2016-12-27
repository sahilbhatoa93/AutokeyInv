<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Logs</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="searchUserlogs" modelAttribute="Login">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">

  <label for="loginID">Select User</label>
  <br>
			 <select id="loginID" name="loginID" >
        		<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${loginIDList}" var="name">
        		<c:if test="${name != selected}">
            		<option value="${name}">${name}</option>
        		</c:if>
        		</c:forEach>
		</select>
			<br><br>
			<label for="logCategory">Select Log Category</label>
			<br>
     <select id="logCategory" name="logCategory" >
        		<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${logCategoryList}" var="logCategory">
        		<c:if test="${logCategory != selected}">
            		<option value="${logCategory}">${logCategory}</option>
        		</c:if>
        		</c:forEach>
		</select>
     <br><br>
			<label for="dateRange">Select Range</label>
			<br>
			<label for="logDateRangeFrom">From</label>
			<input type="date" name="logDateRangeFrom" id="logDateRangeFrom"/>
          <label for="logDateRangeTo">To</label>
           <input type="date" name="logDateRangeTo" id="logDateRangeTo"/>
      
         
   </div>
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>