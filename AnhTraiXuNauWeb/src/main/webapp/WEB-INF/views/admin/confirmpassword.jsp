<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="confirmpassword">
	<div class="brand">
		<span>Anh Trai Xu Nau</span>			
	</div>
	<div class="title">
		<span>Đăng nhập</span>
	</div>
	<div>
		<form:form method="POST" action="admin/confirmPassword" modelAttribute="adminAccount">
			<div class="account">
				<form:hidden path="username"/>
				<form:input type="password" placeholder="Mật khẩu" path="password" />
			</div>
			<div class="manipulation">
				<div class="forgetpassword">
					<a href="#">Quên mật khẩu</a>
				</div>
				<div class="btn-admin-login">
					<button>Đăng nhập</button>
				</div>
			</div>
		</form:form>
	</div>
</div>