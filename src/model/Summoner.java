/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thgnaedi
 */
public class Summoner {

    public final String name;    //Summoner name
    public final long lvl;        //Summoner level associated with the summoner
    public final long revDate;   //Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
    public final int iconID;     //ID of the summoner icon associated with the summoner.
    public final long accountID;  //Account ID.
    public final long summonerID; //Sommoner ID.

    //TODO remove invalid constructor
    private Summoner() {
        name = "";
        lvl = 0;
        revDate = 0;
        iconID = 0;
        accountID = 0;
        summonerID = 0;
    }

    public Summoner(StringBuffer response) {
        String[] resp = response.toString().split(",");

        name = resp[2].substring(8, resp[2].length() - 1);
        lvl = Long.parseLong(resp[5].substring(16, resp[5].length() - 1));
        revDate = Long.parseLong(resp[4].substring(15, resp[4].length()));
        iconID = Integer.parseInt(resp[3].substring(16, resp[3].length()));
        accountID = Long.parseLong(resp[1].substring(12, resp[1].length()));
        summonerID = Long.parseLong(resp[0].substring(6, resp[0].length()));
    }
    

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Beschwörer ").append(name).append(" (lvl ").append(lvl);
        sb.append(") ID = ").append(summonerID);
        return sb.toString();
    }
}
