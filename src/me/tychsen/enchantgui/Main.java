package me.tychsen.enchantgui;

import me.tychsen.enchantgui.Config.EshopConfig;
import me.tychsen.enchantgui.Event.EventManager;
import me.tychsen.enchantgui.Menu.DefaultMenuSystem;
import me.tychsen.enchantgui.Menu.MenuSystem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin implements Listener {
    private static Main instance;
    private MenuSystem system;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Loading configs and stuff...");

        system = new DefaultMenuSystem();
        EventManager manager = new EventManager(system);
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
            // TODO: Move to own class..
            handleCommand(sender, command, args);
        }

        return true;
    }

    private boolean handleCommand(CommandSender sender, Command cmd, String[] args) {
        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    EshopConfig.getInstance().reloadConfig(sender);
                }
            } else {
//TODO: Implement this check somewhere.. Dnu where
//if (playerHasUsePerms(p)) {
                system.showMainMenu(p);
//} else {
                p.sendMessage(ChatColor.DARK_RED + "[EnchantGUI] " + ChatColor.RED + "Sorry, you do not have access to this command");
//}
            }
        }

        return true;
    }
}