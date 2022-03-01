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
  	 <%@include file="css/register.css" %>
  </style>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
  </head>
  <body>
  <c:url var="url" value="/"></c:url>
    <div class="container bd">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
          <div
            class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
          >
            <div class="card-img-left d-none d-md-flex">
              <!-- Background image for card set in CSS! -->
            </div>
            <div class="card-body p-4 p-sm-5">
              <h5 class="card-title text-center mb-5 fw-light fs-5">
                Register
              </h5>
              <form action="${url }register" method="post">
                <div class="form-floating mb-3">
                  <input
                    type="text"
                    class="form-control"
                    id="floatingInputUsername"
                    placeholder="myusername"
                    name="id"
                    required
                    autofocus
                    maxlength="20"
                    pattern="[a-zA-Z0-9]{6,20}"
              		title="Invalid character and from 6 to 10 characters"
                  />
                  <label for="floatingInputUsername">Username</label>
                </div>
                <div class="form-floating mb-3">
                  <input
                    type="text"
                    class="form-control"
                    id="floatingInputUsername"
                    placeholder="myfullname"
                    name="fullname"
                    required
                    autofocus
                    maxlength="50"
                  />
                  <label for="floatingInputUsername">Fullname</label>
                </div>
                <div class="form-floating mb-3">
                  <input
                    type="email"
                    class="form-control"
                    name="email"
                    id="floatingInputEmail"
                    placeholder="name@example.com"
                    required
                    autofocus
                    maxlength="50"
                  />
                  <label for="floatingInputEmail">Email address</label>
                </div>

                <hr />

                <div class="form-floating mb-3">
                  <input
                    type="password"
                    class="form-control"
                   	id="password"
                    placeholder="Password"
                    required
                    autofocus
                    maxlength="255"
                    pattern="[a-zA-Z0-9]{6,12}"
              		title="Invalid character and from 6 to 12 characters"
                  />
                  <label for="floatingPassword">Password</label>
                </div>

                <div class="form-floating mb-3">
                  <input
                    type="password"
                    class="form-control"
                    id="confirmPassword"
                    name="passwords"
                    placeholder="Confirm Password"
                    maxlength="255"
                      pattern="[a-zA-Z0-9]{6,12}"
              		title="Invalid character and from 6 to 12 characters"
                  />
                  <label for="floatingPasswordConfirm">Confirm Password</label>
                </div>

                <div class="d-grid mb-2">
                  <button class=" btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit" onclick="validatePassword()" >
                    Register
                  </button>
                </div>

                <a class="d-block text-center mt-2 small" href="${url }signin"
                  >Have an account? Sign In</a
                >

                <hr class="my-4" />

                <!-- <div class="d-grid mb-2">
                  <button
                    class="
                      btn btn-lg btn-google btn-login
                      fw-bold
                      text-uppercase
                    "
                    type="submit"
                  >
                    <i class="fab fa-google me-2"></i> Sign up with Google
                  </button>
                </div>

                <div class="d-grid">
                  <button
                    class="
                      btn btn-lg btn-facebook btn-login
                      fw-bold
                      text-uppercase
                    "
                    type="submit"
                  >
                    <i class="fab fa-facebook-f me-2"></i> Sign up with Facebook
                  </button>
                </div> -->
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/views/js/register.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
      integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
      integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
