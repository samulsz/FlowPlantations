package me.samulsz.managers;

import me.samulsz.api.PlantationsAPI;
import me.samulsz.objects.EnchantsObject;
import me.samulsz.objects.LoadObjects;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class EnchantmentsManager {

    private final PlantationsAPI plantationsAPI;
    private final LoadObjects loadObjects;

    public EnchantmentsManager(PlantationsAPI plantationsAPI) {
        this.plantationsAPI = plantationsAPI;
        loadObjects = plantationsAPI.getLoadObjects();
    }

    public void explosion(ItemStack itemStack, Location location){
        EnchantsObject explosionObject = loadObjects.getEnchants().get("explosion");

    }
}
