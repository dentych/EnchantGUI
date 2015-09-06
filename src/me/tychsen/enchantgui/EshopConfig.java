package me.tychsen.enchantgui;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EshopConfig {
    private static EshopConfig instance;
    private Main plugin;
    private FileConfiguration config;

    public EshopConfig() {
        instance = this;
        this.plugin = Main.getInstance();
        config = plugin.getConfig();
    }

    public int getPrice(Enchantment ench, int level) {
        String path = ench.getName().toLowerCase() + ".level" + level;
        return config.getInt(path);
    }

    public String getMenuName() {
        String path = "menu-name";
        if (config.contains(path) && config.isSet(path) && config.isString(path)) {
            if (config.getString(path).length() > 32) {
                return config.getString(path).substring(0, 32);
            } else {
                return config.getString(path);
            }
        } else {
            return "EnchantGUI";
        }
    }

    public void reloadConfig(CommandSender sender) {
        if (sender.isOp() || sender.hasPermission("eshop.admin")) {
            plugin.reloadConfig();
            config = plugin.getConfig();
            sender.sendMessage(EshopSystem.start + "Configuration file has been reloaded!");
        }
    }

    public static EshopConfig getInstance() {
        return instance;
    }
}
