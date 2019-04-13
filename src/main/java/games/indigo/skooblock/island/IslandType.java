package games.indigo.skooblock.island;

public enum IslandType {

    CLASSIC("classic"),
    DESERT("desert"),
    INDIGO("indigo"),
    SNOW("snow"),
    HELL("hell"),
    END("end"),
    MUSHROOM("mushroom"),
    SLIME("slime"),
    ORE("ore");

    private String value;

    IslandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
