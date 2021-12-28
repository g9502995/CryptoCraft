package cryptocraft.cryptocraft;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
public class EventListener implements Listener{
    public static final Random RANDOM = new Random();

    public Player owner;
    public static final String TITLE = "BTC 商店";

    public Inventory opop;
    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        InventoryView inv = player.getOpenInventory();
        if (inv.getTitle().equals(menu.TITLE)||inv.getTitle().equals(TITLE)) {
            e.setCancelled(true);
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()) {
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null) {
                return;
            }
            if (clickedItem.getItemMeta().getDisplayName().equals(menu.QUIT_SERVER)) {
                player.kickPlayer("您已离开服务器");
                return;
            }
            if (clickedItem.getItemMeta().getDisplayName().equals(menu.TELEPORT)) {
                player.closeInventory();
                World playerWorld = Bukkit.getWorld("world");
                double randX = RANDOM.nextInt(200000) - 100000;
                double randZ = RANDOM.nextInt(200000) - 100000;
                Location offset = new Location(playerWorld, randX, 0, randZ).toHighestLocation();
                player.teleport(offset);
                player.sendMessage(ChatColor.GREEN + "传送成功！");
                return;
            }
            if (clickedItem.getItemMeta().getDisplayName().equals(menu.SHOW_ANNOUNCEMENT)) {
                ItemStack ann = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta annBm = (BookMeta) ann.getItemMeta();
                String[] acText = Objects.requireNonNullElse(CryptoCraft.instance.getConfig().getString("announcement"), "").split("\\+\\+\\+");
                annBm.setPages(acText);
                annBm.setAuthor("HoofPower");
                annBm.setTitle("服务器公告");
                ann.setItemMeta(annBm);
                player.openBook(ann);
            }

            if (clickedItem.getItemMeta().getDisplayName().equals(menu.btc)) {

              menu.menuBTC(player);



            }if (clickedItem.getItemMeta().getDisplayName().equals(menu.BUY)) {
                try {
                    Economy.setMoney(player.getName(),100000);
                } catch (UserDoesNotExistException ex) {
                    ex.printStackTrace();
                } catch (NoLoanPermittedException ex) {
                    ex.printStackTrace();
                } catch (MaxMoneyException ex) {
                    ex.printStackTrace();
                }
                try {
                    if(Economy.getMoney(player.getName())>binance.BTCUSDT()){
                        try {
                            Economy.setMoney(player.getName(),-binance.BTCUSDT());
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




        }
    }

}
