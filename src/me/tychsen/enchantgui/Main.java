package me.tychsen.enchantgui;

import me.tychsen.enchantgui.Event.EventManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin implements Listener {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Loading configs and stuff...");

        EventManager manager = new EventManager();
        getServer().getPluginManager().registerEvents(manager, this);

        // Generate config.yml if there is none
        saveDefaultConfig();

        // Enable Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().severe("Couldn't start Metrics.");
        }
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eshop")) {
            eshop.handleCommand(sender, command, args);
        }

        return true;
    }
}