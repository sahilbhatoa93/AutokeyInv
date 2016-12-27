<!DOCTYPE html>
<html>


<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>

<title>Add New User</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form action="addTechnicianRequest" method="get" modalAttribute="Login">
<table align="center" class="table table-striped" >
 
   <tr>
    <td>Name<font color="red">*</font>:</td>
    <td><input type="text" name="name"></td>	<td></td>	
  </tr>
  <tr>
    <td>Login Id<font color="red">*</font>:</td>
    <td><input type="text" name="loginID"></td>		<td></td>
  </tr>
  <tr>
    <td>Password<font color="red">*</font>:</td>
    <td><input type="text" name="password"></td>	<td></td>	
  </tr>
   <tr>
    <td>Email<font color="red"></font>:</td>
    <td><input type="email" name="email"></td>	
    <td></td>	
  </tr>
  <tr>
    <td>Role<font color="red">*</font>:</td>
    <td>
   <select name="role">
<option value="ADMIN">ADMIN</option>
<option value="EMPLOYEE">EMPLOYEE</option>
</select>
</td>	
<td></td>	
  </tr>
   <tr>
    <td>Access Inventory List<font color="red">*</font>:</td>
   <td><select name="accessInvList" multiple>
			 <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${invList}" var="invName">
        		<c:if test="${invName != selected}">
            		<option value="${invName}">${invName}</option>
        		</c:if>
        		
    		</c:forEach>
			
			</select></td>
			<td><p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p></td>
  </tr>
     <tr>
   	 <td></td>
    <td><input type="submit" class="btn btn-default" value="Add User"></td>
    <td></td>
    	
  </tr>
  
</table>
</ca:form>
</body>

</html>