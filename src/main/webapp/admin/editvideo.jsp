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

    <div  class="container rounded bg-white mt-5 mb-5">
     <form action="${url }/updatevideo" method="post" enctype="multipart/form-data">
     	 <div class="row">
     	 <h1>
     	 </h1>
        <div class="col-md-5 border-right">
          <div class="d-flex flex-column align-items-center text-center p-3 py-5">
          <div class="card-body">
                <img id="poster" 
              		src="${pageContext.request.contextPath}/images/Neu_em_khong_hanh_phuc.png" alt="No Image" width="90%" class="fluid" />
                	</div>
       			 <div class="button-wrapper">
  				<span class="label">Upload File</span>
   				 <input type="file" name="photo_file" id="upload"
   				  class="upload-box" placeholder="Upload File" onchange="showPoster()" />
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
                  min="0"
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
              <button class="btn btn-primary profile-button" formaction="${url }/updatevideo">
                Update
              </button>
              <button class="btn btn-primary profile-button" formaction="${url }/resetvideo" >
                Reset
              </button>
            </div>
          </div>
        </div>
      </div>
     </form>
    </div>
    
      <c:if test="${message != null }">
  	 <jsp:include page="toasts.jsp">
   <jsp:param value="${message }" name="message"/>
   </jsp:include>
  </c:if>
  <script type="text/javascript">
  	function showPoster() {
  		var  fileSelected = document.getElementById('upload').files;
        console.log("lenght", fileSelected)
        if (fileSelected.length > 0){
            var fileToLoad = fileSelected[0];
            var fileReader = new FileReader();
            fileReader.onload = function (fileLoaderEvent) {
                var srcData = fileLoaderEvent.target.result;
                var newImg = document.getElementById('poster');
                newImg.src = srcData;
                newImg.style = "width: 300px; height: 280px;";
                document.getElementById('poster').innerHTML = newImg.outerHTML;
            }
            fileReader.readAsDataURL(fileToLoad);
        }
	
 	}
  </script>
  </body>
</html>
