package games.indigo.skooblock.island.roles;

import games.indigo.skooblock.island.permissions.IslandMemberPermissionSet;

import java.util.Arrays;

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
