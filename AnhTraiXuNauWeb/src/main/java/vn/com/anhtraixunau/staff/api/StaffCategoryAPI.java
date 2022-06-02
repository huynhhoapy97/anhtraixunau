package vn.com.anhtraixunau.staff.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.anhtraixunau.enums.CategoryMessage;
import vn.com.anhtraixunau.models.Category;
import vn.com.anhtraixunau.services.CategoryService;

@RestController
@RequestMapping("staff/api/category")
public class StaffCategoryAPI {
	private CategoryService categoryService;
	
	@GetMapping("getListCategoryInformation")
	public Map<String, Object> getListCategoryInformation() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Category> listCategoryInformation = new ArrayList<Category>();
		
		try {
			categoryService = new CategoryService();
			listCategoryInformation = categoryService.getListCategoryInformation();
			
			response.put("listCategoryInformation", listCategoryInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GetMapping("getCategoryById/{id}")
	public Map<String, String> getCategoryById(@PathVariable("id") Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		Category category = new Category();
		categoryService = new CategoryService();
		
		try {
			category = categoryService.getCategoryById(id);
			
			if (category.getId() != 0) {
				response.put("categoryId", String.valueOf(category.getId()));
				response.put("categoryName", category.getName());
				response.put("categoryDescription", category.getDescription());
			}
			else {
				response.put("message", "Không có thông tin loại hàng");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi lấy thông tin loại hàng");
		}
		
		return response;
	}
	
	@PostMapping("completeCategoryEdit")
	public Map<String, Object> completeCategoryEdit(@RequestBody Category category, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Category> listCategoryInformation = new ArrayList<Category>();
		categoryService = new CategoryService();
		
		int categoryId = -1;
		String categoryName = "";
		String categoryDescription = "";
		String userName = "";
		
		try {
			categoryId = category.getId();
			categoryName = category.getName();
			categoryDescription = category.getDescription();
			userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			if (categoryId == CategoryMessage.DOES_NOT_EXISTS_CATEGORYID.getId()) {
				categoryService.insertCategory(categoryName, categoryDescription, userName);
			}
			else {
				categoryService.updateCategory(categoryId, categoryName, categoryDescription, userName);
			}
			
			listCategoryInformation = categoryService.getListCategoryInformation();
			
			response.put("listCategoryInformation", listCategoryInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi thêm/sửa thông tin loại hàng");
		}
		
		return response;
	}
	
	@PostMapping("deleteCategoryById/{id}")
	public Map<String, Object> deleteCategoryById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Category> listCategoryInformation = new ArrayList<Category>();
		String userName = "";
		categoryService = new CategoryService();
		
		try {
			userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			categoryService.deleteCategoryById(id, userName);
			
			listCategoryInformation = categoryService.getListCategoryInformation();
			
			response.put("listCategoryInformation", listCategoryInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi xóa thông tin loại hàng");
		}
		
		return response;
	}
}
