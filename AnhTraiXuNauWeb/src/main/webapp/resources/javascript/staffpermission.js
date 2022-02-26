var app = angular.module('StaffPermissionApp', []);
app.controller('StaffPermissionController', function($scope, $http) {
	const STAFFPERMISSION = {
		DOES_NOT_EXISTS_STAFFPERMISSIONID: -1,
		DOES_NOT_EXISTS_STAFFPERMISSIONNAME: '',
		STAFFPERMISSION_ACTION_SAVE: 'Save',
		STAFFPERMISSION_ACTION_UPDATE: 'Update',
		STAFFPERMISSION_ACTION_CANCEL: 'Cancel',
		STAFFPERMISSION_ACTION_DELETE: 'Delete',
		STAFFPERMISSION_ISINSERT_ON: true,
		STAFFPERMISSION_ISINSERT_OFF: false
	};
	
	$scope.staffPermission = {};
	
	$scope.init = function() {
		$scope.staffPermissionAction = STAFFPERMISSION.STAFFPERMISSION_ACTION_SAVE;
		$scope.staffPermission.isInsert = STAFFPERMISSION.STAFFPERMISSION_ISINSERT_ON;
		$scope.staffPermission.id = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONID;
		
		$scope.getListStaffPermissionExisting();
	}
	
	$scope.getListStaffPermissionExisting = function() {
		let url = 'admin/api/staffPermission/getListStaffPermissionExisting';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staffPermission.listStaffPermission = response.data.listStaffPermission;			
			}
			else {
				alert('Error getListStaffPermissionExisting');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListStaffPermissionExisting')
		});
	}
	
	$scope.getStaffPermissionById = function(staffPermissionId) {
		let url = 'admin/api/staffPermission/getStaffPermissionById/' + staffPermissionId;
		
		$http({
			 method: 'GET',
			 url: url
		}).then(function successCallback(response) {
			if (response.data) {
				if (response.data.message) {
					alert(response.data.message);	
				}
				else {
					$scope.staffPermission.id = response.data.staffPermissionId;
					$scope.staffPermission.name = response.data.staffPermissionName;
					
					$scope.staffPermissionAction = STAFFPERMISSION.STAFFPERMISSION_ACTION_UPDATE;
					$scope.staffPermissionDelete = STAFFPERMISSION.STAFFPERMISSION_ACTION_DELETE;
					$scope.staffPermissionCancel = STAFFPERMISSION.STAFFPERMISSION_ACTION_CANCEL;
					$scope.staffPermission.isInsert = STAFFPERMISSION.STAFFPERMISSION_ISINSERT_OFF;
				}
			}
			else {
				alert('Error getStaffPermissionById');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getStaffPermissionById');
		});
	}
	
	$scope.completeStaffPermissionEdit = function() {
		let url = 'admin/api/staffPermission/completeStaffPermissionEdit';
		let data = JSON.stringify({
			id: $scope.staffPermission.id,
			name: $scope.staffPermission.name	
		});
		
		$http({
			method: 'POST',
			url: url,
			data: data
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.staffPermissionAction = STAFFPERMISSION.STAFFPERMISSION_ACTION_SAVE;
				$scope.staffPermission.isInsert = STAFFPERMISSION.STAFFPERMISSION_ISINSERT_ON;
				$scope.staffPermission.id = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONID;
				$scope.staffPermission.name = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONNAME;
				
				$scope.staffPermission.listStaffPermission = response.data.listStaffPermission;	
			}
			else {
				alert('Error completeStaffPermissionEdit');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback completeStaffPermissionEdit')
		});
	}
	
	$scope.deleteStaffPermissionById = function() {
		let staffPermissionId = $scope.staffPermission.id;
		let url = 'admin/api/staffPermission/deleteStaffPermissionById/' + staffPermissionId;
		let warning = 'Are you sure to delete: ' + staffPermissionId;
		
		if (confirm(warning) == true) {
			$http({
				method: 'POST',
				url: url
			}).then(function successCallback(response) {
				if (response.data) {
					$scope.staffPermissionAction = STAFFPERMISSION.STAFFPERMISSION_ACTION_SAVE;
					$scope.staffPermission.isInsert = STAFFPERMISSION.STAFFPERMISSION_ISINSERT_ON;
					$scope.staffPermission.id = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONID;
					$scope.staffPermission.name = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONNAME;
					
					$scope.staffPermission.listStaffPermission = response.data.listStaffPermission;	
				}
				else {
					alert('Error deleteStaffPermissionById');
				}
			}, function errorCallback(response) {
				console.log(JSON.stringify(response));
				alert('Error Callback deleteStaffPermissionById')
			});
		}
	}
	
	$scope.cancelStaffPermissionEdit = function() {
		$scope.staffPermission.id = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONID;
		$scope.staffPermission.name = STAFFPERMISSION.DOES_NOT_EXISTS_STAFFPERMISSIONNAME;
		
		$scope.staffPermissionAction = STAFFPERMISSION.STAFFPERMISSION_ACTION_SAVE;
		$scope.staffPermission.isInsert = STAFFPERMISSION.STAFFPERMISSION_ISINSERT_ON;
	}
	
	$scope.init();
});