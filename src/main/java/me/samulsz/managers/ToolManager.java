package me.samulsz.managers;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.libs.ActionbarAPI;
import me.samulsz.objects.PlantsObject;
import me.samulsz.objects.ToolObject;
import me.samulsz.utils.ItemBuilder;
import me.samulsz.utils.NbtUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToolManager {
    private static ItemStack tool;
    private final PlantationsAPI plantationsAPI;
    private final NbtUtils nbtUtils;

    public ToolManager(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
        nbtUtils = new NbtUtils();
    }

    public void buildTool() {
        ToolObject toolObject = plantationsAPI.getLoadObjects().getToolObject();
        ItemStack item = new ItemBuilder(toolObject.getMaterial())
                .name(toolObject.getName().replace("{blocks}", "0"))
                .lore(toolObject.getLore()).build();
        tool = nbtUtils.setBoolean(item, "plantations-tool", true);
    }

    public ItemStack getTool() {
        return tool;
    }

    public void breakWithTool(ItemStack tool, PlantsObject plant, Player player, int amount) {
        int fortune = nbtUtils.getInt(tool, "fortune") + 1;
        double multiplierlevel = plantationsAPI.getLoadObjects().getEnchants().get("multiplier").getMultiplier();


        double money = plant.getMoney() * fortune;
        double fertilizers = ((nbtUtils.getInt(tool, "multiplier") + 1) * plant.getFertilizers()) + (multiplierlevel * nbtUtils.getInt(tool, "multiplier") * plant.getFertilizers());

        ActionbarAPI actionbar = new ActionbarAPI("§6§lFARM! §2$§a"
                + money + " §6❃" + fertilizers);
        actionbar.sendToPlayer(player);

        //updating tool name
        updateTool(tool, player, amount);
    }

    public void updateTool(ItemStack tool, Player player, int amount) {
        ToolObject toolObject = plantationsAPI.getLoadObjects().getToolObject();
        int breakeds = nbtUtils.getInt(tool, "blocks") + amount;

        String toolName = toolObject.getName().replace("{blocks}", String.valueOf(breakeds));
        ItemMeta itemMeta = tool.getItemMeta();
        itemMeta.setDisplayName(toolName);
        tool.setItemMeta(itemMeta);
        player.setItemInHand(nbtUtils.setInt(tool, "blocks", breakeds));
    }

}
