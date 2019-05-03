package games.indigo.skooblock.island.roles;

import games.indigo.skooblock.island.permissions.IslandMemberPermissionSet;

import java.util.Arrays;

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
