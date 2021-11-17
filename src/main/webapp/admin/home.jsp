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
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/home.css" />
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.1.3/TweenMax.min.js"></script>
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
                    <div><a href="#">Register</a></div>
                    <div><a href="#">Sign in</a></div>
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
                  <div class="logo"><a href="#">BBB</a></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Main Navigation -->
        <nav class="main_nav">
          <div class="container">
            <div class="row">
              <div class="col">
                <div class="main_nav_content d-flex flex-row">
                  <!-- Categories Menu -->
                  <!-- Main Nav Menu -->
                  <div class="main_nav_menu">
                    <ul class="standard_dropdown main_nav_dropdown">
                      <li>
                        <a href="#">Home<i class="fas fa-chevron-down"></i></a>
                      </li>
                      <li class="hassubs">
                        <a href="#">Laptop<i class="bi bi-chevron-down"></i></a>
                        <ul>
                          <li>
                            <a href="${url }/editvideo"
                              >Video Edition<i class="fas fa-chevron-down"></i
                            ></a>
                          </li>
                          <li>
                            <a href="${url }/listvideo"
                              >Video List<i class="fas fa-chevron-down"></i
                            ></a>
                          </li>
                        </ul>
                      </li>
                      <li class="hassubs">
                        <a href="#">Users<i class="bi bi-chevron-down"></i></a>
                        <ul>
                          <li>
                            <a href="#"
                              >User Edition<i class="fas fa-chevron-down"></i
                            ></a>
                          </li>
                          <li>
                            <a href="#"
                              >User List<i class="fas fa-chevron-down"></i
                            ></a>
                          </li>
                        </ul>
                      </li>
                      <li>
                        <a href="blog.html"
                          >Blog<i class="fas fa-chevron-down"></i
                        ></a>
                      </li>
                      <li>
                        <a href="contact.html"
                          >Contact<i class="fas fa-chevron-down"></i
                        ></a>
                      </li>
                    </ul>
                  </div>
                  <!-- Menu Trigger -->
                  <div class="menu_trigger_container ml-auto">
                    <div
                      class="
                        menu_trigger
                        d-flex
                        flex-row
                        align-items-center
                        justify-content-end
                      "
                    >
                      <div class="menu_burger">
                        <div class="menu_trigger_text">menu</div>
                        <div class="cat_burger menu_burger_inner">
                          <span></span><span></span><span></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </nav>
        <!-- Menu -->
        <div class="page_menu">
          <div class="container">
            <div class="row">
              <div class="col">
                <div class="page_menu_content">
                  <div class="page_menu_search">
                    <form action="#">
                      <input
                        type="search"
                        required="required"
                        class="page_menu_search_input"
                        placeholder="Search for products..."
                      />
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>
     		 <article>
				<jsp:include page="${views }">
					<jsp:param value="${listvideo }" name="listvideo"/>
					<jsp:param value="${video }" name="listvideo"/>
				</jsp:include>
			</article>
			<footer>
				<jsp:include page="footer.jsp"></jsp:include>
			</footer>
    </div>
   
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
