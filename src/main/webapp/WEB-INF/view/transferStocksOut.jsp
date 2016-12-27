<!DOCTYPE html>


<html>
<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script type="text/javascript">



var jsArray =  ${skuList};
	 $( function() {
  	$( "#SKU" ).autocomplete({
      source: jsArray
    });
   } );
   
   
   
  </script>
 
<title>Stock Transfer</title>
</head>
<body>
<jsp:include page="employeeoptions.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form  action="stockTransferRequest" modelAttribute="TransferObject">
<input type="hidden" name="result" id="result" value="singleTransfer" />
<input type="hidden" name="data" id="data" value="${data}" />

<table class="table table-striped">
 <tr>
    <td>Transfer</td>
    <td>From</td>		
     <td>To</td>		
  </tr>
  <tr>
    <td>Inventory Name</td>
    <td>
			  <select id="invCodeFrom" name="invCodeFrom" >
        		<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${senderInventoryList}" var="inventory">
        		<c:if test="${inventory != selected}">
            		<option value="${inventory}">${inventory}</option>
        		</c:if>
        		</c:forEach>
		</select>
			
			
			</td>
			<td> <select id="invCodeTo" name="invCodeTo" >
        		<option value="${selectedInvCodeTo}" selected>${selectedInvCodeTo}</option>
   			 <c:forEach items="${receiverInventoryList}" var="inventory">
        		<c:if test="${inventory != selected}">
            		<option value="${inventory}">${inventory}</option>
        		</c:if>
        		</c:forEach>
		</select></td>		
  </tr>
  <tr>
   <td>Select Car Brand</td>
   <td><select id="brand" name="brand" onchange="brandFilter()" >
        <option value="${selectedBrand}" selected>${selectedBrand}</option>
   			 <c:forEach items="${brandList}" var="brand">
        		<c:if test="${brand != selected}">
            		<option value="${brand}">${brand}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			<td></td>
			</tr>
			 <tr>
   <td>Select Model</td>
   <td><select id="model" name="model" >
		</select></td>
			<td></td>
			</tr>
  <tr>
   <td>Category</td>
    <td> <select id="categoryName" name="categoryName" onchange="categoryFilter()" >
        <option value="${selectedCategory}" selected>${selectedCategory}</option>
   			 <c:forEach items="${categoryList}" var="category">
        		<c:if test="${category != selected}">
            		<option value="${category}">${category}</option>
        		</c:if>
        		
    		</c:forEach>

			</select></td>
			 <td></td>		
  </tr>
  <tr>
    <td>Sub Category</td>
    <td> <select id="subCategoryName" name="subCategoryName" >
			</select></td>
			<td> </td>
			</tr>
			  <tr>
    <td></td>
    <td>OR</td>		
      <td>OR</td>		
  </tr>
  <tr>
  
    <td>Item Code</td>
    <td><input type="text" name="itemCodeFrom" id="itemCodeFrom" onchange="fillLocationFieldsFromItemCode()" ></td>		
      <td><input type="text" name="itemCodeTo" id="itemCodeTo"></td>		
  </tr>
   <tr>
    <td></td>
    <td>OR</td>		
      <td>OR</td>		
  </tr>
    <tr>
    <td>SKU</td>
    <td><input type="text"  class="ui-autocomplete-input" name="sku" value="${transferObject.sku}" id="SKU" oninput="fillLocationFieldsFromSKU()"></td>		
 		  <td><a href="searchbysku" ><button class="btn btn-default" id="searchBySKU" type="button" >Search By SKU</button></a></td>
  </tr>
  <tr>
    <td></td>
    <td>Item Location</td>		
      <td></td>		
  </tr>
  
  <tr>
    <td>Rack</td>
    <td><input type="text" name="rackFrom" id="rackFrom" readonly="readonly"></td>
     <td></td>			
  </tr>
  <tr>
    <td>Shelf</td>
    <td><input type="text" name="shelfFrom" id="shelfFrom" readonly="readonly"></td>	
      <td></td>		
  </tr>
  <tr>
    <td>Column</td>
    <td><input type="text" name="columnFrom" id="columnFrom" readonly="readonly"></td>	
      <td></td>	
  </tr>
  <tr>
    <td>Compartment</td>
    <td><input type="text" name="compartmentFrom" id="compartmentFrom" readonly="readonly"></td>		
      <td></td>		
  </tr>
  <tr>
    <td>Number of Items to be transferred</td>
    <td><input type="text" name="transferAmount"  required="required" ></td>		
     <td><input type="button" name="printLabelButton" class="btn btn-default" value="Print Label" onclick="callPrint()"></td>		
  </tr>
   <tr>
   	 <td></td>
    <td><input type="submit" name="submitButton" class="btn btn-default" value="Initiate Transfer"></td>
    	 <td></td>
  </tr>
   
