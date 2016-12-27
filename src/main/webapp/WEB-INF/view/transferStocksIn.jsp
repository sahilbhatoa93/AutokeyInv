<!DOCTYPE html>



<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Stock Transfer</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<%   int counter=0; %>
<jsp:include page="employeeoptions.jsp" />
<ca:form id="acceptTransferForm" action="doTransferRequest" modelAttribute="TransferObject">
<input type="hidden" name="result" id="result" value="${result}" />
<table class="table table-striped">
 <tr>
    <td>Transfer ID</td>
    <td>SKU</td>
       <td>From</td>
        <td>To</td>
         <td>Brand</td>
  	 <td>Category</td>		
     <td>SubCategory</td>
      <td>Model</td>
      <td>Rack</td>
      <td>Shelf</td>
      <td>Column</td>
      <td>Compartment</td>
     <td>Number Of Items</td>	
     <td>Amount Received</td>
     <td>Select Reason If No. Of Items is different from Amount Received</td>		
  </tr>
   <c:forEach items="${TransferObject}" var="dataItem">
        <tr>
            <td>${dataItem.transferId}</td>
  			 <td id="sku<%out.print(counter++);%>">${dataItem.sku}</td>
  			  <td>${dataItem.invCodeFrom}</td>
  			 <td>${dataItem.invCodeTo}</td>
   			 <td>${dataItem.brand}</td>
  			 <td>${dataItem.categoryName}</td>
  			 <td>${dataItem.subCategoryName}</td>
  			  <td>${dataItem.model}</td>
   			  <td>${dataItem.rackTo}</td>
   			  <td>${dataItem.shelfTo}</td>
   			  <td>${dataItem.columnTo}</td>
   			  <td>${dataItem.compartmentTo}</td>
 			  <td  id="transferAmountTD<%out.print(counter-1);%>"  >${dataItem.transferAmount}</td>
 			  <td><input type="number" id="transferAmount<%out.print(counter-1);%>" name="transferAmount<%out.print(counter-1); %>" /></td>
 			    <td><select id="reason<%out.print(counter-1);%>" name="reason<%out.print(counter-1); %>" >
        <option value="${selectedReason}" selected>${selectedReason}</option>
   			 <c:forEach items="${reasonList}" var="reason">
        		<c:if test="${reason != selected}">
            		<option value="${reason}">${reason}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
        </tr>
 
    </c:forEach>
  
  <tr>
   	<td></td>
    <td></td>
    	 <td></td>
    	  <td></td>
    	   <td></td>
    	   <td></td>
    	   <td></td>
    	   <td></td>
    	    <td></td>
    	    <td></td>
    	     <td></td>
    	      <td></td>
    	      <td></td>
    	       <td></td>
    	      <td><input id="acceptTransfer" type="button" class="btn btn-default" value="Accept All"/></td>
  </tr> 
   
   
</table>
</ca:form>
<script type="text/javascript">
$('#acceptTransfer').click(	
function() {
var resultBoolean=true;


<% for (int i=0;i< counter;i++ )
{
	out.println("var e"+i+" = document.getElementById('reason"+i+"');"); 
	out.println("var skuID"+i+" = document.getElementById('sku"+i+"');"); 
	out.println("var reasonValue"+i+" = e"+i+".options[e"+i+".selectedIndex].value;");
	out.println("var reasonValue"+i+" = e"+i+".options[e"+i+".selectedIndex].value;");
	out.println("var realAmount"+i+" = document.getElementById('transferAmount"+i+"');");
	out.println("if (document.getElementById('transferAmount"+i+"').value != document.getElementById('transferAmountTD"+i+"').innerHTML && reasonValue"+i+"=='' ){ alert('Please select reason for '+skuID"+i+".innerHTML); resultBoolean=false; };");
}%>

var result='';
var $table = $(this).closest('table');
<% int counter2=0;%>
$trs=$table.find('tr');
$.each($trs, function() {  
	var $row = $(this).closest('tr');
	if (!(($(this).is(":first-child")) || ($(this).is(":last-child"))))
	{
	$tds = $row.find('td');  
	$.each($tds, function() { 
	result =result+'&&&'+$(this).text();
	});
	<% out.println("result =result+'&&&'+realAmount"+counter2+".value+'###'");%>
	<% counter2++;%>
	}
	
});
document.getElementById('result').value = result;

 if (resultBoolean)
document.getElementById('acceptTransferForm').submit();
});


</script>
</body>

</html>