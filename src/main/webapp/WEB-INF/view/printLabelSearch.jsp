<!DOCTYPE html>


<html>
<head>
 	
   <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>


<title>Print Label</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<form  action="printIndividualLabel" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
   <div class="form-group" align="center">
      <label for="itemCode">Scan/Enter Item Code To Be Updated</label>
      <input id="itemCode" type="text" class="form-control" name="itemCode" 
         placeholder="Enter Item Code">
   </div>
  

 <div class="form-group" align="center">
 <p>OR</p>
  <label for="itemCode">Select Inventory</label>
			<select id="invName" name="invName" >
        		<option value="${selectedInvCode}" selected>${selectedInvCode}</option>
   			 <c:forEach items="${senderInventoryList}" var="inventory">
        			<option value="${inventory}">${inventory}</option>
        		</c:forEach>
	</select>
	
	 <div class="form-group" align="center">
      <label for="sku">Scan/Enter SKU To Be Updated</label>
      <input id="sku" type="text" class="form-control" name="sku" 
         placeholder="Enter SKU">
   </div>
			
     
    
      
         
   </div>
	<p align="center">
   <button type="button" class="btn btn-default" onclick="callPrint()">Print Label</button>
   </p>
   </form>
    
</body>

<script type="text/javascript">

function callPrint()
		{
		invName
		var itemCodeField=document.getElementById('itemCode').value;
		var skuField=document.getElementById('sku').value;
		var invNameField=document.getElementById('invName').value;
		if (itemCodeField=='')
			itemCodeField=null
		if (skuField=='')
		skuField=null;
		if (invNameField=='')
		invNameField=null;
		var data=itemCodeField+'&&&'+skuField+'&&&'+invNameField;
		var obj = {};
		obj['data'] = data;
		$.ajax({
            type: "GET",
            url: 'printIndividualLabel',
            contentType:  "application/json; charset=utf-8",
            data: obj,
            dataType: "text",
            success: function(result) {  
            
            	if (result=='No Item Found')
            	{
            	alert(result);
            	} 
				else
				{
				
				var a = document.createElement('a');
				var linkText = document.createTextNode("");
				a.appendChild(linkText);
				a.title = "Download pdf";
				a.href = "startDownload";
				document.body.appendChild(a);
				a.click();
				}
			 	}
		    });
		}; 

</script>
</html>