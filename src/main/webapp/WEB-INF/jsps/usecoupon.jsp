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
	
	
	
	<div class="coupon_list_container">
	<div class="title"> My Coupons List:</div>
	<div class="title"> Current Value of basket is: ${user.totalPrice}</div>
		<c:forEach items="${couponsList}" var="coupon">
				<form action="/couponused" method="post">
					<div class="coupon_att">
                    <div class="coupon_info">
                    <div>Coupon ID:<div></div> ${coupon.ID}</div></div>
				    <div class="coupon_info">
				    <div>Value:<div></div> ${coupon.value}</div></div>
				    <div class="coupon_info">
				    <div>Remaining:<div></div> ${coupon.usageLeft}</div></div>
                
				<div>
					<button type="submit" value=${coupon.ID} class="button"name="couponID">Use Me!</button>
				</div>
				</div>
			</form>
		</c:forEach>
			<div class="backButton">
			<a href="/"><input type="button" value="Back to main"></a>
		</div>
	</div>
	
</body>
</html>