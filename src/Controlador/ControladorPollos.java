/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Pollo;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.CRUDAves;
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
public class ControladorPollos implements ActionListener,MouseListener{

    private CRUDAves vistaAves;
    private Pollo modeloPollo;
    private Principal principal;
    private String folio;
    private int cantidad = 0;
    private Double kilos;
    private int tipo;
    private int proveedor = 0;
    private String observacion;
    int cantidad_anterior = 0;
    
    
    public ControladorPollos(Principal frame) {
        modeloPollo = new Pollo();
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaAves = new CRUDAves();
        if(estacerrado(vistaAves)){
            principal.contenedor.add(vistaAves);
            vistaAves.setLocation(centradoXY(vistaAves));
            vistaAves.setVisible(true);
            modeloPollo.LlenarComboProveedor(vistaAves);
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
         vistaAves.btn_registrarC.setActionCommand("guardarRegistro");
         vistaAves.btn_eliminarC.setActionCommand("eliminarRegistro");
         vistaAves.btn_actualizarCom.setActionCommand("actualizarRegistro");
         vistaAves.btn_reporteP.setActionCommand("reporte");
         vistaAves.btn_limpiarCasillas.setActionCommand("limpiar");
         vistaAves.btn_transferir.setActionCommand("transferir");
        
         
         /*Agregar los escuchas*/
         vistaAves.btn_registrarC.addActionListener(this);
         vistaAves.btn_eliminarC.addActionListener(this);
         vistaAves.btn_actualizarCom.addActionListener(this);
         vistaAves.btn_reporteP.addActionListener(this);
         vistaAves.btn_limpiarCasillas.addActionListener(this);
         vistaAves.btn_transferir.addActionListener(this);
                
         /*Enalazar la tabla*/
         vistaAves.tablaPollos.addMouseListener(this);
         vistaAves.tablaPollos.setModel(this.modeloPollo.obtenerPollos(""));
         
        
        vistaAves.txt_total.setText(modeloPollo.CantidadPollos()+"");
         
         
         //modeloProveedor.limpiarCajas(vistaProveedor);
         habilitarBotones(true,false,false,false,false);
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Entrada de Aves");
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
            Pollo eliminar=new Pollo();
            int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el Registro","AVISO",JOptionPane.YES_NO_OPTION);

                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarPollo(folio,cantidad))
                             {
                                limpiarCajas();
                                habilitarBotones(true,false,false,false,false);
                                cargarTablaPollos();
                                JOptionPane.showMessageDialog(vistaAves, "Registro eliminado correctamente");
                                
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(vistaAves, "Ocurrio un error al eliminar el Registro, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    vistaAves.remove(dialogButton);
                }

         
            
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "guardarRegistro":
            if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
             proveedor =  modeloPollo.GetIdProveedor(vistaAves.cmb_pollos.getSelectedItem().toString());
             
            if(validarEspacios(vistaAves.txt_folio.getText().toString(),vistaAves.txt_cantidad.getText().toString(),
                    vistaAves.txt_kilos.getText().toString()))
            {
            folio = vistaAves.txt_folio.getText();
            cantidad = Integer.parseInt(vistaAves.txt_cantidad.getText());
            kilos = Double.parseDouble(vistaAves.txt_kilos.getText());
            tipo = 1;
            
            
                
                Pollo datos = new Pollo(folio,cantidad,"",proveedor,kilos,tipo,"Entrada de productos");
                if(datos.registrarPollos(datos))
                {
                   
                    limpiarCajas();
                    habilitarBotones(true,false,false,false,false);
                    cargarTablaPollos();
                    JOptionPane.showMessageDialog(vistaAves, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaAves, "Ocurrio un error al insertar el registro, \n Inténtelo nuevamente");
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
            
            proveedor =  modeloPollo.GetIdProveedor(vistaAves.cmb_pollos.getSelectedItem().toString());
            
            
            if(validarEspacios(vistaAves.txt_cantidad.getText().toString(),
                    vistaAves.txt_kilos.getText().toString()
                    ))
            {
                
                
                folio = vistaAves.txt_folio.getText();
                cantidad = Integer.parseInt(vistaAves.txt_cantidad.getText());
                kilos = Double.parseDouble(vistaAves.txt_kilos.getText());
                tipo = 1;
                
                
                Pollo act = new Pollo(folio,cantidad,"",proveedor,kilos,tipo,"Entrada de productos");
                if(modeloPollo.actualizarPollo(act,cantidad_anterior))
                {
                    limpiarCajas();
                    habilitarBotones(true,false,false,false,false);
                    cargarTablaPollos();
                    vistaAves.txt_folio.setEnabled(true);
                    JOptionPane.showMessageDialog(vistaAves, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaAves, "Ocurrio un error al actualizar el registro, \n Inténtelo nuevamente");
                   limpiarCajas();
                }
                
            }
            else
            {
             JOptionPane.showMessageDialog(vistaAves, "Existe una casilla en blanco, Verifique");   
            }
            break;
        case "limpiar":

            limpiarCajas();
            vistaAves.txt_folio.setEnabled(true);
            habilitarBotones(true,false,false,false,false);
            break;
        case "reporte":
            
            modeloPollo.GenerarReporte(folio);

            break;
            
        case "transferir":
            if(verificarCantidad()){
                ControladorTransferenciaPollos trans = new ControladorTransferenciaPollos(principal, folio, cantidad);
                this.vistaAves.dispose();
            }
            else{
                JOptionPane.showMessageDialog(vistaAves, "La cantidad de la factura es 0, no se puede realizar la tranferencia");
            }
            break;
            
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = vistaAves.tablaPollos.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                habilitarBotones(false,true,true,true,true);   

                folio = String.valueOf(vistaAves.tablaPollos.getValueAt(fila, 0));
                cantidad = Integer.valueOf(String.valueOf(vistaAves.tablaPollos.getValueAt(fila,1)));
                cantidad_anterior = Integer.valueOf(String.valueOf(vistaAves.tablaPollos.getValueAt(fila,1)));
                vistaAves.txt_folio.setText(folio);
                vistaAves.txt_folio.setEnabled(false);
                vistaAves.txt_cantidad.setText(String.valueOf(vistaAves.tablaPollos.getValueAt(fila,1)));
                vistaAves.cmb_pollos.setSelectedItem(String.valueOf(vistaAves.tablaPollos.getValueAt(fila, 3)));
                vistaAves.txt_kilos.setText(String.valueOf(vistaAves.tablaPollos.getValueAt(fila, 4)));
                
               
                
             
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
    
    private void cargarTablaPollos()
    {
        
         vistaAves.tablaPollos.setModel(modeloPollo.obtenerPollos(""));
         vistaAves.txt_total.setText(modeloPollo.CantidadPollos()+"");
         
         vistaAves.tablaPollos.getColumnModel().getColumn(4).setMaxWidth(0);
         vistaAves.tablaPollos.getColumnModel().getColumn(4).setMinWidth(0);
         vistaAves.tablaPollos.getColumnModel().getColumn(4).setPreferredWidth(0);
    
         
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cuatro Botones*/
        vistaAves.btn_registrarC.setEnabled(estado[0]);
        vistaAves.btn_eliminarC.setEnabled(estado[1]);
        vistaAves.btn_actualizarCom.setEnabled(estado[2]);
        vistaAves.btn_transferir.setEnabled(estado[3]);
        vistaAves.btn_reporteP.setEnabled(estado[4]);
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
        if(proveedor == 0 ){
            estado = false;
        }
        return estado;
    }
    
    public void limpiarCajas()
    {
        folio = "";
        proveedor = 0;
        cantidad = 0;
        vistaAves.txt_folio.setText("");
        vistaAves.txt_cantidad.setText("");
        vistaAves.txt_kilos.setText("");
        modeloPollo.LlenarComboProveedor(vistaAves);        
    }
    public boolean verificarCantidad()
    {
        if(cantidad!=0){
            return true;
        }
        else{
            return false;
        }
            
        
    }
    
}
