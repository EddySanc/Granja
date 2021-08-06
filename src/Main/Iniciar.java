/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.ControladorConfiguracionPanel;
import Controlador.ControladorPrincipal;
import Vista.ConfiguracionPanel;
import Vista.Principal;

/**
 *
 * @author jhon
 */
public class Iniciar {
    public static void main(String ...asd)
    {
       /* Principal y=new Principal();
        ControladorPrincipal x=new ControladorPrincipal(y);
        */
        ConfiguracionPanel x=new ConfiguracionPanel();
        ControladorConfiguracionPanel xy=new ControladorConfiguracionPanel(x);
    }
}
