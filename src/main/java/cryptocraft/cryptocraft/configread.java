package cryptocraft.cryptocraft;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Locale;

public class configread {
    public static FileConfiguration config = CryptoCraft.instance.getConfig();

    public static void addBTC(String playerName,int amout){
        config.set(playerName.toLowerCase() + "玩家.玩家名",playerName.toLowerCase());
       int a = config.getInt(playerName.toLowerCase() + "玩家.數量");
        config.set(playerName.toLowerCase() + "玩家.數量",a+amout);
        CryptoCraft.instance.saveConfig();
    }

    public static int LoadBTC(String playerName){

       return config.getInt(playerName.toLowerCase() + "玩家.數量");
    }

    public static void sellBTC(String playerName,int amout){
        config.set(playerName.toLowerCase() + "玩家.玩家名",playerName.toLowerCase());
        int a = config.getInt(playerName.toLowerCase() + "玩家.數量");
        config.set(playerName.toLowerCase() + "玩家.數量",a-amout);
        CryptoCraft.instance.saveConfig();
    }



}
