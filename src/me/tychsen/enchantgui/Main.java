package me.tychsen.enchantgui;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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
        eshop = new EshopEventManager();

        getLogger().info(Enchantment.DIG_SPEED.getName());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        eshop.handleInventoryClickEvent(e);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eshop"))
            return eshop.handleCommand(sender, command);
        else
            return false;
    }
}