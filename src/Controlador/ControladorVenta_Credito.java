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
import Vista.Venta_Credito;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Eddy
 */
public class ControladorVenta_Credito implements ActionListener,MouseListener{

    private Venta_Credito vistaCredito;
    private PedidosVenta modeloPedidos;
    private Cliente modeloCliente;
    private Credito modeloCredito;
    private ControladorFactura controlFactura;
    private Principal principal;
    private int idCliente;
    private int factura;
    private String nombre;
    private double totalcredito;
    DecimalFormat formateador = new DecimalFormat("####.##");
    
    
    public ControladorVenta_Credito(Principal frame,int factura) {
        this.factura = factura;
        modeloPedidos= new PedidosVenta();
        modeloCliente = new Cliente();
        modeloCredito = new Credito();
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaCredito = new Venta_Credito();
        if(estacerrado(vistaCredito)){
            principal.contenedor.add(vistaCredito);
            vistaCredito.setLocation(centradoXY(vistaCredito));
            vistaCredito.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaCredito.btn_registrarAbo.setActionCommand("guardarRegistro");
            vistaCredito.btn_registrarAbo.addActionListener(this);
            
            vistaCredito.txt_nombre.setActionCommand("buscarCliente");
            vistaCredito.txt_nombre.addActionListener(this);
            
            vistaCredito.btn_cancelar.setActionCommand("cancelar");
            vistaCredito.btn_cancelar.addActionListener(this);
            
            /*Enalazar la tabla*/
             vistaCredito.tablaClientes.addMouseListener(this);
            cargarTablaClientes("");
           
            
            
            vistaCredito.txt_factura.setText("<html>  <style type=\"text/css\">  .estilo1{font-family:Dialog;font-weight:bold; font-size:12px;color:rgb(0, 0, 0);}  .estilo2{font-family:Dialog;font-weight:bold; font-size:10px;color:rgb(153, 153, 153);}  </style>  <span class=\"estilo1\"><u>"+factura+"</u></span><br/>   </html>  ");
            totalcredito = modeloCredito.GetTotalCredito(factura);
            vistaCredito.txt_total.disable();
            
            vistaCredito.txt_total.setText("$"+formateador.format(totalcredito));


            limpiarCajas();
            habilitarBotones(false);
        }
        else
        {
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Venta a Credito");
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
            if(validarEspacios(vistaCredito.txt_abono.getText().toString()))
            {
                double monto =Double.parseDouble(vistaCredito.txt_abono.getText().toString());
                
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de agregar al cliente "+nombre+" a la lista de deudores?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp==0)
                  {
                    if(verificarMontos()){  
                        if(modeloPedidos.RegistrarVentaCredito(factura, idCliente,monto))
                        {
                            cargarTablaClientes("");
                            limpiarCajas();
                            habilitarBotones(false);
                            JOptionPane.showMessageDialog(vistaCredito, "Registro Exitoso");

                            controlFactura = new ControladorFactura(principal, new Facturar());
                            controlFactura.abrirVentana();
                            this.vistaCredito.dispose();
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(vistaCredito, "Ocurrio un error al registrar el credito, \n Inténtelo nuevamente");
                           limpiarCajas();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(principal,"El MONTO del pago es superior al restante de la cuenta, o valores negativos");
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
        case "buscarCliente":

            cargarTablaClientes(vistaCredito.txt_nombre.getText().toString());
            vistaCredito.txt_nombre.setText("");
            break;
        case "cancelar":
            controlFactura = new ControladorFactura(principal, new Facturar());
            controlFactura.abrirVentana();
            this.vistaCredito.dispose();
            break;
            
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = vistaCredito.tablaClientes.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                habilitarBotones(true);   

                idCliente=Integer.valueOf(String.valueOf(vistaCredito.tablaClientes.getValueAt(fila, 0)));
                nombre=String.valueOf(vistaCredito.tablaClientes.getValueAt(fila, 1));
                
                vistaCredito.txt_nombreCliente.setText(String.valueOf(vistaCredito.tablaClientes.getValueAt(fila,1)));
                
         
             
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
    
    private void cargarTablaClientes(String busqueda)
    {
        
         vistaCredito.tablaClientes.setModel(modeloCliente.obtenerClientes(busqueda));
         
         /*Oculto la Columna ID de la tabla */
         vistaCredito.tablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaCredito.tablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
         vistaCredito.tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cinco Botones*/
        vistaCredito.btn_registrarAbo.setEnabled(estado[0]);
 
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
        idCliente = 0;
 
        vistaCredito.txt_nombreCliente.setText("");
        vistaCredito.txt_abono.setText("");
        
   
        
    }
    
    public boolean verificarMontos(){
    
        if(totalcredito>=Double.parseDouble(vistaCredito.txt_abono.getText()) && Double.parseDouble(vistaCredito.txt_abono.getText())>=0 ){
            return true;
        }
        return false;
    }
}
