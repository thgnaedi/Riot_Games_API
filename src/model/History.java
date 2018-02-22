package model;

import java.util.HashMap;
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

    /**
     * counts games played for each champion in this Matchhistory
     *
     * @return 2 dim int array with champion ID and number of games played
     */
    private int[][] get_array_from_Queue() {
        int[][] unique = new int[matches.size()][2];        //[x][0] = champion id [x][1] = number of games with this champion
        int size = 0;                           //number of different champions currently in the array "unique"
        int index = 0;
        boolean found;
        for (Match m : matches) {
            found = false;
            index = 0;
            //look if this champion id is already in array
            while (index < size) {
                if (unique[index][0] == m.champion) {
                    unique[index][1] += 1;
                    found = true;
                    break;
                }
                index++;
            }
            //if its not currently in the array, put it in
            if (!found) {
                unique[size][0] = m.champion;
                unique[size][1] = 1;
                size++;
            }
        }

        return unique;
    }

    private int[][] get_most_played_Champion(int[][] unique, boolean remove) {
        int max = 0;
        int position = -1;
        int[][] ret_val = new int[1][2];
        for (int i = 0; i < unique.length; i++) {
            if (unique[i][1] > max) {
                position = i;
                max = unique[i][1];
            }
        }
        if (position == -1) {
            return null;
        }
        ret_val[0][0] = unique[position][0];
        ret_val[0][1] = unique[position][1];

        if (remove) {
            unique[position][0] = 0;
            unique[position][1] = 0;
        }
        return ret_val;
    }

    /**
     * Sorts champions by playfrequenzy, most played Champion will be on the top of the String
     * @return a String with all played Champions and the ammount of games in this History
     */
    public String get_Most_Played_Champions() {
        StringBuilder sb = new StringBuilder();
        Champions c = Champions.getChampions();

        int[][] unique = get_array_from_Queue();
        int[][] current_champion;

        while (true) {
            current_champion = get_most_played_Champion(unique, true);
            if (current_champion == null) {
                break;
            }
            sb.append(c.get_Champion_by_ID(current_champion[0][0])).append(" (").append(current_champion[0][1]).append(")\n");
        }
        return sb.toString();
    }

}
