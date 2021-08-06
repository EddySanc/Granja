/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

//import Java_Arduino.ArduinoRX.JavaRX;
import Vista.Pedidos;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author jhon
 */
public class SerialES 
{
    
    
    
     public static final PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
     private Pedidos vistaPedidos;
     
     public SerialES(String puerto)
             {
                   try {
                       
                       
                       ino.arduinoRXTX(puerto, 9600, listener);
            //System.out.println("as");
                        } 
                   catch (ArduinoException ex) {
            Logger.getLogger(SerialES.class.getName()).log(Level.SEVERE, null, ex);
}
             }
     
     
    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    //jLabelOutput.setText("Resultado: " + ino.printMessage());
                    //System.out.println("Resultado: "+ino.printMessage());
                    //vistaPedidos.txt_kilos.setText(""+ino.printMessage());
                    Pedidos.txt_kilos.setText(""+ino.printMessage());
                }
                else
                {
               // System.out.println("nada");
                }
            } catch (SerialPortException | ArduinoException ex) {
               JOptionPane.showMessageDialog(null, "No se puede comunicar con la báscula 2: "+ex.getLocalizedMessage());
            }
        }
};
    
    
    public static void solicitarPeso()
    {
        try {
            ino.sendData("P");
        } catch (ArduinoException | SerialPortException ex) {
            //Logger.getLogger("Error: "+ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "No se puede comunicar con la báscula"+ex.getLocalizedMessage());
}
    }
    
}
