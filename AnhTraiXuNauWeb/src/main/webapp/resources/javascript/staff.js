var app = angular.module('StaffApp', []);
app.controller('StaffController', function($scope, $http) {
	const STAFF = {
		DOES_NOT_EXISTS_DEPARTMENTID: -1,
		DOES_NOT_EXISTS_STAFFID: -1,
		DOES_NOT_EXISTS_STAFFNAME: '',
		DOES_NOT_EXISTS_STAFFLASTNAME: '',
		DOES_NOT_EXISTS_STAFFFIRSTNAME: '',
		DOES_NOT_EXISTS_STAFFACCOUNTUSERNAME: '',
		DOES_NOT_EXISTS_STAFFACCOUNTEMAIL: '',
		STAFF_ACTION_SAVE: 'Save',
		STAFF_ACTION_UPDATE: 'Update',
		STAFF_ACTION_CANCEL: 'Cancel',
		STAFF_ACTION_DELETE: 'Delete',
		STAFF_ISINSERT_ON: true,
		STAFF_ISINSERT_OFF: false
	};
	
	$scope.staff = {};
	$scope.staff.staffAccount = {};
	$scope.staff.department = {};
	
	$scope.init = function() {
		$scope.staffAction = STAFF.STAFF_ACTION_SAVE;
		$scope.staff.isInsert = STAFF.STAFF_ISINSERT_ON;
		$scope.staff.id = STAFF.DOES_NOT_EXISTS_STAFFID;
		
		$scope.getListStaffInformation();
		$scope.getListDepartmentExisting();
		
		$('#listDepartment').select2();
		$('#listStaffPermission').select2();
	}
	
	$scope.getListStaffInformation = function() {
		let url = 'admin/api/staff/getListStaffInformation';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.listStaffInformation = response.data.listStaffInformation;
			}
			else {
				alert('Error getListStaffInformation')
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListStaffInformation')
		});
	}
	
	$scope.getListDepartmentExisting = function() {
		let url = 'admin/api/department/getListDepartmentExisting';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.listDepartment = response.data.listDepartment;		
			}
			else {
				alert('Error getListDepartmentExisting');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListDepartmentExisting')
		});
	}
	
	$scope.changeDepartment = function(departmentId) {
		let url = 'admin/api/department/getListDepartmentPermissionByDepartmentId/' + departmentId;
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.listStaffPermission = response.data.listStaffPermission;		
			}
			else {
				alert('Error getListDepartmentPermissionByDepartmentId');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListDepartmentPermissionByDepartmentId')
		});
	}
	
	$scope.getStaffInformation = function(staff) {
		$scope.staff.id = staff.id;
		$scope.staff.lastName = staff.lastName;
		$scope.staff.firstName = staff.firstName;
		$scope.staff.dateStart = new Date(staff.dateStart);
		
		let date = new Date(staff.dateStart);
		let dateStart = new Date(staff.dateStart).toLocaleString('en-US');
		
		console.log('dateVal: ' + staff.dateStart);
		console.log('date: ' + date);
		console.log('format: ' + dateStart );
		console.log('day: ' + date.getDate() + ' - month: ' + date.getMonth() + ' - year: ' + date.getFullYear() 
		+ ' - hour: ' + date.getHours() + ' - minute: ' + date.getMinutes());

		//$('#dateStart').val('2022-03-03T17:08');
		$('#listDepartment').val(staff.department.id);
		$('#listDepartment').select2();
		
		$scope.staff.staffAccount.username = staff.staffAccount.username;
		$scope.staff.staffAccount.email = staff.staffAccount.email;
		
		$scope.changeDepartment(staff.department.id);
		//$scope.getListPermissionIdByDepartmentId(staff.department.id);
		$scope.getListPermissionIdByStaffAccountId(staff.staffAccount.id);
		
		$scope.staffAction = STAFF.STAFF_ACTION_UPDATE;
		$scope.staffDelete = STAFF.STAFF_ACTION_DELETE;
		$scope.staffCancel = STAFF.STAFF_ACTION_CANCEL;
		$scope.staff.isInsert = STAFF.STAFF_ISINSERT_OFF;
	}
	
	$scope.getListPermissionIdByDepartmentId = function(departmentId) {
		let url = 'admin/api/department/getListPermissionIdByDepartmentId/' + departmentId;
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.listPermissionId = response.data.listPermissionId;		
				
				let listPermissionIdSelected = new Array();
				let listPermissionId = response.data.listPermissionId;
				if (listPermissionId){
					listPermissionIdSelected = listPermissionId.split(',');
				}
				
				$('#listStaffPermission').val(listPermissionIdSelected);
				$('#listStaffPermission').select2();
			}
			else {
				alert('Error getListDepartmentPermissionByDepartmentId');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListDepartmentPermissionByDepartmentId')
		});
	}
	
	$scope.getListPermissionIdByStaffAccountId = function(staffAccountId) {
		let url = 'admin/api/staff/getListPermissionIdByStaffAccountId/' + staffAccountId;
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.listPermissionId = response.data.listPermissionId;		
				
				let listPermissionIdSelected = new Array();
				let listPermissionId = response.data.listPermissionId;
				if (listPermissionId){
					listPermissionIdSelected = listPermissionId.split(',');
				}
				
				$('#listStaffPermission').val(listPermissionIdSelected);
				$('#listStaffPermission').select2();
			}
			else {
				alert('Error getListPermissionIdByStaffAccountId');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListPermissionIdByStaffAccountId')
		});
	}
	
	$scope.completeStaffEdit = function() {
		let staffId = $scope.staff.id;
		let lastName = $scope.staff.lastName;
		let firstName = $scope.staff.firstName;
		let dateStart = $scope.staff.dateStart;
		let accountUsername = $scope.staff.staffAccount.username;
		let accountEnail = $scope.staff.staffAccount.email;
		let departmentId = $('#listDepartment').val();
		let listPermissionId = $('#listStaffPermission').val();
		
		if (!listPermissionId || listPermissionId.length == 0) {
			alert('Vui lòng chọn phân quyền');
			return;
		}
		
		let objStaff = {
			staffId: staffId,
			lastName: lastName,
			firstName: firstName,
			dateStart: dateStart
		};
		
		let objStaffAccount = {
			username: accountUsername,
			email: accountEnail
		};
		
		let objDepartment = {
			id: departmentId
		};
		
		let permissionId = listPermissionId.join(',');	
		
		let mapData = new Map();
		mapData.set('staff', objStaff);
		mapData.set('staffAccount', objStaffAccount);
		mapData.set('department', objDepartment);
		mapData.set('permissionId', permissionId);
		
		let dataRequest = Object.fromEntries(mapData);
		
		let url = 'admin/api/staff/completeStaffEdit';
		let data = JSON.stringify({dataRequest});
		console.log('dateStart: ' + dateStart);
		$http({
			method: 'POST',
			url: url,
			data: data
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staff.department.id = STAFF.DOES_NOT_EXISTS_DEPARTMENTID;
				$scope.staff.id = STAFF.DOES_NOT_EXISTS_STAFFID;
				$scope.staff.lastName = STAFF.DOES_NOT_EXISTS_STAFFLASTNAME;
				$scope.staff.firstName = STAFF.DOES_NOT_EXISTS_STAFFFIRSTNAME;
				$scope.staff.staffAccount.username = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTUSERNAME;
				$scope.staff.staffAccount.email = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTEMAIL;
				
				$scope.staffAction = STAFF.STAFF_ACTION_SAVE;
				$scope.staff.isInsert = STAFF.STAFF_ISINSERT_ON;
				
				$scope.staff.listStaffInformation = response.data.listStaffInformation;
				
				$('#listDepartment').val($scope.staff.department.id);
				$('#listDepartment').select2();
				
				let listPermissionIdSelected = new Array();
				$('#listStaffPermission').val(listPermissionIdSelected);
				$('#listStaffPermission').select2();
			}
			else {
				alert('Error completeStaffEdit');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback completeStaffEdit')
		});
	}
	
	$scope.deleteStaffById = function() {
		let staffId = $scope.staff.id;
		let url = 'admin/api/staff/deleteStaffById/' + staffId;
		let warning = 'Are you sure to delete: ' + staffId;
		
		if (confirm(warning) == true) {
			$http({
				method: 'POST',
				url: url
			}).then(function successCallback(response) {
				if (response.data) {
					$scope.staff.department.id = STAFF.DOES_NOT_EXISTS_DEPARTMENTID;
					$scope.staff.id = STAFF.DOES_NOT_EXISTS_STAFFID;
					$scope.staff.lastName = STAFF.DOES_NOT_EXISTS_STAFFLASTNAME;
					$scope.staff.firstName = STAFF.DOES_NOT_EXISTS_STAFFFIRSTNAME;
					$scope.staff.staffAccount.username = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTUSERNAME;
					$scope.staff.staffAccount.email = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTEMAIL;
					
					$scope.staffAction = STAFF.STAFF_ACTION_SAVE;
					$scope.staff.isInsert = STAFF.STAFF_ISINSERT_ON;
					
					$scope.staff.listStaffInformation = response.data.listStaffInformation;
					
					$('#listDepartment').val($scope.staff.department.id);
					$('#listDepartment').select2();
					
					let listPermissionIdSelected = new Array();
					$('#listStaffPermission').val(listPermissionIdSelected);
					$('#listStaffPermission').select2();	
				}
				else {
					alert('Error deleteStaffById');
				}
			}, function errorCallback(response) {
				console.log(JSON.stringify(response));
				alert('Error Callback deleteStaffById')
			});
		}
	}
	
	$scope.cancelStaffEdit = function() {
		$scope.staff.department.id = STAFF.DOES_NOT_EXISTS_DEPARTMENTID;
		$scope.staff.id = STAFF.DOES_NOT_EXISTS_STAFFID;
		$scope.staff.lastName = STAFF.DOES_NOT_EXISTS_STAFFLASTNAME;
		$scope.staff.firstName = STAFF.DOES_NOT_EXISTS_STAFFFIRSTNAME;
		$scope.staff.staffAccount.username = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTUSERNAME;
		$scope.staff.staffAccount.email = STAFF.DOES_NOT_EXISTS_STAFFACCOUNTEMAIL;
		
		$scope.staffAction = STAFF.STAFF_ACTION_SAVE;
		$scope.staff.isInsert = STAFF.STAFF_ISINSERT_ON;
		
		$('#listDepartment').val($scope.staff.department.id);
		$('#listDepartment').select2();
		
		let listPermissionIdSelected = new Array();
		$('#listStaffPermission').val(listPermissionIdSelected);
		$('#listStaffPermission').select2();
	}
	
	$scope.sendMailActiveAccount = function(staffAccountId, staffAccountUsername, staffAccountEmail){
		console.log(staffAccountId + ' - ' + staffAccountUsername + ' - ' + staffAccountEmail);
		let url = 'admin/api/staff/sendMailActiveAccount';
		let data = JSON.stringify({
			id: staffAccountId,
			username: staffAccountUsername,
			email: staffAccountEmail	
		});
		
		$http({
			method: 'POST',
			url: url,
			data: data
		}).then(function successCallback(response) {
			if (response.data) {
				if (!response.data.message){
					let strId = '#' + staffAccountId;
					$(strId).html('<span>Đã gửi mail</span>');
				}
				else{
					alert(response.data.message);
				}
			}
			else {
				alert('Error sendMailActiveAccount');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback sendMailActiveAccount')
		});
	}
	
	$scope.init();
});