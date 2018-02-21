package controller;

import model.History;
import model.connect_to_rito;
import model.Summoner;

/**
 *
 * @author thgnaedi
 */
public class RitoAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            connect_to_rito rt = new connect_to_rito(DO_NOT_PUBLISH.KEY);
            Summoner sum = rt.get_summoner("PoroKönig Blade");
            if (sum != null) {
                System.out.println(sum);
                History hist = rt.get_gameHistory(sum);
                rt.get_all_Champions();
                hist.get_Most_Played_Champions();
            }

        } catch (Exception ex) {
            System.out.println("controller.RitoAPI.main()" + "\n\t" + ex);
            System.out.println("last HTTP error " + connect_to_rito.last_error);
        }

    }
}
