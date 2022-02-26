<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="changepassword">
	<div class="brand">
		<span>Anh Trai Xu Nau</span>			
	</div>
	<div class="title">
		<span>Đăng nhập</span>
	</div>
	<div>
		<form:form method="POST" action="admin/changePassword" modelAttribute="adminAccount">
			<form:hidden path="username"></form:hidden>
			<form:input type="password" placeholder="Mật khẩu" path="password" />
			<form:input type="password" placeholder="Nhập lại mật khẩu" path="repassword" />
			<form:button>Xác nhận</form:button>
		</form:form>
	</div>
</div>