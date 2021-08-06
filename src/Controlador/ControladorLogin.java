/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Usuario;
import Vista.Principal;
import Vista.Sesion;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Eddy
 */
public class ControladorLogin implements ActionListener{

    private Sesion vistaLogin;
    private Usuario modeloUsuario;
    private Principal principal;
    private ControladorPrincipal controlPincipal;

    
    public ControladorLogin(Principal frame) {
        modeloUsuario = new Usuario();
        principal = frame;
        AbrirFrame();
        
        
    }
    

    
    private void AbrirFrame(){
        vistaLogin = new Sesion();
        if(estacerrado(vistaLogin)){
            principal.contenedor.add(vistaLogin);
            vistaLogin.setLocation(centradoXY(vistaLogin));
            vistaLogin.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaLogin.btn_entrar.setActionCommand("login");
         
         
            /*Agregar los escuchas*/
            vistaLogin.btn_entrar.addActionListener(this);
   


            limpiarCajas();
         
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Login");
        }
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
    
    private boolean estacerrado(JInternalFrame obj)
    {
        JInternalFrame[] activos=principal.contenedor.getAllFrames();
        boolean cerrado=true;
        int i=0;
        
        while (i<activos.length && cerrado){
            
            if(activos[i].getClass()==obj.getClass()){
                
                cerrado=false;
            }
                
            i++;
        }
        return cerrado;
       }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String accion=e.getActionCommand();
   
    
    switch(accion)
    {    
       
        
        case "login":

            String usuario = vistaLogin.txt_usuario.getText().toString();
            String pwd = vistaLogin.txt_pwd.getText().toString();
            
            if(validarEspacios(usuario,pwd)){
                
                if(modeloUsuario.Login(usuario,pwd)){
                   
                    controlPincipal = new ControladorPrincipal(principal,0);
                    controlPincipal.Act_Desac_Opciones(1,principal.Proveedores,principal.Aves,
                    principal.Carros,principal.Carros,principal.Clientes,
                    principal.Ventas,principal.Reportes,
                    principal.Configuraciones,principal.Salir);
                    
                    this.vistaLogin.dispose();
                    
                    JOptionPane.showMessageDialog(vistaLogin,"Bienvenido");
                }
                else{
                    JOptionPane.showMessageDialog(vistaLogin,"Usuario y/o Contraseña incorectos, verifique su información");
                }
            
            }
            else{
                JOptionPane.showMessageDialog(vistaLogin,"Existe una casilla en blanco, Verifique");
            }
            
            
            break;
            
    }
    }


    public boolean validarEspacios(String ...x)
    {
        
        boolean estado=false;
        for(int i=0; i<x.length; i++)
        {
            if(x[i].equalsIgnoreCase(""))
            {
                i=x.length+2;
                estado = false;
            }
            else
            {
                estado=true;
            }
        }
        
        return estado;
    }
    
    public void limpiarCajas()
    {
      
        vistaLogin.txt_usuario.setText("");
        vistaLogin.txt_pwd.setText("");
   
        
    }
}
