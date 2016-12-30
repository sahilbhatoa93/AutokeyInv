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
<form  action="searchtransferId" modelAttribute="TransferObject" autocomplete="off">
<br><br><br><br><br><br><br><br>
  
 <div  align="center">

  <label for="transferId">Enter Transfer ID</label>
  <br>
			<input name="transferId"  id="transferId" type="number" />
			<br>  
         
   </div>
   <br>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>