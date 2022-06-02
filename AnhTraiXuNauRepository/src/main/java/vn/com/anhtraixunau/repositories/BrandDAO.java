package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.Brand;


public interface BrandDAO {
	public int insertBrand(String brandName, String brandDescription, String userName);
	public int updateBrand(int brandId, String brandName, String brandDescription, String userName);
	public int deleteBrand(int id, String userName);
	public List<Brand> getListBrandInformation();
	public Brand getBrandById(int id);
}
