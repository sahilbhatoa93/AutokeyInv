<!DOCTYPE html>

<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
<title>Search Result</title>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<table class="table table-striped">
  <tr>
    <td>SKU</td>
    <td>Item Code</td>
    <td>Inventory Name</td>
    <td>Brand</td>
    <td>Category</td>
    <td>Sub Category</td>
    <td>Model</td>
      <td>Trim</td>
    <td>Description</td>
    <td>Rack</td>
    <td>Shelf</td>
    <td>Column</td>
    <td>Compartment</td>
    <td>FCC-ID</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
    <td>Battery Part Number</td>
     <td>No. Of Items</td>
    <td>ReStock Limit</td>
  </tr>
   <c:forEach items="${dataList}" var="dataItem">
        <tr>
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
            <td>${dataItem.batteryPartNumber}</td>
            <td>${dataItem.noOfItems}</td>
            <td>${dataItem.restockLimit}</td>
            
            
        </tr>
    </c:forEach>
  </table>


</body>

</html>