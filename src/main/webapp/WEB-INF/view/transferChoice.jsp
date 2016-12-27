<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Transfer Choice</title>
</head>
<body>
<jsp:include page="employeeoptions.jsp" />
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<br><br><br><br><br><br><br><br><br>
<ca:form  name="transferChoiceRequest" action="transferChoiceRequest" >
<p align="center">

	Select Transfer Type
	<br><br>
	<select name="transferChoice" id="transferChoice">
	
	<option>Group Transfer</option>
	<option>Single Transfer</option>
	
	</select><br><br>
     <button type="submit" class="btn btn-default">Submit</button>
</p>

   </ca:form>
   </body>
</html>