<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html ng-app="StaffApp">
	<head>
		<meta charset="utf-8">
		<title>Staff</title>
		<link href="resources/select2/select2.min.css" rel="stylesheet" />
	</head>
	<body ng-controller="StaffController">
		<div>
			<div>
				<h3>Thông tin nhân viên</h3>
			</div>
			<div>
				<input type="text" ng-model="staff.id" id="staffId" ng-show="false" />
			</div>
			<div>
				<input type="text" ng-model="staff.lastName" id="lastName" placeholder="Nhập họ" autocomplete="off" />
			</div>
			<div>
				<input type="text" ng-model="staff.firstName" id="firstName" placeholder="Nhập tên" autocomplete="off" />
			</div>
			<div>
				<input type="datetime-local" ng-model="staff.dateStart" id="dateStart" />
			</div>
			<div>
				<select ng-options="department.name for department in staff.listDepartment track by department.id"
				 		ng-model="department" id="listDepartment" ng-change="changeDepartment(department.id)" style="width: 200px;">
				</select>
			</div>
			<div>
				<h3>Thông tin tài khoản nhân viên</h3>
			</div>
			<div>
				<input type="text" ng-model="staff.staffAccount.username" id="username" placeholder="Tên đăng nhập" autocomplete="off" />
			</div>
			<div>
				<input type="text" ng-model="staff.staffAccount.email" id="email" placeholder="Email" autocomplete="off" />
			</div>
			<div>
				<select ng-options="staffPermission.name for staffPermission in staff.listStaffPermission track by staffPermission.id" 
						ng-model="staffPermission" id="listStaffPermission" style="width: 500px;" multiple="multiple">
				</select>
			</div>
			<div>
				<button ng-model="staff.isInsert" ng-click="completeStaffEdit()">{{staffAction}}</button>
				<button ng-show="!staff.isInsert" ng-click="deleteStaffById()">{{staffDelete}}</button>
				<button ng-show="!staff.isInsert" ng-click="cancelStaffEdit()">{{staffCancel}}</button>
			</div>
		</div>
		<hr>
		<div>
			<table style="border: 1px solid black;">
				<tr>
					<th style="border: 1px solid black;">Họ</th>
					<th style="border: 1px solid black;">Tên</th>
					<th style="border: 1px solid black;">Bộ phận</th>
					<th style="border: 1px solid black;">Tên đăng nhập</th>
					<th style="border: 1px solid black;">Trạng thái tài khoản</th>
					<th style="border: 1px solid black;">Gửi mail kích hoạt TK</th>
				</tr>
				<tr ng-repeat="staff in staff.listStaffInformation" ng-click="getStaffInformation(staff)">
					<td style="border: 1px solid black;">{{staff.lastName}}</td>
					<td style="border: 1px solid black;">{{staff.firstName}}</td>
					<td style="border: 1px solid black;">{{staff.department.name}}</td>
					<td style="border: 1px solid black;">{{staff.staffAccount.username}}</td>
					<td style="border: 1px solid black;" ng-if="staff.staffAccount.password">Đã kích hoạt</td>
					<td style="border: 1px solid black;" ng-if="!staff.staffAccount.password">Chưa kích hoạt</td>
					<td style="border: 1px solid black;" ng-if="staff.staffAccount.staffAccountTokenVerify.token">Đã gửi mail kích hoạt</td>
					<td style="border: 1px solid black;" ng-if="!staff.staffAccount.staffAccountTokenVerify.token">
						<span id="{{staff.staffAccount.id}}">
							<a href="" ng-click="sendMailActiveAccount(staff.staffAccount.id, staff.staffAccount.username, staff.staffAccount.email)">Gửi mail</a>
						</span> 
					</td>
				</tr>
			</table>
		</div>
	
		<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="resources/select2/select2.min.js"></script>
		<script type="text/javascript" src="resources/javascript/staff.js"></script>
	</body>
</html>