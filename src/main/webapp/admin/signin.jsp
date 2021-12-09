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
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
      integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
      crossorigin="anonymous"
    />
    <style type="text/css">
    <%@include file="css/signin.css" %>
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/signin.css" />
  </head>
  <body>
  <c:url var="url" value="/"></c:url>
    <div class="body_x">
      <form autocomplete="off" class="form" action="${url }signinad" method="post">
        <div class="control">
          <h1>Sign In</h1>
        </div>
        <div class="control block-cube block-input">
          <input name="userid" placeholder="Username" type="text" required value="${adminnameid }"/>
          <div class="bg-top">
            <div class="bg-inner"></div>
          </div>
          <div class="bg-right">
            <div class="bg-inner"></div>
          </div>
          <div class="bg">
            <div class="bg-inner"></div>
          </div>
        </div>
        <div class="control block-cube block-input">
          <input name="passwords" placeholder="Password" type="password" required/>
          <div class="bg-top">
            <div class="bg-inner"></div>
          </div>
          <div class="bg-right">
            <div class="bg-inner"></div>
          </div>
          <div class="bg">
            <div class="bg-inner"></div>
          </div>
        </div>
          <h4>Remember</h4>
  <div class='control block-cube block-input'>
    <input name='remember'  type="checkbox" placeholder="Remember">
    <div class='bg-top'>
      <div class='bg-inner'></div>
    </div>
    <div class='bg-right'>
      <div class='bg-inner'></div>
    </div>
    <div class='bg'>
      <div class='bg-inner'></div>
    </div>
  </div>
        <button class="btn block-cube block-cube-hover" >
          <div class="bg-top">
            <div class="bg-inner"></div>
          </div>
          <div class="bg-right">
            <div class="bg-inner"></div>
          </div>
          <div class="bg">
            <div class="bg-inner"></div>
          </div>
          <div class="text" >Log In</div>
        </button>
      </form>
    </div>
  <c:if test="${message != null }">
  	 <jsp:include page="toasts.jsp">
   <jsp:param value="${message }" name="message"/>
   </jsp:include>
  </c:if>
    
  </body>
</html>
