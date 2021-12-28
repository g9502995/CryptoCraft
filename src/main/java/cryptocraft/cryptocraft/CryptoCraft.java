package cryptocraft.cryptocraft;


import com.google.common.net.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import javax.annotation.ParametersAreNonnullByDefault;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public final class CryptoCraft extends JavaPlugin {

    public static JavaPlugin instance;
    public static CryptoCraft plugin;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String WebhookURL = "https://discord.com/api/webhooks/925290194050109440/nu8wWnxGK_5Jn4JkSZIAVsMgGot_RY30gz8tbtp84lAGxDFpsMWiABheNFGXFT6X15cp";

    @Override
    public void onEnable() {

        DiscordWebhook webhook = new DiscordWebhook(WebhookURL);

        webhook.setContent("中文");
        webhook.setAvatarUrl("https://your.awesome/image.png");
        webhook.setUsername("Minecraft BOT");
        webhook.setTts(true);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Title")
                .setDescription("This is a description")
                .setColor(Color.RED)
                .addField("1st Field", "Inline", true)
                .addField("2nd Field", "Inline", true)
                .addField("3rd Field", "No-Inline", false)
                .setThumbnail("https://kryptongta.com/images/kryptonlogo.png")
                .setFooter("Footer text", "https://kryptongta.com/images/kryptonlogodark.png")
                .setImage("https://kryptongta.com/images/kryptontitle2.png")
                .setAuthor("Author Name", "https://kryptongta.com", "https://kryptongta.com/images/kryptonlogowide.png")
                .setUrl("https://kryptongta.com"));
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription("Just another added embed object!"));
        try {
            webhook.execute(); //Handle exception
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Plugin startup logic
        try {
            URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");



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
