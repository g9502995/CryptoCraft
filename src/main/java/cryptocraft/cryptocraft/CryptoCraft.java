package cryptocraft.cryptocraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.net.MalformedURLException;
import java.net.URL;

public final class CryptoCraft extends JavaPlugin {

    public static JavaPlugin instance;
    public static CryptoCraft plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");

            System.out.println(url.getQuery());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        instance = this;
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        setPlugin(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }

    @Override
    public void onLoad() {

        saveDefaultConfig();
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("cd")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        new menu((Player) sender).open();
        return true;
    }

    public static CryptoCraft getPlugin() {
        return plugin;
    }

    public static void setPlugin(CryptoCraft plugin) {
        CryptoCraft.plugin = plugin;
    }
}
