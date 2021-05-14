<%@ page import= "com.requirement" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Requirement</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/requirement.js"></script>
</head>
<body>

<div class="container">
<div class="row">
   <div class="col-8">
   
      <h1 class="m-3"> Requirement </h1>
      
      <form id="formRequirement" name="formRequirement">
 ResId:
<input id="resId" name="resId" type="text"
 class="form-control form-control-sm">
 
<br> Req_Type:
<input id="reqType" name="reqType" type="text"
 class="form-control form-control-sm">
 
<br> Req_Note:
<input id="reqNote" name="reqNote" type="text"
 class="form-control form-control-sm">

<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidreqIdSave" name="hidreqIdSave" value="">
</form>
<br>

<div id="alertSuccess" class = "alert alert-success"></div>
<div id ="alertError" class =" alert alert-danger"></div>

<br>
<div id="divRequirementGrid"> 
<%
requirement requirementObj = new requirement();
out.print(requirementObj.readrequirement());
%>
</div>
</div>
   
   
   </div>


</div>


</body>
</html>