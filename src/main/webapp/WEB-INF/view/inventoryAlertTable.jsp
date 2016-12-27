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
<ca:form id="restockform" name="restockform" action="restockformRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table class="table table-striped" >
  <tr>
  	<td><input type="checkbox" id="primeCheckbox" onchange="checkAll()"/></td>
    <td>SKU</td>
    <td>Item Code</td>
    <td>Inventory Name</td>
    <td>Brand</td>
    <td>Category</td>
    <td>Sub Category</td>
     <td>Model</td> <td>trim</td>
    <td>Description</td>
    <td>Rack</td>
    <td>Shelf</td>
    <td>Column</td>
    <td>Compartment</td>
    <td>FCC-ID</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
   <td >Items Received</td>
     <td>Select Reason</td>
  </tr>
  
   <c:forEach items="${dataList}" var="dataItem">
        <tr>
        	<td><input type="checkbox" id="checkButton<%out.print(counter++);%>" onchange="checkChecker()" /></td>
            <td>${dataItem.sku}</td>
            <td>${dataItem.itemCode}</td>
            <td>${dataItem.invName}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.categoryName}</td>
            <td>${dataItem.subCategoryName}</td>
            <td>${dataItem.model}</td>
                <td>${dataItem.trim}</td>
            <td>${dataItem.description}</td>
            <td>${dataItem.rack}</td>
            <td>${dataItem.shelf}</td>
            <td>${dataItem.column}</td>
            <td>${dataItem.compartment}</td>
            <td>${dataItem.ffcId}</td>
            <td>${dataItem.iC}</td>
          	<td>${dataItem.noOfButton}</td>
            <td>${dataItem.buttonConfiguration}</td>
          
           <%--  <td><button class="btn btn-default" id="reStockButton<%out.print(counter-1);%>" name="reStockButton<%out.print(counter-1); %>"  type="submit">ReStock</button></td> --%>
            <td >
      				<input type="number"   id="numberField<%out.print(counter-1);%>" name="numberField<%out.print(counter-1); %>" required="required" /></td>
      			
            <td> 
			<select id="reason<%out.print(counter-1);%>" name="reason<%out.print(counter-1);%>" >
        		<option value="${selectedReason}" selected>${selectedReason}</option>
   			 <c:forEach items="${reasonList}" var="reason">
        			<option value="${reason}">${reason}</option>
        		</c:forEach>
	</select>
</td>
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
    <td></td>
    <td></td>
    <td></td>
   <td>  <input type="button" class="btn btn-default" id="printLabelButton" value="Print Label" ></td>
      <td> <input type="button" class="btn btn-default" id="reStockButton" value="Restock"></td>
  </tr>
  </table>
 
</ca:form>
<script type="text/javascript">
<%-- <%    
  for (int i=0;i<counter;i++)
  {
  out.println("$('#reStockButton"+i+"').click(	function() {var $row = $(this).closest('tr');  $tds = $row.find('td');  var result='';  $.each($tds, function() {   result =result+'&&&'+$(this).text();});document.getElementById('result').value = result;});");
  
  }
%>
 --%>
 
 
 
 <%	for (int i=0;i<counter;i++)
  {
  out.println("var checkbox"+i+"= document.getElementById('checkButton"+i+"');");
  }
	%>
$('#reStockButton').click(	
function() {
var result='';
var condition=false;
var $table = $(this).closest('table');
$trs=$table.find('tr');
var counter1=0;
$.each($trs, function() 
	{  
	var counter2=0;
	var $row = $(this).closest('tr');

	if (!(($(this).is(":first-child")) || ($(this).is(":last-child"))))
	{
		
		$tds = $row.find('td');
		$.each($tds, function() 
		{
			
		if ($(this).is(":first-child"))
				{
				
			<% for (int i=0;i<counter;i++) 
			{ 
				if (i==0)
				{
				out.print("if (counter1 =="+i+")");
				out.println("{ if (checkbox"+i+".checked) { condition= true;  } else {  document.getElementsByTagName('input')["+i+"].removeAttribute('required'); counter1++; } }");
				}
				else
				{
				out.print("else if (counter1 =="+i+")");
				out.println("{ if (checkbox"+i+".checked) {  condition= true;  } else {  document.getElementsByTagName('input')["+i+"].removeAttribute('required'); counter1++; } }");
				}
				
			}%>}	
		
			if (condition)
			{
				if (result =='')
				result =$(this).text();
				else
				{
				if (!($(this).is(":last-child")))
					if ($(this).text()=='')
					result =result+'&&&'+'No Info Provided';
					else
						result =result+'&&&'+$(this).text();
				}
				
				if ($(this).is(":last-child"))
				{
				<% for (int i=0;i<counter;i++) { 
				if (i==0)
				{
				out.print("if (counter1 =="+i+")");
				out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value; counter1++; var reasonValue=document.getElementById('reason"+i+"').value; }");
				}
				else
				{
				out.print("else if (counter1 =='"+i+"')");
				out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value;  counter1++;  var reasonValue=document.getElementById('reason"+i+"').value;}");
				}
				}%>
				result =result+numberFieldValue+'&&&'+reasonValue+'###';
				condition=false;	
				}
		
			}  
			
		});
	}
	});
