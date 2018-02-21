package model;

/**
 *
 * @author thgnaedi
 */
public class Match {

    public final String lane;
    public final long gameID;
    public final int champion;
    //public final String platformID; platform is saved in Summoner
    public final int season;
    public final int queue;
    public final String role;
    public final long timestamp;

    private Match() {
        lane = "";
        gameID = 0;
        champion = 0;
        season = 0;
        queue = 0;
        role = "";
        timestamp = 0;
    }
    
    public Match(String[] response){

        lane = response[7].substring(8,response[7].length()-2).replace("\"", ""); //replace, because last element in List contains one char more
        gameID = Long.parseLong(response[1].substring(9));
        champion = Integer.parseInt(response[2].substring(11));
        season = Integer.parseInt(response[4].substring(9));
        queue = Integer.parseInt(response[3].substring(8));
        role = response[6].substring(8,response[6].length()-1);
        timestamp = Long.parseLong(response[5].substring(12));        
    }
    
}
