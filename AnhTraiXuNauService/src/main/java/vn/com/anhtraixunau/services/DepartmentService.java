package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.models.Department;
import vn.com.anhtraixunau.models.StaffPermission;
import vn.com.anhtraixunau.repositories.DepartmentDAO;
import vn.com.anhtraixunau.repositories.DepartmentDAOImpl;

public class DepartmentService {
	private DepartmentDAO departmentDAO;
	
	public List<Department> getListDepartmentExisting(){
		List<Department> listDepartment = new ArrayList<Department>();
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			listDepartment = departmentDAO.getListDepartmentExisting();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDepartment;
	}
	
	public Department getDepartmentById(int id) {
		Department department = new Department();
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			department = departmentDAO.getDepartmentById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return department;
	}
	
	public List<Integer> getListDepartmentPermissionByDepartmentId(int departmentId){
		List<Integer> listDepartmentPermission = new ArrayList<Integer>();
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			listDepartmentPermission = departmentDAO.getListDepartmentPermissionByDepartmentId(departmentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDepartmentPermission;
	}
	
	public int insertDepartment(String name, String userName) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.insertDepartment(name, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateDepartment(int id, String name, String userName) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.updateDepartment(id, name, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertDepartmentPermission(int departmentId, int permissionId) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.insertDepartmentPermission(departmentId, permissionId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int removeDepartmentPermission(int departmentId, int permissionId) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.removeDepartmentPermission(departmentId, permissionId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteDepartmentById(int id, String userName) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.deleteDepartmentById(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteDepartmentPermissionByDepartmentId(int departmentId) {
		int result = -1;
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			result = departmentDAO.deleteDepartmentPermissionByDepartmentId(departmentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<StaffPermission> getListStaffPermissionByDepartmentId(int departmentId){
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		departmentDAO = new DepartmentDAOImpl();
		
		try {
			listStaffPermission = departmentDAO.getListStaffPermissionByDepartmentId(departmentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStaffPermission;
	}
}
