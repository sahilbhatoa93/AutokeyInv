<!DOCTYPE html>
<html>


<head>
 <link href="js/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery-1.9.1.js"></script>
   <script src="js/bootstrap.min.js"></script>

<title>Add Alerts</title>
</head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="ca" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="adminoptions.jsp" />
<ca:form action="addAlertRequest" method="get" modalAttribute="Alert">
<table  class="table table-striped" >
 
   <tr>
    <td> User Name<font color="red">*</font>:</td>
     <td><select name="userName" multiple>
			<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${loginIDList}" var="userName">
        		<c:if test="${userName != selected}">
            		<option value="${userName}">${userName}</option>
        		</c:if>
        		</c:forEach>
		
			</select></td>	
			<td></td>
  </tr>
  <tr>
    <td>Inventory List<font color="red">*</font>:</td>
   <td><select name="invName" multiple>
			<option value="${selectedInvCodeFrom}" selected>${selectedInvCodeFrom}</option>
   			 <c:forEach items="${allInvList}" var="invName">
        		<c:if test="${invName != selected}">
            		<option value="${invName}">${invName}</option>
        		</c:if>
        		</c:forEach>
        		
			</select></td>
			<td><p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p></td>
  </tr>
 
  <tr>
		<td>Alert Name<font color="red">*</font>:</td>
		<td><input name="alertName" type="text" required/></td>
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
      <p>Every <input name="alertMinutes" type="number" max="59" maxlength=2 /> minutes(s)</p>
      
    </div>
    <div id="hoursPage" class="tab-pane fade">
        <p>Every <input name="alertHours" type="number" max="23" maxlength=1 /> hours(s)</p>
       
    </div>
     <div id="dailyPage" class="tab-pane fade">
        <p>Every <input name="alertDay" type="number" max="31" maxlength=1 /> day(s)</p>
        <p>Start Time:<input type="time" class="form-control" name="scheduleTIme" 
         placeholder="Enter New Time"></p>
    </div>
     <div id="weeklyPage" class="tab-pane fade">
        <p><input type="checkbox" name="dayname" value="MON"> Monday</p>
        <p><input type="checkbox" name="dayname" value="TUE"> Tuesday</p>
        <p><input type="checkbox" name="dayname" value="WED"> Wednesday</p>
        <p><input type="checkbox" name="dayname" value="THU"> Thursday</p>
        <p><input type="checkbox" name="dayname" value="FRI"> Friday</p>
        <p><input type="checkbox" name="dayname" value="SAT"> Saturday</p>
        <p><input type="checkbox" name="dayname" value="SUN"> Sunday</p>
         <p>Start Time:<input type="time" class="form-control" name="scheduleTIme" 
         placeholder="Enter New Time"></p>
    </div>
     <div id="monthlyPage" class="tab-pane fade">
        <p>Day <input name="monthDate" type="number" max="31" maxlength=2 /> of every <input name="monthNumber" type="number" max="11" maxlength=2 /> months(s)</p>
        <p>Start Time:<input type="time" class="form-control" name="scheduleTIme" 
         placeholder="Enter New Time"></p>
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
        <p>Start Time:<input type="time" class="form-control" name="scheduleTIme" 
         placeholder="Enter New Time"></p>
    </div>
  </div>
</div>
  </td>
  <td></td>
  </tr>
     <tr>
   	 <td></td>
    <td><input type="submit" class="btn btn-default" value="Create Alert"></td>
    <td></td>
    	
  </tr>
  
</table>
</ca:form>
</body>

</html>