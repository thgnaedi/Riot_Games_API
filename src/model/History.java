package model;

import java.util.Queue;

/**
 *
 * @author thgnaedi
 */
public class History {

    public final Queue<Match> matches;
    public final int totalGames;

    private History() {
        matches = null;
        totalGames = 0;
    }

    public History(Queue q, String s) {
        matches = q;
        totalGames = Integer.parseInt(s.substring(13, s.length() - 1));
    }

    public String get_Most_Played_Champions() {
        StringBuilder sb = new StringBuilder();
        int[] champ = new int[matches.size()];
        int i = 0;
        for (Match m : matches) {
            champ[i++] = m.champion;
        }
        for (int a : champ) {
            System.out.println(a);
        }

        return sb.toString();
    }

}
