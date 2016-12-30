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
<jsp:include page="adminoptions.jsp" />
<ca:form id="itemAssignmentForm" name="itemAssignmentForm" action="itemAssignmentFormRequest"  modelAttribute = "Item">
<input type="hidden" name="result" id="result" value="${result}" />

<table id="table1" class="table table-striped" >
  <tr>
  	<td>SKU</td>
  	 <td>Brand</td>
    <td>Category</td>
    <td>Sub Category</td>
     <td>Model</td>
      <td>Trim</td>
    <td>Description</td>
    <td>IC</td>
    <td>Number Of Buttons</td>
    <td>Button Configuration</td>
   <td >From Year</td>
     <td>To Year</td>
  </tr>
   <tr>
            <td>${result.sku}</td>
            <td>${result.brand}</td>
            <td>${result.categoryName}</td>
            <td>${result.subCategoryName}</td>
            <td>${result.model}</td>
            <td>${result.trim}</td>
            <td>${result.description}</td>
            <td>${result.iC}</td>
          	<td>${result.noOfButton}</td>
            <td>${result.buttonConfiguration}</td>
             <td>${result.fromYear}</td>
              <td>${result.toYear}</td>
        </tr>
  
  </table>
  <br>
  <br>
<table id="table2" class="table table-striped" >
  <tr>
  	<td>Select Inventory to Be Assigned</td>
  	 <td><select id="invName" name="invName" >
        	<c:forEach items="${senderInventoryList}" var="inventory">
        		<c:if test="${inventory != selected}">
            		<option value="${inventory}">${inventory}</option>
        		</c:if>
        		</c:forEach>
		</select></td>
    
  </tr>
   <tr>
            <td>Rack</td>
            <td><input type="text"   id="rack" name="rack" required="required" /></td>
           </tr>
  <tr>
            <td>Shelf</td>
            <td><input type="text"   id="shelf" name="shelf" required="required" /></td>
           </tr>
           <tr>
            <td>Column</td>
            <td><input type="text"   id="column" name="column" required="required" /></td>
           </tr>
           <tr>
            <td>Compartment</td>
            <td><input type="text"   id="compartment" name="compartment" required="required" /></td>
           </tr>
           <tr>
            <td>No of Items</td>
            <td><input type="number"   id="noOfItems" name="noOfItems" required="required" /></td>
           </tr>
           <tr>
            <td>Restock Limit</td>
            <td><input type="number"   id="restockLimit" name="restockLimit" required="required" /></td>
           </tr>
             <tr>
            <td></td>
            <td><input type="submit" class="btn btn-default"  id="submitButton"  value="Submit"/></td>
           </tr>
  </table>
 <input type="hidden" name="sku" id="sku" value="${result.sku}" />
</ca:form>
</body>

</html>