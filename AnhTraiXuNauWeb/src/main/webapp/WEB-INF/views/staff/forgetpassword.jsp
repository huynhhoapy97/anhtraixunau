<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Forget Password</title>
	</head>
	<body>
		<div>
			<form:form method="POST" action="staffpassword/forgetpassword" modelAttribute="staffAccount">
				<form:input type="text" placeholder="Email" path="email" />
				<form:button>Xác nhận</form:button>
			</form:form>
		</div>
	</body>
</html>