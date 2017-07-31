package com.agit.brooks2.user.management.domain.role;

import com.agit.brooks2.shared.type.StatusData;
import java.util.List;

/**
 *
 * @author bayutridewanto
 */
public interface RoleRepository {
    void saveOrUpdate(Role role);
    Role findByID(String roleID);
    List<Role> findAll();
    List<Role> findAllByStatus(StatusData statusData);
    List<Role> findByParameter(String roleName);
}
