<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="styles/employeedetails.css">
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <c:if test="${not empty authorisationMessage}"><div id="snackbar">${authorisationMessage}</div></c:if>

<div class="w3-content">
  <div class="w3-container w3-content w3-center w3-padding-64" style="max-width:800px" id="band">
    <h2 class="ml6 w3-wide">
  <span class="text-wrapper">
    <span class="letters">We are Ideas2It!</span>
  </span>
</h2>
    <p class="w3-opacity"><i>Innovation Partner</i></p>
  </div>
</div>
    <jsp:include page="footer.jsp"/>
  </body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
  <script src="script/script.js"></script>
</html>
