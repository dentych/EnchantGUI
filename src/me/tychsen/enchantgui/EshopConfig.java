package me.tychsen.enchantgui;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class EshopConfig {
    private JavaPlugin plugin;
    private FileConfiguration config;

    public EshopConfig() {
        this.plugin = Main.getInstance();
        config = plugin.getConfig();
    }

    public int getPrice(Enchantment ench, int level) {
        return config.getInt(ench.getName().toLowerCase() + ".level" + level);
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
}
