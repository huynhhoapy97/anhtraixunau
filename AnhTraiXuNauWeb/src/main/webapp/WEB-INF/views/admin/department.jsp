<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html ng-app="DepartmentApp">
	<head>
		<meta charset="utf-8">
		<title>Department</title>
		<link href="resources/select2/select2.min.css" rel="stylesheet" />
	</head>
	<body ng-controller="DepartmentController">
		<div>
			<div>
				<select ng-options="staffPermission.name for staffPermission in department.listStaffPermission track by staffPermission.id" ng-model="staffPermission" 
						id="listStaffPermission" style="width: 500px;" multiple="multiple">
				</select>
			</div>
			<div>
				<input type="text" ng-model="department.id" id="departmentId" ng-show="false" />
				<input type="text" ng-model="department.name" id="departmentName" placeholder="Nhập tên phòng ban" />
			</div>
			<div>
				<button ng-model="department.isInsert" ng-click="completeDepartmentEdit()">{{departmentAction}}</button>
				<button ng-show="!department.isInsert" ng-click="deleteDepartmentById()">{{departmentDelete}}</button>
				<button ng-show="!department.isInsert" ng-click="cancelDepartmentEdit()">{{departmentCancel}}</button>
			</div>
		</div>
		<hr>
		<div>
			<table style="border: 1px solid black;">
				<tr>
					<th style="border: 1px solid black;">Bộ phận</th>
					<th style="border: 1px solid black;">Ngày tạo</th>
				</tr>
				<tr ng-repeat="department in department.listDepartment" ng-click="getDepartmentById(department.id)">
					<td style="border: 1px solid black;">{{department.name}}</td>
					<td style="border: 1px solid black;">{{department.createDate | date: "dd/MM/yyyy"}} - {{department.createUser}}</td>
				</tr>
			</table>
		</div>
	
		<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="resources/select2/select2.min.js"></script>
		<script type="text/javascript" src="resources/javascript/department.js"></script>
	</body>
</html>