package cryptocraft.cryptocraft;




import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class binance {


    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Gson gson = new Gson();

        String json = readUrl("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");

        // Page page = gson.fromJson(json, Page.class);
        Response response = gson.fromJson(json, Response.class);


    }

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }

    public static int BTCUSDT(){
        try {
         String A = readUrl("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
         String g = A.replaceAll("[^\\d.]", "");
         String b = g.substring(0, g.indexOf("."));
            return Integer.parseInt(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    static class Response{
        String version;
        String termsOfService;
    }


}
