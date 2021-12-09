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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
</head>
<body>
	<c:url var="url" value="/AdminServlet"></c:url>
	<table class="table table-primary">
		<tr class="table-primary">
			<th class="table-primary">ID</th>
			<th class="table-secondary">Title</th>
			<th class="table-success">Descriptions</th>
			<th class="table-danger">Views</th>
			<th class="table-warning">Poster</th>
			<th class="table-info">Status</th>
			<th class="table-light"></th>
			<th class="table-dark"></th>
		</tr>
		<c:forEach var="item" items="${listvideo }">
			<tr>
				<td class="table-primary">${item.id }</td>
				<td class="table-secondary">${item.title }</td>
				<td class="table-success">${item.descriptions }</td>
				<td class="table-danger">${item.views }</td>
				<td class="table-warning">${item.poster }</td>
				<td class="table-info">${item.actives ? 'Active' : 'Inactive'}</td>
				<td class="table-light"><a
					href="${url }/editvideo?VideoID=${item.id}">Edit</a></td>
				<td class="table-dark">
					<a href="${url }/removevideo?VideoID=${item.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<c:if test="${message != null }">
		<jsp:include page="toasts.jsp">
			<jsp:param value="${message }" name="message" />
		</jsp:include>
	</c:if>
</body>
</html>
