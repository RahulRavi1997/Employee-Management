<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="styles/employeedetails.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Client Details</title>
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
  </head>
  <jsp:include page="header.jsp"/>
  <body>
    <c:if test="${not empty message}">
     <div id="snackbar">${message}</div>
    </c:if>
      <div class="row full-width">
        <div class="col-sm-4 col-xs-4 text-align-center"> 
          <a href="create_client">
          <button class="btn-margin-format add-button">
          Add New Client
          </button>
          </a>
        </div>
        <div class="col-sm-4 col-xs-4 search-bar-display"> 
           <form  autocomplete="off" class="form-inline" action="search_client" method="get">
              <input name="id"  type="number" class="form-control mr-sm-2" placeholder="Search" required>
              <button class="glyphicon glyphicon-search"  type="submit"/>    
            </form>
        </div>
        <div class="col-sm-4 col-xs-4 text-align-center">
           <a href="display_clients">
          <button class="btn-margin-format add-button">
             View All Clients
          </button>
          </a>
        </div>
      </div>
<div class="container">
  <ul class="pager">
     <input type="hidden" id="pager-id" value="${client.id}">
    <li id="previous" class="previous"><a id="previous-link" href="search_client?id=${client.id-1}">Previous</a></li>
     <li> <b>Employee Details</b></li>
    <li id="next" class="next"><a href="search_client?id=${client.id+1}">Next</a></li>
  </ul>
</div>
    <c:if  test="${empty client.id}">
      <h2 align="center">${failMessage}.</h2>
    </c:if>
    <c:if  test="${not empty client.id}">
      <div >
        <table border="1" cellpadding="5" class="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Company Name</th>
              <th>Email</th>
              <th>#</th>
              <c:if test="${client.isActive()}">
                <th>#</th>
                <th>#</th>
              </c:if>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <input type="hidden" name="id" value="${client.id}"/>
                <c:out value="${client.id}"/>
              </td>
              <td>
                <input type="hidden" name="name" value="${client.name}"/>
                <c:out value="${client.name}"/>
              </td>
              <td>
                <input type="hidden" name="companyName" value="${client.companyName}"/>
                <c:out value="${client.companyName}"/>
              </td>
              <td>
                <input type="hidden" name="Email" value="${client.email}"/>
                <c:out value="${client.email}"/>
              </td>
              <c:if test="${!client.isActive()}">
                <td colspan="3" class="type">
                  <form action="restore_client" method="POST">
                    <input type="hidden" name="id" value="${client.id}">
                    <button type="submit"  class="btn-margin-format" >Restore</button>
                  </form>
                </td>
              </c:if>
              <c:if test="${client.isActive()}">
                <td>
                  <form action ="obtain_projects" method="get">
                    <input type="hidden" name="id" value="${client.id}">
                    <input type="submit" class="btn btn-default" value="Assign">
                  </form>
                </td>
                <form action = "modify_client" method = "get">
                  <input type="hidden" name="id" value="${client.id}">
                  <td><input type="submit" class="btn-primary" value="Update"></td>
                </form>
                <form action ="delete_client" method="POST">
                  <input type="hidden" name="id" value="${client.id}">
                  <td><input type="submit"  class="btn-danger" value="Delete" onclick="return confirm('Delete client id : ${client.id} ?. All Project Information will be Lost.')" ></td>
                </form>
              </c:if>
            </tr>
        </table>
      </div>
    </c:if>
    <c:if test="${not empty client.addresses}">
      <table border="1" cellpadding="5" class="table">
        <tr>
          <th>Type</th>
          <th>Door-Number</th>
          <th>Street</th>
          <th>City</th>
          <th>Country</th>
          <th>Pin-Code</th>
        </tr>
        <c:forEach var="address" items="${client.addresses}">
          <tr>
            <td>
              <c:out value="${address.type}"/>
            </td>
            <td>
              <c:out value="${address.doorNumber}"/>
            </td>
            <td>
              <c:out value="${address.street}"/>
            </td>
            <td>
              <c:out value="${address.city}"/>
            </td>
            <td>
              <c:out value="${address.country}"/>
            </td>
            <td>
              <c:out value="${address.pinCode}"/>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <c:if test="${not empty client.name}">
        <div align="center">
          <h3>${client.name}'s projects</h3>
        </div>
    <c:if test="${empty client.projects}">
        <div align="center">
          <h4>No Projects have been assigned at the moment.</h4>
        </div>
    </c:if>
    </c:if>
    <c:if test="${not empty client.projects}">
      <div class="scrollable-client-search">
      <table border="1" cellpadding="5" class="table assign-table">
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>NumberOfResources</th>
          <th>#</th>
        </tr>
        <c:forEach var="project" items="${client.projects}">
          <tr>
            <td>${project.id}</td>
            <td>
              <form action="search_project" method="get"><button type="submit" class="button-as-link">${project.name}</button>
                <input type="hidden" name="id" value="${project.id}" /> 
              </form>
            </td>
            <td>${project.numberOfResources}</td>
            <td>
              <form action ="remove_project" method="POST" onclick="return confirm('Remove project (id:${project.id}) from client ?')">
                <input type="hidden" name="clientid" value="${client.id}">
                <input type="hidden" name="projectid" value="${project.id}">
                <input type="submit" value="Remove" class="btn-danger">
              </form>
            </td>
          </tr>
        </c:forEach>
      </table>
      </div>
    </c:if>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
