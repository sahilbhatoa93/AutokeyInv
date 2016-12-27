<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
function validateForm() {
    var x = document.forms["updatePasswordForm"]["currentPassword"].value;
     var y = document.forms["updatePasswordForm"]["newPassword"].value;
     var z = document.forms["updatePasswordForm"]["confirmNewPassword"].value;
     
    if (x == null || x == "") {
        alert("Current Password must be filled out");
        return false;
    }
    if (y == null || y == "") {
        alert("New Password must be filled out");
        return false;
    }
    if (z == null || z == "") {
        alert("Confirm New Password must be filled out");
        return false;
    }
    if (y!=z) {
        alert("New Password and Confirm New Password doesn't match");
        return false;
    }
}

</script>
<title>Change Password</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>

<jsp:include page="employeeoptions.jsp" />
<h2>Update Password :</h2>
<ca:form name="updatePasswordForm" action="updatePasswordRequest" method="post" onsubmit="return validateForm()" modelAttribute="Login">

<table class="table table-striped">
 
   <tr>
    <td>Current Password <font color="red">*</font>:</td>
    <td><input type="password" name="currentPassword" ></td>		
      <td><p>${currentPassword}</p></td>		
  </tr>
  <tr>
    <td>New Password<font color="red">*</font>:</td>
    <td><input type="password" name="newPassword"></td>	
       <td><p>${currentPassword}</p></td>		
  </tr>
  <tr>
    <td>Confirm New Password<font color="red">*</font>:</td>
    <td><input type="password" name="confirmNewPassword"></td>	
       <td><p>${currentPassword}</p></td>		
  </tr>
 <tr>
   	 <td></td>
    <td><input type="submit" class="btn btn-default" value="Change Password"></td>
    	
  </tr>
  
</table>
<br>
</ca:form>

</body>
</html>