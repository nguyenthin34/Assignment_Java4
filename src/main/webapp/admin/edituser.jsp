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
    <style type="text/css">
    <%@include file="css/edituser.css" %>
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/edituser.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <div class="form_wrapper">
      <div class="form_container">
        <div class="title_container">
          <h2>User Edition</h2>
        </div>
        <div class="row clearfix">
          <div class="">
            <form action="${url }/updateuser" method="post">
              <div class="row clearfix">
                <div class="col_half">
                  <div class="input_field">
                    <span><i class="far fa-id-card"></i></span>
                    <input
                      type="text"
                      name="id"
                      placeholder="UserID"
                      value="${user.id }"
                      required
                    />
                  </div>
                </div>
                <div class="col_half">
                  <div class="input_field">
                    <span><i aria-hidden="true" class="fa fa-user"></i></span>
                    <input
                      type="text"
                      name="fullname"
                      placeholder="full Name"
                      required
                      value="${user.fullname }"
                    />
                  </div>
                </div>
              </div>
              <div class="input_field">
                <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
                <input type="email" name="email" placeholder="Email" required value="${user.email }" />
              </div>
              <div class="input_field">
                <span><i aria-hidden="true" class="fa fa-lock"></i></span>
                <input
                  type="password"
                  name="passwords"
                  placeholder="Password"
                  required
                  value="${user.passwords }"
                />
              </div>
             
               <c:choose>
               		<c:when test="${empty user }">
               		 <div class="input_field radio_option">
               			 <input type="radio" name="admins" id="rd1" value="true"/>
               			 <label for="rd1">Admin</label>
                		 <input type="radio" name="admins" id="rd2" value="false"/>
                		 <label for="rd2">User</label>
                		  </div>
               		</c:when>
               		<c:otherwise>
               		 <div class="input_field radio_option">
               			 <input type="radio" name="admins" id="rd1" value="true"  ${user.admins ? 'checked' : '' }/>
               			 <label for="rd1">Admin</label>
                		 <input type="radio" name="admins" id="rd2" value="false" ${user.admins ? '' : 'checked' }/>
                		 <label for="rd2">User</label>
                		 </div>
               		</c:otherwise>
               </c:choose>
              <input class="button" type="submit" value="Update" formaction="${url }/updateuser"/>
            </form>
          </div>
        </div>
      </div>
    </div>
     <c:if test="${message != null }">
  	 <jsp:include page="toasts.jsp">
   <jsp:param value="${message }" name="message"/>
   </jsp:include>
  </c:if>
  </body>
</html>
