package dk.dennist.enchantgui;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Dennis on 03/05/2014.
 */
public class Main extends JavaPlugin {
    GUIManager gm;
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        gm = new GUIManager(this, this);
        getServer().getPluginManager().registerEvents(new GUIListener(this, this), this);

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getConfig().getDouble("version") < 1.1) {
            getLogger().severe("WARNING: Your config version is not up to date!");
            getLogger().severe("WARNING: Your config version is not up to date!");
            getLogger().severe("WARNING: Your config version is not up to date!");
            this.getPluginLoader().disablePlugin(this);
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public boolean takeMoneyFromPlayer(Player p, String path) {
        double price = getConfig().getDouble(path + ".price");
        EconomyResponse r = econ.withdrawPlayer(getServer().getOfflinePlayer(p.getUniqueId()), price);

        if (r.transactionSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public String getPermissionName(Enchantment ent) {
        String base = "eshop.enchant.";
        String[] split = ent.getName().toLowerCase().split("_");
        if (split.length == 2) {
            return base + split[0] + "-" + split[1];
        } else if (split.length == 3) {
            return base + split[0] + "-" + split[1] + "-" + split[2];
        } else {
            return base + split[0];
        }
    }

    public String getDisplayName(Enchantment ent) {
        String[] split = ent.getName().toLowerCase().split("_");
        String first, second, third, name;
        if (split.length == 2) {
            first = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
            second = split[1].substring(0, 1).toUpperCase() + split[1].substring(1);
            name = first + " " + second;

            return name;
        } else if (split.length == 3) {
            first = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
            second = split[1].substring(0, 1).toUpperCase() + split[1].substring(1);
            third = split[2].substring(0, 1).toUpperCase() + split[2].substring(1);
            name = first + " " + second + " " + third;

            return name;
        } else {
            first = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);

            return first;
        }
    }

    public String getConfigName(Enchantment ent) {
        String[] split = ent.getName().toLowerCase().split("_");
        if (split.length == 2) {
            return split[0] + "-" + split[1];
        } else if (split.length == 3) {
            return split[0] + "-" + split[1] + "-" + split[2];
        } else {
            return split[0];
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eshop")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("eshop.use")) {
                        Player p = (Player) sender;
                        gm.openEnchantShop(p);
                    } else {
                        sender.sendMessage(ChatColor.RED + "You do not have permission to use EnchantGUI");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Sorry, this command can only be used by in-game players.");
                }
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.isOp() || sender.hasPermission("eshop.reload")) {
                        sender.sendMessage(ChatColor.AQUA + "[EnchantGUI] " + ChatColor.WHITE + "Reloading " + ChatColor.GREEN + "EnchantGUI" + ChatColor.WHITE + "!");
                        this.saveDefaultConfig();
                        this.reloadConfig();
                    } else {
                        sender.sendMessage(ChatColor.RED + "Access denied!");
                    }
                }
            }
        }
        return true;
    }
}
