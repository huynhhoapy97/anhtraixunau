package vn.com.anhtraixunau.admin.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.anhtraixunau.enums.StaffMessage;
import vn.com.anhtraixunau.models.Staff;
import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.services.StaffAccountService;
import vn.com.anhtraixunau.services.StaffService;

@RestController
@RequestMapping("admin/api/staff")
public class AdminStaffAPI {
	private StaffService staffService;
	private StaffAccountService staffAccountService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("getListStaffInformation")
	public Map<String, Object> getListStaffInformation(){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Staff> listStaffInformation = new ArrayList<Staff>();
		staffService = new StaffService();
		
		try {
			listStaffInformation = staffService.getListStaffInformation();
			
			response.put("listStaffInformation", listStaffInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("completeStaffEdit")
	public Map<String, Object> completeStaffEdit(@RequestBody Map<String, Object> dataRequest, HttpServletRequest httpServletRequest){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Staff> listStaffInformation = new ArrayList<Staff>();
		staffService = new StaffService();
		staffAccountService = new StaffAccountService();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> staff = new HashMap<String, Object>();
		Map<String, Object> staffAccount = new HashMap<String, Object>();
		Map<String, Object> department = new HashMap<String, Object>();
		
		int staffId = -1;
		String lastName = new String();
		String firstName = new String();
		String accountUsername = new String();
		String accountEmail = new String();
		String permissionId = new String();
		String userName = new String();
		int departmentId = -1;
		int staffAccountId = -1;
		Date dateStart = new Date();
		java.sql.Date sqlDateStart = null;
		
		List<String> listPermissionId = new ArrayList<String>();
		List<Integer> listStaffAccountPermission = new ArrayList<Integer>();
		List<Integer> listStaffAccountPermissionAdd = new ArrayList<Integer>();
		List<Integer> listStaffAccountPermissionRemove = new ArrayList<Integer>();
		
		mapData = (HashMap<String, Object>) dataRequest.get("dataRequest");
		
		staff = (HashMap<String, Object>) mapData.get("staff");
		staffId = (int) staff.get("staffId");
		lastName = (String) staff.get("lastName");
		firstName = (String) staff.get("firstName");
		//dateStart = (Date) staff.get("dateStart");
		
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd").parse((String) staff.get("dateStart"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		sqlDateStart = new java.sql.Date(dateStart.getTime());
		
		staffAccount = (HashMap<String, Object>) mapData.get("staffAccount");
		accountUsername = (String) staffAccount.get("username");
		accountEmail = (String) staffAccount.get("email");
		
		department = (HashMap<String, Object>) mapData.get("department");
		departmentId = Integer.valueOf(String.valueOf(department.get("id")));
		
		permissionId = (String) mapData.get("permissionId");
		listPermissionId = Arrays.asList(permissionId.split(","));
		
		userName = (String) httpServletRequest.getSession().getAttribute("username");
		
		try {
			listStaffAccountPermission = staffAccountService.getListStaffAccountPermissionByStaffId(staffId);
			
			if (listStaffAccountPermission != null && listStaffAccountPermission.size() > 0) {
				for (String staffAccountPermissionId : listPermissionId) {
					if (!listStaffAccountPermission.contains(Integer.valueOf(staffAccountPermissionId))) {
						listStaffAccountPermissionAdd.add(Integer.valueOf(staffAccountPermissionId));
					}
				}
				
				for (int staffAccountPermissionId : listStaffAccountPermission) {
					if (!listPermissionId.contains(String.valueOf(staffAccountPermissionId))) {
						listStaffAccountPermissionRemove.add(staffAccountPermissionId);
					}
				}
			}
			
			if (staffId == StaffMessage.DOES_NOT_EXISTS_STAFFID.getId()) {
				staffId = staffService.insertStaff(lastName, firstName, departmentId, sqlDateStart, userName);
				
				staffAccountId = staffAccountService.insertStaffAccount(accountUsername, accountEmail, staffId, userName);
				
				for (String staffAccountPermissionId : listPermissionId) {
					staffAccountService.insertStaffAccountPermission(staffAccountId, Integer.valueOf(staffAccountPermissionId));
				}
			}
			else {
				staffService.updateStaff(staffId, lastName, firstName, departmentId, sqlDateStart, userName);
				
				staffAccountService.updateStaffAccount(accountUsername, accountEmail, userName);
				
				for (int staffAccountPermissionId : listStaffAccountPermissionAdd) {
					staffAccountService.insertStaffAccountPermission(staffAccountId, staffAccountPermissionId);
				}
				
				for (int staffAccountPermissionId : listStaffAccountPermissionRemove) {
					staffAccountService.removeStaffAccountPermission(staffAccountId, staffAccountPermissionId);
				}
			}
			
			listStaffInformation = staffService.getListStaffInformation();
			
			response.put("listStaffInformation", listStaffInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi thêm/sửa thông tin nhân viên");
		}
		
		return response;
	}
	
	@PostMapping("deleteStaffById/{staffId}")
	public Map<String, Object> deleteStaffById(@PathVariable("staffId") Integer staffId, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Staff> listStaffInformation = new ArrayList<Staff>();
		String userName = new String();
		StaffAccount staffAccount = new StaffAccount();
		staffService = new StaffService();
		staffAccountService = new StaffAccountService();
		
		try {
			userName = (String) httpServletRequest.getSession().getAttribute("username");
			
			staffService.deleteStaffById(staffId, userName);
			
			staffAccount = staffAccountService.getStaffAccountByStaffId(staffId);
			staffAccountService.deleteStaffAccountById(staffAccount.getId(), userName);
			staffAccountService.deleteStaffAccountPermissionByStaffAccountId(staffAccount.getId());
			
			listStaffInformation = staffService.getListStaffInformation();
			
			response.put("listStaffInformation", listStaffInformation);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			response.put("message", "Lỗi xóa thông tin nhân viên");
		}
		
		return response;
	}
	
	@GetMapping("getListPermissionIdByStaffAccountId/{staffAccountId}")
	public Map<String, Object> getListPermissionIdByStaffAccountId(@PathVariable("staffAccountId") Integer staffAccountId) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Integer> listPermissionId = new ArrayList<Integer>();
		StringBuilder strPermissionId = new StringBuilder();
		staffAccountService = new StaffAccountService();
		
		try {
			listPermissionId = staffAccountService.getListPermissionIdByStaffAccountId(staffAccountId);
			if (listPermissionId.size() > 0) {
				strPermissionId.append(listPermissionId.get(0));
				
				for (int i = 1; i < listPermissionId.size(); i++) {
					strPermissionId.append(',');
					strPermissionId.append(listPermissionId.get(i));
				}
			}
			
			response.put("listPermissionId", strPermissionId.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@PostMapping("sendMailActiveAccount")
	public Map<String, Object> sendMailActiveAccount(@RequestBody StaffAccount staffAccount, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashMap<String, Object>();
		int staffAccountId = -1;
		String staffAccountUsername = "";
		String staffAccountEmail = "";
		String staffAccountToken = "";
		String mailFrom = "";
		String contentText = "";
		String queryString = "";
		String href = "";
		staffAccountService = new StaffAccountService();
		
		try {
			staffAccountId = staffAccount.getId();
			staffAccountUsername = staffAccount.getUsername();
			staffAccountEmail = staffAccount.getEmail();
			staffAccountToken = UUID.randomUUID().toString();
			mailFrom = "javamailsender97@gmail.com";
			queryString = "/staffpassword/changepassword?staffaccountid=" + staffAccountId 
					+ "&staffaccountusername=" + staffAccountUsername
					+ "&staffaccounttoken=" + staffAccountToken;
			href = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + 
					  httpServletRequest.getServerPort() + queryString;
			contentText = "<a href='" + href + "'>Click link</a>";
			
			int result = staffAccountService.insertStaffAccountTokenVerify(staffAccountId, staffAccountToken);
			if (result != -1) {
				// Gửi mail
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
				
				mimeMessageHelper.setFrom(mailFrom, "AnhTraiXuNau");
				mimeMessageHelper.setReplyTo(mailFrom, "AnhTraiXuNau");
				mimeMessageHelper.setTo(staffAccountEmail);
				mimeMessageHelper.setSubject("Active staff account");
				
				mimeMessage.setContent(contentText, "text/html");
				
				mailSender.send(mimeMessage);
			}
			
			response.put("message", "");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			staffAccountService.insertStaffAccountTokenVerify(staffAccountId, "");
			response.put("message", "Lỗi gửi email kích hoạt tài khoản");
		}
		
		return response;
	}
}
