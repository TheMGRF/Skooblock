package me.themgrf.skooblock.island.roles;

import me.themgrf.skooblock.island.permissions.IslandMemberPermissionSet;

public abstract class IslandMemberRole {

    private final String identifier, name;
    private final IslandMemberPermissionSet permissionSet;

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
