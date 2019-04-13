package games.indigo.skooblock.island;

import games.indigo.skooblock.Main;

import java.io.IOException;
import java.util.List;

public class UserIsland {

    // TODO: island centre pos, max upper and lower bounds, initial bounds (for expansion + border)

    // TODO: Owner, Members, Biome, Home location, Warp location, settings, level

    private String owner, biome;
    private List<String> members;
    private String centre, lowerBound, upperBound, home, warp;
    private List<Boolean> settings;
    private int size, level;

    public UserIsland(String owner,
                      List<String> members,
                      String centre,
                      String lowerBound,
                      String upperBound,
                      String biome,
                      String home,
                      String warp,
                      List<Boolean> settings,
                      int size,
                      int level) {
        this.owner = owner;
        this.members = members;
        this.centre = centre;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.biome = biome;
        this.home = home;
        this.warp = warp;
        this.settings = settings;
        this.size = size;
        this.level = level;

        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".owner", owner);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".members", members);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".centre", centre);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".lowerBound", lowerBound);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".upperBound", upperBound);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".biome", biome);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".home", home);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".warp", warp);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".settings", settings);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".size", size);
        Main.getInstance().getConfigManager().userIslandsConfig.set("user-islands." + owner + ".level", level);
        try {
            Main.getInstance().getConfigManager().userIslandsConfig.save(Main.getInstance().getConfigManager().userIslandsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
