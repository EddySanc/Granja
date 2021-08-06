/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Pollo;
import Modelo.Privilegios;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.CRUDAves;
import Vista.CRUDClientes;
import Vista.CRUDProveedor;
import Vista.CRUDUsuarios;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Eddy
 */
public class ControladorUsuario implements ActionListener,MouseListener{

    private CRUDUsuarios vistaUsuarios;
    private Privilegios modeloPrivilegios;
    private Usuario modeloUsuario;
    private Principal principal;
    private int id;
    private String nombre;
    private String usuario;
    private String pwd;
    private int estado;
    
    ArrayList<String> privilegios = new ArrayList<>();
    
    
    public ControladorUsuario(Principal frame) {
        modeloUsuario = new Usuario();
        modeloPrivilegios = new Privilegios();
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaUsuarios = new CRUDUsuarios();
        if(estacerrado(vistaUsuarios)){
            principal.contenedor.add(vistaUsuarios);
            vistaUsuarios.setLocation(centradoXY(vistaUsuarios));
            vistaUsuarios.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaUsuarios.btn_registrarUsu.setActionCommand("guardarRegistro");
            vistaUsuarios.btn_eliminarUsu.setActionCommand("eliminarRegistro");
            vistaUsuarios.btn_actualizarUsu.setActionCommand("actualizarRegistro");
            vistaUsuarios.btn_reporte.setActionCommand("reporte");
            vistaUsuarios.btn_limpiarCasillas.setActionCommand("limpiar");
        
         
            /*Agregar los escuchas*/
            vistaUsuarios.btn_registrarUsu.addActionListener(this);
            vistaUsuarios.btn_eliminarUsu.addActionListener(this);
            vistaUsuarios.btn_actualizarUsu.addActionListener(this);
            vistaUsuarios.btn_reporte.addActionListener(this);
            vistaUsuarios.btn_limpiarCasillas.addActionListener(this);

            /*Enalazar la tabla*/
            vistaUsuarios.tablaEmpleados.addMouseListener(this);
            vistaUsuarios.tablaEmpleados.setModel(this.modeloUsuario.obtenerUsuarios(""));
            
            

            /*Oculto la Columna ID de la tabla*/
            vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setMaxWidth(0);
            vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setMinWidth(0);
            vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);


            limpiarCajas();
            habilitarBotones(true,false,false);
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Agregar, Modificar, Empleeados");
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
       
        case "eliminarRegistro":
          
            if(Usuario.eliminar == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
            }
            Usuario eliminar=new Usuario();
            int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el Registro","AVISO",JOptionPane.YES_NO_OPTION);

                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarUsuario(id))
                             {
                                limpiarCajas();
                                habilitarBotones(true,false,false);
                                cargarTablaUsuarios();
                                JOptionPane.showMessageDialog(vistaUsuarios, "Registro eliminado correctamente");
                                
                             }
                             else
                             {
                                 limpiarCajas();
                                 JOptionPane.showMessageDialog(vistaUsuarios, "Ocurrio un error al eliminar el Registro, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    vistaUsuarios.remove(dialogButton);
                }

         
            
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "guardarRegistro":
            if(Usuario.crear == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
            }
            if(vistaUsuarios.checkCrear.isSelected())
                privilegios.add("1");
            if(vistaUsuarios.checkModificar.isSelected())
                privilegios.add("2");
            if(vistaUsuarios.checkEliminar.isSelected())
                privilegios.add("3");
            if(vistaUsuarios.checkVentas.isSelected())
                privilegios.add("4");
   

            if(validarEspacios(vistaUsuarios.txt_nombre.getText().toString(),vistaUsuarios.txt_usuario.getText().toString(),
                    vistaUsuarios.txt_pwd.getText().toString()))
            {
                nombre = vistaUsuarios.txt_nombre.getText();
                usuario = vistaUsuarios.txt_usuario.getText();
                pwd = vistaUsuarios.txt_pwd.getText();

                Usuario datos = new Usuario(0,nombre,usuario,pwd);
                
                if(datos.registrarUsuario(datos,privilegios))
                {
                    cargarTablaUsuarios();
                    limpiarCajas();
                    JOptionPane.showMessageDialog(vistaUsuarios, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaUsuarios, "Ocurrio un error al registrar el Usuario, \n Inténtelo nuevamente");
                   limpiarCajas();
    
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(principal, "Existe una casilla en blanco, Verifique");
            }
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/            
        case "actualizarRegistro":
            
            if(Usuario.modificar == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
            }
            
            if(vistaUsuarios.checkCrear.isSelected())
                privilegios.add("1");
            if(vistaUsuarios.checkModificar.isSelected())
                privilegios.add("2");
            if(vistaUsuarios.checkEliminar.isSelected())
                privilegios.add("3");
            if(vistaUsuarios.checkVentas.isSelected())
                privilegios.add("4");
   
            if(validarEspacios(vistaUsuarios.txt_nombre.getText().toString(),vistaUsuarios.txt_usuario.getText().toString(),
                    vistaUsuarios.txt_pwd.getText().toString()))
            {
                
                nombre = vistaUsuarios.txt_nombre.getText();
                usuario = vistaUsuarios.txt_usuario.getText();
                pwd = vistaUsuarios.txt_pwd.getText();
                
                Usuario act = new Usuario(id,nombre,usuario,pwd);
                
                if(modeloUsuario.actualizarUsuario(act,privilegios))
                {
                     cargarTablaUsuarios();
                     limpiarCajas();
                     habilitarBotones(true,false,false);
                     JOptionPane.showMessageDialog(vistaUsuarios, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaUsuarios, "Ocurrio un error al actualizar el registro, \n Inténtelo nuevamente");
                   limpiarCajas();
                }
                
            }
            else
            {
             JOptionPane.showMessageDialog(vistaUsuarios, "Existe una casilla en blanco, Verifique");   
            }
            break;
        case "limpiar":

            limpiarCajas();
            habilitarBotones(true,false,false);
            break;
            
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<Privilegios> listPriv = new ArrayList<>();
        
        if( e.getButton()== 1)//boton izquierdo
        {
            
            
            
             int fila = vistaUsuarios.tablaEmpleados.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                habilitarBotones(false,true,true);   
                
                vistaUsuarios.checkCrear.setSelected(false);
                vistaUsuarios.checkModificar.setSelected(false);
                vistaUsuarios.checkEliminar.setSelected(false);
                vistaUsuarios.checkVentas.setSelected(false);
                
                id=Integer.valueOf(String.valueOf(vistaUsuarios.tablaEmpleados.getValueAt(fila, 0)));
               
                vistaUsuarios.txt_nombre.setText(String.valueOf(vistaUsuarios.tablaEmpleados.getValueAt(fila,1)));
                vistaUsuarios.txt_usuario.setText(String.valueOf(vistaUsuarios.tablaEmpleados.getValueAt(fila, 2)));
                vistaUsuarios.txt_pwd.setText(String.valueOf(vistaUsuarios.tablaEmpleados.getValueAt(fila, 3)));
                
                listPriv = modeloPrivilegios.ObtenerPrivilegios(id);
                if(!listPriv.isEmpty()){
                    for(int i = 0; i<listPriv.size(); i++){
                    
                        switch(listPriv.get(i).getId()){
                            case 1:
                                vistaUsuarios.checkCrear.setSelected(true);
                                break;
                            case 2: 
                                vistaUsuarios.checkModificar.setSelected(true);
                                break;
                            case 3:
                                vistaUsuarios.checkEliminar.setSelected(true);
                                break;
                            case 4:
                                vistaUsuarios.checkVentas.setSelected(true);
                                break;
                        
                        }
                        
                    }
                }
         
             
             }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cargarTablaUsuarios()
    {
        
         vistaUsuarios.tablaEmpleados.setModel(modeloUsuario.obtenerUsuarios(""));
         
         /*Oculto la Columna ID de la tabla */
         vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setMinWidth(0);
         vistaUsuarios.tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cinco Botones*/
        vistaUsuarios.btn_registrarUsu.setEnabled(estado[0]);
        vistaUsuarios.btn_eliminarUsu.setEnabled(estado[1]);
        vistaUsuarios.btn_actualizarUsu.setEnabled(estado[2]);
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
        id = 0;
        
        privilegios.clear();
      
        vistaUsuarios.txt_nombre.setText("");
        vistaUsuarios.txt_usuario.setText("");
        vistaUsuarios.txt_pwd.setText("");
   
        vistaUsuarios.checkCrear.setSelected(false);
        vistaUsuarios.checkModificar.setSelected(false);
        vistaUsuarios.checkEliminar.setSelected(false);
        vistaUsuarios.checkVentas.setSelected(false);
        
    }
}
