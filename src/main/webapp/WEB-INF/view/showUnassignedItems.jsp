<!DOCTYPE html>
<%@ page import="java.time.Year" %>
<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
     
<title>Brand/Trim Assignment</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<%   int counter=0; %>
<jsp:include page="adminoptions.jsp" />
<ca:form id="itemAssignmentForm" name="itemAssignmentForm" action="trimModelItemAssignmentFormRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table id="table1" class="table table-striped" >
  <tr>
  	<td>SKU</td>
  	
    <td>Category</td>
    <td>Sub Category</td>
    <td>Transponder</td>
    <td>Description</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
  
  </tr>
   <c:forEach items="${unassignedList}" var="result">
   <tr>
            <td>${result.sku}</td>
            <td>${result.categoryName}</td>
            <td>${result.subCategoryName}</td>
             <td>${result.transponder}</td>
            <td>${result.description}</td>
            <td>${result.iC}</td>
          	<td>${result.noOfButton}</td>
            <td>${result.buttonConfiguration}</td>
        </tr>
      </c:forEach>

  </table>
  <br>
  <br>
  <div>
  </div>
  </ca:form>
   </body>

</html>