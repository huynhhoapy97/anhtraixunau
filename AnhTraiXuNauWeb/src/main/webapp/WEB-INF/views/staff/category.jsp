<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html ng-app="CategoryApp">
	<head>
		<meta charset="utf-8">
		<title>Category</title>
	</head>
	<body ng-controller="CategoryController">
		<div>
			<div>
				<input type="text" ng-model="category.id" id="categoryId" ng-show="false" />
				<input type="text" ng-model="category.name" id="categoryName" placeholder="Nhập tên loại hàng" />
				<textarea ng-model="category.description" id="categoryDescription" placeholder="Mô tả loại hàng" rows="5" cols="15"></textarea>
			</div>
			<div>
				<button ng-model="category.isInsert" ng-click="completeCategoryEdit()">{{categoryAction}}</button>
				<button ng-show="!category.isInsert" ng-click="deleteCategoryById()">{{categoryDelete}}</button>
				<button ng-show="!category.isInsert" ng-click="cancelCategoryEdit()">{{categoryCancel}}</button>
			</div>
		</div>
		<hr>
		<div>
			<table style="border: 1px solid black;">
				<tr>
					<th style="border: 1px solid black;">Loại hàng</th>
					<th style="border: 1px solid black;">Ngày tạo</th>
					<th style="border: 1px solid black;">Mô tả</th>
				</tr>
				<tr ng-repeat="category in category.listCategoryInformation" ng-click="getCategoryById(category.id)">
					<td style="border: 1px solid black;">{{category.name}}</td>
					<td style="border: 1px solid black;">{{category.createDate | date: "dd/MM/yyyy"}} - {{category.createUser}}</td>
					<td style="border: 1px solid black;"><button id="btnPreview" ng-click="preview(category.id)">Xem</button></td>
				</tr>
			</table>
		</div>
		
		<script type="text/javascript" src="resources/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="resources/ckeditor/ckeditor.js" charset="utf-8"></script>
		<script type="text/javascript" src="resources/javascript/category.js"></script>
	</body>
</html>