<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:u="urn:jsptagdir:/WEB-INF/tags/utils"
      xmlns:com="urn:jsptagdir:/WEB-INF/tags/common">
<style>
  body {
    height: 100%;
    width: 100%;
    background-color: #99ddf0;
  }
  .container {
    width: 100%;
    height: 70%;
    position: static;
    background-color: #99ddf0;
  }
  #footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    background-color :#99ddf0;
    font-size: 15px;
    font-family: "Arial";
    color: #4D555C;
    text-align: left;
    vertical-align: middle;
  }
</style>
  <head>
    <title>ПОДБОР ПРОФИЛЕЙ И КОМПЛЕКТАЦИИ ДЛЯ СИСТЕМЫ ИНТЕРЬЕРНЫХ ПЕРЕГОРОДОК ALT111</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  </head>
<body style="background-color: #99ddf0">
<div class="header" style="width: 100%; color: #4D555C">
  <jsp:directive.include file="header.jsp"/>
</div>

  <div class="table-responsive">
    <table class="table">
      <thead>
      <br>
      <tr>
        <th>Код</th>
        <th>Артикул</th>
        <th>Наименование</th>
        <th>Цвет</th>
        <th>Ед.изм.</th>
        <th>Количество</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach var="prop" items="${props}" >
      <tr>
        <td>${prop.id}</td>
        <td>${prop.art}</td>
        <td>${prop.um}</td>
        <td>${prop.name}</td>
        <td>${prop.color}</td>
        <td>${prop.value}</td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

</div>
<div id="footer" style="width: 100%">
  <jsp:directive.include file="footer.jsp"/>
</div>
</body>
</html>
