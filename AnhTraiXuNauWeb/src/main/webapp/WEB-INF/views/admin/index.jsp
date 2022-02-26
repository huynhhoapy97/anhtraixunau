<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<title>Administrator</title>
		<link rel="stylesheet" type="text/css" href="resources/css/admin.css">
	</head>
	<body>
		<div class="mainview">
			<div class="header">
			
			</div>
			<div class="body">
				<jsp:include page="${pageName}"></jsp:include>
			</div>
			<div class="footer">
				<h3>${message}</h3>
			</div>
		</div>
	</body>
</html>