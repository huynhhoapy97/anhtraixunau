package vn.com.anhtraixunau.components;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		redirectStrategy = new DefaultRedirectStrategy();
		
		String url = "/staff/dashboard";
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		
		// Kiểm tra xem có Session user đăng nhập chưa?
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("username") == null) {
			// Chưa tồn tại Session user đăng nhập hoặc Hết thời gian tồn tại Session user đăng nhập, thì tạo lại
			httpSession.setAttribute("username", username);
			httpSession.setMaxInactiveInterval(60*60);
		}
		
		redirectStrategy.sendRedirect(request, response, url);
	}
}
