/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class Controller {
    private Model model;
    
    
    
    Controller(Model mo){
        model=mo;
    }
    
   /* public void setModel(Model mo){
    this.model = new Model();
    }*/
    
    public void playOneSong(String name){
       
    this.model.playOneSong(name);
    }
    
}
