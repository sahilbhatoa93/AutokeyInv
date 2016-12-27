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

<ca:form name="cycleCountForm" action="cycleCountRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />
<h3>Please Do the Daily Cycle Count</h3>
<table class="table table-striped">
  <tr>
    <td>SKU</td>
    <td>Inventory Name</td>
    <td>Item Code</td>
    <td>Brand</td>
    <td>Category</td>
    <td>Sub Category</td>
    <td>Description</td>
    <td>Rack</td>
    <td>Shelf</td>
    <td>Column</td>
    <td>Compartment</td>
    <td>No. of Buttons</td>
    <td>Enter Number of Items</td>
  </tr>
  
   <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td>${dataItem.sku}</td>
            <td>${dataItem.invName}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.categoryName}</td>
            <td>${dataItem.subCategoryName}</td>
            <td>${dataItem.description}</td>
            <td>${dataItem.rack}</td>
            <td>${dataItem.shelf}</td>
            <td>${dataItem.column}</td>
            <td>${dataItem.compartment}</td>
            <td>${dataItem.noOfButton}</td>
            <td><input type="text" id="numberField<%out.print(counter++);%>" name="numberField<%out.print(counter-1); %>" required="required"/></td>
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
            <td></td> <td></td>
            <td><button type="submit" class="btn btn-default"  id="submitButton" name="submitButton">Submit</button></td>
        </tr>
  </table>
 <input type="hidden" name="result" id="result" value="${result}" />
</ca:form>
<script type="text/javascript">
$('#submitButton').click(	
function() {
var result='';
var $table = $(this).closest('table')
$trs=$table.find('tr');
var counter1=0;
$.each($trs, function() {  
var counter2=0;
var $row = $(this).closest('tr');
if (!(($(this).is(":first-child")) || ($(this).is(":last-child"))))
{
$tds = $row.find('td');
$.each($tds, function() {   
result =result+'&&&'+$(this).text();
if ($(this).is(":last-child"))
{
<% for (int i=0;i<counter;i++) { 
if (i==0)
{
out.print("if (counter1 =="+i+")");
out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value; counter1++; }");
}
else
{
out.print("else if (counter1 =='"+i+"')");
out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value;  counter1++; }");
}

}%>
result =result+numberFieldValue+'###';
}
});
}
});
document.getElementById('result').value = result;

});


 </script>
</body>

</html>