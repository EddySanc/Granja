/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Crear;
import Modelo.Usuario;
import Vista.Bascula;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhon
 */
public class ControladorBascula implements ActionListener
{
    private Principal principal;
    private Bascula vistaBascula;
    
    public ControladorBascula(Principal principal,Bascula vistaBascula)
    {
        this.principal=principal;
        this.vistaBascula=vistaBascula;
        llenarPuertos();
        cargarPuerto();
    }
    
    public void abrirVentana()
    {
          this.principal.contenedor.add(vistaBascula);
        this.vistaBascula.setLocation(centradoXY(vistaBascula));
        this.vistaBascula.setVisible(true);
        
        this.vistaBascula.btn_registro.setActionCommand("registrar");
        this.vistaBascula.btn_registro.addActionListener(this);
        
        
        
    }
    
    
        
        /*_Centrar Jinternal*/
        private Point centradoXY(JInternalFrame jif)
    {
        Point p = new Point(0,0);
        //se obtiene dimension del contenedor
        Dimension pantalla = this.principal.contenedor.getSize();
        //se obtiene dimension del JInternalFrame
        Dimension ventana = jif.getSize();
        //se calcula posición para el centrado
        p.x = (pantalla.width - ventana.width) / 2;
        p.y = (pantalla.height - ventana.height) / 2;
        return p;
    }

        private void llenarPuertos()
        {
              this.vistaBascula.listaPuertos.removeAllItems();
                 
                 String lista[]=PuertoSerial.verPuertos();
                 for(int i=0; i<lista.length; i++)
                 {
                     //System.out.println("Puerto Disponible: "+lista[i]);
                     this.vistaBascula.listaPuertos.addItem(lista[i]);
                 }
        }
        
        
        private void cargarPuerto()
        {
            this.vistaBascula.txt_puerto.setText(""+Crear.leerPuerto());
            //System.out.println(System.getProperty("user.dir") );
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         String accion=e.getActionCommand();
         
         switch(accion)
         {
             case "registrar":
                if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
                 try
                 {
                 String categoria1 = (String) vistaBascula.listaPuertos.getSelectedItem();
                 Crear.escribir(categoria1);
                 cargarPuerto();
                 JOptionPane.showMessageDialog(principal,"Puerto asignado correctamente");
                 }
                 catch(Exception gt)
                 {
                     System.out.println("Error puerto: "+gt.getLocalizedMessage());
                 }
                 break;
                 
             default:
                 System.exit(0);
                 break;
         }
    }
    
}
