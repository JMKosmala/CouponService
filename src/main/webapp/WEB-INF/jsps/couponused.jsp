<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><%@include file="/resources/style/couponStyle.css"%></style> 
<title>Insert title here</title>
</head>
<body>
	<div class="coupon_box">
		<div class="couponused_box">
			<p>Your coupon nr ${coupon.ID} have been used. </p>
			<p>You save ${coupon.value}. </p>
			<p>New price is now :${user.totalPrice}.</p>
		</div>
			<div class="backButton">
				<a href="/"><input type="button" value="Back to index"></a>
			</div>
		</div>
</body>
</html>