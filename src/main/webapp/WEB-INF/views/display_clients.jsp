<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Client Details</title>
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
  </head>
  <link rel="stylesheet" href="styles/employeedetails.css">
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
      <div class="col-sm-4 col-xs-4"> 
        <input name="id" autocomplete="off" id="myInput" class="form-control mr-sm-2  " placeholder="Search"/>
      </div>
      <div class="col-sm-4 col-xs-4 text-align-center">
        <h5>
          Filter :
          <select id="choice">
            <option value>All</option>
            <option value="Delete">Active</option>
            <option value="Restore">Inactive</option>
          </select>
        </h5>
      </div>
    </div>
    <c:if test="${empty clientList}">
      <h2 align="center">No Clients are available/present At the moment. Sorry!</h2>
    </c:if>
    <c:if test="${not empty clientList}">
      <div class="row full-width">
          <div class="col-xs-9 margin-left-30">
          <b>Client Details</b>
        </div>
          <div class="col-xs-2">
          <b>Number Of Clients- [${numberOfClients}]</b>
        </div>
      </div>
      <div class="scrollable">
      <table border="1" cellpadding="5" class="table sortable assign-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Company Name</th>
            <th>Email</th>
            <th>#</th>
            <th>#</th>
            <th>#</th>
          </tr>
        </thead>
        <tbody id="myTable">
          <c:forEach var="client" items="${clientList}">
            <tr>
              <td>${client.id}</td>
              <td>
                <form action="search_client" method="GET">
                  <input type="hidden" name="id" value="${client.id}">
                  <button class="button-as-link">
                  ${client.name}
                  </button>
                </form>
              </td>
              <td>${client.companyName}</td>
              <td>${client.email}</td>
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
                    <input type="submit" class="btn btn-default" value="Assign Projects">
                  </form>
                </td>
                <form action = "modify_client" method = "GET">
                  <input type="hidden" name="id" value="${client.id}">
                  <td><input type="submit" class="btn btn-primary" value="Update"></td>
                </form>
                <form action ="delete_client" method="POST">
                  <input type="hidden" name="id" value="${client.id}">
                  <td class="type"><button  class="btn-danger"  onclick="return confirm('Delete client id : ${client.id} ?. All Project Information will be Lost.')" >Delete</button></td>
                </form>
              </c:if>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      </div>
    </c:if>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
