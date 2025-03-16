package me.samulsz.objects;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ConfigurationObject {
    private final List<String> ALLOWED_WORDS;
    private final ItemStack TOOL_ITEM;

    public ConfigurationObject(List<String> allowedWords, ItemStack toolItem) {
        ALLOWED_WORDS = allowedWords;
        TOOL_ITEM = toolItem;
    }

    public List<String> getAllowedWords() {
        return ALLOWED_WORDS;
    }
    public ItemStack getToolItem() {
        return  TOOL_ITEM;
    }
}
