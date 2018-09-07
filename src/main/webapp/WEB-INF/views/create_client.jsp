<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Client Registration Form</title>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="operation" value="update_client"/>
    <c:set var="value" value="Edit"/>
    <c:if test="${empty client.name}">
      <c:set var="operation" value="add_client"  />
      <c:set var="value" value="Add"/>
    </c:if>
    <form:form commandName="client" action="${operation}" method="post" value="${value}" onsubmit="submit_form()">
      <form:hidden path="id" value="${client.id}"/>
      <form:hidden path="active" value="${client.active}"/>
      <div class="container bg-input-container">
      <legend align="center" class="bg-blue"><b>${value} Client Details</b></legend>
      <div class="row">
        <div class="col-sm-6 col-xs-6 create-col-padding">
          <div class="row">
            <div class="col-sm-3 col-xs-3 required">
              <label for="name">Name</label>
            </div>
            <div class="col-sm-3 col-xs-3">
              <form:input type="text" path="name" placeholder="Name" aria-describedby="basic-addon1" maxlength="50" required="required"/>
              <br/>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-3 col-xs-3">
              <label for="companyName">Company Name</label>
            </div>
            <div class="col-sm-3 col-xs-3">
              <form:input type="text" path="companyName" placeholder="Company Name"  aria-describedby="basic-addon1" maxlength="50"/>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-3 col-xs-3 required">
              <label for="email">Email-Id</label>
            </div>
            <div class="col-sm-3 col-xs-3">
              <form:input type="email" path="email" placeholder="Email-Id" aria-describedby="basic-addon1" maxlength="50" required="required"/>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xs-6">
          <c:forEach items="${client.addresses}" varStatus="vs">
              <c:if test="${vs.index+1 eq 1}"> <c:set var="addrstype" value="Permanent"/></c:if>
              <c:if test="${vs.index+1 eq 2}"> <c:set var="addrstype" value="Temporary"/></c:if>
              <div class="row">
                <div class="col-sm-6 col-xs-6"> 
                  <b>${addrstype} Address</b>
                </div>
              </div>
               <form:hidden path="addresses[${vs.index}].type" value="${addrstype}"/>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <form:label class="color-black" path="addresses[${vs.index}].doorNumber" cssErrorClass="invalid">Door Number</form:label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <div class="input">
                  <form:input type="number" min="0" placeholder="Door Number" path="addresses[${vs.index}].doorNumber" cssErrorClass="invalid " required="required" maxLength="10"/>
                  <form:label path="addresses[${vs.index}].doorNumber" cssErrorClass="icon invalid" />
                  <form:errors path="addresses[${vs.index}].doorNumber" cssClass="inline_invalid" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <form:label class="color-black" path="addresses[${vs.index}].street" cssErrorClass="invalid">Street</form:label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <div class="input">
                  <form:input placeholder="Street" path="addresses[${vs.index}].street" cssErrorClass="invalid " required="required" maxLength="50"/>
                  <form:label path="addresses[${vs.index}].street" cssErrorClass="icon invalid" />
                  <form:errors path="addresses[${vs.index}].street" cssClass="inline_invalid" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <form:label class="color-black" path="addresses[${vs.index}].city" cssErrorClass="invalid">City</form:label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <div class="input">
                  <form:input placeholder="City" path="addresses[${vs.index}].city" cssErrorClass="invalid " required="required" maxLength="50"/>
                  <form:label path="addresses[${vs.index}].city" cssErrorClass="icon invalid" />
                  <form:errors path="addresses[${vs.index}].city" cssClass="inline_invalid" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <form:label class="color-black" path="addresses[${vs.index}].country" cssErrorClass="invalid">Country</form:label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <div class="input">
                  <form:input placeholder="Country" path="addresses[${vs.index}].country" cssErrorClass="invalid " required="required" maxLength="50"/>
                  <form:label path="addresses[${vs.index}].country" cssErrorClass="icon invalid" />
                  <form:errors path="addresses[${vs.index}].country" cssClass="inline_invalid" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <form:label class="color-black" path="addresses[${vs.index}].pinCode" cssErrorClass="invalid">PinCode</form:label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <div class="input">
                  <form:input placeholder="Pin Code" type="number" min="0" path="addresses[${vs.index}].pinCode" cssErrorClass="invalid " required="required" maxLength="10"/>
                  <form:label path="addresses[${vs.index}].pinCode" cssErrorClass="icon invalid" />
                  <form:errors path="addresses[${vs.index}].pinCode" cssClass="inline_invalid" />
                </div>
              </div>
            </div>
          </c:forEach>
        </div></div>
</br>
   <div align="center">
  <button type="button" class="btn-primary" data-toggle="modal" data-target="#myModal">Assign Projects.</button>
   </div>
</br>

  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg modal-lg-project">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Projects</h4>
        </div>
        <div class="modal-body">


        <c:if test="${empty assignedProjects}">
          <c:if test="${not empty client.name}">
            <h4 align="center"> No Projects are assigned at the moment.</h4>
          </c:if>
        </c:if>
        <div class="search-bar-padding"> <input name="id" autocomplete="off" id="myInput" class="form-control mr-sm-2  " placeholder="Search"/></div>
        <c:if test="${not empty assignedProjects}">
            <div align="center">
              <h2>Assigned Projects</h2>
            </div>
            <div class="update-scrollable">
          <table cellpadding="10" border="1" class="table sortable assign-table table-hover modal-lg-project">
            <thead>
              <tr width="100%">
                <th>ID</th>
                <th>Name</th>
                <th>Resources</th>
                <th>#</th>
              </tr>
            </thead>
            <tbody   id="myTable1">
              <tr>
                <c:forEach var="project" items="${assignedProjects}">
                  <td>${project.id}</td>
                  <td>
                    ${project.name}
                  </td>
                  <td>${project.numberOfResources}</td>
                  <td><input type="checkbox" name="idOfProjects" value="${project.id}" checked></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
         </div>
        </c:if>
        <c:if test="${empty projectList}">
          <c:if test="${empty client.name}">
            <h2 align="center"> No Projects are available at the moment. Sorry!</h2>
          </c:if>
        </c:if>
        <c:if test="${not empty projectList}">
            <div align="center">
              <h2>Add Projects</h2>
            </div>
            <div class="update-scrollable">
          <table cellpadding="10" border="1" class="table sortable assign-table table-hover modal-lg-project">
            <thead>
              <tr width="100%">
                <th>ID</th>
                <th>Name</th>
                <th>Resources</th>
                <th>#</th>
              </tr>
            </thead>
            <tbody   id="myTable">
              <tr>
                <c:forEach var="project" items="${projectList}">
                  <td>${project.id}</td>
                  <td>
                    ${project.name}
                  </td>
                  <td>${project.numberOfResources}</td>
                  <td><input type="checkbox" name="idOfProjects" value="${project.id}"></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
         </div>
        </c:if>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">Confirm Changes</button>
        </div>
      </div>
    </div>
  </div>
        <div class="row" align="center">
          <input class="btn margin-format" type="reset" value="Reset"/>     
          <input class="btn-primary" type="submit" value="Submit"/>
        </div>
      </div>
      </div>
    </form:form>
    </br>
    </br>
    </br>
    </br>
    <jsp:include page="footer.jsp"/>
  </body>
  <script src="script/script.js"></script>
</html>
