<%@ include file="companyPanelHeader.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <table border = 1>
 <tbody>
 <tr><th>CustomerId</th><th>amount</th><th>month</th><th>year</th></tr>
   <c:forEach  items="${couponreqlist}" var="couponreqlist">
          <tr>
                 <td>${couponreqlist.customer.id}</td>
       <td>${couponreqlist.amount}</td>
        <td>${couponreqlist.month}</td>
       <td>${couponreqlist.year}</td>
           </tr>
           
 
          </c:forEach>
         
          </tbody>
   </table>
<%@ include file="companyPanelFooter.jsp"%>
