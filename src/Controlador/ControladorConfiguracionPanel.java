/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Hash;
import Modelo.ModeloConfiguracion;
import Vista.ConfiguracionPanel;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class ControladorConfiguracionPanel implements ActionListener
{
    
    private ConfiguracionPanel vistaConfiguracion;
    private ModeloConfiguracion modeloConfiguracion;
    public static String cadena_datos="bBWaMXrhYZ]BCP]x[c:n";
    
    public ControladorConfiguracionPanel(ConfiguracionPanel vistaConfiguracion)
    {
        this.vistaConfiguracion=vistaConfiguracion;
        modeloConfiguracion=new ModeloConfiguracion();
        this.vistaConfiguracion.btn_registrar.setActionCommand("registrar");
        this.vistaConfiguracion.btn_registrar.addActionListener(this);
        
        
        evaluar();
        
        
    }
    
    
    
    public void evaluar()
    {
        ArrayList<ModeloConfiguracion> lista=modeloConfiguracion.obtenerConfiguracion();
        System.out.println("Tamaño"+lista.size());
        if(lista.size()<=0)
        {
            abrirVentana();
        }
        else
        {
                String datos[]=new String[4];
                datos[0]=lista.get(0).getNombre();
                datos[1]=lista.get(0).getDireccion();
                datos[2]=lista.get(0).getTelefono();
                datos[3]=lista.get(0).getCodigo();
                 if(datos[3]==null )
                {
                    JOptionPane.showMessageDialog(null, "Licencia no válida\n El sistema se cerrará");
                    System.exit(0);
                }
                else if(modeloConfiguracion.getCode().equals(Hash.sha1(cadena_datos)))
                {

                    Principal y=new Principal();
                    ControladorPrincipal x=new ControladorPrincipal(y);
                }
                else
                {
                     JOptionPane.showMessageDialog(null, "Licencia no válida\n El sistema se cerrará");
                    System.exit(0);
                }
        
        }
        
        
      
        
       
        
        

    }
    
    
    
    public void abrirVentana()
    {
        this.vistaConfiguracion.setVisible(true);
        this.vistaConfiguracion.setTitle("Ventana de Inicio");
        this.vistaConfiguracion.setLocationRelativeTo(null);
        this.limpiar();
    }
    
    private void limpiar()
    {
        this.vistaConfiguracion.txt_codigo.setText("");
               this.vistaConfiguracion.txt_direccion.setText("");
                      this.vistaConfiguracion.txt_nombre.setText("");
                             this.vistaConfiguracion.txt_telefono.setText("");
        
    }
    
  

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       
          String accion=e.getActionCommand();
          switch(accion)
          {
              case "registrar":
                  modeloConfiguracion.setNombre(this.vistaConfiguracion.txt_nombre.getText());
                  modeloConfiguracion.setDireccion(this.vistaConfiguracion.txt_direccion.getText());
                  modeloConfiguracion.setTelefono(this.vistaConfiguracion.txt_telefono.getText());
                  modeloConfiguracion.setCodigo(this.vistaConfiguracion.txt_codigo.getText());
                  if(modeloConfiguracion.registrarNegocio(modeloConfiguracion))
                  {
                      JOptionPane.showMessageDialog(null, "Datos guardados exitosamente\n Favor de volver abrir la aplicación");
                      System.exit(0);
                            
                  }
                  
                  break;
              default:
                  System.exit(0);
                  break;
              
          }
    }
    
    
    
    
}
