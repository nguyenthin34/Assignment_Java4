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
	<%@include file="css/input.css" %>
</style>
</head>
<body>
	<label for="inp" class="inp">
  <input type="number" id="inp" pattern=".{6,}" placeholder="index" name="indexpage" required value="${indexpage == null ? 0 : indexpage}"/>
</label>
</form>
</body>
</html>