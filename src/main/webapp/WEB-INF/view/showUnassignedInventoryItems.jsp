<!DOCTYPE html>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title> Inventory Assignment</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />
<ca:form id="itemAssignmentForm" name="itemAssignmentForm" action="itemAssignmentFormRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table id="table1" class="table table-striped" >
  <tr>
  	<td>SKU</td>
  	<td>Brand</td>
  	<td>Model</td>
  	<td>Trim</td>
    <td>Category</td>
    <td>Sub Category</td>
    <td>Description</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
  	<td>No Of Items</td>
  	<td>From Year</td>
  	<td>To Year</td>
  </tr>
  <c:forEach items="${unassignedList}" var="result">
   <tr>
            <td>${result.sku}</td>
       <td>${result.brand}</td>
       <td>${result.model}</td>
       <td>${result.trim}</td>
            <td>${result.categoryName}</td>
            <td>${result.subCategoryName}</td>
            
            <td>${result.description}</td>
            <td>${result.iC}</td>
          	<td>${result.noOfButton}</td>
            <td>${result.buttonConfiguration}</td>
            <td>${result.noOfItems}</td>
            <td>${result.fromYear}</td>
            <td>${result.toYear}</td>
        </tr>
      </c:forEach>
  </table>
  </ca:form>

</body>

</html>