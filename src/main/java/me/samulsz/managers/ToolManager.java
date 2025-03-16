package me.samulsz.managers;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class ToolManager {
    private static ItemStack tool;
    private final PlantationsAPI plantationsAPI;

    public ToolManager(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
    }

    public ItemStack getTool() {
        return tool;
    }

    public void buildTool() {
        tool = plantationsAPI.getLoadObjects().getConfigurationObject().getToolItem();
    }
}
