/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Queue;

/**
 *
 * @author thgnaedi
 */
public class History {
    public final Queue matches;
    public final int totalGames;
    
    private History(){
        matches = null;
        totalGames = 0;
    }
    public History(Queue q, String s){
        matches = q;
        totalGames = Integer.parseInt(s.substring(13,s.length()-1));
    }
    
}
