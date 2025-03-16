package me.samulsz.listeners;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.objects.PlantsObject;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

public class BlockBreakListener implements Listener {
    private final PlantationsAPI api;
    public BlockBreakListener(PlantationsAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        String world = event.getBlock().getWorld().getName();
        Material mat = event.getBlock().getType();
        if (!api.getLoadObjects().getConfigurationObject().getAllowedWords().contains(world)) return;
        if (api.getLoadObjects().getPlants().values().stream()
                .noneMatch(plantsObject -> plantsObject.getMaterial().equals(mat))) return;

    }
}
