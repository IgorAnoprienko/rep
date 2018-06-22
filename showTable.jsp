<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>showTable</title>
</head>
<body>
	<%
		String tableName = (String) (session.getAttribute("tableName"));
		TableModel tableModel = (TableModel) (session.getAttribute("tableModel"));

	%>
	<center>
		<b> Таблиця <%=tableName%></b>
		<table class="bordered" >
		<thead>
			<tr>
				<%
					int nCol = tableModel.getColumnCount();
				%>
				<th width="30"><%=tableModel.getColumnName(0)%></th>
				<%
					for (int i = 1; i < nCol; i++) {
						String h = tableModel.getColumnName(i);
				%>
				<th width="110"><%=h%></th>
				<%
					}
				%>
			</tr>
			</thead>
			<%
				int nRow = tableModel.getRowCount();
				for (int r = 0; r < nRow; r++) {
			%>
			<tr>
				<%
					for (int j = 0; j < nCol; j++) {
							String str = tableModel.getValueAt(r, j).toString();
				%>
				<td  align="center"><%=str%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
</body>
</html>
