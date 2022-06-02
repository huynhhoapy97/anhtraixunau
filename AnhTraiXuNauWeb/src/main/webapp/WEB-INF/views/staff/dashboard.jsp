<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<title>Staff dashboard</title>
	</head>
	<body>
		<div>
			<a href="staff/logout">Logout</a>
		</div>
		<h3>Giao diện quản lý của Staff</h3>
		<div>
			<a href="staff/category">Quản lý loại hàng (Category)</a>
		</div>
		<div>
			<a href="staff/commodity">Quản lý mặt hàng (Commodity)</a>
		</div>
		<div>
			<a href="staff/brand">Quản lý thương hiệu (Brand)</a>
		</div>
	</body>
</html>