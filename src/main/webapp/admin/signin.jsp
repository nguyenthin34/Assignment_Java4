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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/signin.css" />
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <div class="body_x">
      <form autocomplete="off" class="form" action="${url }/signinad" method="post">
        <div class="control">
          <h1>Sign In</h1>
           <h3>${message }</h3>
        </div>
        <div class="control block-cube block-input">
          <input name="userid" placeholder="Username" type="text" required/>
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
  </body>
</html>
