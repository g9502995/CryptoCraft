package cryptocraft.cryptocraft;





import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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

import static java.lang.Math.round;
import static org.bukkit.Bukkit.getServer;

public class menu {

    public static Inventory components;
    public static Inventory opop,buymenu;
    public static Player owner;
    public static final String TITLE = "SHINN 的伺服器選單";
    public static final String BTCBUY = "BTC購買";
    public static final String QUIT_SERVER = "退出伺服器";
    public static final String SHOW_ANNOUNCEMENT = ChatColor.GOLD + "顯示公告";
    public static final String TELEPORT = ChatColor.GREEN + "隨機傳送";
    public static final String Profile = ChatColor.GREEN + "玩家資訊";
    public static final String btc = ChatColor.GREEN + "BTC";
    public static final String BUY = "購買BTC";
    public static final String SELL = "販賣BTC";



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
        profilesmeta.setDisplayName("玩家訊息");
        profilesmeta.setLore(Arrays.asList(
                ChatColor.RED + "玩家血量:"+ round(player.getHealth()),
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
//BTC商店
    public static void menuBTC(Player player) {

        try {
           int a = Integer.valueOf((int) Economy.getMoney(player.getName()));

        components.close();
        opop = Bukkit.createInventory(player, 27, EventListener.TITLE);
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

        ItemStack sellbtc = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta sellbtcmeta = sellbtc.getItemMeta();
        sellbtcmeta.setDisplayName(SELL);
        sellbtc.setItemMeta(sellbtcmeta);

            ItemStack history = new ItemStack(Material.BOOK, 1);
            ItemMeta historymeta = history.getItemMeta();
            historymeta.setDisplayName("交易歷史");
            history.setItemMeta(historymeta);

        ItemStack BTCprofile = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

        SkullMeta BTCprofilemeta = (SkullMeta) BTCprofile.getItemMeta();
        BTCprofilemeta.setOwner(player.getName());
        BTCprofilemeta.setDisplayName("玩家資訊");
        BTCprofilemeta.setLore(Arrays.asList(
                ChatColor.GREEN + "玩家名字:" + player.getName(),
                ChatColor.GREEN + "目前擁有BTC數量:" + configread.LoadBTC(player.getName()),
                ChatColor.GREEN + "目前擁有金錢數量:" + a));

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
        opop.setItem(12, sellbtc);
            opop.setItem(15, history);
        opop.setItem(16, BTCprofile);
        owner.openInventory(opop);
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }

    }

    public static void menuBTCBuy(Player player) {

        try {
            if(Economy.getMoney(player.getName())>binance.BTCUSDT()){
                player.sendMessage(player.getName()+"請輸入你需要購買幾個數量");
                try {
                    int a= Integer.valueOf((int) Economy.getMoney(player.getName())) - binance.BTCUSDT();
                    int g = Integer.valueOf((int) Economy.getMoney(player.getName()));
                    player.sendMessage("你好"+a+"不好"+g);
                    Economy.setMoney(player.getName(),a);
                    player.closeInventory();
                } catch (NoLoanPermittedException ex) {
                    ex.printStackTrace();
                } catch (MaxMoneyException ex) {
                    ex.printStackTrace();
                }
                configread.addBTC(player.getName(),1);
            }else {
                player.sendMessage("你不夠錢");
            }
        } catch (UserDoesNotExistException ex) {
            ex.printStackTrace();
        }

    }

    public static void sellBTCBuy(Player player) {



            if (configread.LoadBTC(player.getName()) <= 0) {
                player.sendMessage("你目前沒有BTC");

            }else {

                try {
                    int a = Integer.valueOf((int) Economy.getMoney(player.getName())) + binance.BTCUSDT();
                    Economy.setMoney(player.getName(),a);
                    configread.sellBTC(player.getName(),1);
                    player.sendMessage("販賣完成");
                    player.closeInventory();
                } catch (UserDoesNotExistException e) {
                    e.printStackTrace();
                } catch (NoLoanPermittedException e) {
                    e.printStackTrace();
                } catch (MaxMoneyException e) {
                    e.printStackTrace();
                }

            }

    }



    public void open() {
        owner.openInventory(components);
    }




}
