/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.io.IOException;
import jssc.*;

/**
 *
 * @author jhon
 */
public class PuertoSerial {

public static String[] verPuertos()
{
    String[] portNames = SerialPortList.getPortNames();
        
if (portNames.length == 0) {
    //System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
    //System.out.println("Press Enter to exit...");
    try {
        System.in.read();
    } catch (IOException e) {
         // TODO Auto-generated catch block
          e.printStackTrace();
    }
    //return;
}
/*
for (int i = 0; i < portNames.length; i++){
    System.out.println(portNames[i]);
}*/

return portNames;

}





}