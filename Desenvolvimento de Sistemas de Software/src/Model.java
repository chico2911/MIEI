/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class Model {
    private int x;
    
    Model(){
        x=0;
    }
    
    public void playOneSong(String name){
        String fileName="vlc Música/" + name + "\t";
       
           try{Process vlc1 = Runtime.getRuntime().exec(fileName);
        }
        catch(Exception w){}
    }
}
