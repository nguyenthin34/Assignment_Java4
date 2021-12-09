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
   <%@include file="css/homepage.css"%>
   </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/homepage.css" />
  </head>
  <body>
    <div class="body_c">
      <audio
        id="audio"
        src="https://s3-whjr-curriculum-uploads.whjr.online/7c08fc45-867c-4159-9e97-5dd7cbcfd24d.mp3"
      ></audio>

      <audio
        id="audio"
        src="https://s3-whjr-curriculum-uploads.whjr.online/10598a54-94e9-452e-8e21-2e6046de05bf.mp3"
      ></audio>
      <audio3
        id="audio"
        src="https://s3-whjr-curriculum-uploads.whjr.online/e853934b-0de7-41e5-9dc4-979de110d53c.mp3"
      ></audio3>
      <audio4
        id="audio4"
        src="https://s3-whjr-curriculum-uploads.whjr.online/b017a40c-c95a-42c3-a2f1-fac7e0b956de.mp3"
      ></audio4>
      <audio5
        id="audio"
        src="https://s3-whjr-curriculum-uploads.whjr.online/1f3fe682-c55f-4f83-883e-2d6cf0a82ea5.mp3"
      ></audio5>

      <center>
        <h1 id="h01">Musical demo</h1>
        <h4>click on image to play</h4>
        <br />
        <span>cradle ringtone</span>
        <span>iphone ringtone</span>
        <span>pikachu ringtone</span>
        <script src=""></script>
        <div>
          <img
            id="im1"
            onclick="function1()"
            src="https://s3-whjr-curriculum-uploads.whjr.online/68c0781c-abed-4e1b-b982-6b3bb91e8815.gif"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;
          <img
            id="im2"
            onclick="function2()"
            src="https://s3-whjr-curriculum-uploads.whjr.online/515b1c46-b2e4-4023-b398-4a6b728f630d.gif"
          />&nbsp;&nbsp;
          <img
            id="im3"
            onclick="function3()"
            src="https://s3-whjr-curriculum-uploads.whjr.online/1e6b4d10-0006-4cf2-98ce-b3c61b628d8e.gif"
          />
          <br /><br />
        </div>
        <span>shape of you ringtone</span>
        <span>Taki-Taki ringtone</span>
        <div>
          <img
            id="im4"
            onclick="function4()"
            src="https://s3-whjr-curriculum-uploads.whjr.online/6833680d-0eef-42f8-9f57-54d9930c7527.gif"
          />
          <img
            id="im5"
            onclick="function5()"
            src="https://s3-whjr-curriculum-uploads.whjr.online/c8aa5483-665f-4b94-9951-e6ac271d807e.gif"
          />
        </div>
        <h4>Note: Refersh page to play other track</h4>
      </center>
    </div>
    <script src="test1.js"></script>
  </body>
</html>
