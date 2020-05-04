package me.themgrf.skooblock.island.members;

import me.themgrf.skooblock.Main;
import me.themgrf.skooblock.SkooBlock;
import me.themgrf.skooblock.island.roles.IslandMemberRole;

public class IslandMember {

    private final String island, uuid;
    private IslandMemberRole memberRole;

    public IslandMember(String island, String uuid) {
        this.island = island;
        this.uuid = uuid;
    }

    public String getIsland() { return island; }

    public String getUuid() {
        return uuid;
    }

    public IslandMemberRole getMemberRole() {
        for (IslandMember member : SkooBlock.getInstance().getIslandManager().getUserIslandFromUUID(getIsland()).getMembers()) {
            if (getUuid().equals(member.getUuid())) {
                return SkooBlock.getInstance().getIslandMemberRoleManager().getRoleById(Main.getInstance().getConfigManager().getUserConfig(getIsland()).getString("members." + getUuid()));
            }
        }

        return memberRole;
    }
}
