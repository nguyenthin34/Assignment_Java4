<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
	<%@include file="css/pagination.css" %>
</style>
</head>
<body>
<c:url var="url" value="/UserServlet/move"></c:url>
		<div class="pagination_container">
      <ul class="pagi">
        <li><a href="#">First</a></li>
        <li><a href="${url }/prev">Prev</a></li>
        <li><a href="${url }/next">Next</a></li>	
        <li><a href="#">Last</a></li>
      </ul>
    </div>
</body>
</html>