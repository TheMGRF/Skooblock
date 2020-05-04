package me.themgrf.skooblock.island.permissions;

public class IslandMemberPermissionSet {

    private final boolean ALLOW_BUILDING;
    private final boolean ALLOW_INTERACTIONS;
    private final boolean ALLOW_CONTAINERS;
    private final boolean ALLOW_MOB_INTERACTIONS;
    private final boolean ALLOW_USE_VEHICLES;
    private final boolean ALLOW_ITEM_PICKUP;
    private final boolean ALLOW_ITEM_DROPPING;
    private final boolean ALLOW_EDIT_RANKS;
    private final boolean ALLOW_PROMOTING;
    private final boolean ALLOW_DEMOTING;
    private final boolean ALLOW_KICKING;
    private final boolean ALLOW_BANNING;
    private final boolean ALLOW_INVITING;

    public IslandMemberPermissionSet(boolean ALLOW_BUILDING,
                                     boolean ALLOW_INTERACTIONS,
                                     boolean ALLOW_CONTAINERS,
                                     boolean ALLOW_MOB_INTERACTIONS,
                                     boolean ALLOW_USE_VEHICLES,
                                     boolean ALLOW_ITEM_PICKUP,
                                     boolean ALLOW_ITEM_DROPPING,
                                     boolean ALLOW_EDIT_RANKS,
                                     boolean ALLOW_PROMOTING,
                                     boolean ALLOW_DEMOTING,
                                     boolean ALLOW_KICKING,
                                     boolean ALLOW_BANNING,
                                     boolean ALLOW_INVITING) {
        this.ALLOW_BUILDING = ALLOW_BUILDING;
        this.ALLOW_INTERACTIONS = ALLOW_INTERACTIONS;
        this.ALLOW_CONTAINERS = ALLOW_CONTAINERS;
        this.ALLOW_MOB_INTERACTIONS = ALLOW_MOB_INTERACTIONS;
        this.ALLOW_USE_VEHICLES = ALLOW_USE_VEHICLES;
        this.ALLOW_ITEM_PICKUP = ALLOW_ITEM_PICKUP;
        this.ALLOW_ITEM_DROPPING = ALLOW_ITEM_DROPPING;
        this.ALLOW_EDIT_RANKS = ALLOW_EDIT_RANKS;
        this.ALLOW_PROMOTING = ALLOW_PROMOTING;
        this.ALLOW_DEMOTING = ALLOW_DEMOTING;
        this.ALLOW_KICKING = ALLOW_KICKING;
        this.ALLOW_BANNING = ALLOW_BANNING;
        this.ALLOW_INVITING = ALLOW_INVITING;
    }

    public boolean canBuild() {
        return ALLOW_BUILDING;
    }

    public boolean canInteract() {
        return ALLOW_INTERACTIONS;
    }

    public boolean canOpenContainers() {
        return ALLOW_CONTAINERS;
    }

    public boolean canInteractWithMobs() {
        return ALLOW_MOB_INTERACTIONS;
    }

    public boolean canUseVehicles() {
        return ALLOW_USE_VEHICLES;
    }

    public boolean canPickUpItems() {
        return ALLOW_ITEM_PICKUP;
    }

    public boolean canDropItems() {
        return ALLOW_ITEM_DROPPING;
    }

    public boolean canEditRanks() {
        return ALLOW_EDIT_RANKS;
    }

    public boolean canPromote() {
        return ALLOW_PROMOTING;
    }

    public boolean canDemote() {
        return ALLOW_DEMOTING;
    }

    public boolean canKick() {
        return ALLOW_KICKING;
    }

    public boolean canBan() {
        return ALLOW_BANNING;
    }

    public boolean canInvite() {
        return ALLOW_INVITING;
    }
}
