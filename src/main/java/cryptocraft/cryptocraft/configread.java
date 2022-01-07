package cryptocraft.cryptocraft;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.management.MalformedObjectNameException;
import java.util.Locale;

public class configread {


    public static FileConfiguration config = CryptoCraft.instance.getConfig();


    public static void addBTC(String playerName, int amout, int money, Player player){
        config.set(playerName.toLowerCase() + "玩家.玩家名",playerName.toLowerCase());
       int a = config.getInt(playerName.toLowerCase() + "玩家.數量");
        config.set(playerName.toLowerCase() + "玩家.數量",a+amout);
        if(config.get(playerName.toLowerCase()+"玩家.已購買數量")==null){
            config.set(playerName.toLowerCase() + "玩家.已購買數量",1);
            config.set(playerName.toLowerCase() + "玩家.前一次購買價格",money);
        }else{
          int g = config.getInt(playerName.toLowerCase()+"玩家.已購買數量") +1;
            config.set(playerName.toLowerCase() + "玩家.已購買數量",g);
            config.set(playerName.toLowerCase() + "玩家.前一次購買價格",money);
            player.sendMessage("成功購買 購買價格為:"+ money);
        }

        CryptoCraft.instance.saveConfig();
    }

    public static int LoadBTC(String playerName){

       return config.getInt(playerName.toLowerCase() + "玩家.數量");
    }

    public static void sellBTC(String playerName,int amout,int money){
        int gg = config.getInt(playerName.toLowerCase() + "玩家.前一次購買價格") - money;
        config.set(playerName.toLowerCase() + "玩家.玩家名",playerName.toLowerCase());
        int a = config.getInt(playerName.toLowerCase() + "玩家.數量");
        config.set(playerName.toLowerCase() + "玩家.數量",a-amout);
        if(config.get(playerName.toLowerCase()+"玩家.賣")==null){
            config.set(playerName.toLowerCase() + "玩家.賣",1);

            config.set(playerName.toLowerCase() + "玩家.收益" ,gg);
        }else{
            int g = config.getInt(playerName.toLowerCase()+"玩家.賣") +1;
            config.set(playerName.toLowerCase() + "玩家.賣",g);
            config.set(playerName.toLowerCase() + "玩家.收益",gg);
        }
        CryptoCraft.instance.saveConfig();
    }

    public static void history(String playerName,int amout){

    }



}
