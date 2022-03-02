var app = angular.module('DepartmentApp', []);
app.controller('DepartmentController', function($scope, $http) {
	const DEPARTMENT = {
		DOES_NOT_EXISTS_DEPARTMENTID: -1,
		DOES_NOT_EXISTS_DEPARTMENTNAME: '',
		DEPARTMENT_ACTION_SAVE: 'Save',
		DEPARTMENT_ACTION_UPDATE: 'Update',
		DEPARTMENT_ACTION_CANCEL: 'Cancel',
		DEPARTMENT_ACTION_DELETE: 'Delete',
		DEPARTMENT_ISINSERT_ON: true,
		DEPARTMENT_ISINSERT_OFF: false
	};
	
	$scope.department = {};
	
	$scope.init = function() {
		$scope.departmentAction = DEPARTMENT.DEPARTMENT_ACTION_SAVE;
		$scope.department.isInsert = DEPARTMENT.DEPARTMENT_ISINSERT_ON;
		$scope.department.id = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTID;
		
		$scope.getListDepartmentExisting();
		$scope.getListStaffPermissionExisting();
		
		$('#listStaffPermission').select2();
	}
	
	$scope.getListDepartmentExisting = function() {
		let url = 'admin/api/department/getListDepartmentExisting';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.department.listDepartment = response.data.listDepartment;		
			}
			else {
				alert('Error getListDepartmentExisting');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListDepartmentExisting')
		});
	}
	
	$scope.getListStaffPermissionExisting = function() {
		let url = 'admin/api/staffPermission/getListStaffPermissionExisting';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.department.listStaffPermission = response.data.listStaffPermission;			
			}
			else {
				alert('Error getListStaffPermissionExisting');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListStaffPermissionExisting')
		});
	}
	
	$scope.getDepartmentById = function(departmentId) {
		let url = 'admin/api/department/getDepartmentById/' + departmentId;
		
		$http({
			 method: 'GET',
			 url: url
		}).then(function successCallback(response) {
			if (response.data) {
				if (response.data.message) {
					alert(response.data.message);	
				}
				else {
					let listStaffPermissionSelected = new Array();
					let listDepartmentPermission = response.data.listDepartmentPermission;
					if (listDepartmentPermission){
						listStaffPermissionSelected = listDepartmentPermission.split(',');
					}
					
					$('#listStaffPermission').val(listStaffPermissionSelected);
					$('#listStaffPermission').select2();
					
					$scope.department.id = response.data.departmentId;
					$scope.department.name = response.data.departmentName;
					
					$scope.departmentAction = DEPARTMENT.DEPARTMENT_ACTION_UPDATE;
					$scope.departmentDelete = DEPARTMENT.DEPARTMENT_ACTION_DELETE;
					$scope.departmentCancel = DEPARTMENT.DEPARTMENT_ACTION_CANCEL;
					$scope.department.isInsert = DEPARTMENT.DEPARTMENT_ISINSERT_OFF;
				}
			}
			else {
				alert('Error getDepartmentById');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getDepartmentById');
		});
	}
	
	$scope.completeDepartmentEdit = function() {
		let listStaffPermissionSelected = $('#listStaffPermission').val();
		
		if (listStaffPermissionSelected.length == 0) {
			alert('Vui lòng chọn phân quyền');
			return;
		}
		
		let staffPermissionSelected = listStaffPermissionSelected.join(',');	
		
		let url = 'admin/api/department/completeDepartmentEdit';
		let objDepartment = {
			id: $scope.department.id,
			name: $scope.department.name
		};
		
		let mapData = new Map();
		mapData.set('department', objDepartment);
		mapData.set('staffPermissionSelected', staffPermissionSelected);
		
		let dataRequest = Object.fromEntries(mapData);
		
		let data = JSON.stringify({dataRequest});
		
		$http({
			method: 'POST',
			url: url,
			data: data
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.departmentAction = DEPARTMENT.DEPARTMENT_ACTION_SAVE;
				$scope.department.isInsert = DEPARTMENT.DEPARTMENT_ISINSERT_ON;
				$scope.department.id = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTID;
				$scope.department.name = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTNAME;
				
				$scope.department.listDepartment = response.data.listDepartment;	
				
				let listStaffPermissionSelected = new Array();
				$('#listStaffPermission').val(listStaffPermissionSelected);
				$('#listStaffPermission').select2();
			}
			else {
				alert('Error completeDepartmentEdit');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback completeDepartmentEdit')
		});
	}
	
	$scope.deleteDepartmentById = function() {
		let departmentId = $scope.department.id;
		let url = 'admin/api/department/deleteDepartmentById/' + departmentId;
		let warning = 'Are you sure to delete: ' + departmentId;
		
		if (confirm(warning) == true) {
			$http({
				method: 'POST',
				url: url
			}).then(function successCallback(response) {
				if (response.data) {
					$scope.departmentAction = DEPARTMENT.DEPARTMENT_ACTION_SAVE;
					$scope.department.isInsert = DEPARTMENT.DEPARTMENT_ISINSERT_ON;
					$scope.department.id = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTID;
					$scope.department.name = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTNAME;
					
					$scope.department.listDepartment = response.data.listDepartment;	
					
					let listStaffPermissionSelected = new Array();
					$('#listStaffPermission').val(listStaffPermissionSelected);
					$('#listStaffPermission').select2();	
				}
				else {
					alert('Error deleteDepartmentById');
				}
			}, function errorCallback(response) {
				console.log(JSON.stringify(response));
				alert('Error Callback deleteDepartmentById')
			});
		}
	}
	
	$scope.cancelDepartmentEdit = function() {
		$scope.department.id = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTID;
		$scope.department.name = DEPARTMENT.DOES_NOT_EXISTS_DEPARTMENTNAME;
		
		$scope.departmentAction = DEPARTMENT.DEPARTMENT_ACTION_SAVE;
		$scope.department.isInsert = DEPARTMENT.DEPARTMENT_ISINSERT_ON;
		
		let listStaffPermissionSelected = new Array();
		$('#listStaffPermission').val(listStaffPermissionSelected);
		$('#listStaffPermission').select2();
	}
	
	$scope.init();
});