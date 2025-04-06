package me.samulsz.commands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import me.samulsz.api.PlantationsAPI;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ToolCommand {

    private final PlantationsAPI plantationsAPI;

    public ToolCommand(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
    }

    @Command(name = "tool", aliases = {"gettool"}, target = CommandTarget.PLAYER)
    public void handleCommandChild(Context<Player> context) {
        Player p = context.getSender();
        p.getInventory().addItem(plantationsAPI.getToolManager().getTool());
    }
}