</table>
</form>
 <script type="text/javascript">
		var locationDetails=${locationMap};
		var categoryDetails=${categoryMap};
		var brandDetails=${brandMap};
		function fillLocationFieldsFromSKU()
		{
		var itemSku=document.getElementById('SKU').value;
		var itemInventoryName=document.getElementById('invCodeFrom').value;
		var key=itemInventoryName+itemSku;
		
		if (!(locationDetails[key].includes("undefined")))
		{
		
		document.getElementById('rackFrom').value=locationDetails[key].charAt(0);
		document.getElementById('shelfFrom').value=locationDetails[key].charAt(2);
		document.getElementById('columnFrom').value=locationDetails[key].charAt(4);
		document.getElementById('compartmentFrom').value=locationDetails[key].charAt(6);
		}
		};
			function callPrint()
		{
		var itemSku=document.getElementById('SKU').value;
		var itemCodeFrom=document.getElementById('itemCodeFrom').value;
		var itemCodeTo=document.getElementById('itemCodeTo').value;
		var invCodeFrom=document.getElementById('invCodeFrom').value;
		var invCodeTo=document.getElementById('invCodeTo').value;
		var brand=document.getElementById('brand').value;
		var categoryName=document.getElementById('categoryName').value;
		var subCategoryName=document.getElementById('subCategoryName').value;
		var model=document.getElementById('model').value;
		var transferAmount=document.getElementById('transferAmount').value;
		
		if  (itemSku=="")
		{
		itemSku=null
		}
		if  (itemCodeFrom=="")
		{
		itemCodeFrom=null
		}
		if  (itemCodeTo=="")
		{
		itemCodeTo=null
		}
		if  (invCodeFrom=="")
		{
		invCodeFrom=null
		}
		if  (invCodeTo=="")
		{
		invCodeTo=null
		}
		if  (brand=="")
		{
		brand=null
		}
		if  (categoryName=="")
		{
		categoryName=null
		}
		if  (subCategoryName=="")
		{
		subCategoryName=null
		}
		if  (model=="")
		{
		model=null
		}
		/* var data = {};
		data['sku'] = itemSku;
		data['itemCodeFrom'] = itemCodeFrom;
		data['itemCodeTo'] = itemCodeTo;
		data['invCodeFrom'] = invCodeFrom;
		data['invCodeTo'] = invCodeTo;
		data['brand'] = brand;
		data['model'] = model;
		data['categoryName'] = categoryName;
		data['subCategoryName'] = subCategoryName;
		 */
		 
		 var data1=itemSku+'&&&'+itemCodeFrom+'&&&'+itemCodeTo+'&&&'+invCodeFrom+'&&&'+invCodeTo+'&&&'+brand+'&&&'+model+'&&&'+categoryName+'&&&'+subCategoryName+'&&&'+transferAmount;
		 document.getElementById('data').value = data1;
		 var data2=document.getElementById('data').value;
		var obj = {};
		obj['data'] = data2;
		 $.ajax({
            type: "GET",
            url: 'printLabel',
            contentType: "application/json; charset=utf-8",
            data: obj,
            dataType: "text",
            success: function(result) {var a = document.createElement('a');
				var linkText = document.createTextNode("");
				a.appendChild(linkText);
				a.title = "Download pdf";
				a.href = "startDownload";
				document.body.appendChild(a);
				a.click(); }

            });
		
		};
		
		function categoryFilter()
		{
		var categoryName=document.getElementById("categoryName").value;
		var select = document.getElementById("subCategoryName");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=categoryDetails[categoryName];
		for (var i=0;i<options.length;i++)
		{
		var opt=options[i];
		var subCategoryName = opt.split(",");
		for (var j=0;j<subCategoryName.length;j++)
		{
			var e1=document.createElement('option');
			e1.textContent=subCategoryName[j];
			e1.value=subCategoryName[j];
			select.appendChild(e1);
		}
		}
		};
		 function brandFilter()
		{
		var categoryName=document.getElementById("brand").value;
		var select = document.getElementById("model");
		if (select.childElementCount !=0)
		{
		while (select.firstChild) {
 				   select.removeChild(select.firstChild);
			}
		}
		var options=brandDetails[categoryName];
		for (var i=0;i<options.length;i++)
		{
		var opt=options[i];
		var subCategoryName = opt.split(",");
		for (var j=0;j<subCategoryName.length;j++)
		{
			var e1=document.createElement('option');
			e1.textContent=subCategoryName[j];
			e1.value=subCategoryName[j];
			select.appendChild(e1);
		}
		}
		};
		function fillLocationFieldsFromItemCode()
		{
		var inventoryMap=${inventoryMapString};
		var invCodeFrom=document.getElementById('invCodeFrom').value;
		var itemCode=document.getElementById("itemCodeFrom").value;
		var inventoryCode=inventoryMap[invCodeFrom];
		var inventoryCodeLength=inventoryCode.length;
		document.getElementById('rackFrom').value=itemCode.charAt(inventoryCodeLength);
		document.getElementById('shelfFrom').value=itemCode.charAt(inventoryCodeLength+1);
		document.getElementById('columnFrom').value=itemCode.charAt(inventoryCodeLength+2);
		document.getElementById('compartmentFrom').value=itemCode.charAt(inventoryCodeLength+3);
		};
  </script>
  
</body>

</html>