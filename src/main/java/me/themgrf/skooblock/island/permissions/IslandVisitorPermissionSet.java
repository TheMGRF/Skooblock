package me.themgrf.skooblock.island.permissions;

public class IslandVisitorPermissionSet {

    private final boolean ALLOW_BLOCK_PLACING;
    private final boolean ALLOW_BLOCK_BREAKING;
    private final boolean ALLOW_CONTAINER_ACCESS;
    private final boolean ALLOW_WORKBENCH_ACCESS;
    private final boolean ALLOW_FURNACE_ACCESS;
    private final boolean ALLOW_ENCHANTING;
    private final boolean ALLOW_ANVIL_USE;
    private final boolean ALLOW_ARMOUR_STAND_USE;
    private final boolean ALLOW_BEACON_USE;
    private final boolean ALLOW_JUKEBOX_USE;
    private final boolean ALLOW_BED_USE;
    private final boolean ALLOW_POTION_BREWING;
    private final boolean ALLOW_ANIMAL_BREADING;
    private final boolean ALLOW_BUCKET_USE;
    private final boolean ALLOW_DOOR_USE;
    private final boolean ALLOW_GATE_USE;
    private final boolean ALLOW_CROP_TRAMPLE;
    private final boolean ALLOW_THROWING_EGGS;
    private final boolean ALLOW_THROWING_EPEARLS;
    private final boolean ALLOW_VEHICLE_USE;
    private final boolean ALLOW_HURT_PEACEFUL;
    private final boolean ALLOW_HURT_HOSTILE;
    private final boolean ALLOW_LEASHING;
    private final boolean ALLOW_BUTTON_USE;
    private final boolean ALLOW_LEVER_USE;
    private final boolean ALLOW_PRESSUREPLATE_USE;
    private final boolean ALLOW_PVP;
    private final boolean ALLOW_COW_MILKING;
    private final boolean ALLOW_SHEEP_SHEARING;
    private final boolean ALLOW_VILLAGER_TRADING;
    private final boolean ALLOW_ITEM_DROPPING;
    private final boolean ALLOW_ITEM_PICKUP;
    private final boolean ALLOW_KEEP_EXP_ON_DEATH;
    private final boolean ALLOW_KEEP_ITEMS_ON_DEATH;

    public IslandVisitorPermissionSet(boolean ALLOW_BLOCK_PLACING,
                                      boolean ALLOW_BLOCK_BREAKING,
                                      boolean ALLOW_CONTAINER_ACCESS,
                                      boolean ALLOW_WORKBENCH_ACCESS,
                                      boolean ALLOW_FURNACE_ACCESS,
                                      boolean ALLOW_ENCHANTING,
                                      boolean ALLOW_ANVIL_USE,
                                      boolean ALLOW_ARMOUR_STAND_USE,
                                      boolean ALLOW_BEACON_USE,
                                      boolean ALLOW_JUKEBOX_USE,
                                      boolean ALLOW_BED_USE,
                                      boolean ALLOW_POTION_BREWING,
                                      boolean ALLOW_ANIMAL_BREADING,
                                      boolean ALLOW_BUCKET_USE,
                                      boolean ALLOW_DOOR_USE,
                                      boolean ALLOW_GATE_USE,
                                      boolean ALLOW_CROP_TRAMPLE,
                                      boolean ALLOW_THROWING_EGGS,
                                      boolean ALLOW_THROWING_EPEARLS,
                                      boolean ALLOW_VEHICLE_USE,
                                      boolean ALLOW_HURT_PEACEFUL,
                                      boolean ALLOW_HURT_HOSTILE,
                                      boolean ALLOW_LEASHING,
                                      boolean ALLOW_BUTTON_USE,
                                      boolean ALLOW_LEVER_USE,
                                      boolean ALLOW_PRESSUREPLATE_USE,
                                      boolean ALLOW_PVP,
                                      boolean ALLOW_COW_MILKING,
                                      boolean ALLOW_SHEEP_SHEARING,
                                      boolean ALLOW_VILLAGER_TRADING,
                                      boolean ALLOW_ITEM_DROPPING,
                                      boolean ALLOW_ITEM_PICKUP,
                                      boolean ALLOW_KEEP_EXP_ON_DEATH,
                                      boolean ALLOW_KEEP_ITEMS_ON_DEATH) {
        this.ALLOW_BLOCK_PLACING = ALLOW_BLOCK_PLACING;
        this.ALLOW_BLOCK_BREAKING = ALLOW_BLOCK_BREAKING;
        this.ALLOW_CONTAINER_ACCESS = ALLOW_CONTAINER_ACCESS;
        this.ALLOW_WORKBENCH_ACCESS = ALLOW_WORKBENCH_ACCESS;
        this.ALLOW_FURNACE_ACCESS = ALLOW_FURNACE_ACCESS;
        this.ALLOW_ENCHANTING = ALLOW_ENCHANTING;
        this.ALLOW_ANVIL_USE = ALLOW_ANVIL_USE;
        this.ALLOW_ARMOUR_STAND_USE = ALLOW_ARMOUR_STAND_USE;
        this.ALLOW_BEACON_USE = ALLOW_BEACON_USE;
        this.ALLOW_JUKEBOX_USE = ALLOW_JUKEBOX_USE;
        this.ALLOW_BED_USE = ALLOW_BED_USE;
        this.ALLOW_POTION_BREWING = ALLOW_POTION_BREWING;
        this.ALLOW_ANIMAL_BREADING = ALLOW_ANIMAL_BREADING;
        this.ALLOW_BUCKET_USE = ALLOW_BUCKET_USE;
        this.ALLOW_DOOR_USE = ALLOW_DOOR_USE;
        this.ALLOW_GATE_USE = ALLOW_GATE_USE;
        this.ALLOW_CROP_TRAMPLE = ALLOW_CROP_TRAMPLE;
        this.ALLOW_THROWING_EGGS = ALLOW_THROWING_EGGS;
        this.ALLOW_THROWING_EPEARLS = ALLOW_THROWING_EPEARLS;
        this.ALLOW_VEHICLE_USE = ALLOW_VEHICLE_USE;
        this.ALLOW_HURT_PEACEFUL = ALLOW_HURT_PEACEFUL;
        this.ALLOW_HURT_HOSTILE = ALLOW_HURT_HOSTILE;
        this.ALLOW_LEASHING = ALLOW_LEASHING;
        this.ALLOW_BUTTON_USE = ALLOW_BUTTON_USE;
        this.ALLOW_LEVER_USE = ALLOW_LEVER_USE;
        this.ALLOW_PRESSUREPLATE_USE = ALLOW_PRESSUREPLATE_USE;
        this.ALLOW_PVP = ALLOW_PVP;
        this.ALLOW_COW_MILKING = ALLOW_COW_MILKING;
        this.ALLOW_SHEEP_SHEARING = ALLOW_SHEEP_SHEARING;
        this.ALLOW_VILLAGER_TRADING = ALLOW_VILLAGER_TRADING;
        this.ALLOW_ITEM_DROPPING = ALLOW_ITEM_DROPPING;
        this.ALLOW_ITEM_PICKUP = ALLOW_ITEM_PICKUP;
        this.ALLOW_KEEP_EXP_ON_DEATH = ALLOW_KEEP_EXP_ON_DEATH;
        this.ALLOW_KEEP_ITEMS_ON_DEATH = ALLOW_KEEP_ITEMS_ON_DEATH;
    }

