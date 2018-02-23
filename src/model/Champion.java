/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *  Class not finished yet, just a tiny implementation for tests.
 * @author thgnaedi
 */
public class Champion {
    public final String name;
    public final int start_index;   //index in String array where champion can be found
    
    public Champion(String name, int i){
        this.name = name;
        this.start_index = i;
    }
    
}
