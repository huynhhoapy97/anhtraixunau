<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html ng-app="StaffPermissionApp">
	<head>
		<meta charset="utf-8">
		<title>Staff Permission</title>
	</head>
	<body ng-controller="StaffPermissionController">
		<div>
			<div>
				<div>
					<input type="text" ng-model="staffPermission.id" id="staffPermissionId" ng-show="false" />
					<input type="text" ng-model="staffPermission.name" id="staffPermissionName" placeholder="Nhập tên phân quyền" />
				</div>
				<div>
					<button ng-model="staffPermission.isInsert" ng-click="completeStaffPermissionEdit()">{{staffPermissionAction}}</button>
					<button ng-show="!staffPermission.isInsert" ng-click="deleteStaffPermissionById()">{{staffPermissionDelete}}</button>
					<button ng-show="!staffPermission.isInsert" ng-click="cancelStaffPermissionEdit()">{{staffPermissionCancel}}</button>
				</div>
			</div>
		</div>
		<hr>
		<div>
			<table style="border: 1px solid black;">
				<tr>
					<th style="border: 1px solid black;">Phân quyền</th>
					<th style="border: 1px solid black;">Ngày tạo</th>
				</tr>
				<tr ng-repeat="staffPermission in staffPermission.listStaffPermission" ng-click="getStaffPermissionById(staffPermission.id)">
					<td style="border: 1px solid black;">{{staffPermission.name}}</td>
					<td style="border: 1px solid black;">{{staffPermission.createDate | date: "dd/MM/yyyy"}} - {{staffPermission.createUser}}</td>
				</tr>
			</table>
		</div>
		
		<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="resources/javascript/staffpermission.js"></script>
	</body>
</html>