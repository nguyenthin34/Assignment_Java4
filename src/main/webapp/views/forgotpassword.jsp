<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="Yinka Enoch Adedokun">
    <title>Document</title>
        <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
    <style type="text/css">
    	<%@include file="css/forgotpassword.css"%>
    	</style>

  </head>

  <body>
  <c:url var="url" value="/"></c:url>
   
    <!-- Main Content -->
	<div class="container-fluid">
		<div class="row main-content bg-success text-center">
			<div class="col-md-4 text-center company__info">
				<span class="company__logo"><h2><span class="fa fa-android"></span></h2></span>
				<h4 class="company_title">Forgot PassWord</h4>
			</div>
			<div class="col-md-8 col-xs-12 col-sm-12 login_form ">
				<div class="container-fluid">
					<div class="row">
						
					</div>
					<div class="row">
						<form control="" class="form-group" action="${url }forgot" method="post">
							<div class="row">
								<input type="text" name="username" id="username" class="form__input" placeholder="Username"
								required pattern="[a-zA-Z0-9]+" title="Invalid character">
							</div>
							<div class="row">
								<!-- <span class="fa fa-lock"></span> -->
								<input type="email" name="email" id="password" class="form__input" placeholder="Email" required>
							</div>
			
							<div class="row">
								<button  class="btn">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${message != null }">
  	 <jsp:include page="/admin/toasts.jsp">
   	<jsp:param value="${message }" name="message"/>
  	 </jsp:include>
  	</c:if>
  </body>
</html>
