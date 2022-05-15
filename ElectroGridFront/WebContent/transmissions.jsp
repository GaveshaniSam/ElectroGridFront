<%@page import="com.Transmission"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transmissions Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/transmissions.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 

     <h2>TRANSMISSION DETAILS</h2><br>
     
     <form id="formTransmission" name="formTransmission">
     
     <!-- NO -->
     Transmission No : 
        <input id="no" name="no" type="text"
           class="form-control form-control-sm">
     <br>
     
     <!-- AREA  -->
     Transmission Area : 
        <input id="area" name="area" type="text"
           class="form-control form-control-sm">
     <br>
     
     <!-- NAME -->
     Transmission Name : 
        <input id="name" name="name" type="text"
           class="form-control form-control-sm">
     <br>
     
     <!-- VOLTAGE -->
     Transmission Voltage : 
        <input id="voltage" name="voltage" type="text"
           class="form-control form-control-sm">
     <br>
     
     <!-- DATE -->
     Transmission Date : 
        <input id="date" name="date" type="text"
           class="form-control form-control-sm">
     <br>
     
     <!-- TIME -->
     Transmission Time : 
        <input id="time" name="time" type="text"
           class="form-control form-control-sm">
     <br>
     
     <input id="btnSave" name="btnSave" type="button" value="Save"
                class="btn btn-primary">
 	    <input type="hidden" id="hidTransmissionIDSave" name="hidTransmissionIDSave" value="">
     
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>

<div id="divTransmissionGrid">

 <%
 Transmission transmissionObj = new Transmission();
 out.print(transmissionObj.readTransmissions()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
