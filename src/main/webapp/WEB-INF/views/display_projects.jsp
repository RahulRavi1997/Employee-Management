<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="styles/images/ideas1.jpg"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <title>Project Details</title>
  </head>
  <link rel="stylesheet" href="styles/employeedetails.css">
  <jsp:include page="header.jsp"/>
  <body>
    <c:if test="${not empty message}">
     <div id="snackbar">${message}</div>
    </c:if>
    <c:if test="${empty clientid}">
      <div class="row full-width">
      <div class="col-sm-4 col-xs-4 text-align-center"> 
        <a href="create_project">
        <button class="btn-margin-format add-button">
        Add New Project
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
    </c:if>
    <c:if test="${empty projectList}">
      <h2 align="center"> No Projects are available/present at the moment. Sorry!</h2>
    </c:if>
    <c:if test="${not empty projectList}">
      <c:if test="${not empty clientid}">
          <div class="col-sm-10 col-xs-10 left-padding"> <input name="id" autocomplete="off" id="myInput" class="form-control mr-sm-2" placeholder="Search"/>
        </div>
      </c:if>
      <div class="row full-width">
          <div class="col-xs-9 margin-left-30">
          <b>Project Details</b>
        </div>
          <div class="col-xs-2">
          <b>Number Of Projects- [${numberOfProjects}]</b>
        </div>
      </div>
      <div class="scrollable">
      <table cellpadding="10" border="1" class="table sortable table-hover assign-table"  id="myTable">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Resources</th>
            <c:if test="${empty clientid}">
              <th>#</th>
              <th>#</th>
              <th>#</th>
            </c:if>
            <c:if test="${not empty clientid}">
              <th>#</th>
            </c:if>
          </tr>
        </thead>
        <tbody>
          <tr>
            <c:forEach var="project" items="${projectList}">
              <td>${project.id}</td>
              <td>
                <c:if test="${empty clientid}">
                  <form action="search_project" method="get"><button type="submit" class="button-as-link">${project.name}</button>
                    <input type="hidden" name="id" value="${project.id}" /> 
                  </form>
                </c:if>
                <c:if test="${not empty clientid}">
                  ${project.name}
                </c:if>
              </td>
              <td>${project.numberOfResources}</td>
                <c:if test="${!project.isActive()}">
                <c:if test="${empty clientid}">
                  <td class="type" colspan="3">
                    <form action ="restore_project" method="get">
                      <input type="hidden" name="id" value="${project.id}" />
                      <button type="submit"  class="btn-margin-format">Restore</button>
                    </form>
                  </td>
                </c:if>
              </c:if>
              <c:if test="${project.isActive()}">
                <c:if test="${empty clientid}">
                  <td>
                    <form action ="obtain_employees" method="get">
                      <input type="hidden" name="id" value="${project.id}" />
                      <input type="submit" class="btn btn-default" value="Assign Employees">
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
              </c:if>
              <form action="assign_projects" method="POST" id="assign-form">
                <input type="hidden" name="clientid" value="${clientid}"/>
                <c:if test="${not empty clientid}">
                  <td><input type="checkbox" name="idOfProjects" value="${project.id}" form="assign-form"> </td>
                </c:if>
              </form>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      </div>
    </c:if>
      <c:if test="${not empty projectList}">
      <c:if test="${not empty clientid}">
        <div class="row  inherit-width full-width">
          <div class="col-sm-10 col-xs-10"> 
          </div>
          <div  class="col-sm-2 col-xs-2">
            <input type="button" onclick="validate();" value="Add Selected Projects" class="btn btn-success" form="assign-form"/>
          </div>
        </div>
       </div>
      </c:if>
      </c:if>
  </body>
  <jsp:include page="footer.jsp"/>
  <script src="script/script.js"></script>
</html>
