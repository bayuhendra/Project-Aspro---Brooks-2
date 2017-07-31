package com.agit.brooks2.user.management.domain.role;

import com.agit.brooks2.shared.type.AccessType;
import com.agit.brooks2.user.management.domain.privilege.Privilege;


public class RolePrivilegeBuilder {
    private Privilege privilege;
    private AccessType accessType;

    public RolePrivilegeBuilder() {
    }

    public RolePrivilegeBuilder setPrivilege(Privilege privilege) {
        this.privilege = privilege;
        return this;
    }

    public RolePrivilegeBuilder setAccessType(AccessType accessType) {
        this.accessType = accessType;
        return this;
    }

    public RolePrivilege createRolePrivilege() {
        return new RolePrivilege(privilege, accessType);
    }

}
