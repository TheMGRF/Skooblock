package games.indigo.skooblock;

import games.indigo.skooblock.commands.IslandCmd;
import games.indigo.skooblock.guis.MainMenu;
import games.indigo.skooblock.listeners.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    private MainMenu mainMenu;
    private Utils utils;

    public void onEnable() {
        instance = this;

        // Classes
        mainMenu = new MainMenu();
        utils = new Utils();

        // Commands
        getCommand("island").setExecutor(new IslandCmd());

        // Listeners
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);

        //Material[] testMats = {Material.STONE, Material.GRASS_BLOCK, Material.COBBLESTONE, Material.OAK_LOG};
        //new StructureManager().addStructure("test", testMats, "", "", "", "Test", "", "desc", Arrays.asList("1", "2"), 1.0);
    }

    public static Main getInstance() {
        return instance;
    }

    public MainMenu getMainMenu() { return mainMenu; }
    public Utils getUtils() { return utils; }
}