document.getElementById('result').value = result;
if (result=="")
{
alert("Please select atleast one Checkbox to print Label");
}
else
{
document.getElementById("restockform").submit();
}


});	
	
$('#printLabelButton').click(	
function() {
var result='';
var condition=false;
var $table = $(this).closest('table')
$trs=$table.find('tr');
var counter1=0;
$.each($trs, function() 
	{  
	var counter2=0;
	var $row = $(this).closest('tr');

	if (!(($(this).is(":first-child")) || ($(this).is(":last-child"))))
	{
		
		$tds = $row.find('td');
		$.each($tds, function() 
		{
			
		if ($(this).is(":first-child"))
				{
				
			<% for (int i=0;i<counter;i++) 
			{ 
				if (i==0)
				{
				out.print("if (counter1 =="+i+")");
				out.println("{ if (checkbox"+i+".checked) { condition= true; } else { counter1++;} }");
				}
				else
				{
				out.print("else if (counter1 =="+i+")");
				out.println("{ if (checkbox"+i+".checked) {  condition= true;} else { counter1++;} }");
				}
				
			}%>}	
		
			if (condition)
			{
				if (result =='')
				result =$(this).text();
				else
				{
				if (!($(this).is(":last-child")))
					{
					if ($(this).text()=='')
						result =result+'&&&'+'No Info Provided';
					else
						result =result+'&&&'+$(this).text();
					}
				}
				
				if ($(this).is(":last-child"))
				{
				<% for (int i=0;i<counter;i++) { 
				if (i==0)
				{
				out.print("if (counter1 =="+i+")");
				out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value;  var reasonValue=document.getElementById('reason"+i+"').value; counter1++;}");
				}
				else
				{
				out.print("else if (counter1 =="+i+")");
				out.println("{ var numberFieldValue=document.getElementById('numberField"+i+"').value;    var reasonValue=document.getElementById('reason"+i+"').value; counter1++;}");
				}
				}%>
				result =result+numberFieldValue+'&&&'+reasonValue+'###';
				condition=false;	
				}
				
			}  
			
		});
	}
	});
document.getElementById('result').value = result;
if (result=="")
{
alert("Please select atleast one Checkbox to print Label");
}
else
{
callPrint();
}


});

function callPrint()
		{
		var data=document.getElementById('result').value;
		var obj = {};
		obj['data'] = data;
		$.ajax({
            type: "GET",
            url: 'printLabelInvAlertTable',
            contentType:  "application/json; charset=utf-8",
            data: obj,
            dataType: "text",
            success: function(result) {   
				var a = document.createElement('a');
				var linkText = document.createTextNode("");
				a.appendChild(linkText);
				a.title = "Download pdf";
				a.href = "startDownload";
				document.body.appendChild(a);
				a.click();
				}
			
            });
		
		}; 
 
function checkChecker()
{

	
	
<%	for (int i=0;i<counter;i++)
  {
  out.println("if (!(checkbox"+i+".checked)) { primeCheckbox.checked= false; }");
  }
	%>
	

}

function checkAll()
{
	var primeCheckbox=document.getElementById("primeCheckbox");

	
	
	
	if (primeCheckbox.checked)
	{
<%	for (int i=0;i<counter;i++)
  {
  out.println("document.getElementById('checkButton"+i+"').checked = true;");
  }
	%>
	}
	else
	{
<%	for (int i=0;i<counter;i++)
  {
  out.println("document.getElementById('checkButton"+i+"').checked = false;");
  } %>
	}






}
 </script>
</body>

</html>