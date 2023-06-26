<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Order Details</title>
    
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
	
	#pay {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -412px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#pay:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -448px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>Order</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			Order：${order.oid }　Placed：${order.ordertime }　Total：<font color="red"><b>${order.total }￥</b></font>
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


</table>
<br/>
<form method="post" action="<c:url value='/OrderServlet'/>" id="form" target="_parent">
	<input type="hidden" name="method" value="pay"/>
	<input type="hidden" name="oid" value="${order.oid }"/>
	Address：<input type="text" name="address" size="50" value=""/><br/>

	Choose a bank：<br/>
	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>ICBC
	<img src="../../bank_img/icbc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>BOC
	<img src="../../bank_img/bc.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>ABC
	<img src="../../bank_img/abc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>CCB
	<img src="../../bank_img/ccb.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>BOCO
	<img src="../../bank_img/bcc.bmp" align="middle"/><br/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>

  </body>
</html>

