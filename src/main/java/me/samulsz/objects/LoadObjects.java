package me.samulsz.objects;

import me.samulsz.managers.FileManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class LoadObjects {
    private final FileManager fileManager;

    public LoadObjects( FileManager fileManager) {
        this.fileManager = fileManager;
    }

    private static HashMap<String, PlantsObject> plants = new HashMap<>();
    private static HashMap<String, EnchantsObject> enchants = new HashMap<>();
    public void load(){
        //loading plants
        FileConfiguration plantsConfig = fileManager.getConfig("plants.yml");
        for (String key : plantsConfig.getConfigurationSection("economy").getKeys(false)) {
            PlantsObject plant = new PlantsObject(
                    Material.matchMaterial(plantsConfig.getString("enchants." + key + ".material")),
                    plantsConfig.getDouble("enchants." + key + ".fertilizers"),
                    plantsConfig.getDouble("enchants." + key + ".money")
            );
            plants.put(key, plant);
        }
        //loading enchants
        FileConfiguration enchantsConfig = fileManager.getConfig("enchants.yml");
        for (String key : enchantsConfig.getConfigurationSection("enchants").getKeys(false)) {
            EnchantsObject enchant = new EnchantsObject(
                    enchantsConfig.getInt("enchants." + key + ".level.default"),
                    enchantsConfig.getInt("enchants." + key + ".level.max"),
                    enchantsConfig.getDouble("enchants." + key + ".price"),
                    enchantsConfig.getDouble("enchants." + key + ".multiplier"),
                    enchantsConfig.getInt("enchants." + key + ".area"),
                    plantsConfig.getString("enchants." + key + ".icon.name"),
                    plantsConfig.getString("enchants." + key + ".icon.head"),
                    plantsConfig.getStringList("enchants." + key + ".icon.lore")
            );
            enchants.put(key, enchant);
        }
    }
    public HashMap<String, PlantsObject> getPlants(){
        return plants;
    }

    public HashMap<String, EnchantsObject> getEnchants() {
        return enchants;
    }
}
