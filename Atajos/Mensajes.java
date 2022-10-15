package Atajos;

import Diapositivas.Inicio;
import Diapositivas.Login;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Daniela
 */
public class Mensajes {
    
    // método para crear un jLabel de no coincidencia de datos/ datos incorrectos
    public void DatosIncorrectos(JLabel jLabelMensaje, String mensaje){
        
        Timer miTimer = new Timer();
        jLabelMensaje.setText(mensaje);
        jLabelMensaje.setForeground(Color.RED);
       
        TimerTask miTarea = new TimerTask(){
            @Override
            public void run() {
                jLabelMensaje.setText("");
            }
        };
        miTimer.schedule(miTarea, 3000);       
    }

    // NO ESTA FUNCIONANDO EN LOGIN, NO TIRA MENSAJE
    // método para crear jLable de datos correctos
    public void DatosCorrectos(JLabel jLabelMensaje, String mensaje){

        Timer miTimer = new Timer();
        jLabelMensaje.setText(mensaje);
        jLabelMensaje.setForeground(Color.GREEN);
       
        TimerTask miTarea = new TimerTask(){
            @Override
            public void run() {
                jLabelMensaje.setText("");
            }
        };
        miTimer.schedule(miTarea, 2000);  
        
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        }
        
}
