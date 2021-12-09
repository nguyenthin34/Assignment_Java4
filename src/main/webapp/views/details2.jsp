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
    	<%@include file="css/detials.css"%>
    </style>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
  </head>
  <body>
	<c:url var="url" value="/UserServlet"></c:url>
    <div class="row">
    	<div class="body_details col-9">
      <div class="wrapper">
        <div class="product-img">
          <iframe
            src="https://www.youtube.com/embed/${nameytb.id }"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
            height="420"
            width="327"
          ></iframe>
          <!-- <img src="http://bit.ly/2tMBBTd" height="420" width="327" /> -->
        </div>
        <div class="product-info">
          <div class="product-text">
            <h1>${nameytb.title }</h1>
            <h2>${nameytb.descriptions }</h2>
          </div>
          <div class="row">
          	<div  class="product-price-btn">
          	<a href="${url }/unlike?VideoID=${nameytb.id }&page=details"><button type="button" class="col-6">
          	UnLike</button></a>
          	<a href="${url }/share?VideoID=${nameytb.id }"><button type="button" class="col-6">Share</button></a>
         	 </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-3" id="scroll_box">
    	 <jsp:include page="listVDWT.jsp">
    	<jsp:param value="${list }" name="list"/>		
    </jsp:include>
    </div>
    </div>
   
  </body>
</html>
