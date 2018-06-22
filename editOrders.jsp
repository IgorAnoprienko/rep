<%@page import="controller.JpaController"%>
<%@page import="model.Book"%>
<%@page import="model.Orders"%>
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
					Class<Orders> s = Orders.class;
					Orders obj = null;
					for (Object x : controller.getObjectList(s)) {
						 obj = (Orders) x;
				%>
				<option value="<%=obj.getId()%>">
					<%=obj.getId()%></option>
				<%
					}
				%>
			</select>
		</p>
		  <p> Книга:
		 <select size="1" id="book" name="bookID">
		 <option value="0" disabled selected>Виберіть книгу</option>
			<%
				Class<Book> b = Book.class;
				for (Object x : controller.getObjectList(b)) {
					Book ob = (Book) x;
			%>
			<option value ="<%=ob.getId()%>">
				<%=ob.getName()%></option>
			<%
				}
			%>
		</select>
		</p>
		<p>
			Замовник: 
			 <select size="1" id="customer" name="customerID">
			 <option value="0" disabled selected>Виберіть замовника</option>
			<%
				Class<Customer> p = Customer.class;
				for (Object x : controller.getObjectList(p)) {
					Customer o = (Customer) x;
			%>
			<option value ="<%=o.getId()%>">
				<%=o.getName()%></option>
			<%
				}
			%>
		</select>
		</p>
		<p>
		<div class="checkbox">
			Сплачено: <input type="checkbox" id="checkbox" class="checkbox" value="true" name="paidOrder">
			<label for="checkbox"></label>
		</div>
		</p>
		</p>
		<p>
			Дата: <input type="date" id="date" name="dateOrder">
		</p>
		<p>
			<input type="submit" id="down" value="Редагувати" >
		</p>
	</form>
	
	<script type="text/javascript">
			$(document).ready(function() {
				$("#id").change(function() {
					
					var sel = document.getElementById("id"); 
					var txt = sel.options[sel.selectedIndex].value;
				<%
					for (Object x : controller.getObjectList(s)) {
						 obj = (Orders) x;
						 
				%>
				if(txt == 0){
					$("#paid").val("false");
					$("#date").val(""); 
				}
				if(txt == <%=obj.getId()%>){
				$("#paid").val("<%=obj.getPaid()%>");
				<% int year = obj.getDate().getYear()+1900;
				
				int month = obj.getDate().getMonth()+1;
				int day = obj.getDate().getDate();
				%>
				
				var today ="<%=year%>"+"-0"+"<%=month%>"+"-"+"<%=day%>";
				
				$('#date').val(today);
				}
				<%
					}
				%>
				});
				
			});
		</script>
</body>
</html>