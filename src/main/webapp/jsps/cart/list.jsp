<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Shopping Cart</title>
    
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
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
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
<h1>Shopping Cart</h1>
<c:choose>
	<c:when	test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0}">
		<img src="<c:url value='/images/cart.png'/>"/>
	</c:when>
	<c:otherwise>
		<table border="1" width="100%" cellspacing="0" background="black">
			<tr>
				<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
					<a href="<c:url value='/CartServlet?method=clear'/>">Clear Cart</a>
				</td>
			</tr>
			<tr>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Qty</th>
				<th>Subtotal</th>
				<th>Operation</th>
			</tr>

			<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
				<tr>
					<td><div><img src="<c:url value='/${cartItem.book.image }'/>"/></div></td>
					<td>${cartItem.book.bname }</td>
					<td>${cartItem.book.author }</td>
					<td>${cartItem.book.price }￥</td>
					<td>${cartItem.count }</td>
					<td>${cartItem.subTotal }￥</td>
					<td><a href="<c:url value='/CartServlet?method=delete&bid=${cartItem.book.bid }'/>">Delete</a></td>
				</tr>
			</c:forEach>


			<tr>
				<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
					subTotal：${sessionScope.cart.total }￥
				</td>
			</tr>
			<tr>
				<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
					<a id="buy" href="<c:url value='/OrderServlet?method=add'/>"></a>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>

  </body>
</html>
