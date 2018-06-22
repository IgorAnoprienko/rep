<%@page import="controller.JpaController"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  rel="stylesheet" type="text/css" href="style.css"/>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-darkness/jquery-ui.css"
	rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="ExecuteOperation" target="forTable" method="POST">
		<p>
			ID : <select id="id" size="1" name="ID">
			<option value="0" ></option>
				<% 
					JpaController controller = (JpaController) session.getAttribute("controller");
					Class<Customer> b = Customer.class;
					Customer obj = null;
					for (Object x : controller.getObjectList(b)) {
						 obj = (Customer) x;
				%>
				<option value="<%=obj.getId()%>">
					<%=obj.getId()%></option>
				<%
					}
				%>
			</select>
		</p>
		<p>
			Ім'я: <input type="text" id="name" name="customerName">
		</p>
		<p>
			Адреса: <input type="text" id="address" name="customerAddress">
		</p>
		<p>
			Телефон: <input type="text" id="phone" name="customerPhone">
		</p>
		<p>
			<input type="submit" id="down" value="Редагувати">
		</p>
	</form>
	
	<script type="text/javascript">
			$(document).ready(function() {
				$("#id").change(function() {
					var sel = document.getElementById("id"); 
					var txt = sel.options[sel.selectedIndex].value;
				<%
					for (Object x : controller.getObjectList(b)) {
						 obj = (Customer) x;
						 
				%>
				if(txt == 0){
					$("#name").val("");
					$("#address").val("");
					$("#phone").val("");  
				}
				if(txt == <%=obj.getId()%>){
				$("#name").val("<%=obj.getName()%>");
				$("#address").val("<%=obj.getAddress()%>"); 
				$("#phone").val("<%=obj.getPhone()%>"); 
				}
				<%
					}
				%>
				});
				
			});
		</script>
</body>
</html>