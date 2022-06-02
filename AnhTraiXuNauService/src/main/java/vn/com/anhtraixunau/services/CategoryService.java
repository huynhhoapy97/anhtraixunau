package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.models.Category;
import vn.com.anhtraixunau.repositories.CategoryDAO;
import vn.com.anhtraixunau.repositories.CategoryDAOImpl;

public class CategoryService {
	private CategoryDAO categoryDAO;

	public int insertCategory(String categoryName, String categoryDescription, String userName) {
		int result = -1;
		categoryDAO = new CategoryDAOImpl();
		
		try {
			result = categoryDAO.insertCategory(categoryName, categoryDescription, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateCategory(int categoryId, String categoryName, String categoryDescription, String userName) {
		int result = -1;
		categoryDAO = new CategoryDAOImpl();
		
		try {
			result = categoryDAO.updateCategory(categoryId, categoryName, categoryDescription, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteCategoryById(int id, String userName) {
		int result = -1;
		categoryDAO = new CategoryDAOImpl();
		
		try {
			result = categoryDAO.deleteCategory(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Category> getListCategoryInformation() {
		List<Category> listCategoryInformation = new ArrayList<Category>();
		categoryDAO = new CategoryDAOImpl();
		
		try {
			listCategoryInformation = categoryDAO.getListCategoryInformation();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listCategoryInformation;
	}

	public Category getCategoryById(int id) {
		Category category = new Category();
		categoryDAO = new CategoryDAOImpl();
		
		try {
			category = categoryDAO.getCategoryById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return category;
	}
}
