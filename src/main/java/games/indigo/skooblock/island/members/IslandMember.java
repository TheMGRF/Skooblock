package games.indigo.skooblock.island.members;

import games.indigo.skooblock.island.UserIsland;

public class IslandMember {

    private UserIsland userIsland;
    private String uuid;
    private IslandMemberRole memberRole;

    public IslandMember(UserIsland userIsland, String uuid, IslandMemberRole memberRole) {
        this.userIsland = userIsland;
        this.uuid = uuid;
        this.memberRole = memberRole;
    }

    public UserIsland getUserIsland() {
        return userIsland;
    }

    public String getUuid() {
        return uuid;
    }

    public IslandMemberRole getMemberRole() {
        return memberRole;
    }
}
