package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author thgnaedi
 */
public class connect_to_rito {

    private final String key;
    private final String region;

    public static int last_error = 0; //contains last HTTP error, like 504 ,Gateway timeout UNUSED AT THE MOMENT

    /**
     *
     * @param key is your Riot API key, you can get it from
     * https://developer.riotgames.com/
     */
    public connect_to_rito(String key) {
        this.key = key;
        this.region = "euw1";
    }

    public connect_to_rito(String key, String region) {
        this.key = key;
        this.region = region;
    }

    protected StringBuffer call_Rito(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        if (con.getResponseCode() != 200) {
            last_error = con.getResponseCode();
            return null;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    public Summoner get_summoner(String sName) {

        try {
            String url = "https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + URLEncoder.encode(sName, "UTF-8").replace("+", "%20") + "?api_key=" + key;
            StringBuffer response = call_Rito(url);
            return new Summoner(response);
        } catch (Exception ex) {
            System.err.println(ex + "\t HTTP-Errorcode " + last_error + "\t Summoner name : " + sName);
            if (last_error == 403) {
                System.err.println("your key might be expired, check https://developer.riotgames.com/");
            }
            return null;
        }
    }

    public History get_gameHistory(Summoner s) throws Exception {
        String url = "https://" + region + ".api.riotgames.com/lol/match/v3/matchlists/by-account/" + s.accountID + "/recent?api_key=" + key;
        StringBuffer response = call_Rito(url);
        String[] resp = response.toString().split(","); //should now be array from 0 to 21
        String[] match = new String[8];
        Queue matches = new LinkedList();
        int pos = 0;
        while ((pos + 1) * 8 < resp.length) {
            System.arraycopy(resp, pos * 8, match, 0, 8);
            matches.add(new Match(match));
            pos++;
        }
        History hist = new History(matches, resp[resp.length - 1]);
        return hist;
    }

 
    /**
     * collects all Champions with id, trhey can be found in Champions.java as HashMap
     * @throws Exception 
     */
    public void get_all_Champions() throws Exception {
        StringBuffer response = call_Rito(Champions.DDRAGON_URL);
        String resp = response.toString().replace(", ", ""); //remove all "," seperators in Champion story!
        String[] allChamps = resp.split(",");
        
        //TODO collect more informations about champions, dont throw everything away!
        Champions c = Champions.getChampions();
        for (int i = 3; i < allChamps.length; i++) {     //index 0-3 are unnecessary informations
            if(allChamps[i].startsWith("\"id\":")){      //new champion found! (champion data does not always have the same ammount of cells in array)
                c.add_Champion(allChamps[i+1].substring(7,allChamps[i+1].length()-1), allChamps[i].substring(6,allChamps[i].length()-1));
                i += 30;                                 //next few cells cant be a new champion.
            }
        }        
    }

}
