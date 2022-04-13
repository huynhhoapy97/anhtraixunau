package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.models.StaffPermission;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private StaffAccountService staffAccountService;
	private UserDetails userDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		staffAccountService = new StaffAccountService();
		List<StaffAccount> listStaffAccount = new ArrayList<StaffAccount>();
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String userName = "nousername";
		String password = "nopassword";
		
		try {
			listStaffAccount = staffAccountService.getListStaffAccountByUsername(username);
			
			if (listStaffAccount != null && listStaffAccount.size() > 0) {
				userName = listStaffAccount.get(0).getUsername();
				password = listStaffAccount.get(0).getPassword();
				
				listStaffPermission = listStaffAccount.get(0).getListStaffPermission();
				for (StaffPermission staffPermission : listStaffPermission) {
					String permissionName = staffPermission.getName();
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionName);
					
					grantedAuthorities.add(grantedAuthority);
				}
			}
			
			userDetails = new User(userName, password, grantedAuthorities);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return userDetails;
	}

}
