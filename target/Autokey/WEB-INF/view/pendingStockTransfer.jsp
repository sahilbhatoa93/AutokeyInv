<!DOCTYPE html>
<%@page import="model.TransferObject"%>


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
<jsp:include page="employeeoptions.jsp" />
<ca:form  action="doTransferRequest" modelAttribute="TransferObject">
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
      <td>Date</td>
     <td>Number Of Items</td>			
  </tr>
  <c:forEach items="${dataList}" var="dataItem">
        <tr>
            <td>${dataItem.transferId}</td>
            <td>${dataItem.sku}</td>
            <td>${dataItem.invCodeFrom}</td>
            <td>${dataItem.invCodeTo}</td>
            <td>${dataItem.brand}</td>
            <td>${dataItem.categoryName}</td>
            <td>${dataItem.subCategoryName}</td>
            <td>${dataItem.model}</td>
            <td>${dataItem.date}</td>
            <td>${dataItem.transferAmount}</td>
           
            
            
        </tr>
    </c:forEach>
</table>
</ca:form>

</body>

</html>