package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.Category;

public interface CategoryDAO {
	public int insertCategory(String categoryName, String categoryDescription, String userName);
	public int updateCategory(int categoryId, String categoryName, String categoryDescription, String userName);
	public int deleteCategory(int id, String userName);
	public List<Category> getListCategoryInformation();
	public Category getCategoryById(int id);
}
