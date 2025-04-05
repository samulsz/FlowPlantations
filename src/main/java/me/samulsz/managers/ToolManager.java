package me.samulsz.managers;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.libs.ActionbarAPI;
import me.samulsz.objects.PlantsObject;
import me.samulsz.objects.ToolObject;
import me.samulsz.utils.ItemBuilder;
import me.samulsz.utils.NbtUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ToolManager {
    private static ItemStack tool;
    private final PlantationsAPI plantationsAPI;
    private final ToolObject toolObject;
    private final NbtUtils nbtUtils;

    public ToolManager(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
        toolObject = plantationsAPI.getToolObject();
        nbtUtils = plantationsAPI.getNbtUtils();
    }

    public void buildTool() {
        List<String> enchants = new ArrayList<>();
        enchants.add("Fortuna 0");
        enchants.add("Multiplicador 0");
        ItemStack item = new ItemBuilder(toolObject.getMaterial())
                .name(toolObject.getName().replace("{blocks}", "0"))
                .lore(String.valueOf(toolObject.getLore().addAll(enchants)))
                .build();

        tool = nbtUtils.setBoolean(item, "plantations-tool", true);
    }

    public ItemStack getTool() {
        return tool;
    }

    public void breakWithTool(ItemStack tool, PlantsObject plant, Player player, int amount) {
        ItemBuilder item = new ItemBuilder(tool);
        int fortune = item.getInt("fortune");
        double multiplierlevel = plantationsAPI.getLoadObjects().getEnchants().get("multiplier").getMultiplier();


        double money = plant.getMoney() * fortune;
        double fertilizers = (multiplierlevel * item.getInt("multiplier")) * plant.getFertilizers();

        ActionbarAPI actionbar = new ActionbarAPI("§6§lFARM! §2$§a"
                + money + " §6❃" + fertilizers);
        actionbar.sendToPlayer(player);

        //updating tool name
        updateTool(item.build(), player, amount);
    }

    public void updateTool(ItemStack tool, Player player, int amount) {
        ItemBuilder item = new ItemBuilder(tool);
        int breakeds = nbtUtils.getInt(item.getItemStack(), "blocks") + amount;
        String toolName = toolObject.getName().replace("{blocks}", String.valueOf(breakeds));
        item.name(toolName);
        player.setItemInHand(item.build());
    }

}
