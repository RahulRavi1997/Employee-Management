<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="header" >
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="styles/employeedetails.css"/>
  <nav class="navbar navbar-default ">
    <div class="container-fluid overflow-y-hidden">
      <div class="navbar-header">
        <a class="fa fa-home navbar-brand" href="main_menu">Home</a>
      </div>
      <div class="nav navbar-header nav-padding">
        <a class="navbar-brand" href="display_employees">Employee</a>
      </div>
      <c:if test="${sessionScope.role == 'Admin'}">
        <div class="nav navbar-header nav-padding">
          <a class="navbar-brand" href="display_projects">Project</a>
        </div>
      </c:if>
      <c:if test="${sessionScope.role == 'Admin'}">
        <div class="nav navbar-header nav-padding">
          <a class="navbar-brand" href="display_clients">Client</a>
        </div>
      </c:if>

<div class="dropdown navbar-right right-nav">
  <ul class="nav navbar-nav">
    <li class="logo-border dropdown">

      <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
      <img src="styles/images/ideas1.jpg" alt="Ideas2it" class="logo">
      <span class="circle">${fn:toUpperCase(fn:substring(sessionScope.email, -1, 1))}</span><div class="caret"></div></a>
      <ul class="dropdown-menu dropdown-menu-large row dropdown-menu-right">
        <li class="col-sm-12">
          <li class="info-padding">
          ${sessionScope.email}	
          </li>
          <li class="info-padding">
          Access Level - <b>${sessionScope.role}</b>
          </li>
		<li class="divider"></li>
          <li>
   <a href="logout" onclick="return confirm('Do you want to Sign Out?')" ><button class="btn btn-margin"><span class="glyphicon glyphicon-log-out"></span> Sign out</button></a>
          </li>
        </li>
      </ul>
    </li>
  </ul>
</div>


      </ul>
    </div>
  </nav>
</div>
