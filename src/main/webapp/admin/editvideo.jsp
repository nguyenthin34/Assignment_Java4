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
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/editvideo.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/inputvideo.css" />
  </head>
  <body>
  <c:url var="url" value="/AdminServlet"></c:url>
    <div class="container rounded bg-white mt-5 mb-5">
     <form action="${url }" method="post" enctype="multipart/form-data">
     	 <div class="row">
        <div class="col-md-5 border-right">
          <div class="d-flex flex-column align-items-center text-center p-3 py-5">
            <img
              class="rounded-circle mt-5"
              width="150px"
              src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"/>
       			 <div class="button-wrapper">
  				<span class="label">Upload File</span>
   				 <input type="file" name="photo_file" id="upload"
   				  class="upload-box" placeholder="Upload File"  />
					</div>
            <span class="font-weight-bold">${message }</span>
            <span class="text-black-50">edogaru@mail.com.my</span><span> </span>
          </div>
        </div>
        <div class="col-md-7 border-right">
          <div class="p-3 py-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
              <h4 class="text-right">Profile Settings</h4>
            </div>
            <div class="row mt-2">
              <div class="col-md-6">
                <label class="labels">Youtube ID</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="Youtube ID"
                  name="id"
                  required
                  maxlength="20"
                  value="${video.id }"
                />
              </div>
              <div class="col-md-6">
                <label class="labels">Video Title</label
                ><input
                  type="text"
                  class="form-control"
                  name="title"
                  placeholder="Video Title"
                  required
                  maxlength="50"
                   value="${video.title }"
                />
              </div>
            </div>
            <div class="row mt-3">
              <div class="col-md-12">
                <label class="labels">View Count</label
                ><input
                  type="number"
                  class="form-control"
                  placeholder="enter view number"
                  name = "views"
                  required
                   value="${video.views }"
                />
              </div>
              <div class="row mt-2">
                <c:choose>
                	<c:when test="${empty video }">
                	<div class="col-md-6">
                  		<div class="form-check">
                    	<label class="labels">Active</label>
                    	<input type="radio" class="form-check-input" value="true" name="actives" checked/>
                  		</div>
                	</div>
                	<div class="col-md-6">
                  		<div class="form-check">
                    	<label class="labels">Inactive</label>
                    	<input type="radio" class="form-check-input" value="false" name="actives"/>
                   		</div>
                   </div>
                </c:when>
                <c:otherwise>
                	<div class="col-md-6">
                  		<div class="form-check">
                    	<label class="labels">Active</label>
                    	<input type="radio" class="form-check-input" value="true" name="actives" ${video.actives ? 'checked' : '' }/>
                  		</div>
                	</div>
                	<div class="col-md-6">
                  		<div class="form-check">
                    	<label class="labels">Inactive</label>
                    	<input type="radio" class="form-check-input" value="false" name="actives" ${video.actives ? '' : 'checked' }/>
                   		</div>
                   </div>
                </c:otherwise>
                </c:choose>
              </div>
              <div class="col-md-12">
                <label class="labels">Desc</label>
                <textarea class="form-control" name="descriptions" required maxlength="1000" >
                	${video.descriptions }
                </textarea>
              </div>
            </div>
            <div class="mt-5 text-center">
              <button class="btn btn-primary profile-button" onclick="addreq()" formaction="${url }/insertvideo">
                Save
              </button>
              <button class="btn btn-primary profile-button" onclick="removereq()" formaction="${url }/updatevideo">
                Update
              </button>
              <button class="btn btn-primary profile-button" type="button">
                Delete
              </button>
              <button class="btn btn-primary profile-button" type="button">
                Reset
              </button>
            </div>
          </div>
        </div>
      </div>
     </form>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/editvideo.js"></script>
  </body>
</html>
