<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Transfer ID</title>
</head>
<body>

<jsp:include page="employeeoptions.jsp" />
<h2>Please Note the Transfer ID :</h2>
<table class="table table-striped">
 
   <tr>
    <td>Transfer ID :</td>
    <td>${result}</td>		
      		
  </tr>
  <tr>
    <td>Status:</td>
    <td>Initiated</td>	
       		
  </tr>

</table>
<br>
<script type="text/javascript">

function callPrint()
		{
		
		var obj = {};
		obj['data'] = ${result};
		$.ajax({
            type: "GET",
            url: 'printTransferLabel',
            contentType:  "application/json; charset=utf-8",
            data: obj,
            dataType: "text",
            success: function(result) { alert(result); }
			
            });
		
		}; 
</script>

</body>
</html>