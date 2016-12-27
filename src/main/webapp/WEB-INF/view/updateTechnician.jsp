<!DOCTYPE html>
<html>


<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>

<title>Update User</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form action="updateTechnicianRequest" method="get" modalAttribute="Login">
<table align="center" class="table table-striped" >
 
   <tr>
    <td>Name<font color="red">*</font>:</td>
    <td><input type="text" name="name" value="${resultlogin.name}"></td>	<td></td>	
  </tr>
  <tr>
    <td>Login Id<font color="red">*</font>:</td>
    <td><input type="text" name="loginID" value="${resultlogin.loginID}"></td>	<td></td>	
  </tr>
  <tr>
    <td>Password<font color="red">*</font>:</td>
    <td><input type="text" name="password" value="${resultlogin.password}"></td>	<td></td>	
  </tr>
  <tr>
    <td>Email<font color="red">*</font>:</td>
    <td><input type="email" name="email" value="${resultlogin.email}"></td>	<td></td>	
  </tr>
  <tr>
    <td>Role<font color="red">*</font>:</td>
    <td>
   <select name="role">
  		 <c:forEach items="${roleList}" var="role">
        		<c:choose>
        			 <c:when test="${role == selectedRole}">
       					<option selected="selected" value="${role}">${role}</option>
    				</c:when>  
            		<c:otherwise>
            			<option  value="${role}">${role}</option>	
            		</c:otherwise>
        		</c:choose>
        	</c:forEach>
</select>
</td>		<td></td>
  </tr>
  <tr>
  
   <td>Current List<font color="red">*</font>:</td>
    <td>${resultlogin.accessInvList}</td>		
  <td></td>
  </tr>
   <tr>
    <td>Access Inventory List<font color="red">*</font>:</td>
   <td><select name="accessInvList" multiple>
			 <c:forEach items="${invList}" var="invName">
   				 <c:forEach items="${selectedInvList}" var="selectedInvName">
   			 		<c:choose>
        			 <c:when test="${invName == selectedInvName}">
       					<option selected="selected" value="${selectedInvName}">${selectedInvName}</option>
    				</c:when>  
            	</c:choose>
        	</c:forEach>
        	</c:forEach>
			 <c:forEach items="${remainingInvList}" var="invName">
			 			<option value="${invName}">${invName}</option>
   			</c:forEach>
			
			
			
			</select></td>
			<td><p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p></td>
  </tr>
    <tr>
   	 <td></td>
    <td><input type="submit" class="btn btn-default" value="Update User"></td>
    	<td></td>
  </tr>
  
</table>
</ca:form>
</body>

</html>