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
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <table class="table">
      <thead>
        <tr>
          <th>User ID</th>
          <th>Fullname</th>
          <th>Email</th>
          <th>Admin?</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${listuser }">
        <tr>
          <td data-label="Contact ID">${item.id }</td>
          <td data-label="Power">${item.fullname }</td>
          <td data-label="Expiration">${item.email }</td>
          <td data-label="Value">${item.admins }</td>
          <td data-label=""><a href="${url }/edituser?UserID=${item.id}" class="btn btn__active">Edit</a></td>
          <td data-label="">
          <button class="btn btn-primary"
     		 data-bs-toggle="modal"
     		 data-bs-target="#exampleModal">
     		 <c:set value="${item.id }" var="iddelete"></c:set>
         <!-- <a href="${url }/removeuser?UserID=${item.id}" class="btn btn__pledged">Delete</a> -->
         	Delete
          </button>        
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ask delete?</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">Are you sure you want to delete?</div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              No
            </button>
            <a class="btn btn-warning" href="${url }/removeuser?UserID=${iddelete}">
			Yes</a>
          </div>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
     <c:if test="${message != null }">
  	 <jsp:include page="toasts.jsp">
   <jsp:param value="${message }" name="message"/>
   </jsp:include>
   </c:if>
  </body>
</html>
