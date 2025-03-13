package me.samulsz.objects;

import java.util.List;

public class EnchantsObject {
    private final int defaultLevel;
    private final int maxLevel;
    private final double price;
    private final double multiplier;
    private final int area;
    private final String iconName;
    private final String iconHead;
    private final List<String> iconLore;

    public EnchantsObject(int defaultLevel, int maxLevel, double price, double multiplier, int area, String iconName, String iconHead, List<String> iconLore) {
        this.defaultLevel = defaultLevel;
        this.maxLevel = maxLevel;
        this.price = price;
        this.multiplier = multiplier;
        this.area = area;
        this.iconName = iconName;
        this.iconHead = iconHead;
        this.iconLore = iconLore;
    }

    public int getDefaultLevel() {
        return defaultLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public double getPrice() {
        return price;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getArea() {
        return area;
    }

    public String getIconName() {
        return iconName;
    }

    public String getIconHead() {
        return iconHead;
    }

    public List<String> getIconLore() {
        return iconLore;
    }
}
