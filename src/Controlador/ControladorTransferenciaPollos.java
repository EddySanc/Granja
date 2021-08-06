/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Credito;
import Modelo.PedidosVenta;
import Modelo.Pollo;        
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.CRUDAves;
import Vista.CRUDClientes;
import Vista.CRUDProveedor;
import Vista.Facturar;
import Vista.Principal;
import Vista.Transferencia_Pollo;
import Vista.Venta_Credito;
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
public class ControladorTransferenciaPollos implements ActionListener,MouseListener{

    private Transferencia_Pollo vistaTransferencia;
    private Pollo modeloPollo;
    private ControladorPollos controlPollos;
    private Principal principal;
    private String folioDescontar;
    private String folioAgregar;
    private int cantidadRestante;
    private int cantidadDescontar;
    private double kilos;

    
    
    public ControladorTransferenciaPollos(Principal frame,String folio,int cantidad) {
        this.folioDescontar = folio;
        this.cantidadRestante = cantidad;
        modeloPollo = new Pollo();
                
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaTransferencia = new Transferencia_Pollo();
        if(estacerrado(vistaTransferencia)){
            principal.contenedor.add(vistaTransferencia);
            vistaTransferencia.setLocation(centradoXY(vistaTransferencia));
            vistaTransferencia.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaTransferencia.btn_transferir.setActionCommand("guardarRegistro");
            vistaTransferencia.btn_transferir.addActionListener(this);
            
  
            
            vistaTransferencia.btn_cancelar.setActionCommand("cancelar");
            vistaTransferencia.btn_cancelar.addActionListener(this);
            
            /*Enalazar la tabla*/
             vistaTransferencia.tablaPollos.addMouseListener(this);
             cargarTablaPollos("");
           
            
            
            vistaTransferencia.txt_folioDescontar.setText("<html>  <style type=\"text/css\">  .estilo1{font-family:Dialog;font-weight:bold; font-size:12px;color:rgb(0, 0, 0);}  .estilo2{font-family:Dialog;font-weight:bold; font-size:10px;color:rgb(153, 153, 153);}  </style>  <span class=\"estilo1\"><u>"+folioDescontar+"</u></span><br/>   </html>  ");
            vistaTransferencia.txt_restantes.disable();
            vistaTransferencia.txt_restantes.setText(cantidadRestante+"");


            limpiarCajas();
            habilitarBotones(false);
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Transferencia");
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
                 
        case "guardarRegistro":
            if(Usuario.crear == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
            }

            if(validarEspacios(vistaTransferencia.txt_cantidad.getText().toString(),vistaTransferencia.txt_kilos.getText().toString()))
            {
                cantidadDescontar =Integer.parseInt(vistaTransferencia.txt_cantidad.getText().toString());
                kilos =Double.parseDouble(vistaTransferencia.txt_kilos.getText().toString());
                
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar la tranferencia de "+cantidadDescontar+" Pollos a la factura: "+folioAgregar+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp==0)
                  {
                    if(verificarFactura()){
                        if(verificarMontos()){  
                            if(modeloPollo.Transferir(folioDescontar, folioAgregar,cantidadDescontar,kilos))
                            {

                                JOptionPane.showMessageDialog(vistaTransferencia, "Tranferencia Exitosa");

                                controlPollos = new ControladorPollos(principal);
                                this.vistaTransferencia.dispose();
                            }
                            else
                            {
                               JOptionPane.showMessageDialog(vistaTransferencia, "Ocurrio un error al realizar la tranferencia, \n Inténtelo nuevamente");
                               limpiarCajas();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(principal,"La CANTIDAD de pollos es superior al restante del folio "+folioDescontar+", o esta agregando una cantidad no valida");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(principal,"No se puede hacer la tranferencia al mismo folio");
                    }
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(principal, "Existe una casilla en blanco, Verifique");
            }
            break;
            
        case "limpiar":
            limpiarCajas();
            habilitarBotones(false);
            break;
     
        case "cancelar":
            controlPollos = new ControladorPollos(principal);
            this.vistaTransferencia.dispose();
            break;
            
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = vistaTransferencia.tablaPollos.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                habilitarBotones(true);   

                folioAgregar=String.valueOf(vistaTransferencia.tablaPollos.getValueAt(fila, 0));
                vistaTransferencia.txt_folioAgregar.setText(folioAgregar);

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
    
    private void cargarTablaPollos(String busqueda)
    {
        
         vistaTransferencia.tablaPollos.setModel(modeloPollo.obtenerPollosTransferencia());
         
         /*Oculto la Columna ID de la tabla */
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cinco Botones*/
        vistaTransferencia.btn_transferir.setEnabled(estado[0]);
 
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
        
        vistaTransferencia.txt_kilos.setText("");
        vistaTransferencia.txt_cantidad.setText("");
        
   
        
    }
    
    public boolean verificarMontos(){
    
        if(cantidadRestante>=Integer.parseInt(vistaTransferencia.txt_cantidad.getText()) && Double.parseDouble(vistaTransferencia.txt_cantidad.getText())>0 ){
            return true;
        }
        return false;
    }
    
    public boolean verificarFactura(){
    
        if(folioAgregar.equals(folioDescontar)){
            return false;
        }
        else{
            return true;
        }
        
    }
}
