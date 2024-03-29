<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "CS BookStore");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	bar1.add("Category Management", "View details", "<c:url value='/admin/AdminCategoryServlet?method=findAll'/>", "body");
	bar1.add("Category Management", "Add a category", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("Books Management", "View details", "<c:url value='/admin/AdminBookServlet?method=findAll'/>", "body");
	bar1.add("Books Management", "Add a book", "<c:url value='/admin/AdminBookServlet?method=addpre'/>", "body");

	bar1.add("Orders Management", "All", "<c:url value='/admin/AdminOrderServlet?method=load&state=0'/>", "body");
	bar1.add("Orders Management", "Unpaid Orders", "<c:url value='/admin/AdminOrderServlet?method=load&state=1'/>", "body");
	bar1.add("Orders Management", "Paid Orders", "<c:url value='/admin/AdminOrderServlet?method=load&state=2'/>", "body");
	bar1.add("Orders Management", "Awaiting Delivery", "<c:url value='/admin/AdminOrderServlet?method=load&state=3'/>", "body");
	bar1.add("Orders Management", "Completed", "<c:url value='/admin/AdminOrderServlet?method=load&state=4'/>", "body");

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
