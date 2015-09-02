package me.tychsen.enchantgui;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EshopVault {
    private Economy econ = null;
    private JavaPlugin plugin;

    public EshopVault(JavaPlugin plugin) {
        this.plugin = plugin;

        if (!setupEconomy()) {
            plugin.getLogger().severe("Dependency (Vault) not found. Disabling the plugin!");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    public boolean withdrawMoney(Player p, int amount) {
        EconomyResponse r = econ.withdrawPlayer(p, amount);

        if (r.transactionSuccess())
            return true;
        else
            return false;
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
