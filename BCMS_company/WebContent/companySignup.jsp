<%@ include file="header.jsp"%>
<link href="./resources/signupform.css" rel="stylesheet" type="text/css" />
<center>
<h2>
<%
if (request.getAttribute("signUpStatus:") == null) {
	out.println();
}
else {
	out.println(request.getAttribute("signUpStatus:"));
}
%>
</h2>
</center>

<div class="simple-form">
<form id="CompanySignUp"action="./BCMSController" method="post">
<br>
<style>
h1{text-align:center;color:black}
</style>
<h1>Customer SignUp </h1><br><br>
			<input type = "text" name = "id" id = "button" placeholder = "Enter CompanyId" required><br><br>
			<input type="text" name="name" id="button" placeholder="Enter Name" required><br><br>
			<input type="text" name="doe" id="button" placeholder="Enter Establishment" required><br><br>
			<input type="text" name="dor" id="button" placeholder="Enter Date Of Registration" required><br><br>
			<input type="password" name="password" id="button" placeholder="Enter password" required><br><br>
			<input type="email" name="email_id" id="button" placeholder="Enter Email" required><br><br>
			<input type="text" name="survey_no" id="phone" placeholder="Enter SurveyNo" required><br><br>
			<input type="text" name="landmark" id="button" placeholder="Enter Landmark" required><br><br>
			<input type="text" name="location" id="button" placeholder="Enter Location" required><br><br>
			<input type="text" name="city" id="button" placeholder="Enter city" required><br><br>
			<input type="text" name="state" id="button" placeholder="Enter State" required><br><br>
			<input type="text" name="country" id="button" placeholder="Enter country" required><br><br>
			<input type="text" name="pincode" id="button" placeholder="Enter pincode" required><br><br>
			<input type="submit" value="Register" id="butt">	
			<input type = "hidden" name = "action" value = "CompanySignUp"/>	

</form>
</div>
<%@ include file="footer.jsp"%>
