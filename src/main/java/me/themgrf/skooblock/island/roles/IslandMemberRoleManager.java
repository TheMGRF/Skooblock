package me.themgrf.skooblock.island.roles;

import java.util.Arrays;
import java.util.List;

public class IslandMemberRoleManager {

    public IslandMemberRole getRoleById(String id) {
        List<IslandMemberRole> roles = Arrays.asList(new IslandMemberDefaultRole(), new IslandMemberModRole(), new IslandMemberAdminRole(), new IslandMemberOwnerRole());

        for (IslandMemberRole role : roles) {
            if (role.getIdentifier().equals(id)) {
                return role;
            }
        }

        return roles.get(0);
    }

}
