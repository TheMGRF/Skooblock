package games.indigo.skooblock.island.members;

public class IslandMemberRole {

    private String identifier, name;
    //private IslandPermission islandPermission;
    // TODO: Permission manager?

    public IslandMemberRole(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}
