package Atajos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Daniela
 */
public class Salir {
    
    // método para activar botón cerrar.
    public void botonCerrar(){
        String botones[] = {"Cerrar", "Cancelar"};
        
        int eleccion = JOptionPane.showOptionDialog(null, "Estás por salir del sistema, ¿deseas hacerlo?", "Salir del sistema", 0, 0, null, botones, this);
       
        if(eleccion == JOptionPane.YES_OPTION){
            System.exit(0);
        } else if(eleccion == JOptionPane.NO_OPTION){
            System.out.println("Se canceló el cierre, continuarás en el sistema");
            JOptionPane.showMessageDialog(null, "Se canceló el cierre, continuarás en el sistema");
        }
    }
}
