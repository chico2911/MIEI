
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class MediaCenter {
   public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Model model;
                model = new Model();
                Controller controller = new Controller(model);
                //controller.setModel(model);
                View view = new View(controller); 
                /** view registada como observador do controller para poder actualizar o écrandurante a construção do número no controller  */
               
                /** view registada como observador do model para poder actualizar o écran após operações no model */
               // model.addObserver(view);
                /** controller registado como observador do model para poder actualizar o valor após operações no model */
               // model.addObserver(controller);           
                view.run(); 
            }
        });
    }
}
