<%@ include file="companyPanelHeader.jsp"%>
<form id="fileStatus" action="./BCMSController" method="post" enctype="multipart/form-data">
		<table style="width: 50%">
			<h4>Upload File here : </h4>
				<tr>
					<td><input type="file" accept=".xls" name="upload_file" /></td>
				</tr>
		</table>
                <input type = "submit" name = "upload file" value="Submit"/>
				<input type = "hidden" name = "action" value="fileStatus"/>
</form>

<%
if (request.getAttribute("msg") == null) {
	out.println();
}
else {
	out.println(request.getAttribute("msg"));
}
%>

<%@ include file="companyPanelFooter.jsp"%>
