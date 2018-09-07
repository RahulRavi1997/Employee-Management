<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="value" value="Add"/>
    <c:set var="operation" value="add_employee"/>
    <c:if test="${not empty employee.name}">
      <c:set var="value" value="Edit"/>
      <c:set var="operation" value="update_employee"/>
    </c:if>
    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />
    <fmt:formatDate var="currentYear" value="${now}" pattern="yyyy" />
    <fmt:formatDate var="currentMonth" value="${now}" pattern="MM" />
    <fmt:formatDate var="currentDay" value="${now}" pattern="dd" />
    <fmt:parseDate value="${currentYear-18}-${currentMonth}-${currentDay}" pattern="yyyy-MM-dd" var="maxdob" type="date" />
    <fmt:formatDate value="${maxdob}" var="maxBirthDate" type="date" pattern="yyyy-MM-dd" />
    <fmt:parseDate value="${currentYear-100}-${currentMonth}-${currentDay}" pattern="yyyy-MM-dd" var="mindob" type="date" />
    <fmt:formatDate value="${mindob}" var="minBirthDate" type="date" pattern="yyyy-MM-dd" />
    <form:form commandName="employee" action="${operation}" method="post" onsubmit="submit_form(this.form)">
      <form:hidden path="id" value="${employee.id}"/>
      <form:hidden path="active" value="${employee.active}"/>
      <div class="container bg-input-container">
        <legend align="center"  class="bg-blue"><b>${value} Employee Details</b></legend>
        <div class="row">
          <c:if test="${empty employee.name}" var="noName"/>
          <c:if test="${empty employee.email}" var="noEmail"/>
          <div class="col-sm-6 col-xs-6 create-col-padding">
            <div class="row">
              <div class="col-sm-3 col-xs-3 required">
                <label for="name">Name</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="text" path="name" placeholder="Name" aria-describedby="basic-addon1"  required="required"/>
                <br/>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3 required">
                <label for="birthDate">Birth Date</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="date" class="date-width" id="birthDate" path="birthDate" placeholder="Birth Date" aria-describedby="basic-addon1" min="${minBirthDate}" max="${maxBirthDate}" required="required"/>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <label for="Age">Age</label>
              </div>
              <div class="col-sm-3 col-xs-3">
               <input type="text" name="age" id="age" readonly>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-3 col-xs-3 required">
                <label for="email">Email-Id</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="email" path="email" placeholder="Email-Id" aria-describedby="basic-addon1" maxLength="20" required="required"/>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <label for="role">Role</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="text" path="role" placeholder="Role" aria-describedby="basic-addon1" maxLength="50"/>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <label for="salary">Salary</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="number" path="salary" placeholder="Salary" aria-describedby="basic-addon1" maxLength="10" min="0"/>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3 col-xs-3">
                <label for="joiningDate">Joining Date</label>
              </div>
              <div class="col-sm-3 col-xs-3">
                <form:input type="date" class="date-width" path="joiningDate" id="datefield" min="${minBirthDate}" max="${currentDate}" placeholder="Joining Date" aria-describedby="basic-addon1"/>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xs-6">
            <c:forEach items="${employee.addresses}" varStatus="vs">
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
                    <form:input type="number"  min="0" placeholder="Door Number" path="addresses[${vs.index}].doorNumber" cssErrorClass="invalid " required="required" maxLength="10" />
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
                    <form:input placeholder="Street" path="addresses[${vs.index}].street" cssErrorClass="invalid "  maxLength="50"/>
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
                    <form:input placeholder="City" path="addresses[${vs.index}].city" cssErrorClass="invalid "  maxLength="50"/>
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
                    <form:input placeholder="Country" path="addresses[${vs.index}].country" cssErrorClass="invalid " maxLength="50" required="required" />
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
                    <form:input placeholder="Pin Code" type="number" min="0" path="addresses[${vs.index}].pinCode" cssErrorClass="invalid" required="required" maxLength="10"/>
                    <form:label path="addresses[${vs.index}].pinCode" cssErrorClass="icon invalid" />
                    <form:errors path="addresses[${vs.index}].pinCode" cssClass="inline_invalid" />
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="row" align="center">
          <input class="btn margin-format" type="reset" value="Reset"/>     
          <input class="btn-primary" type="submit" value="Submit"/>     
        </div>
      </div>
    </form:form>
    </br>
    </br>
    <jsp:include page="footer.jsp"/>
  </body>
  <script src="script/script.js"></script>
</html>
