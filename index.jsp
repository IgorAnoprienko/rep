<%@page import="controller.JpaController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookShop DataBase</title>
<link  rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
	<center>
	<h3>Робота з базою даних BookShop</h3>
	<iframe name="forMenu" src="menuTable.html" width="300" height="330">
	</iframe>
	<iframe name="forTable" width="620" height="330"> </iframe>
	<br>
	<iframe name="forOperation" src="menuOperation.html" width="930"
		height="100"> </iframe>
	<br>
	<iframe name="forDialog" width="930" height="290"> </iframe>
</center>
		<%
				session.setAttribute("controller", new JpaController());
		%>
	
</body>
</html>