<%@ include file="header.jsp"%>
	<link href="./resources/loginform.css" rel="stylesheet" type="text/css">
	<div class="title"></div><center><h1> Customer Login</h1></center></div>
	<div class="container">
	<div class="left"></div>
	<div class="right">
		<div class="formBox">
			<form id="CompanyLogin" action="./BCMSController" method="post">
			<p> Id</p>
			<input type="text" name="companyId" placeholder="Enter Id" required>
			<p> Password</p>
			<input type="password" name="password" placeholder="Enter Password" required>
			<input type="submit" name="Login" value="Submit">
			<input type = "hidden" name = "action" value = "CompanyLogin"/>
			</form>
			<td><h5>new User?</h5></td>
			<a href="./BCMSController?action=Signup">Sign Up</a>
			
		</div>
	
	</div>
	</div>
<%@ include file="footer.jsp"%>
