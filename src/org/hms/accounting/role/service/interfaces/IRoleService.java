package org.hms.accounting.role.service.interfaces;

import java.util.List;

import org.hms.accounting.role.ROL001;
import org.hms.accounting.role.Role;

public interface IRoleService {
	public Role addNewRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Role role);

	public Role findRoleById(String id);

	public List<ROL001> findAllRole();

}
