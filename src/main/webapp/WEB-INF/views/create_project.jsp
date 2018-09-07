<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Employee Registration Form</title>
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
  </head>
  <link rel="stylesheet" href="styles/employeedetails.css">
  <jsp:include page="header.jsp"/>
  <body>
    <c:if test="${not empty message}">
     <script>alert("${message}");</script>
    </c:if>
    <jsp:useBean id="now" class="java.util.Date" />
    <c:set var="operation" value="update_project"/>
    <c:set var="value" value="Edit"/>
    <c:if test="${empty project.name}">
      <c:set var="operation" value="add_project"  />
      <c:set var="value" value="Add"/>
    </c:if>
    <form:form commandName="project" action="${operation}" onsubmit="setResources();" method="post">
      <form:hidden path="active" value="${project.active}"/>
      <form:hidden path="id" value="${project.id}"/>
      <div class="container bg-input-container">
        <legend align="center"  class="bg-blue"><b>${value} Project Details</b></legend>
        <div class="row">
          <div class="col-sm-12 col-xs-12">
            <div class="row">
              <div class="col-sm-6 col-xs-6 required pad-left-project-label">
                <label for="name">Name :</label>
              </div>
              <div class="col-sm-6 col-xs-6">
                <form:input type="text" path="name" placeholder="Name" aria-describedby="basic-addon1" required="required" maxLength="50"/>
                <br/>
              </div>
            </div>
         <form:hidden placeholder="Number Of Resources" id="resources" path="numberOfResources" aria-describedby="basic-addon1" min="0" maxLength="10"/>
        </div>
      </br>
   <div align="center">
  <button type="button" class="btn-primary" data-toggle="modal" data-target="#myModal">Assign Employees.</button>
   </div>
    </br>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg modal-lg-employee">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Assign Employees</h4>
        </div>
        <div class="modal-body">
        <div class="search-bar-padding"> <input autocomplete="off" id="myInput" class="form-control mr-sm-2  " placeholder="Search"/></div>
          <c:if test="${not empty assignedEmployees}">
            <caption>
              <h4>Assigned Employees - </h4>
            </caption>
            <div class="update-scrollable">
            <table cellpadding="10" border="1" class="assign-table table table-hover"  id="myTable1">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Role</th>
                  <th>DOB</th>
                  <th>Salary</th>
                  <th>Date of Joining</th>
                  <th>#</th>
                </tr>
              </thead>
               <tbody> 
                <tr>
                  <c:forEach var="employee" items="${assignedEmployees}">
                    <fmt:formatDate var="birthyear" value="${employee.birthDate}" pattern="yyyy" />
                    <fmt:formatDate var="joiningyear" value="${employee.joiningDate}" pattern="yyyy" />
                    <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
                    <td>
                      ${employee.id}
                    </td>
                    <td>
                      ${employee.name}
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
                    <td><input type="checkbox" name="idOfEmployees" value="${employee.id}" checked> </td>
                </tr>
                </c:forEach>
            </table>
            </div>
          </c:if>
    <c:if test="${not empty project.name}">
          <c:if test="${empty assignedEmployees}">
            <h4>No Associated employees in this project</h4>
          </c:if>
    </c:if>
          <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
          <c:if test="${empty employeeList}">
            <c:if test="${empty project.name}">
              <h2 align="center">No Employees are available/present At the moment. Sorry!</h2>
            </c:if>
          </c:if>
          <c:if test="${not empty employeeList}">
            <caption>
              <h4>Add Employees - </h4>
            </caption>
            <div class="update-scrollable">
            <table cellpadding="10" border="1" class="assign-table table table-hover" id="myTable">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Role</th>
                  <th>DOB</th>
                  <th>Salary</th>
                  <th>Date of Joining</th>
                  <th>#</th>
                </tr>
              </thead>
               <tbody>
                <tr>
                  <c:forEach var="employee" items="${employeeList}">
                    <fmt:formatDate var="birthyear" value="${employee.birthDate}" pattern="yyyy" />
                    <fmt:formatDate var="joiningyear" value="${employee.joiningDate}" pattern="yyyy" />
                    <fmt:formatDate var="currentyear" value="${now}" pattern="yyyy" />
                    <td>
                      ${employee.id}
                    </td>
                    <td>
                      ${employee.name}
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
                    <td><input type="checkbox" name="idOfEmployees" value="${employee.id}"> </td>
                </tr>
                </c:forEach>
            </table>
            </div>
          </c:if>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default btn-primary" data-dismiss="modal">Confirm Changes</button>
        </div>
      </div>
    </div>
  </div>
          <div class="row" align="center">
            <input class="btn margin-format" type="reset" value="Reset"/>     
            <input class="btn-primary" value="Submit" type="submit"/>   
          </div>
        </div>
      </div>
    </form:form>
     </br>
     </br>
     </br>
     </br>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
