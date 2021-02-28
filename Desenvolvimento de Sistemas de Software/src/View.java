/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class View {
    private Controller control;
    
    View(Controller co){
        this.control=co;
    }
    
    public void run() {
        Test test=new Test(control);
        test.setVisible(true);
    }
    
    
}
