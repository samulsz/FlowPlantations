package me.samulsz.objects;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ConfigurationObject {
    private final List<String> ALLOWED_WORDS;

    public ConfigurationObject(List<String> allowedWords) {
        ALLOWED_WORDS = allowedWords;
    }

    public List<String> getAllowedWords() {
        return ALLOWED_WORDS;
    }
}
