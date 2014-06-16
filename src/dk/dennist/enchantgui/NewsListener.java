package dk.dennist.enchantgui;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsListener implements Listener {

    JavaPlugin plugin;
    Main main;

    NewsListener(JavaPlugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;
    }

    @EventHandler
    public void onLoginEvent(PlayerJoinEvent evt) {
        if (evt.getPlayer().isOp() || evt.getPlayer().hasPermission("eshop.receivenews")) {
            final PlayerJoinEvent event = evt;
            BukkitScheduler scheduler = evt.getPlayer().getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    try {
                        URL myURL = new URL("http://tahg.me/news.php?v=" + Main.version);
                        URLConnection con = myURL.openConnection();

                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String input;
                        while ((input = br.readLine()) != null) {
                            if (input.toLowerCase().equals("none")) {
                                break;
                            }
                            else {
                                event.getPlayer().sendMessage(ChatColor.AQUA + "[EnchantGUI News] " + ChatColor.GRAY + input);
                            }
                        }
                        br.close();
                    }
                    catch (MalformedURLException e) {
                        event.getPlayer().getServer().getLogger().severe("Couldn't connect to news website. Received MalformedURLException: " + e.getMessage());
                    }
                    catch (IOException e) {
                        event.getPlayer().getServer().getLogger().severe("Couldn't connect to news website. Received I/O Exception: " + e.getMessage());
                    }
                }
            }, 20);
        }
    }
}
