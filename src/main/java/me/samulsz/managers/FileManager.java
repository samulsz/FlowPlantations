package me.samulsz.managers;

import me.samulsz.FlowPlantacoes;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static Plugin plugin;

    public FileManager(Plugin plugin) {
        FileManager.plugin = plugin;
    }

    public void createConfig(String file) {
        if (!(new File(plugin.getDataFolder(), String.valueOf(file) + ".yml")).exists())
            plugin.saveResource(String.valueOf(file) + ".yml", false);
    }

    public FileConfiguration getConfig(String file) {
        File arquivo = new File(plugin.getDataFolder() + File.separator + file + ".yml");
        return (FileConfiguration) YamlConfiguration.loadConfiguration(arquivo);
    }

    public void reloadConfig(FileConfiguration config, String file) throws IOException, InvalidConfigurationException {
        File arquivo = new File(plugin.getDataFolder() + File.separator + file + ".yml");
        config.load(arquivo);
    }

    public void saveConfig(FileConfiguration config, String file) {
        File arquivo = new File(plugin.getDataFolder() + File.separator + file + ".yml");
        try {
            config.save(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
