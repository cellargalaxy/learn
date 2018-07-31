package top.cellargalaxy.securityandshirodemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import top.cellargalaxy.securityandshirodemo.model.SecurityPermission;
import top.cellargalaxy.securityandshirodemo.model.SecurityUser;
import top.cellargalaxy.securityandshirodemo.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author cellargalaxy
 * @time 2018/7/30
 */
public class AuthenticationFilter extends GenericFilterBean {
	private final SecurityService securityService;
	private final ObjectMapper objectMapper;

	public AuthenticationFilter(SecurityService securityService) {
		this.securityService = securityService;
		objectMapper = new ObjectMapper();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		String token = httpServletRequest.getHeader(LoginFilter.TOKEN_KEY);
		if (token == null) {
			Cookie[] cookies = httpServletRequest.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(LoginFilter.TOKEN_KEY)) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}
		if (token == null) {
			token = httpServletRequest.getParameter(LoginFilter.TOKEN_KEY);
		}

		System.out.println("检验token: " + token);

		SecurityUser securityUser = securityService.checkToken(token);
		if (securityUser != null) {
			List<GrantedAuthority> authorities = new LinkedList<>();
			for (SecurityPermission securityPermission : securityUser.getSecurityPermissions()) {
				authorities.add(new SimpleGrantedAuthority(securityPermission.getPermission()));
			}
			Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser.getUsername(), null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			System.out.println("过检token: " + authentication);
		} else {
			Map<String, Object> vo = new HashMap<>();
			vo.put("status", 0);
			vo.put("massage", "空token或者非法token");
			vo.put("data", null);
			httpServletResponse.setCharacterEncoding("utf-8");
			httpServletResponse.setContentType("application/json");
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.getWriter().write(objectMapper.writeValueAsString(vo));
			System.out.println("空token或者非法token");
		}
	}
}
