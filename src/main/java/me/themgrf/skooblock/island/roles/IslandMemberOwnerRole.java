package me.themgrf.skooblock.island.roles;

import me.themgrf.skooblock.island.permissions.IslandMemberPermissionSet;

public class IslandMemberOwnerRole extends IslandMemberRole {

    public IslandMemberOwnerRole() {
        super("owner", "Owner", new IslandMemberPermissionSet(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true));
    }

}
