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
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
    />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.min.css"
      rel="stylesheet"
    />
   <style type="text/css">
   		<%@include file="css/infomation.css" %>
   </style>
  </head>
  <body>
  <c:url value="/UserServlet" var="url"></c:url>
    <div class="body_info">
      <div class="container">
        <div class="text-center mt-5">
          <h1>Infomation User</h1>
        
        </div>
        <div class="row">
          <div class="col-lg-7 mx-auto">
            <div class="card mt-2 mx-auto p-4 bg-light">
              <div class="card-body bg-light">
                <div class="container">
                  <form id="contact-form" role="form" action="${url }/updateprofile" method="post">
                    <div class="form-icon">
                      <span><i class="icon icon-user"></i></span>
                    </div>
                    <div class="controls">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="form_name">Username</label>
                            <input
                              id="form_name"
                              type="text"
                              name="name"
                              class="form-control"
								value="${profile.id }"
								disabled
                              data-error="Firstname is required."
                            />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="form_lastname">Fullname *</label>
                            <input
                              id="form_lastname"
                              type="text"
                              name="fullname"
                              class="form-control"
                              placeholder="Please enter your fullname *"
                              required="required"
                              data-error="Fullname is required."
                              maxlength="50"
                              value="${profile.fullname }"
                            />
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="form_email">Email</label>
                            <input
                              id="form_email"
                              type="email"
                              name="email"
                              class="form-control"
                              placeholder="Please enter your email *"
                              required="required"
                              data-error="Valid email is required."
                              value="${profile.email }"
                            />
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="form_need">Password</label>
                            <input
                              id="form_email"
                              type="password"
                              name="passwords"
                              class="form-control"
                              placeholder="Password"
                              disabled
                            />
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-12">
                          <a href="${url }/updateprofile"><input
                            type="submit"
                            class="btn btn-primary btn-send pt-2 btn-block"
                            value="Update Infomation"/></a>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <!-- /.8 -->
          </div>
          <!-- /.row-->
        </div>
      </div>
      <c:if test="${message != null }">
  	 <jsp:include page="/admin/toasts.jsp">
   	<jsp:param value="${message }" name="message"/>
  	 </jsp:include>
  	</c:if>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
    </div>
  </body>
</html>
