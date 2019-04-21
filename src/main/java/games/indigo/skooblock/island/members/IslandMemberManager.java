package games.indigo.skooblock.island.members;

import games.indigo.skooblock.island.UserIsland;

public class IslandMemberManager {

    public IslandMemberRole getMemberRole(UserIsland userIsland, String uuid) {
        return new IslandMemberRole(userIsland, uuid, "");
    }

    public void addIslandMember(UserIsland userIsland, String uuid) {
        userIsland.getMembers().add(uuid);
    }

}
