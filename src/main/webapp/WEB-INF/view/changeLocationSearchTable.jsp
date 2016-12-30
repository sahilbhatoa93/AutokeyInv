<!DOCTYPE html>
<%@ page import="java.time.Year" %>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>Change Location</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />
<ca:form id="changeLocationRequest" name="changeLocationRequest" action="changeLocationRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table id="table1" class="table table-striped" >
  <tr>
  	<td>SKU</td>
  	 	<td>Inventory</td>
  	<td>Category</td>
    <td>Sub Category</td>
   	<td>Brand</td>
   	<td>Model</td>
   	<td>Trim</td>
    <td>From Year</td>
    <td>To Year</td>
    <td>Item Code</td>
    <td>Rack</td>
    <td>Shelf</td>
    <td>Column</td>
    <td>Compartment</td>
    
  
  </tr>
   <tr>
            <td>${data[0]}</td>
              <td>${data[13]}</td>
            <td>${data[1]}</td>
            <td>${data[2]}</td>
            <td>${data[3]}</td>
            <td>${data[4]}</td>
            <td>${data[5]}</td>
            <td>${data[6]}</td>
            <td>${data[7]}</td>
            <td>${data[8]}</td>
            <td>${data[9]}</td>
            <td>${data[10]}</td>
            <td>${data[11]}</td>
            <td>${data[12]}</td>
            
           
           
        </tr>
  
  </table>
  <br>
  <br>

  
   
<table id="table3" class="table table-striped" >
  <tr>
  	<td>New Rack</td>
  	 <td><input id="rack" name="rack" type="text"/></td>
    
  </tr>
   <tr>
            <td>New Shelf</td>
     <td><input id="shelf" name="shelf" type="text"/></td>       </tr>
  <tr>
            <td>New Column</td>
           <td><input id="column" name="column" type="text"/></td>
          </tr>
           <tr>
            <td>New Compartment</td>
           <td><input id="compartment" name="compartment" type="text"/></td>
           </tr>
           <tr>
            <td></td>
              <td><input id="submitButton" name="submitButton"  class="btn btn-default" value="Change Location" type="submit"/></td> 
         
  </table>
   <br><br>
          <input id="sku" name="sku" value="${data[0]}" type="hidden"/>  <input id="invName" name="invName" value="${data[13]}" type="hidden"/>
  </ca:form>
</body>

</html>