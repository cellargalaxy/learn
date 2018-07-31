package top.cellargalaxy.securityandshirodemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author cellargalaxy
 * @time 2018/7/31
 */
public interface SecurityUser extends Serializable {
	String getUsername();

	String getPassword();

	List<SecurityPermission> getSecurityPermissions();
}
