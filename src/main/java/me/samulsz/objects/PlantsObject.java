package me.samulsz.objects;

import org.bukkit.Material;

public class PlantsObject {
    private final Material  material;
    private final double fertilizers;
    private final double money;

    public PlantsObject(Material material, double fertilizers, double money) {
        this.material = material;
        this.fertilizers = fertilizers;
        this.money = money;
    }

    public Material getMaterial() {
        return material;
    }

    public double getFertilizers() {
        return fertilizers;
    }

    public double getMoney() {
        return money;
    }
}
