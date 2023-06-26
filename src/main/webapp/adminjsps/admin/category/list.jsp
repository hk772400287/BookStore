<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Category List</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
	table {font-family: 宋体; font-size: 11pt; border-color: rgb(78,78,78);  width: 60%;}
	#th {background: rgb(78,78,78);}
</style>
  </head>
  
  <body>
    <h2 style="text-align: center;">Category List</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<tr id="th" bordercolor="rgb(78,78,78)">
    		<th>Category</th>
    		<th>Operation</th>
    	</tr>
    <c:forEach items="${categoryList }" var="category">
		<tr bordercolor="rgb(78,78,78)">
			<td>${category.cname }</td>
			<td>
				<a href="<c:url value='/admin/AdminCategoryServlet?method=editpre&cid=${category.cid }'/>">Modify</a> |
				<a onclick="return confirm('Confirm deletion？')" href="<c:url value='/admin/AdminCategoryServlet?method=delete&cid=${category.cid }'/>">Delete</a>
			</td>
		</tr>
	</c:forEach>

   
    </table>
  </body>
</html>
