package me.themgrf.skooblock.island.roles;

import me.themgrf.skooblock.island.permissions.IslandMemberPermissionSet;

public class IslandMemberModRole extends IslandMemberRole {

    public IslandMemberModRole() {
        super("mod", "Mod", new IslandMemberPermissionSet(
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                true,
                false,
                true));
    }

}
