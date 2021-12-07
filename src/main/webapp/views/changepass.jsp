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
    	<%@include file="css/changepass.css" %>
    </style>

  </head>
  <body>
  <c:url var="url" value="/UserServlet"></c:url>
    <div class="mainDiv">
      <div class="cardStyle">
        <form action="${url }/change" method="post" name="signupForm" id="signupForm">
          <img src="" id="signupLogo" />

          <h2 class="formTitle">Login to your account</h2>
           <h4 class="formTitle" style="color: red;">${message }</h4>
          <div class="inputDiv">
            <label class="inputLabel" for="username">Username</label>
            <input type="text" id="username" name="usern" required maxlength="10"
                    pattern="[a-zA-Z0-9]{1,10}"
              		title="Invalid character and from 1 to 10 characters"/>
          </div>
          <div class="inputDiv">
            <label class="inputLabel" for="password">Current Password</label>
            <input
              type="password"
              id="currentpassword"
              name="currentpassword"
             required 
                    pattern="[a-zA-Z0-9]+"
              		title="Invalid characters"
            />
          </div>

          <div class="inputDiv">
            <label class="inputLabel" for="password">New Password</label>
            <input type="password" id="password" name="newpassword" required 
                    pattern="[a-zA-Z0-9]{6,12}"
              		title="Invalid character and from 6 to 12 characters" />
          </div>

          <div class="inputDiv">
            <label class="inputLabel" for="confirmPassword"
              >Confirm Password</label
            >
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
                    pattern="[a-zA-Z0-9]{6,12}"
              		title="Invalid character and from 6 to 12 characters"
            />
          </div>

          <div class="buttonWrapper">
            <button
              type="submit"
              id="submitButton"
               onclick="validatePassword()"
              class="submitButton pure-button pure-button-primary"
            >
              <span>Continue</span>
            </button>
          </div>
        </form>
      </div>
    </div>
    <c:if test="${message != null }">
  	 <jsp:include page="/admin/toasts.jsp">
   	<jsp:param value="${message }" name="message"/>
  	 </jsp:include>
  	</c:if>    
  	<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/change.js">
  	</script>
  </body>
</html>
