<!DOCTYPE html>




<html>
<head>
 <script src="js/jquery-1.9.1.js"></script>
   <script src="js/jquery-ui.js"></script>
 

<script type="text/javascript">
  
	 $( function() {
	
  	$( "#SKU" ).autocomplete({
      source: ${skuList}
    });
   } );
  </script>
<title>Search</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<body>
<jsp:include page="employeeoptions.jsp" />
<form  action="skuSearchResultTable" modelAttribute="Item">
<br><br><br><br><br><br><br><br>
  
 <div align="center">

  <label for="sku">Select SKU</label>
  <br>
			<input name="sku" id="SKU" type="text">
			<br>  
         
   </div>
   <br>
	<p align="center">
   <button type="submit" class="btn btn-default">Submit</button>
   </p>
   </form>
    
</body>
</html>