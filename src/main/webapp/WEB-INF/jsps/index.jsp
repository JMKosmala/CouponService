<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <style><%@include file="/resources/style/couponStyle.css"%></style> 
    <title>CouponService</title>
 </head>

<body>


	<div class="index_box">
		<div class="title">
			<h2>USE YOUR COUPON</h2>
		</div>
		<div class="useCoupon_box">
		<form action="/usecoupon" method="post">
			User ID: <input type="number" name="userID" placeholder="Type userID" />
			<input type="submit" value="userID" class="button" /> <br>
			<br>
		</form>
		</div>


	</div>


</body>

</html>
