package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thgnaedi
 */
public class Champions {

    public static final String DDRAGON_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json";
    private static Champions c = null;
    private Map<Integer, Champion> champions;

    private Champions() {
        champions = new HashMap<>();
        c = this;
    }

    public static Champions getChampions() {
        if (c != null) {
            return c;
        } else {
            return new Champions();
        }
    }
    
    public void add_Champion(String key, String value, int i){
        champions.put(new Integer(key), new Champion(value,i));
    }
    
    public String get_Champion_name_by_ID(int id){
        return champions.get(new Integer(id)).name;
    }

}
