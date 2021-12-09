<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style type="text/css">
	<%@include file="css/home.css" %>
</style>
</head>
<body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>
		<c:url var="url" value="/"></c:url>
    <div class="super_container">
      <!-- Header -->
      <header class="header">
        <!-- Top Bar -->
        <div class="top_bar">
          <div class="container">
            <div class="row">
              <div class="col d-flex flex-row">
                <div class="top_bar_contact_item">
                  <div class="top_bar_icon">
                    <img
                      src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918577/phone.png"
                      alt=""
                    />
                  </div>
                  +84 326 667 028
                </div>
                <div class="top_bar_contact_item">
                  <div class="top_bar_icon">
                    <img
                      src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918597/mail.png"
                      alt=""
                    />
                  </div>
                  <a href="mailto:fastsales@gmail.com"
                    >nguyenthin34hd@gmail.com</a
                  >
                </div>
                <div class="top_bar_content ml-auto">
                  <div class="top_bar_menu">
                    <ul class="standard_dropdown top_bar_dropdown">
                      <li>
                        <a href="#"
                          >English<i class="fas fa-chevron-down"></i
                        ></a>
                      </li>
                      <li>
                        <a href="#"
                          >Vietnamese<i class="fas fa-chevron-down"></i
                        ></a>
                      </li>
                    </ul>
                  </div>
                  <div class="top_bar_user">
                    <div class="user_icon">
                      <img
                        src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918647/user.svg"
                        alt=""
                      />
                    </div>
                     <c:choose>
                    	<c:when test="${!empty user }">
                    		<div><a href="${ url}UserServlet/profile">Profile</a></div>
                    		<div><a href="${ url}UserServlet/change">Change Password</a></div>
                    		<div><a href="${ url}logoff">Log Off</a></div>
                    	</c:when>
                    	<c:otherwise>
                    		<div><a href="${ url}register">Register</a></div>
                    		<div><a href="${ url}signin">Sign in</a></div>
                    	</c:otherwise>
                    </c:choose>	    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Header Main -->
        <div class="header_main">
          <div class="container">
            <div class="row">
              <!-- Logo -->
              <div class="col-lg-2 col-sm-3 col-3 order-1">
                <div class="logo_container">
                <c:choose>
                    	<c:when test="${!empty user }">
                    		 <div class="logo"><a href="${url }/list">Music Remix</a></div>
                    	</c:when>
                    	<c:otherwise>
                    		  <div class="logo"><a href="${url }">Music Remix</a></div>
                    	</c:otherwise>
                    </c:choose>	    
                
                </div>
              </div>
              <!-- Search -->
              <div
                class="
                  col-lg-6 col-12
                  order-lg-2 order-3
                  text-lg-left text-right
                "
              >
                <div class="header_search">
                  <div class="header_search_content">
                    <div class="header_search_form_container">
                      <form action="${url }findtitle" class="header_search_form clearfix" method="get">
                        <input
                          type="search"
                          required="required"
                          class="header_search_input"
                          placeholder="Search for products..."
                          name="title"
                        />
                        <button class="header_search_button trans_300" formaction="${url }/findtitle">
                          <img
                            src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918770/search.png"
                            alt=""
                          />
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Wishlist -->
              <div
                class="
                  col-lg-4 col-9
                  order-lg-3 order-2
                  text-lg-left text-right
                "
              >
                <div
                  class="
                    wishlist_cart
                    d-flex
                    flex-row
                    align-items-center
                    justify-content-end
                  "
                >
                  <div
                    class="
                      wishlist
                      d-flex
                      flex-row
                      align-items-center
                      justify-content-end
                    "
                  >
                    <div class="wishlist_icon">
                      <img
                        src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918681/heart.png"
                        alt="" />
                    </div>
                    <div class="wishlist_content">
                    		 <div class="wishlist_text"><a href="${url }UserServlet/myfavorite">Wishlist</a></div>
							<div class="wishlist_count">${quantity }</div>
                    </div>
                  </div>
                  <!-- Cart -->
                  <div class="cart">
                    <div
                      class="
                        cart_container
                        d-flex
                        flex-row
                        align-items-center
                        justify-content-end
                      "
                    >
                      <div class="cart_icon">
                        <img
                          src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560918704/cart.png"
                          alt=""
                        />
                        <div class="cart_count"><span>3</span></div>
                      </div>
                      <div class="cart_content">
                        <div class="cart_text"><a href="#">Cart</a></div>
                        <div class="cart_price">$185</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </header>
    <div>	
			<article >
				<jsp:include page="${views}">
					<jsp:param value="${video }" name="video"/>
					<jsp:param value="${list }" name="list"/>
					<jsp:param value="${message }" name="message"/>
					<jsp:param value="${topview }" name="topview"/>
					<jsp:param value="${toplike }" name="toplike"/>
					<jsp:param value="${topshare }" name="topshare"/>
				</jsp:include>
			</article>
			
		</div>
      <footer>
      	<jsp:include page="footer.jsp"></jsp:include>
      </footer>
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