<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>>

<div class="login">
	<div class="brand">
		<span>Anh Trai Xu Nau</span>			
	</div>
	<div class="title">
		<span>Đăng nhập</span>
	</div>
	<div>
		<form:form method="POST" action="admin/login" modelAttribute="adminAccount">
			<div class="account">
				<form:input type="text" placeholder="Tên tài khoản" path="username" />
			</div>
			<div class="manipulation">
				<div class="forgetpassword">
					<a href="#">Quên mật khẩu</a>
				</div>
				<div class="continue">
					<button>Tiếp theo</button>
				</div>
			</div>
		</form:form>
	</div>
</div>