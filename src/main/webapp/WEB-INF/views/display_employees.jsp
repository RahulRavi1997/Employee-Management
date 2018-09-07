<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Employee Details</title>
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
  </head>
  <link rel="stylesheet" href="styles/employeedetails.css"/>
  <body>
    <c:if test="${not empty message}">
     <div id="snackbar">${message}</div>
    </c:if>
    <jsp:include page="header.jsp"/>
    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
    <c:if test="${empty project.id}">
      <div class="row full-width">
        <div class="col-sm-4 col-xs-4 text-align-center"> 
          <a href="create_employee">
          <button class="btn-margin-format add-button">
          Add New Employee
          </button>
          </a>
        </div>
        <div class="col-sm-4 col-xs-4"> 
          <input name="id" autocomplete="off" id="myInput" class="form-control mr-sm-2  " placeholder="Search" required/>
        </div>
        <div class="col-sm-4 col-xs-4 text-align-center">
          <h4>
            Filter :
            <select id="choice">
              <option value>All</option>
              <option value="Delete">Active</option>
              <option value="Restore">Inactive</option>
            </select>
          </h4>
        </div>
      </div>
    </c:if>
    <c:if test="${empty employeeList}">
      <h2 align="center">No Employees are available/present at the moment. Sorry!</h2>
    </c:if>
    <c:if test="${not empty employeeList}">
      <c:if test="${not empty project.id}">
        <input name="id" autocomplete="off" id="myInput" class="form-control mr-sm-2" placeholder="Search"/>
      </c:if>
      <div class="row full-width">
          <div class="col-xs-9 margin-left-30">
          <b>Employee Details</b>
        </div>
          <div class="col-xs-2">
          <b>Number Of Employees- [${numberOfEmployees}]</b>
        </div>
      </div>
      <div class="scrollable">
      <table cellpadding="10" border="1" class=" assign-table table sortable table-hover" >
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>DOB</th>
            <th>Salary</th>
            <th>Date of Joining</th>
            <th>Age</th>
            <th>Experience</th>
            <c:if test="${empty project.id}">
              <th>#</th>
              <th>#</th>
            </c:if>
            <c:if test="${not empty project.id}">
              <th>#</th>
            </c:if>
          </tr>
        </thead>
        <tbody id="myTable">
          <tr>
            <c:forEach var="employee" items="${employeeList}">
              <fmt:formatDate var="joiningyear" value="${employee.joiningDate}" pattern="yyyy" />
              <td>
                ${employee.id}
              </td>
              <td>
                <c:if test="${empty project.id}">
                  <form action="search_employee" method="get" id="search-form">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <button class="button-as-link" type="submit">${employee.name}</button>
                  </form>
                </c:if>
                <c:if test="${not empty project.id}">
                  ${employee.name}
                </c:if>
              </td>
              <td>
                ${employee.email}
              </td>
              <td>
                ${employee.role}
              </td>
              <td>${employee.birthDate}</td>
              <td>$${employee.salary}</td>
              <td>${employee.joiningDate}</td>
              <td>${employee.age} years</td>
              <td>${currentyear - joiningyear} years</td>
              <c:if test="${!employee.isActive()}">
                <c:if test="${empty project.id}">
                  <td colspan="2" class="type">
                    <form action ="restore_employee" method="get">
                      <input type="hidden" name="id" value="${employee.id}"/>
                      <button type="submit" class="btn-margin-format">Restore</button>
                    </form>
                  </td>
                </c:if>
              </c:if>
              <c:if test="${employee.isActive()}">
                <c:if test="${empty project.id}">
                  <form action = "modify_employee" method = "GET">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <td><input type="submit" class="btn btn-primary" value="Update"></td>
                  </form>
                  <form action ="delete_employee" method="get">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <td class="type"><button type="submit"  class="btn-danger" onclick="return confirm('Delete employee id : ${employee.id} ?')" >Delete </button></td>
                  </form>
                </c:if>
              </c:if>
              <form action="assign_employees" method="POST" id="assign-form">
                <c:if test="${not empty project.id}">
                  <input type="hidden" name="projectid" value="${project.id}">
                  <td><input type="checkbox" name="idOfEmployees" value="${employee.id}" form="assign-form"> </td>
                </c:if>
              </form>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      </div>
    </c:if>
    </td>
    </table>
      <c:if test="${not empty project.id}">
      <c:if test="${not empty employeeList}">
        <div class="row  inherit-width full-width">
          <div class="col-sm-10 col-xs-10"> 
          </div>
          <div  class="col-sm-2 col-xs-2">
            <input type="button" onclick="validate();" value="Add Selected Employees" class="btn btn-success" form="assign-form"/>
          </div>
        </div>
       </div>
      </c:if>
      </c:if>
    <jsp:include page="footer.jsp"/>
  </body>
  <script src="script/script.js"></script>
</html>
