package me.samulsz.listeners;

import me.samulsz.api.PlantationsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    private final PlantationsAPI plantationsAPI;

    public PlayerLoginListener(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        plantationsAPI.getUserCache().createPlayer(player.getName());
    }
}
