<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Order List</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body style="background: rgb(254,238,189);">
<h1>Order List</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<c:forEach items="${orderList }" var="order">
	<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
		<td colspan="6">
			Order：${order.oid }　Placed：${order.ordertime }　Total：<font color="red"><b>${order.total }</b></font>
			<c:choose>
				<c:when test="${order.state eq 1}">Awaiting Payment</c:when>
				<c:when test="${order.state eq 2}">Awaiting Shipment</c:when>
				<c:when test="${order.state eq 3}">Confirm Receipt</c:when>
				<c:when test="${order.state eq 4}">Completed</c:when>
			</c:choose>
		</td>
	</tr>
		<c:forEach items="${order.orderItemList }" var="orderItem">
	<tr bordercolor="rgb(78,78,78)" align="center">
		<td width="15%">
			<div><img src="<c:url value='/${orderItem.book.image }'/>" height="75"/></div>
		</td>
		<td>Title：${orderItem.book.bname }</td>
		<td>Price：${orderItem.book.price }￥</td>
		<td>Author：${orderItem.book.author }</td>
		<td>Qty：${orderItem.count }</td>
		<td>Subtotal：${orderItem.subtotal }￥</td>
	</tr>
		</c:forEach>
	</c:forEach>
</table>
  </body>
</html>
