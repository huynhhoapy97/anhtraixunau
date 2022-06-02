package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.models.Brand;
import vn.com.anhtraixunau.repositories.BrandDAO;
import vn.com.anhtraixunau.repositories.BrandDAOImpl;


public class BrandService {
	private BrandDAO brandDAO;

	public int insertBrand(String brandName, String brandDescription, String userName) {
		int result = -1;
		brandDAO = new BrandDAOImpl();
		
		try {
			result = brandDAO.insertBrand(brandName, brandDescription, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateBrand(int brandId, String brandName, String brandDescription, String userName) {
		int result = -1;
		brandDAO = new BrandDAOImpl();
		
		try {
			result = brandDAO.updateBrand(brandId, brandName, brandDescription, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteBrandById(int id, String userName) {
		int result = -1;
		brandDAO = new BrandDAOImpl();
		
		try {
			result = brandDAO.deleteBrand(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Brand> getListBrandInformation() {
		List<Brand> listBrandInformation = new ArrayList<Brand>();
		brandDAO = new BrandDAOImpl();
		
		try {
			listBrandInformation = brandDAO.getListBrandInformation();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBrandInformation;
	}

	public Brand getBrandById(int id) {
		Brand brand = new Brand();
		brandDAO = new BrandDAOImpl();
		
		try {
			brand = brandDAO.getBrandById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return brand;
	}
}
