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
    <title>Employee Details</title>
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
          <a href="create_employee">
          <button class="btn-margin-format add-button">
          Add New Employee
          </button>
          </a>
        </div>
        <div class="col-sm-4 col-xs-4 search-bar-display">
           <form  autocomplete="off" class="form-inline" action="search_employee" method="get">
              <input name="id"  type="number" class="form-control mr-sm-2" placeholder="Search"  required>
              <button class="glyphicon glyphicon-search"  type="submit"/>    
            </form>
        </div>
        <div class="col-sm-4 col-xs-4 text-align-center">
           <a href="display_employees">
          <button class="btn-margin-format add-button">
             View All Employees
          </button>
          </a>
        </div>
      </div>

    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
<div class="container">
  <ul class="pager">
     <input type="hidden" id="pager-id" value="${employee.id}">
    <li id="previous" class="previous"><a id="previous-link" href="search_employee?id=${employee.id-1}">Previous</a></li>
     <li> <b>Employee Details</b></li>
    <li id="next" class="next"><a href="search_employee?id=${employee.id+1}">Next</a></li>
  </ul>
</div>
    <c:if  test="${empty employee.id}">
      <h2 align="center">${failMessage}.</h2>
    </c:if>
    <c:if  test="${not empty employee.id}">
      <div align="center">
        <table border="1" cellpadding="5" class="table ">
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
            <c:if test="${employee.isActive()}">
              <th>#</th>
            </c:if>
          </tr>
          <fmt:formatDate var="joiningyear" value="${employee.joiningDate}" pattern="yyyy" />
          <tr>
            <td>
              <input type="hidden" name="id" value="${employee.id}"/>
              <c:out value="${employee.id}"/>
            </td>
            <td>
              <input type="hidden" name="name" value="${employee.name}"/>
              <c:out value="${employee.name}"/>
            </td>
            <td>
              <input type="hidden" name="email" value="${employee.email}"/>
              <c:out value="${employee.email}"/>
            </td>
            <td>
              <input type="hidden" name="role" value="${employee.role}"/>
              <c:out value="${employee.role}"/>
            </td>
            <td><input type="hidden" name="birthDate" value="${employee.birthDate}"/>${employee.birthDate}</td>
            <td><input type="hidden" name="salary" value="${employee.salary}"/>${employee.salary}</td>
            <td><input type="hidden" name="joiningDate" value="${employee.joiningDate}"/>${employee.joiningDate}</td>
            <td>${employee.age}</td>
            <td>${currentyear - joiningyear} years</td>
            <c:if test="${!employee.isActive()}">
              <td colspan="2" class="type">
                <form action ="restore_employee" method="POST">
                  <input type="hidden" name="id" value="${employee.id}"/>
                  <button type="submit" class="btn-margin-format">Restore</button>
                </form>
              </td>
            </c:if>
            <c:if test="${employee.isActive()}">
              <form action = "modify_employee" method = "GET">
                <input type="hidden" name="id" value="${employee.id}"/>
                <td><input type="submit" class="btn btn-primary" value="Update"></td>
              </form>
              <form action ="delete_employee" method="POST">
                <input type="hidden" name="id" value="${employee.id}"/>
                <td class="type"><button type="submit"  class="btn-danger" onclick="return confirm('Delete employee id : ${employee.id} ?')" >Delete </button></td>
              </form>
            </c:if>
          </tr>
        </table>
      </div>
    </c:if>
    <c:if test="${not empty employee.addresses}">
      <table border="1" cellpadding="5" class="table">
        <tr>
          <th>Type</th>
          <th>Door-Number</th>
          <th>Street</th>
          <th>City</th>
          <th>Country</th>
          <th>Pin-Code</th>
        </tr>
        <c:forEach var="address" items="${employee.addresses}">
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
    <c:if test="${not empty employee.workingProjects}">
      <div align="center"><h3>${employee.name}'s Projects</h3></div>
       <div class="scrollable-project-search">
      <table border="1" cellpadding="5" class="table">
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Resources</th>
        </tr>
        <c:forEach var="project" items="${employee.workingProjects}">
          <tr>
            <td>
              <c:out value="${project.id}"/>
            </td>
            <td>
              <form action="search_project" method="get"><button type="submit" class="button-as-link">${project.name}</button>
                <input type="hidden" name="id" value="${project.id}" /> 
              </form>
            </td>
            <td>
              <c:out value="${project.numberOfResources}"/>
            </td>
          </tr>
        </c:forEach>
      </table></div>
    </c:if>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
