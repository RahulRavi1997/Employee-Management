<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head></head>
  <link rel="stylesheet" href="styles/employeedetails.css">
  <jsp:include page="header.jsp"/>
  <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Expires" CONTENT="-1">
  <head></head>
  <body>
    <div align="center">
      <h1>
        -An Error has Occured-${errorMessage}.
        <c:if test="${empty errorMessage}">
          No such URL!
        </c:if>
      </h1>
    </div>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
