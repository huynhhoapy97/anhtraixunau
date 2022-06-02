<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html ng-app="BrandApp">
	<head>
		<meta charset="utf-8">
		<title>Brand</title>
	</head>
	<body ng-controller="BrandController">
		<div>
			<div>
				<input type="text" ng-model="brand.id" id="brandId" ng-show="false" />
				<input type="text" ng-model="brand.name" id="brandName" placeholder="Nhập tên thương hiệu" />
				<textarea ng-model="brand.description" id="brandDescription" placeholder="Mô tả thương hiệu" rows="5" cols="15"></textarea>
			</div>
			<div>
				<button ng-model="brand.isInsert" ng-click="completeBrandEdit()">{{brandAction}}</button>
				<button ng-show="!brand.isInsert" ng-click="deleteBrandById()">{{brandDelete}}</button>
				<button ng-show="!brand.isInsert" ng-click="cancelBrandEdit()">{{brandCancel}}</button>
			</div>
		</div>
		<hr>
		<div>
			<table style="border: 1px solid black;">
				<tr>
					<th style="border: 1px solid black;">Thương hiệu</th>
					<th style="border: 1px solid black;">Ngày tạo</th>
					<th style="border: 1px solid black;">Mô tả</th>
				</tr>
				<tr ng-repeat="brand in brand.listBrandInformation" ng-click="getBrandById(brand.id)">
					<td style="border: 1px solid black;">{{brand.name}}</td>
					<td style="border: 1px solid black;">{{brand.createDate | date: "dd/MM/yyyy"}} - {{brand.createUser}}</td>
					<td style="border: 1px solid black;"><button id="btnPreview" ng-click="preview(brand.id)">Xem</button></td>
				</tr>
			</table>
		</div>
		
		<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="resources/ckeditor/ckeditor.js" charset="utf-8"></script>
		<script type="text/javascript" src="resources/ckfinder/ckfinder.js" charset="utf-8"></script>
		<script type="text/javascript" src="resources/javascript/brand.js"></script>
	</body>
</html>