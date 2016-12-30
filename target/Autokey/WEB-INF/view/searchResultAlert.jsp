<!DOCTYPE html>
<html>


<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>

<title>Update Alerts</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form action="updateAlertRequest" method="get" commandName="result" modalAttribute="result">
<table  class="table table-striped" >

   <tr>
    <td>Selected Users:</td>
     <td>${result.userName}</td>	
			<td></td>
  </tr>
   <tr>
    <td>Selected Inventory:</td>
     <td>${result.invName}</td>	
			<td></td>
  </tr>
 
   <tr>
    <td> User Name<font color="red">*</font>:</td>
     <td><ca:select path="userName" name="userName" multiple="true">
			<c:forEach items="${userList}" var="userList">
   				 <c:forEach items="${selectedUserList}" var="selectedUser">
   			 		<c:choose>
        			 <c:when test="${userList == selectedUser}">
       					<option selected="selected" value="${selectedUser}">${selectedUser}</option>
    				</c:when>  
            	</c:choose>
        	</c:forEach>
        	</c:forEach>
			 <c:forEach items="${remainingUserList}" var="invName">
			 			<option value="${invName}">${invName}</option>
   			</c:forEach>
			</ca:select></td>	
			<td></td>
  </tr>
  <tr>
    <td>Inventory List<font color="red">*</font>:</td>
   <td><select name="invName" multiple>
				
   			 <c:forEach items="${invList}" var="invName">
   				 <c:forEach items="${selectedInvList}" var="selectedInvName">
   			 		<c:choose>
        			 <c:when test="${invName == selectedInvName}">
       					<option selected="selected" value="${selectedInvName}">${selectedInvName}</option>
    				</c:when>  
            	</c:choose>
        	</c:forEach>
        	</c:forEach>
			 <c:forEach items="${remainingInvList}" var="invName">
			 			<option value="${invName}">${invName}</option>
   			</c:forEach>
			
			
			</select></td>
			<td><p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p></td>
  </tr>
 
  <tr>
		<td>Alert Name<font color="red">*</font>:</td>
		<td><ca:input path="alertName" value="${result.alertName}" readonly="true" /></td>
		<td></td>
  </tr>
   
  <tr>
  <td>Repeat Duration:</td>
  <td><div class="container">
  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#minutesPage">Minutes</a></li>
    <li><a data-toggle="tab" href="#hoursPage">Hourly</a></li>
    <li><a data-toggle="tab" href="#dailyPage">Daily</a></li>
    <li><a data-toggle="tab" href="#weeklyPage">Weekly</a></li>
    <li><a data-toggle="tab" href="#monthlyPage">Monthly</a></li>
    <li><a data-toggle="tab" href="#yearlyPage">Yearly</a></li>
  </ul>

  <div class="tab-content">
    <div id="minutesPage" class="tab-pane fade in active">
      <p>Every <ca:input path="alertMinutes" value="${result.alertMinutes}"  type="number" max="59" maxlength="2" /> minutes(s)</p>
      
    </div>
    <div id="hoursPage" class="tab-pane fade">
        <p>Every <ca:input path="alertHours" value="${result.alertHours}" type="number" max="23" maxlength="1" /> hours(s)</p>
       
    </div>
     <div id="dailyPage" class="tab-pane fade">
        <p>Every <ca:input path="alertDay" value="${result.alertDay}" type="number" max="31" maxlength="1" /> day(s)</p>
        <p>Start Time:<ca:input path="scheduleTIme" value="${result.scheduleTIme}" type="time" class="form-control" 
         placeholder="Enter New Time"/></p>
    </div>
     <div id="weeklyPage" class="tab-pane fade">
        <p><input type="checkbox" name="dayname" value="MON"> Monday</p>
        <p><input type="checkbox" name="dayname" value="TUE"> Tuesday</p>
        <p><input type="checkbox" name="dayname" value="WED"> Wednesday</p>
        <p><input type="checkbox" name="dayname" value="THU"> Thursday</p>
        <p><input type="checkbox" name="dayname" value="FRI"> Friday</p>
        <p><input type="checkbox" name="dayname" value="SAT"> Saturday</p>
        <p><input type="checkbox" name="dayname" value="SUN"> Sunday</p>
         <p>Start Time:<ca:input path="scheduleTIme" value="${result.scheduleTIme}" type="time" class="form-control" 
         placeholder="Enter New Time"/></p>
    </div>
     <div id="monthlyPage" class="tab-pane fade">
        <p>Day <ca:input path="monthDate" value="${result.monthDate}" type="number" max="31" maxlength="2" /> of every <ca:input path="monthNumber" value="${result.monthNumber}" type="number" max="11" maxlength="2" /> months(s)</p>
        <p>Start Time:<ca:input path="scheduleTIme" value="${result.scheduleTIme}" type="time" class="form-control" 
         placeholder="Enter New Time"/></p>
    </div>
    <div id="yearlyPage" class="tab-pane fade">
        <p>Every <select name="monthName" >
        <option value="1">January</option>
        <option value="2">February</option>
        <option value="3">March</option>
        <option value="4">April</option>
        <option value="5">May</option>
        <option value="6">June</option>
        <option value="7">July</option>
        <option value="8">August</option>
        <option value="9">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
        </select> <input name="monthDate" type="number" max="11" maxlength=2 /></p>
        <p>Start Time:<ca:input path="scheduleTIme" value="${result.scheduleTIme}" type="time" class="form-control" 
         placeholder="Enter New Time"/></p>
    </div>
  </div>
</div>
  </td>
  <td></td>
  </tr>
     <tr>
   	 <td></td>
    <td><input type="submit" class="btn btn-default" value="Update Alert"></td>
    <td></td>
    	
  </tr>
  
</table>
</ca:form>
</body>

</html>