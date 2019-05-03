package games.indigo.skooblock.island.roles;

import games.indigo.skooblock.island.permissions.IslandMemberPermissionSet;

import java.util.Arrays;

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
