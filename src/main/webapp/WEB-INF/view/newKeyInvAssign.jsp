<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Search</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<form  action="newKeyInvAssignRequest" modelAttribute="Key">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="description">Enter/Scan Item Code </label><br>
      <input type="text" class="form-control" name="itemCodePart1"  
         placeholder="Enter Item Code">
         <br><br>
         OR <br><br>
      <input type="text" class="form-control" name=invCode  
         value="${invName}" readonly="readonly">
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
<br>

	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>