<%@ include file="companyPanelHeader.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<table border="1">
<tbody>
<tr><th>CompanyId</th><th>Name</th><th>Date of Establishment</th><th>Date of Registration</th><th>Email</th><th>Address</th></tr>
<tr><td>${ccompany.id}</td><td>${ccompany.name }</td><td>${ccompany.doe}</td><td>${ccompany.dor}</td><td>${ccompany.email }</td><td>${ccompany.surveyNo}, ${ccompany.landmark }, ${ccompany.location }, ${ccompany.city }, ${ccompany.state }, ${ccompany.country }, ${ccompany.pincode }</td></tr>
</tbody>
</table>
<%@ include file="companyPanelFooter.jsp"%>
