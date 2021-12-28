package cryptocraft.cryptocraft;





import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.checkerframework.checker.nullness.qual.NonNull;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class menu {

    public static Inventory components;
    public static Inventory opop;
    public static Player owner;
    public static final String TITLE = "HoofPower 菜单";
    public static final String QUIT_SERVER = "退出服务器";
    public static final String SHOW_ANNOUNCEMENT = ChatColor.GOLD + "显示公告";
    public static final String TELEPORT = ChatColor.GREEN + "随机传送";
    public static final String Profile = ChatColor.GREEN + "玩家資訊";
    public static final String btc = ChatColor.GREEN + "BTC";
    public static final String BUY = "BTC買賣商店";



    public menu(Player player) {
        components = Bukkit.createInventory(player, 9, TITLE);
        owner = player;

        ItemStack quitServer = new ItemStack(Material.BARRIER);
        ItemMeta quitServerMeta = quitServer.getItemMeta();
        quitServerMeta.setDisplayName(QUIT_SERVER);
        quitServerMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "离开此服务器"));
        quitServer.setItemMeta(quitServerMeta);

        ItemStack showAnnouncement = new ItemStack(Material.BOOK);
        ItemMeta showAnnouncementMeta = showAnnouncement.getItemMeta();
        showAnnouncementMeta.setDisplayName(SHOW_ANNOUNCEMENT);
        showAnnouncementMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "查看公告"));
        showAnnouncement.setItemMeta(showAnnouncementMeta);

        ItemStack teleport = new ItemStack(Material.COMPASS);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(TELEPORT);
        teleportMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "在当前世界随机传送"));
        teleport.setItemMeta(teleportMeta);

        ItemStack profiles = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

        SkullMeta profilesmeta = (SkullMeta) profiles.getItemMeta();
        profilesmeta.setOwner(player.getName());
        profilesmeta.setDisplayName(Profile);
        profilesmeta.setLore(Arrays.asList(
                ChatColor.RED + "玩家血量:"+player.getHealth(),
                ChatColor.GREEN + "玩家飽食度:"+ player.getFoodLevel()));

        profiles.setItemMeta(profilesmeta);

        ItemStack BTC = new ItemStack(Material.GOLD_INGOT);
        ItemMeta BTCMeta = BTC.getItemMeta();
        BTCMeta.setDisplayName(btc);

        BTC.setItemMeta(BTCMeta);

        components.setItem(0, quitServer);
        components.setItem(1,profiles);
        components.setItem(2,BTC);
        components.setItem(4, showAnnouncement);
        components.setItem(8, teleport);
    }

    public static void menuBTC(Player player) {


        components.close();
        opop = Bukkit.createInventory(player, 27, TITLE);
        owner = player;

        ItemStack profiles = new ItemStack(Material.GOLD_INGOT, 1, (short) 3);

        ItemMeta profilesmeta = profiles.getItemMeta();
        profilesmeta.setDisplayName("Profile");
        profilesmeta.setLore(Arrays.asList(
                ChatColor.RED + "BTC價格:" + binance.BTCUSDT()
        ));
        profiles.setItemMeta(profilesmeta);

        ItemStack buybtc = new ItemStack(Material.DIAMOND, 1);
        ItemMeta buybtcmeta = buybtc.getItemMeta();
        buybtcmeta.setDisplayName(BUY);
        buybtc.setItemMeta(buybtcmeta);

        ItemStack BTCprofile = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

        SkullMeta BTCprofilemeta = (SkullMeta) BTCprofile.getItemMeta();
        BTCprofilemeta.setOwner(player.getName());
        BTCprofilemeta.setDisplayName("玩家資訊");
        BTCprofilemeta.setLore(Arrays.asList(
                ChatColor.GREEN + "玩家名字:" + player.getName(),
                ChatColor.GREEN + "目前擁有BTC數量:" + configread.LoadBTC(player.getName())));

        BTCprofile.setItemMeta(BTCprofilemeta);

        ItemStack glass = new ItemStack(Material.PINK_STAINED_GLASS_PANE, 1, (short) 3);

        for (int i = 0; i < 10; i++) {
            opop.setItem(i, glass);
        }
        for(int i = 17;i<27;i++){
            opop.setItem(i, glass);
        }
        opop.setItem(10, profiles);
        opop.setItem(11, buybtc);
        opop.setItem(16, BTCprofile);
        owner.openInventory(opop);

    }






    public void open() {
        owner.openInventory(components);
    }


}
