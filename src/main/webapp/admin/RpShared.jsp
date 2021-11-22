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
 <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css"
    />
    <link  rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/toasts.css">
</head>
<body <c:if test="${empty listreport3 }">onload="launch_toast()"</c:if> >
<c:url var="url" value="/AdminServlet"></c:url>
	<div class="container">
		<form action="${url }" method="get">
			<div class="input-group mb-3">
			<input type="text" class="form-control"
				placeholder="Recipient's username" aria-label="Recipient's username"
				aria-describedby="button-addon2" name="keyword" required />
			<button class="btn btn-outline-secondary" formaction="${url }/findshared"
				id="button-addon2" >Button</button>
		</div>
		</form>
		<h2>${title }</h2>
		<div class="container table-responsive py-5">
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Sender Name</th>
						<th scope="col">Sender Email</th>
						<th scope="col">Receiver Email</th>
						<th scope="col">Sent Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listreport3 }">
					
						<tr>
						<th scope="row">${item.senderName }</th>
						<td>${item.senderMail }</td>
						<td>${item.receiverMail }</td>
						<td>${item.sentDate }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		
			<div id="toast">
      		<div id="img"><i class="bi bi-app-indicator"></i></div>
      		<div id="desc">There are no records!</div>
    		</div>
     <script src="${pageContext.request.contextPath}/admin/js/toasts.js"></script>
</body>
</html>