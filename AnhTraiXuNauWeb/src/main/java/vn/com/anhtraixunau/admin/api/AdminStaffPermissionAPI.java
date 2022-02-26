package vn.com.anhtraixunau.admin.api;

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

import vn.com.anhtraixunau.enums.StaffPermissionMessage;
import vn.com.anhtraixunau.models.StaffPermission;
import vn.com.anhtraixunau.services.StaffPermissionService;

@RestController
@RequestMapping("admin/api/staffPermission")
public class AdminStaffPermissionAPI {
	private StaffPermissionService staffPermissionService;
	
	@GetMapping("getListStaffPermissionExisting")
	public Map<String, Object> getListStaffPermissionExisting() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		
		try {
			staffPermissionService = new StaffPermissionService();
			listStaffPermission = staffPermissionService.getListStaffPermissionExisting();
			
			response.put("listStaffPermission", listStaffPermission);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GetMapping("getStaffPermissionById/{id}")
	public Map<String, String> getStaffPermissionById(@PathVariable("id") Integer id) {
		Map<String, String> response = new HashMap<String, String>();
		StaffPermission staffPermission = new StaffPermission();
		staffPermissionService = new StaffPermissionService();
		
		try {
			staffPermission = staffPermissionService.getStaffPermissionById(id);
			
			if (staffPermission != null && staffPermission.getId() != 0) {
				response.put("staffPermissionId", String.valueOf(staffPermission.getId()));
				response.put("staffPermissionName", staffPermission.getName());
				response.put("staffPermissionCreateUser", staffPermission.getCreateUser());
				response.put("staffPermissionCreateDate", String.valueOf(staffPermission.getCreateDate()));
			}
			else {
				response.put("message", "Không có thông tin phân quyền");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi lấy thông tin phân quyền");
		}
		
		return response;
	}
	
	@PostMapping("completeStaffPermissionEdit")
	public Map<String, Object> completeStaffPermissionEdit(@RequestBody StaffPermission staffPermission, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		staffPermissionService = new StaffPermissionService();
		
		try {
			int staffPermissionId = staffPermission.getId();
			String staffPermissionName = staffPermission.getName();
			String userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			if (staffPermissionId == StaffPermissionMessage.DOES_NOT_EXISTS_STAFFPERMISSIONID.getId()) {
				staffPermissionService.insertStaffPermission(staffPermissionName, userName);
			}
			else {
				staffPermissionService.updateStaffPermission(staffPermissionId, staffPermissionName, userName);
			}
			
			List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
			listStaffPermission = staffPermissionService.getListStaffPermissionExisting();
			
			response.put("staffPermissionId", staffPermission.getId());
			response.put("listStaffPermission", listStaffPermission);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi thêm/sửa thông tin phân quyền");
		}
		
		return response;
	}
	
	@PostMapping("deleteStaffPermissionById/{id}")
	public Map<String, Object> deleteStaffPermissionById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		staffPermissionService = new StaffPermissionService();
		
		try {
			String userName = (String) httpServletRequest.getSession().getAttribute("username");
			staffPermissionService.deleteStaffPermissionById(id, userName);
			
			List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
			listStaffPermission = staffPermissionService.getListStaffPermissionExisting();
			
			response.put("listStaffPermission", listStaffPermission);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi xóa thông tin phân quyền");
		}
		
		return response;
	}
}
