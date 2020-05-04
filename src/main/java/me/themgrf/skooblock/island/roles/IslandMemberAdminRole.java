package me.themgrf.skooblock.island.roles;

import me.themgrf.skooblock.island.permissions.IslandMemberPermissionSet;

public class IslandMemberAdminRole extends IslandMemberRole {

    public IslandMemberAdminRole() {
        super("admin", "Admin", new IslandMemberPermissionSet(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                true,
                true));
    }

}
