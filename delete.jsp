<%@page import="controller.JpaController"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  rel="stylesheet" type="text/css" href="style.css"/>
<title>Insert title here</title>
</head>
<body>
<form action="ExecuteOperation" target="forTable" method="POST">

		<p>
			ID: <select id="id" size="1" name="ID">
			<option  value="0" ></option>
				<% 
					String tableName = (String) (session.getAttribute("tableName"));
					JpaController controller = (JpaController) session.getAttribute("controller");
					
					Class clazz = Class.forName("model."+tableName);
					
					for (Object x : controller.getObjectList(clazz)) {
						IModel  obj = (IModel) x;
				%>
				<option value="<%=obj.getId()%>">
					<%=obj.toString()%></option>
				<%
					}
				%>
			</select>
		</p>
	
		<p>
			<input type="submit" id="down" value="Видалити">
		</p>
	</form>
</body>
</html>