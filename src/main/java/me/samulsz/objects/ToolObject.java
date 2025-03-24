package me.samulsz.objects;

import org.bukkit.Material;

import java.util.List;
import java.util.stream.Collectors;

public class ToolObject {
    private final Material material;
    private final String name;
    private final List<String> lore;

    public ToolObject(Material material, String name, List<String> lore) {
        this.material = material;
        this.name = name.replace("&", "§");
        this.lore = lore.stream()
                .map(line -> line.replace("&", "§"))
                .collect(Collectors.toList());
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
