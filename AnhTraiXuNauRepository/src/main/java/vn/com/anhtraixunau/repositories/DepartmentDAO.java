package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.Department;

public interface DepartmentDAO {
	public List<Department> getListDepartmentExisting();
	public Department getDepartmentById(int id);
	public List<Integer> getListDepartmentPermissionByDepartmentId(int departmentId);
	public int insertDepartment(String name, String userName);
	public int updateDepartment(int id, String name, String userName);
	public int insertDepartmentPermission(int departmentId, int permissionId);
	public int removeDepartmentPermission(int departmentId, int permissionId);
	public int deleteDepartmentById(int id, String userName);
	public int deleteDepartmentPermissionByDepartmentId(int departmentId);
}
