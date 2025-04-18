package me.samulsz;

import jdk.nashorn.internal.objects.annotations.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.samulsz.api.PlantationsAPI;
import me.samulsz.cache.UserCache;
import me.samulsz.commands.ToolCommand;
import me.samulsz.connections.MysqlConnection;
import me.samulsz.listeners.BlockBreakListener;
import me.samulsz.listeners.PlayerLoginListener;
import me.samulsz.managers.FileManager;
import me.samulsz.managers.ToolManager;
import me.samulsz.objects.LoadObjects;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FlowPlantacoes extends JavaPlugin {
    private PlantationsAPI plantationsAPI;
    private FileManager fileManager;
    private LoadObjects loadObjects;
    private MysqlConnection mysqlConnection;
    private ToolManager toolManager;

    public void onEnable() {
        plantationsAPI = new PlantationsAPI(this); //loading the mysql here
        fileManager = plantationsAPI.getFileManager();
        loadObjects = plantationsAPI.getLoadObjects();
        mysqlConnection = plantationsAPI.getMysqlConnection();
        toolManager = plantationsAPI.getToolManager();
        setup();
    }

    public void onDisable() {
        plantationsAPI.getUserCache().saveAll();
    }

    private void setup(){
        //loading files
        saveDefaultConfig();
        fileManager.createConfig("plants");
        fileManager.createConfig("enchants");
        //loading objects
        loadObjects.load();
        toolManager.buildTool();
        //registering events
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(plantationsAPI), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(plantationsAPI), this);
        //registering commands
        BukkitFrame frame = new BukkitFrame(this);
        frame.registerCommands(new ToolCommand(plantationsAPI));
    }
}