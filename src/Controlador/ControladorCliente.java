/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Pollo;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.CRUDAves;
import Vista.CRUDClientes;
import Vista.CRUDProveedor;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Eddy
 */
public class ControladorCliente implements ActionListener,MouseListener{

    private CRUDClientes vistaClientes;
    private Cliente modeloCliente;
    private Principal principal;
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    
    
    public ControladorCliente(Principal frame) {
        modeloCliente = new Cliente();
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaClientes = new CRUDClientes();
        if(estacerrado(vistaClientes)){
            principal.contenedor.add(vistaClientes);
            vistaClientes.setLocation(centradoXY(vistaClientes));
            vistaClientes.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaClientes.btn_registrarC.setActionCommand("guardarRegistro");
            vistaClientes.btn_eliminarC.setActionCommand("eliminarRegistro");
            vistaClientes.btn_actualizarCom.setActionCommand("actualizarRegistro");
            vistaClientes.btn_reporteP.setActionCommand("reporte");
            vistaClientes.btn_limpiarCasillas.setActionCommand("limpiar");
        
         
            /*Agregar los escuchas*/
            vistaClientes.btn_registrarC.addActionListener(this);
            vistaClientes.btn_eliminarC.addActionListener(this);
            vistaClientes.btn_actualizarCom.addActionListener(this);
            vistaClientes.btn_reporteP.addActionListener(this);
            vistaClientes.btn_limpiarCasillas.addActionListener(this);

            /*Enalazar la tabla*/
            vistaClientes.tablaClientes.addMouseListener(this);
            vistaClientes.tablaClientes.setModel(this.modeloCliente.obtenerClientes(""));
            
            //vistaClientes.txt_telefono.setValue(new Integer(0));

            /*Oculto la Columna ID de la tabla*/
            vistaClientes.tablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
            vistaClientes.tablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
            vistaClientes.tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);


            limpiarCajas();
            habilitarBotones(true,false,false);
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Agregar, Modificar, Eliminar Clientes");
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
          
            Cliente eliminar=new Cliente();
            int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el Registro","AVISO",JOptionPane.YES_NO_OPTION);

                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarCliente(id))
                             {
                                limpiarCajas();
                                habilitarBotones(true,false,false);
                                cargarTablaClientes();
                                JOptionPane.showMessageDialog(vistaClientes, "Registro eliminado correctamente");
                                
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(vistaClientes, "Ocurrio un error al eliminar el Registro, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    vistaClientes.remove(dialogButton);
                }

         
            
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "guardarRegistro":
            
            if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }

            if(validarEspacios(vistaClientes.txt_nombre.getText().toString(),vistaClientes.txt_telefono.getText().toString(),
                    vistaClientes.txt_direccion.getText().toString()))
            {
                nombre = vistaClientes.txt_nombre.getText();
                telefono = vistaClientes.txt_telefono.getText();
                direccion = vistaClientes.txt_direccion.getText();

                Cliente datos = new Cliente(0,nombre,telefono,direccion);
                
                if(datos.registrarCliente(datos))
                {
                    cargarTablaClientes();
                    limpiarCajas();
                    JOptionPane.showMessageDialog(vistaClientes, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaClientes, "Ocurrio un error al registrar el Proveedor, \n Inténtelo nuevamente");
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
            if(validarEspacios(vistaClientes.txt_nombre.getText().toString(),vistaClientes.txt_telefono.getText().toString(),
                    vistaClientes.txt_direccion.getText().toString()))
            {
                
                nombre = vistaClientes.txt_nombre.getText();
                telefono = vistaClientes.txt_telefono.getText();
                direccion = vistaClientes.txt_direccion.getText();
                
                Cliente act = new Cliente(id,nombre,telefono,direccion);
                
                if(modeloCliente.actualizarCliente(act))
                {
                     cargarTablaClientes();
                     limpiarCajas();
                     habilitarBotones(true,false,false);
                     JOptionPane.showMessageDialog(vistaClientes, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaClientes, "Ocurrio un error al actualizar el registro, \n Inténtelo nuevamente");
                   limpiarCajas();
                }
                
            }
            else
            {
             JOptionPane.showMessageDialog(vistaClientes, "Existe una casilla en blanco, Verifique");   
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
        
        if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = vistaClientes.tablaClientes.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                habilitarBotones(false,true,true);   

                id=Integer.valueOf(String.valueOf(vistaClientes.tablaClientes.getValueAt(fila, 0)));
               
                vistaClientes.txt_nombre.setText(String.valueOf(vistaClientes.tablaClientes.getValueAt(fila,1)));
                vistaClientes.txt_telefono.setText(String.valueOf(vistaClientes.tablaClientes.getValueAt(fila, 2)));
                vistaClientes.txt_direccion.setText(String.valueOf(vistaClientes.tablaClientes.getValueAt(fila, 3)));
         
             
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
    
    private void cargarTablaClientes()
    {
        
         vistaClientes.tablaClientes.setModel(modeloCliente.obtenerClientes(""));
         
         /*Oculto la Columna ID de la tabla */
         vistaClientes.tablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaClientes.tablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
         vistaClientes.tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cinco Botones*/
        vistaClientes.btn_registrarC.setEnabled(estado[0]);
        vistaClientes.btn_eliminarC.setEnabled(estado[1]);
        vistaClientes.btn_actualizarCom.setEnabled(estado[2]);
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
 
        vistaClientes.txt_nombre.setText("");
        vistaClientes.txt_telefono.setText("0000000000");
        vistaClientes.txt_direccion.setText("");
   
        
    }
}
