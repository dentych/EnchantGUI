package me.tychsen.enchantgui.Config;

import me.tychsen.enchantgui.Economy.MoneyPayment;
import me.tychsen.enchantgui.Economy.NullPayment;
import me.tychsen.enchantgui.Economy.PaymentStrategy;
import me.tychsen.enchantgui.Menu.DefaultMenuSystem;
import me.tychsen.enchantgui.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public class EshopConfig {
    private static EshopConfig instance;
    private Main plugin;
    private FileConfiguration config;
    private PaymentStrategy economy;

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
            economy = null;
            sender.sendMessage(DefaultMenuSystem.start + "Configuration file has been reloaded!");
        } else {
            sender.sendMessage(DefaultMenuSystem.start + "Sorry, you do not have access to this command");
        }
    }

    public String[] getEnchantLevels(Enchantment ench) {
        String path = ench.getName().toLowerCase();
        Map<String, Object> enchantMap = config.getConfigurationSection(path).getValues(false);
        String[] enchantLevels = new String[enchantMap.size()];

        int position = 0;
        for (Map.Entry<String, Object> entry : enchantMap.entrySet()) {
            enchantLevels[position] = entry.getKey();
            position++;
        }

        return enchantLevels;
    }

    public PaymentStrategy getEconomy() {
        if (economy == null) {
            switch (config.getString("payment-currency").toLowerCase()) {
                case "money":
                    economy = new MoneyPayment();
                    break;
                case "xp":
                    // TODO: Implement XP payment.
                    economy = new NullPayment();
                    break;
                default:
                    economy = new NullPayment();
                    break;
            }
        }

        return economy;
    }

    public static EshopConfig getInstance() {
        return instance;
    }
}
