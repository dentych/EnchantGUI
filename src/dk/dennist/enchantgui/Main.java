package dk.dennist.enchantgui;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static String version;
    GUIManager gm;
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public Map<String, Integer> menuPage = new HashMap<String, Integer>();
    // Currency 1 = money, 2 = XP
    public int currency = 1;

    @Override
    public void onEnable() {
        PluginDescriptionFile pdf = this.getDescription();
        version = pdf.getVersion();

        this.saveDefaultConfig();
        gm = new GUIManager(this, this);
        getServer().getPluginManager().registerEvents(new GUIListener(this, this), this);

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getConfig().getDouble("version") < 1.3) {
            getLogger().severe("WARNING: Your config version is not up to date! Go to the BukkitDev page to read about what to do");
            getLogger().severe("WARNING: Your config version is not up to date! Go to the BukkitDev page to read about what to do");
            getLogger().severe("WARNING: Your config version is not up to date! Go to the BukkitDev page to read about what to do");
            this.getPluginLoader().disablePlugin(this);
        }

        if (getConfig().getString("payment-currency").equalsIgnoreCase("money"))
            currency = 1;
        else
            currency = 2;
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

    public boolean takeMoneyFromPlayer(Player p, Enchantment ent, int level) {
        double price;
        EconomyResponse r;
        String path = getConfigName(ent);
        level += 1;

        if (getConfig().contains(path + ".all")) {
            price = getConfig().getDouble(path + ".all");
            r = econ.withdrawPlayer(getServer().getOfflinePlayer(p.getUniqueId()), price);
        } else {
            price = getConfig().getDouble(path + ".level" + String.valueOf(level));
            r = econ.withdrawPlayer(getServer().getOfflinePlayer(p.getUniqueId()), price);
        }

        if (r.transactionSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean takeExpFromPlayer(Player p, Enchantment ent, int level) {
        String path = getConfigName(ent);
        int price;
        level += 1;

        if (getConfig().contains(path + ".all")) {
            price = getConfig().getInt(path + ".all");
        } else {
            price = getConfig().getInt(path + ".level" + String.valueOf(level));
        }

        if (p.getTotalExperience() > price) {
            int newXP = p.getTotalExperience()-price;
            p.setExp(0);
            p.setLevel(0);
            p.setTotalExperience(0);

            while (newXP > 0) {
                int expToLevelUp = expCost(p.getLevel());
                newXP -= expToLevelUp;
                if (newXP >= 0) {
                    p.giveExp(expToLevelUp);
                }
                else {
                    newXP += expToLevelUp;
                    p.giveExp(newXP);
                    newXP = 0;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // The expCost method has been found on the MinecraftWiki on Experience.
    private int expCost(int currentLevel) {
        if (currentLevel >= 30) {
            return 62 + (currentLevel - 30) * 7;
        } else if (currentLevel >= 15) {
            return 17 + (currentLevel - 15) * 3;
        } else {
            return 17;
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

    public String getEnchantPrice(Enchantment ent, int level) {
        String configName = getConfigName(ent);
        if (getConfig().contains(configName + ".all"))
            return getConfig().getString(configName + ".all");
        else
            return getConfig().getString(configName + ".level" + (level + 1));
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
                        if (getConfig().getString("payment-currency").equalsIgnoreCase("money"))
                            currency = 1;
                        else
                            currency = 2;
                    } else {
                        sender.sendMessage(ChatColor.RED + "Access denied!");
                    }
                } else if (args[0].equalsIgnoreCase("lvltake")) {
                    if (sender.isOp()) {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            p.giveExpLevels(-1);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Access denied!");
                    }
                }
            }
        }
        return true;
    }
}
