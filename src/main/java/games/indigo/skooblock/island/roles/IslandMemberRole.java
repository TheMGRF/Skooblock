package games.indigo.skooblock.island.roles;

import games.indigo.skooblock.island.permissions.IslandMemberPermissionSet;

public abstract class IslandMemberRole {

    private String identifier, name;
    private IslandMemberPermissionSet permissionSet;

    public IslandMemberRole(String identifier, String name, IslandMemberPermissionSet permissionSet) {
        this.identifier = identifier;
        this.name = name;
        this.permissionSet = permissionSet;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public IslandMemberPermissionSet getPermissionSet() { return permissionSet; }
}
