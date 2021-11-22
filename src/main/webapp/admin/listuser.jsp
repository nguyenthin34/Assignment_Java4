<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/listuser.css" />
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <table class="table">
      <thead>
        <tr>
          <th>User ID</th>
          <th>Fullname</th>
          <th>Email</th>
          <th>Admin?</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${listuser }">
        <tr>
          <td data-label="Contact ID">${item.id }</td>
          <td data-label="Power">${item.fullname }</td>
          <td data-label="Expiration">${item.email }</td>
          <td data-label="Value">${item.admins }</td>
          <td data-label=""><a href="${url }/edituser?UserID=${item.id}" class="btn btn__active">Edit</a></td>
          <td data-label=""><a href="${url }/removeuser?UserID=${item.id}" class="btn btn__pledged">Delete</a></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
     <c:if test="${message != null }">
  	 <jsp:include page="toasts.jsp">
   <jsp:param value="${message }" name="message"/>
   </jsp:include>
   </c:if>
  </body>
</html>
