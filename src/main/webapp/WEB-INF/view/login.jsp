<!DOCTYPE html>
<html>
<head>
 	<title>Login Page</title>
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
   


</head>
<body>
<form  action="loginRequest" modelAttribute="login" method="post">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="loginID">Login ID</label>
      <input type="text" class="form-control" name="loginID" 
         placeholder="Enter Login ID" required="required">
   </div>
   <div class="form-group" align="center">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" 
         placeholder="Enter Password" required="required">
   </div>
   <p align="center">
   Enter Role<br>
<select name="role">
<option value="ADMIN">ADMIN</option>
<option value="EMPLOYEE">EMPLOYEE</option>
</select>
<br><br>
   <button  id="submitbtn" type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
  
</body>
</html>