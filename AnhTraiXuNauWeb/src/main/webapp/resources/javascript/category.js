var app = angular.module('CategoryApp', []);
app.controller('CategoryController', function($scope, $http) {
	const CATEGORY = {
		DOES_NOT_EXISTS_CATEGORYID: -1,
		DOES_NOT_EXISTS_CATEGORYNAME: '',
		DOES_NOT_EXISTS_CATEGORYDESCRIPTION: '',
		CATEGORY_ACTION_SAVE: 'Save',
		CATEGORY_ACTION_UPDATE: 'Update',
		CATEGORY_ACTION_CANCEL: 'Cancel',
		CATEGORY_ACTION_DELETE: 'Delete',
		CATEGORY_ISINSERT_ON: true,
		CATEGORY_ISINSERT_OFF: false
	};
	
	var ckeditorDescription = null;
	
	$scope.category = {};
	
	$scope.init = function(){
		$scope.categoryAction = CATEGORY.CATEGORY_ACTION_SAVE;
		$scope.category.isInsert = CATEGORY.CATEGORY_ISINSERT_ON;
		$scope.category.id = CATEGORY.DOES_NOT_EXISTS_CATEGORYID;
		
		ckeditorDescription = CKEDITOR.replace('categoryDescription', {
			removeButtons: 'Image,Source',
			entities_greek: false,
			entities_latin: false
		});
		
		$scope.getListCategoryExisting();
	}
	
	$scope.getListCategoryExisting = function() {
		let url = 'staff/api/category/getListCategoryInformation';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.category.listCategoryInformation = response.data.listCategoryInformation;		
			}
			else {
				alert('Error getListCategoryInformation');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListCategoryInformation')
		});
	}
	
	$scope.getCategoryById = function(categoryId) {
		let url = 'staff/api/category/getCategoryById/' + categoryId;
		
		$http({
			 method: 'GET',
			 url: url
		}).then(function successCallback(response) {
			if (response.data) {
				if (response.data.message) {
					alert(response.data.message);	
				}
				else {
					$scope.category.id = response.data.categoryId;
					$scope.category.name = response.data.categoryName;
					
					ckeditorDescription.setData(response.data.categoryDescription, function(){});
					
					$scope.categoryAction = CATEGORY.CATEGORY_ACTION_UPDATE;
					$scope.categoryDelete = CATEGORY.CATEGORY_ACTION_DELETE;
					$scope.categoryCancel = CATEGORY.CATEGORY_ACTION_CANCEL;
					$scope.category.isInsert = CATEGORY.CATEGORY_ISINSERT_OFF;
				}
			}
			else {
				alert('Error getCategoryById');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getCategoryById');
		});
	}
	
	$scope.completeCategoryEdit = function(){
		let id = $scope.category.id;
		let name = $scope.category.name;
		let description = '';
		if (ckeditorDescription){
			description = ckeditorDescription.getData();
		}
		
		let url = 'staff/api/category/completeCategoryEdit';
		let objCategory = {
			id: id,
			name: name,
			description: description 
		};
		
		let data = JSON.stringify(objCategory);
		
		$http({
			method: 'POST',
			url: url,
			data: data
		}).then(function successCallback(response) {
			if (response.data) {
				if (response.data.message){
					alert(response.data.message);
				}
				else {
					$scope.categoryAction = CATEGORY.CATEGORY_ACTION_SAVE;
					$scope.category.isInsert = CATEGORY.CATEGORY_ISINSERT_ON;
					$scope.category.id = CATEGORY.DOES_NOT_EXISTS_CATEGORYID;
					$scope.category.name = CATEGORY.DOES_NOT_EXISTS_CATEGORYNAME;
					
					ckeditorDescription.setData('', function(){});
					
					$scope.category.listCategoryInformation = response.data.listCategoryInformation;
				}
			}
			else {
				alert('Error completeCategoryEdit');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback completeCategoryEdit')
		});
	}
	
	$scope.deleteCategoryById = function() {
		let categoryId = $scope.category.id;
		let url = 'staff/api/category/deleteCategoryById/' + categoryId;
		let warning = 'Are you sure to delete: ' + categoryId;
		
		if (confirm(warning) == true) {
			$http({
				method: 'POST',
				url: url
			}).then(function successCallback(response) {
				if (response.data) {
					$scope.categoryAction = CATEGORY.CATEGORY_ACTION_SAVE;
					$scope.category.isInsert = CATEGORY.CATEGORY_ISINSERT_ON;
					$scope.category.id = CATEGORY.DOES_NOT_EXISTS_CATEGORYID;
					$scope.category.name = CATEGORY.DOES_NOT_EXISTS_CATEGORYNAME;
					
					ckeditorDescription.setData('', function(){});
					
					$scope.category.listCategoryInformation = response.data.listCategoryInformation;
				}
				else {
					alert('Error deleteCategoryById');
				}
			}, function errorCallback(response) {
				console.log(JSON.stringify(response));
				alert('Error Callback deleteCategoryById')
			});
		}
	}
	
	$scope.cancelCategoryEdit = function() {
		$scope.category.id = CATEGORY.DOES_NOT_EXISTS_CATEGORYID;
		$scope.category.name = CATEGORY.DOES_NOT_EXISTS_CATEGORYNAME;
		
		ckeditorDescription.setData('', function(){});
		
		$scope.categoryAction = CATEGORY.CATEGORY_ACTION_SAVE;
		$scope.category.isInsert = CATEGORY.CATEGORY_ISINSERT_ON;
	}
	
	$scope.preview = function(categoryId){
		let requestURL = window.location.protocol + '//' + window.location.host + '/';
		let contextPath = window.location.pathname.trim().split('/')[1];
		let servletPath = '/category/previewDescription/' + categoryId;
		let url = requestURL + contextPath + servletPath;
		
		let features = 'location=yes,height=570,width=520,scrollbars=yes,status=yes';
		window.open(url, '_blank', features);
	}
	
	$scope.init();
});