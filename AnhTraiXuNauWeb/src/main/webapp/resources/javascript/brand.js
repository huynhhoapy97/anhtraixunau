var app = angular.module('BrandApp', []);
app.controller('BrandController', function($scope, $http) {
	const BRAND = {
		DOES_NOT_EXISTS_BRANDID: -1,
		DOES_NOT_EXISTS_BRANDNAME: '',
		DOES_NOT_EXISTS_BRANDDESCRIPTION: '',
		BRAND_ACTION_SAVE: 'Save',
		BRAND_ACTION_UPDATE: 'Update',
		BRAND_ACTION_CANCEL: 'Cancel',
		BRAND_ACTION_DELETE: 'Delete',
		BRAND_ISINSERT_ON: true,
		BRAND_ISINSERT_OFF: false
	};
	
	var ckeditorDescription = null;
	
	$scope.brand = {};
	
	$scope.init = function(){
		$scope.brandAction = BRAND.BRAND_ACTION_SAVE;
		$scope.brand.isInsert = BRAND.BRAND_ISINSERT_ON;
		$scope.brand.id = BRAND.DOES_NOT_EXISTS_BRANDID;
		
		ckeditorDescription = CKEDITOR.replace('brandDescription', {
			filebrowserImageUploadUrl: '/staff/api/brand/imageUpload',
			filebrowserUploadMethod: 'form',
			entities_greek: false,
			entities_latin: false,
			removeButtons: 'Source'
		});
		
		$scope.getListBrandExisting();
	}
	
	$scope.getListBrandExisting = function() {
		let url = 'staff/api/brand/getListBrandInformation';
		
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			if (response.data) {
				$scope.brand.listBrandInformation = response.data.listBrandInformation;		
			}
			else {
				alert('Error getListBrandInformation');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getListBrandInformation')
		});
	}
	
	$scope.getBrandById = function(brandId) {
		let url = 'staff/api/brand/getBrandById/' + brandId;
		
		$http({
			 method: 'GET',
			 url: url
		}).then(function successCallback(response) {
			if (response.data) {
				if (response.data.message) {
					alert(response.data.message);	
				}
				else {
					$scope.brand.id = response.data.brandId;
					$scope.brand.name = response.data.brandName;
					
					ckeditorDescription.setData(response.data.brandDescription, function(){});
					
					$scope.brandAction = BRAND.BRAND_ACTION_UPDATE;
					$scope.brandDelete = BRAND.BRAND_ACTION_DELETE;
					$scope.brandCancel = BRAND.BRAND_ACTION_CANCEL;
					$scope.brand.isInsert = BRAND.BRAND_ISINSERT_OFF;
				}
			}
			else {
				alert('Error getBrandById');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback getBrandById');
		});
	}
	
	$scope.completeBrandEdit = function(){
		let id = $scope.brand.id;
		let name = $scope.brand.name;
		let description = '';
		if (ckeditorDescription){
			description = ckeditorDescription.getData();
		}
		
		let url = 'staff/api/brand/completeBrandEdit';
		let objBrand = {
			id: id,
			name: name,
			description: description 
		};
		
		let data = JSON.stringify(objBrand);
		
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
					$scope.brandAction = BRAND.BRAND_ACTION_SAVE;
					$scope.brand.isInsert = BRAND.BRAND_ISINSERT_ON;
					$scope.brand.id = BRAND.DOES_NOT_EXISTS_BRANDID;
					$scope.brand.name = BRAND.DOES_NOT_EXISTS_BRANDNAME;
					
					ckeditorDescription.setData('', function(){});
					
					$scope.brand.listBrandInformation = response.data.listBrandInformation;
				}
			}
			else {
				alert('Error completeBrandEdit');
			}
		}, function errorCallback(response) {
			console.log(JSON.stringify(response));
			alert('Error Callback completeBrandEdit')
		});
	}
	
	$scope.deleteBrandById = function() {
		let brandId = $scope.brand.id;
		let url = 'staff/api/brand/deleteBrandById/' + brandId;
		let warning = 'Are you sure to delete: ' + brandId;
		
		if (confirm(warning) == true) {
			$http({
				method: 'POST',
				url: url
			}).then(function successCallback(response) {
				if (response.data) {
					$scope.brandAction = BRAND.BRAND_ACTION_SAVE;
					$scope.brand.isInsert = BRAND.BRAND_ISINSERT_ON;
					$scope.brand.id = BRAND.DOES_NOT_EXISTS_BRANDID;
					$scope.brand.name = BRAND.DOES_NOT_EXISTS_BRANDNAME;
					
					ckeditorDescription.setData('', function(){});
					
					$scope.brand.listBrandInformation = response.data.listBrandInformation;
				}
				else {
					alert('Error deleteBrandById');
				}
			}, function errorCallback(response) {
				console.log(JSON.stringify(response));
				alert('Error Callback deleteBrandById')
			});
		}
	}
	
	$scope.cancelBrandEdit = function() {
		$scope.brand.id = BRAND.DOES_NOT_EXISTS_BRANDID;
		$scope.brand.name = BRAND.DOES_NOT_EXISTS_BRANDNAME;
		
		ckeditorDescription.setData('', function(){});
		
		$scope.brandAction = BRAND.BRAND_ACTION_SAVE;
		$scope.brand.isInsert = BRAND.BRAND_ISINSERT_ON;
	}
	
	$scope.preview = function(brandId){
		let requestURL = window.location.protocol + '//' + window.location.host + '/';
		let contextPath = window.location.pathname.trim().split('/')[1];
		let servletPath = '/brand/previewDescription/' + brandId;
		let url = requestURL + contextPath + servletPath;
		
		let features = 'location=yes,height=570,width=520,scrollbars=yes,status=yes';
		window.open(url, '_blank', features);
	}
	
	$scope.init();
});