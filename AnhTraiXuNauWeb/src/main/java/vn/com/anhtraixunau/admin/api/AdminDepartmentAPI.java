package vn.com.anhtraixunau.admin.api;

import java.util.ArrayList;
import java.util.Arrays;
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

import vn.com.anhtraixunau.enums.DepartmentMessage;
import vn.com.anhtraixunau.models.Department;
import vn.com.anhtraixunau.models.StaffPermission;
import vn.com.anhtraixunau.services.DepartmentService;
import vn.com.anhtraixunau.services.StaffPermissionService;

@RestController
@RequestMapping("admin/api/department")
public class AdminDepartmentAPI {
	private DepartmentService departmentService;
	
	@GetMapping("getListDepartmentExisting")
	public Map<String, Object> getListDepartmentExisting() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Department> listDepartment = new ArrayList<Department>();
		
		try {
			departmentService = new DepartmentService();
			listDepartment = departmentService.getListDepartmentExisting();
			
			response.put("listDepartment", listDepartment);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GetMapping("getDepartmentById/{id}")
	public Map<String, String> getDepartmentById(@PathVariable("id") Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		Department department = new Department();
		List<Integer> listDepartmentPermission = new ArrayList<Integer>();
		StringBuilder strDepartmentPermission = new StringBuilder();
		departmentService = new DepartmentService();
		
		try {
			department = departmentService.getDepartmentById(id);
			
			if (department.getId() != 0) {
				response.put("departmentId", String.valueOf(department.getId()));
				response.put("departmentName", department.getName());
				response.put("departmentCreateUser", department.getCreateUser());
				response.put("departmentCreateDate", String.valueOf(department.getCreateDate()));
				
				listDepartmentPermission = departmentService.getListDepartmentPermissionByDepartmentId(department.getId());
				if (listDepartmentPermission.size() > 0) {
					strDepartmentPermission.append(listDepartmentPermission.get(0));
					
					for (int i = 1; i < listDepartmentPermission.size(); i++) {
						strDepartmentPermission.append(',');
						strDepartmentPermission.append(listDepartmentPermission.get(i));
					}
				}
				
				response.put("listDepartmentPermission", strDepartmentPermission.toString());
			}
			else {
				response.put("message", "Không có thông tin phòng ban");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi lấy thông tin phòng ban");
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("completeDepartmentEdit")
	public Map<String, Object> completeDepartmentEdit(@RequestBody Map<String, Object> dataRequest, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Department> listDepartment = new ArrayList<Department>();
		departmentService = new DepartmentService();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> department = new HashMap<String, Object>();
		int departmentId = -1;
		String departmentName = new String();
		String staffPermissionSelected = new String();
		String userName = new String();
		
		List<String> listStaffPermissionSelected = new ArrayList<String>();
		List<Integer> listDepartmentPermission = new ArrayList<Integer>();
		List<Integer> listStaffPermissionAdd = new ArrayList<Integer>();
		List<Integer> listStaffPermissionRemove = new ArrayList<Integer>();
		
		mapData = (HashMap<String, Object>) dataRequest.get("dataRequest");
		
		department = (HashMap<String, Object>) mapData.get("department");
		departmentId = Integer.valueOf(String.valueOf(department.get("id")));
		departmentName = (String) department.get("name");
		
		staffPermissionSelected = (String) mapData.get("staffPermissionSelected");
		listStaffPermissionSelected = Arrays.asList(staffPermissionSelected.split(","));
		
		try {
			userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			listDepartmentPermission = departmentService.getListDepartmentPermissionByDepartmentId(departmentId);
			
			if (listDepartmentPermission != null && listDepartmentPermission.size() > 0) {
				for (String permissionId : listStaffPermissionSelected) {
					if (!listDepartmentPermission.contains(Integer.valueOf(permissionId))) {
						listStaffPermissionAdd.add(Integer.valueOf(permissionId));
					}
				}
				
				for (int permissionId : listDepartmentPermission) {
					if (!listStaffPermissionSelected.contains(String.valueOf(permissionId))) {
						listStaffPermissionRemove.add(permissionId);
					}
				}
			}
			
			if (departmentId == DepartmentMessage.DOES_NOT_EXISTS_DEPARTMENTID.getId()) {
				departmentId = departmentService.insertDepartment(departmentName, userName);
				
				for (String permissionId : listStaffPermissionSelected) {
					departmentService.insertDepartmentPermission(departmentId, Integer.valueOf(permissionId));
				}
			}
			else {
				departmentService.updateDepartment(departmentId, departmentName, userName);
				
				for (int permissionId : listStaffPermissionAdd) {
					departmentService.insertDepartmentPermission(departmentId, permissionId);
				}
				
				for (int permissionId : listStaffPermissionRemove) {
					departmentService.removeDepartmentPermission(departmentId, permissionId);
				}
			}
			
			listDepartment = departmentService.getListDepartmentExisting();
			
			response.put("departmentId", departmentId);
			response.put("listDepartment", listDepartment);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi thêm/sửa thông tin phòng ban");
		}
		
		return response;
	}
	
	@PostMapping("deleteDepartmentById/{id}")
	public Map<String, Object> deleteDepartmentById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Department> listDepartment = new ArrayList<Department>();
		String userName = new String();
		departmentService = new DepartmentService();
		
		try {
			userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			departmentService.deleteDepartmentById(id, userName);
			departmentService.deleteDepartmentPermissionByDepartmentId(id);
			
			listDepartment = departmentService.getListDepartmentExisting();
			
			response.put("listDepartment", listDepartment);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi xóa thông tin phòng ban");
		}
		
		return response;
	}
}
