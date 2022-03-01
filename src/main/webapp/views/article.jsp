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
 <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
</head>
<body>
<c:url var="url" value="/UserServlet"></c:url>
	<div>
	<div>
	</div>
      <section class="row">
        <!-- card -->
        <div class="col-9">
          <div class="row p-2">
            <!-- card 1 -->
            <c:forEach var="item" items="${video }">
            	<div class="col-4 mt-2">
             		<div class="card text-center">
                	<div class="card-body">
                  		<img src="${pageContext.request.contextPath}/images/${item.poster}" alt="No image" width="80%" class="fluid"/>
                 	 <div class="row border-top mt-2">
                   		 <a href="${pageContext.request.contextPath}/details?VideoID=${item.id}"><b>${item.title }</b>
                   		 </a>
                 	 </div>
                	</div>
                	<div class="card-footer">
                		<a href="${url }/likevd?VideoID=${item.id}&page=article" class="btn btn-success">Like</a>       			
                  		<a href="${url }/share?VideoID=${item.id}" class="btn btn-info">Share</a>
               		 </div>
              		</div>
            </div>
            </c:forEach>
     			
     			<c:choose>
     				<c:when test="${article }"></c:when>
     				<c:otherwise>
     					<jsp:include page="pagination.jsp">
             				<jsp:param value="${video }" name="video"/>
             				<jsp:param value="${user }" name="user"/>
             			</jsp:include>
     				</c:otherwise>
     			</c:choose>
             
		</div>   
        </div>
        <div class="col-3">
        		<jsp:include page="aside.jsp">
        			<jsp:param value="${topview }" name="topview"/>
        			<jsp:param value="${toplike }" name="toplike"/>
        			<jsp:param value="${topshare }" name="topshare"/>
        		</jsp:include>
        </div>
      </section>
    </div>
	<c:if test="${message != null }">
  	 <jsp:include page="/admin/toasts.jsp">
   	<jsp:param value="${message }" name="message"/>
  	 </jsp:include>
  	</c:if>
  	 
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