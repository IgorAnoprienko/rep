<%@page import="controller.JpaController"%>
<%@page import="model.Book"%>
<%@page import="model.Customer"%>
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
	  <p> Книга:
	  
		 <select size="1" name="bookID">
			<%
			JpaController controller = (JpaController) session.getAttribute("controller");
				Class<Book> b = Book.class;
				for (Object x : controller.getObjectList(b)) {
					Book obj = (Book) x;
			%>
			<option value ="<%=obj.getId()%>">
				<%=obj.toString()%></option>
			<%
				}
			%>
		</select>
		
		</p>
		<p>
			Замовник: 
			 <select size="1" name="customerID">
			<%
				Class<Customer> c = Customer.class;
				for (Object x : controller.getObjectList(c)) {
					Customer obj = (Customer) x;
			%>
			<option value ="<%=obj.getId()%>">
				<%=obj.toString()%></option>
			<%
				}
			%>
		</select>
		<p>
		<div class="checkbox">
			Сплачено: <input type="checkbox" id="checkbox" class="checkbox" value="true" name="paidOrder">
			<label for="checkbox"></label>
		</div>
		</p>
		</p>
		<p>
			Дата: <input type="date" name="dateOrder">
		</p>
		<p>
			<input type="submit" id="down" value="Додати" >
		</p>
	</form>
</body>
</html>