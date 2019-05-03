package games.indigo.skooblock.island.roles;

import games.indigo.skooblock.island.permissions.IslandMemberPermissionSet;

public class IslandMemberDefaultRole extends IslandMemberRole {

    public IslandMemberDefaultRole() {
        super("default", "Member", new IslandMemberPermissionSet(
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
                false,
                false,
                false));
    }

}
