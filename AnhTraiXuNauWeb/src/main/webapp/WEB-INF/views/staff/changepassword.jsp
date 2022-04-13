<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Staff change password</title>
	</head>
	<body>
		<div>
			<form:form method="POST" action="staffpassword/changepassword" modelAttribute="staffAccount">
				<form:hidden path="username"></form:hidden>
				<form:input type="password" placeholder="Mật khẩu" path="password" />
				<form:input type="password" placeholder="Nhập lại mật khẩu" path="repassword" />
				<form:button>Xác nhận</form:button>
			</form:form>
		</div>
	</body>
</html>