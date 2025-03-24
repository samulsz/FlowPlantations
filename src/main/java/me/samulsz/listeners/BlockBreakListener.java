package me.samulsz.listeners;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.cache.UserCache;
import me.samulsz.libs.ActionbarAPI;
import me.samulsz.managers.ToolManager;
import me.samulsz.objects.PlantsObject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class BlockBreakListener implements Listener {
    private final PlantationsAPI api;
    private final UserCache userCache;
    private final ToolManager toolManager;

    public BlockBreakListener(PlantationsAPI api) {
        this.api = api;
        userCache = api.getUserCache();
        toolManager = api.getToolManager();

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String world = event.getBlock().getWorld().getName();
        Material mat = event.getBlock().getType();
        ItemStack item = player.getItemInHand();
        if (!api.getLoadObjects().getConfigurationObject().getAllowedWords().contains(world)) return;
        Optional<PlantsObject> optionalPlant = api.getLoadObjects().getPlants().values().stream()
                .filter(plantsObject -> plantsObject.getMaterial().equals(mat))
                .findFirst();
        if (!optionalPlant.isPresent()) return;
        if (api.getNbtUtils().getBoolean(item, "plantations-tool")){
            PlantsObject plant = optionalPlant.get();
            toolManager.breakWithTool(item, plant, player, 1);
            userCache.addCoins(player.getName(), plant.getFertilizers());
        }


    }
}
