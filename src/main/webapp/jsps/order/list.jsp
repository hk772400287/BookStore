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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>My Orders</h1>

<table border="1" width="100%" cellspacing="0" background="black">
<c:forEach items="${orders }" var="order">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			Order：${order.oid }　Placed：${order.ordertime }　Total：<font color="red"><b>${order.total }</b></font>
			<c:choose>
				<c:when test="${order.state eq 1}">
					<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">Go to payment</a>
				</c:when>
				<c:when test="${order.state eq 2}">Awaiting Shipment</c:when>
				<c:when test="${order.state eq 3}">
					<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid }'/>">Confirm Receipt</a>
				</c:when>
				<c:when test="${order.state eq 4}">Completed</c:when>
			</c:choose>
		</td>
	</tr>


	<c:forEach items="${order.orderItemList }" var="orderItem">

		<tr bordercolor="gray" align="center">
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
