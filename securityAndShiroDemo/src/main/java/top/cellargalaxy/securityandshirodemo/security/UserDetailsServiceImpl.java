package top.cellargalaxy.securityandshirodemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.cellargalaxy.securityandshirodemo.model.SecurityPermission;
import top.cellargalaxy.securityandshirodemo.model.SecurityUser;
import top.cellargalaxy.securityandshirodemo.service.SecurityService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author cellargalaxy
 * @time 2018/7/30
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private final SecurityService securityService;

	public UserDetailsServiceImpl(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SecurityUser securityUser = securityService.getSecurityUser(s);
		if (securityUser == null) {
			String string = UUID.randomUUID().toString();
			return new UserDetailsImpl(string, "{noop}" + string, Collections.emptyList());
		}
		List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
		for (SecurityPermission securityPermission : securityUser.getSecurityPermissions()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(securityPermission.getPermission()));
		}
		return new UserDetailsImpl(securityUser.getUsername(), securityUser.getPassword(), grantedAuthorities);
	}
}
