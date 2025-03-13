package me.samulsz;

import jdk.nashorn.internal.objects.annotations.Getter;
import me.samulsz.api.PlantationsAPI;
import me.samulsz.cache.UserCache;
import me.samulsz.connections.MysqlConnection;
import me.samulsz.managers.FileManager;
import me.samulsz.objects.LoadObjects;
import org.bukkit.plugin.java.JavaPlugin;

public class FlowPlantacoes extends JavaPlugin {
    private PlantationsAPI plantationsAPI;
    private FileManager fileManager;
    private LoadObjects loadObjects;
    private MysqlConnection mysqlConnection;

    public void onEnable() {
        setup();
        plantationsAPI = new PlantationsAPI(this); //loading the mysql here
        fileManager = plantationsAPI.getFileManager();
        loadObjects = plantationsAPI.getLoadObjects();
        mysqlConnection = plantationsAPI.getMysqlConnection();
    }

    public void onDisable() {
    }

    private void setup(){
        saveDefaultConfig();
        fileManager.createConfig("plants.yml");
        fileManager.createConfig("enchants.yml");
        loadObjects.load();
    }
}