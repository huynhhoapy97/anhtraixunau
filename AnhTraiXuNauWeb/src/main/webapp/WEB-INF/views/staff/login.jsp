<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="login">
	<div class="brand">
		<span>Staff ATXN</span>			
	</div>
	<div class="title">
		<span>Đăng nhập</span>
	</div>
	<div>
		<form:form method="POST" action="staff/login" modelAttribute="staffAccount">
			<div class="account">
				<form:input type="text" placeholder="Tên tài khoản" autocomplete="off" path="username" />
			</div>
			<div class="account">
				<form:input type="password" placeholder="Mật khẩu" path="password" />
			</div>
			<div class="manipulation">
				<div class="forgetpassword">
					<a href="staffpassword/forgetpassword">Quên mật khẩu</a>
				</div>
				<div class="continue">
					<button>Đăng nhập</button>
				</div>
			</div>
		</form:form>
	</div>
</div>