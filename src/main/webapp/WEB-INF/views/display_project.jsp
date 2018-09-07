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
    <title>Project Details</title>
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
          <a href="create_project">
          <button class="btn-margin-format add-button">
          Add New Project
          </button>
          </a>
        </div>
        <div class="col-sm-4 col-xs-4 search-bar-display"> 
           <form  autocomplete="off" class="form-inline" action="search_project" method="get">
              <input name="id"  type="number" class="form-control mr-sm-2" placeholder="Search" required>
              <button class="glyphicon glyphicon-search"  type="submit"/>    
            </form>
        </div>
        <div class="col-sm-4 col-xs-4 text-align-center">
           <a href="display_projects">
          <button class="btn-margin-format add-button">
             View All Projects
          </button>
          </a>
        </div>
      </div>
      <jsp:useBean id="now" class="java.util.Date" />
      <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
<div class="container">
  <ul class="pager">
     <input type="hidden" id="pager-id" value="${project.id}">
    <li id="previous" class="previous"><a id="previous-link" href="search_project?id=${project.id-1}">Previous</a></li>
     <li> <b>Project Details</b></li>
    <li id="next" class="next"><a href="search_project?id=${project.id+1}">Next</a></li>
  </ul>
</div>
    <c:if  test="${empty project.id}">
      <h2 align="center">${failMessage}.</h2>
    </c:if>
    <c:if  test="${not empty project.id}">
      <table border="1" cellpadding="5" class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Resources</th>
            <th>#</th>
            <c:if test="${project.isActive()}">
              <th>#</th>
              <th>#</th>
            </c:if>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input type="hidden" name="id" value="${project.id}"/>
              <c:out value="${project.id}"/>
            </td>
            <td>
              <input type="hidden" name="name" value="${project.name}"/>
              <c:out value="${project.name}"/>
            </td>
            <td>
              <input type="hidden" name="numberofresources" value="${project.numberOfResources}"/>
              <c:out value="${project.numberOfResources}"/>
            </td>
            <c:if test="${!project.isActive()}">
              <td class="type" colspan="3">
                <form action ="restore_project" method="get">
                  <input type="hidden" name="id" value="${project.id}" />
                  <button type="submit"  class="btn-margin-format"> Restore</button>
                </form>
              </td>
            </c:if>
            <c:if test="${project.isActive()}">
              <td>
                <form action ="obtain_employees" method="get">
                  <input type="hidden" name="id" value="${project.id}" />
                  <input type="submit" class="btn btn-default" value="Assign">
                </form>
              </td>
              <form action = "modify_project" method = "GET">
                <input type="hidden" name="id" value="${project.id}" />
                <td><input type="submit" class="btn btn-primary" value="Update"></td>
              </form>
              <form action ="delete_project" method="get">
                <input type="hidden" name="id" value="${project.id}" />
                <td class="type"><button type="submit"  class="btn-danger" onclick="return confirm('Delete project id : ${project.id} ?. All Employee Information will be Lost.')" > Delete</button></td>
              </form>
            </c:if>
          </tr>
      </table>
    </c:if>
    <c:if test="${not empty project.id}">
          <div align="center"><h3>${project.name}'s employees</h3></div>
    <c:if test="${empty project.projectMembers}">
        <div align="center">
          <h4>No Employees have been assigned at the moment.</h4>
        </div>
    </c:if>

    <c:if test="${not empty project.projectMembers}">
      <div class="scrollable-employee-search">
      <table border="1" cellpadding="5" class="table assign-table">
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
          <th>Role</th>
          <th>BirthDate</th>
          <th>Salary</th>
          <th>JoiningDate</th>
          <th>Age</th>
          <th>Experience</th>
          <th>#</th>
        </tr>
        <c:forEach var="employee" items="${project.projectMembers}">
          <fmt:formatDate var="birthyear" value="${employee.birthDate}" pattern="yyyy" />
          <fmt:formatDate var="joiningyear" value="${employee.joiningDate}" pattern="yyyy" />
          <tr>
            <td>${employee.id}</td>
            <td>
              <form action="search_employee" method="get" id="search-form">
                <input type="hidden" name="id" value="${employee.id}"/>
                <button class="button-as-link" type="submit">${employee.name}</button>
              </form>
            </td>
            <td>${employee.email}</td>
            <td>${employee.role}</td>
            <td>${employee.birthDate}</td>
            <td>${employee.salary}</td>
            <td>${employee.joiningDate}</td>
            <td>${currentyear - birthyear}</td>
            <td>${currentyear - joiningyear} years</td>
            <td>
              <form action ="remove_employee" method="get" onclick="return confirm('Remove employee(id:${employee.id}) from project ?')">
                <input type="hidden" name="projectid" value="${project.id}" />
                <input type="hidden" name="employeeid" value="${employee.id}" />
                <button type="submit" class="btn-danger" value="Remove">Remove</button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </table>
      </div>
    </c:if>
    </c:if>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
