package model;

/**
 *
 * @author thgnaedi
 */
public class Champions {

    public static final String url = "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json";
    private static Champions c = null;

    private Champions() {

        c = this;
    }

    public static Champions getChampions() {
        if (c != null) {
            return c;
        } else {
            return new Champions();
        }
    }

}
