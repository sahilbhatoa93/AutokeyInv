<!DOCTYPE html>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>My Inventory List</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<%   int counter=0; %>
<jsp:include page="employeeoptions.jsp" />
<ca:form name="Restockform" action="stockTransferRequest"  modelAttribute = "TransferObject">
<input type="hidden" name="result" id="result" value="${result}" />
<table class="table table-striped">
  <tr>
    <td>SKU</td>
    <td>From Inventory</td>
    <td>To Inventory</td>
    <td>Brand</td>
    <td>Category</td>
    <td>Sub Category</td>
     <td>Model</td>
    <td>Number of Items</td>
    <td>Click To Make Changes</td>
    
  </tr>
  
   <c:forEach items="${groupTransferList}" var="dataItem">
        <tr>
            <td id="sku<%out.print(counter++);%>" >${dataItem.sku}</td>
            <td id="invCodeFrom<%out.print(counter-1);%>">${dataItem.invCodeFrom}</td>
            <td id="invCodeTo<%out.print(counter-1);%>">${dataItem.invCodeTo}</td>
            <td id="brand<%out.print(counter-1);%>">${dataItem.brand}</td>
            <td id="categoryName<%out.print(counter-1);%>">${dataItem.categoryName}</td>
            <td id="subCategoryName<%out.print(counter-1);%>">${dataItem.subCategoryName}</td>
             <td id="model<%out.print(counter-1);%>">${dataItem.model}</td>
             <td id="transferAmount<%out.print(counter-1);%>">${dataItem.transferAmount}</td>
           
           
            <td><button class="btn btn-default" id="updateButton<%out.print(counter-1);%>" name="updateButton<%out.print(counter-1); %>" value="updateButton<%out.print(counter-1); %>"  type="button">Update</button>    <button class="btn btn-default" id="deleteButton<%out.print(counter++);%>" name="deleteButton<%out.print(counter-1); %>" value="deleteButton<%out.print(counter-1); %>"  type="button">Delete</button></td>
               
            
            
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
    <td><button id="cancelTransferButton" name="" class="btn btn-default" type="submit">Cancel Transfer</button></td>
    <td><button id="confirmTransferButton" name="" class="btn btn-default" type="submit">Confirm and Initiate Transfer</button></td>
    
  </tr>
  </table>
 
</ca:form>
<script type="text/javascript">
<%    
  for (int i=0;i<counter;i++)
  {
  out.println("$('#updateButton"+i+"').click(	function() { var oldValue=$('#transferAmount"+i+"').text(); var newValue = prompt('Enter New Number Of Items', oldValue);  if (newValue != null) {   $('#transferAmount"+i+"').html(newValue);}});");
   out.println("$('#deleteButton"+i+"').click(	function() {var $row = $(this).closest('tr');  if (confirm('Do want to Delete this Item ?') == true) {  $row.remove(); } else {}  });");
  }
%>
$('#confirmTransferButton').click(	
function() {
var result='';
var $table = $(this).closest('table')
$trs=$table.find('tr');
$.each($trs, function() {  
var $row = $(this).closest('tr');
$tds = $row.find('td');  
$.each($tds, function() {   
result =result+'&&&'+$(this).text();
});
result =result+'###';
});
document.getElementById('result').value = result;

});

 </script>
</body>

</html>