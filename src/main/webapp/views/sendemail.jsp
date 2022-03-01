<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style type="text/css">
    	<%@include file="css/sendemail.css" %>
    </style>
  </head>
  <body>
  <c:url var="url" value="/UserServlet"></c:url>
    <div class="body_sendemail">
    	<div class="subscribe">
      <h2 class="subscribe__title">Share the video to your friend's email</h2>
      <p class="subscribe__copy">
       		http://localhost:8080/Assigment/UserServlet/details?VideoID=<span>${videoid }</span>
      </p>
      <form method="post">
      	<div class="form">
        <input
          type="email"
          class="form__email"
          placeholder="Enter your email address"
          name = "to"
        />
       <button class="form__button" formaction="${url }/share?VideoID=${videoid}">Send</button>
      </div>
      	</form>
      <!-- <div class="notice">
        <input type="checkbox" />
        <span class="notice__copy"
          >I agree to my email address being stored and uses to recieve monthly
          newsletter.</span
        >
      </div> -->
    </div>
    </div>
  </body>
</html>