    public boolean isALLOW_BLOCK_PLACING() {
        return ALLOW_BLOCK_PLACING;
    }

    public boolean isALLOW_BLOCK_BREAKING() {
        return ALLOW_BLOCK_BREAKING;
    }

    public boolean isALLOW_CONTAINER_ACCESS() {
        return ALLOW_CONTAINER_ACCESS;
    }

    public boolean isALLOW_WORKBENCH_ACCESS() {
        return ALLOW_WORKBENCH_ACCESS;
    }

    public boolean isALLOW_FURNACE_ACCESS() {
        return ALLOW_FURNACE_ACCESS;
    }

    public boolean isALLOW_ENCHANTING() {
        return ALLOW_ENCHANTING;
    }

    public boolean isALLOW_ANVIL_USE() {
        return ALLOW_ANVIL_USE;
    }

    public boolean isALLOW_ARMOUR_STAND_USE() {
        return ALLOW_ARMOUR_STAND_USE;
    }

    public boolean isALLOW_BEACON_USE() {
        return ALLOW_BEACON_USE;
    }

    public boolean isALLOW_JUKEBOX_USE() {
        return ALLOW_JUKEBOX_USE;
    }

    public boolean isALLOW_BED_USE() {
        return ALLOW_BED_USE;
    }

    public boolean isALLOW_POTION_BREWING() {
        return ALLOW_POTION_BREWING;
    }

    public boolean isALLOW_ANIMAL_BREADING() {
        return ALLOW_ANIMAL_BREADING;
    }

    public boolean isALLOW_BUCKET_USE() {
        return ALLOW_BUCKET_USE;
    }

    public boolean isALLOW_DOOR_USE() {
        return ALLOW_DOOR_USE;
    }

    public boolean isALLOW_GATE_USE() {
        return ALLOW_GATE_USE;
    }

    public boolean isALLOW_CROP_TRAMPLE() {
        return ALLOW_CROP_TRAMPLE;
    }

    public boolean isALLOW_THROWING_EGGS() {
        return ALLOW_THROWING_EGGS;
    }

    public boolean isALLOW_THROWING_EPEARLS() {
        return ALLOW_THROWING_EPEARLS;
    }

    public boolean isALLOW_VEHICLE_USE() {
        return ALLOW_VEHICLE_USE;
    }

    public boolean isALLOW_HURT_PEACEFUL() {
        return ALLOW_HURT_PEACEFUL;
    }

    public boolean isALLOW_HURT_HOSTILE() {
        return ALLOW_HURT_HOSTILE;
    }

    public boolean isALLOW_LEASHING() {
        return ALLOW_LEASHING;
    }

    public boolean isALLOW_BUTTON_USE() {
        return ALLOW_BUTTON_USE;
    }

    public boolean isALLOW_LEVER_USE() {
        return ALLOW_LEVER_USE;
    }

    public boolean isALLOW_PRESSUREPLATE_USE() {
        return ALLOW_PRESSUREPLATE_USE;
    }

    public boolean isALLOW_PVP() {
        return ALLOW_PVP;
    }

    public boolean isALLOW_COW_MILKING() {
        return ALLOW_COW_MILKING;
    }

    public boolean isALLOW_SHEEP_SHEARING() {
        return ALLOW_SHEEP_SHEARING;
    }

    public boolean isALLOW_VILLAGER_TRADING() {
        return ALLOW_VILLAGER_TRADING;
    }

    public boolean isALLOW_ITEM_DROPPING() {
        return ALLOW_ITEM_DROPPING;
    }

    public boolean isALLOW_ITEM_PICKUP() {
        return ALLOW_ITEM_PICKUP;
    }

    public boolean isALLOW_KEEP_EXP_ON_DEATH() {
        return ALLOW_KEEP_EXP_ON_DEATH;
    }

    public boolean isALLOW_KEEP_ITEMS_ON_DEATH() {
        return ALLOW_KEEP_ITEMS_ON_DEATH;
    }
}
