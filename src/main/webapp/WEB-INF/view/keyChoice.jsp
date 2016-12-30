<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Key Choice</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<form id="keyChoiceForm" action="keyChoiceRequest" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="brand">Please Select</label><br>
      <button type="button" class="btn btn-default" onclick="choiceSelector('For Sale')">For Sale</button> <button type="button" class="btn btn-default" onclick="choiceSelector('For Inventory')">For Inventory</button>
   </div>
<br>

	<p align="center">
  
   </p>
   <input type="hidden" id="choice" name="choice" />
   </form>
    <script type="text/javascript">
    function choiceSelector(choice)
    {
    document.getElementById("choice").value=choice;
    document.getElementById("keyChoiceForm").submit();
    }
    </script>
</body>
</html>