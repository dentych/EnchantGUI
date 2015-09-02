package me.tychsen.enchantgui;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private EshopEventManager eshop;

    @Override
    public void onEnable() {
        getLogger().info("Loading configs and stuff...");

        getServer().getPluginManager().registerEvents(this, this);
        eshop = new EshopEventManager(this);

        // Generate config.yml if there is none
        saveDefaultConfig();
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
            return;

        getLogger().severe(e.getCurrentItem().toString());
        try {
            eshop.handleInventoryClickEvent(e);
        } catch (Exception exc) {
            getLogger().severe("EXCEPTION: " + exc.getMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eshop"))
            return eshop.handleCommand(sender, command);
        else
            return false;
    }
}