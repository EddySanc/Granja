/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Credito;
import Modelo.Usuario;
import Vista.CRUDClientes;
import Vista.CRUDCredito;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eddy
 */
public class ControladorCredito implements ActionListener,MouseListener,ItemListener{

    private CRUDCredito vistaCredito;
    private Credito modeloCredito;
    private Principal principal;
    private int idFactura;
    private int idCliente;
    private int idCredito;
    private int idAbono;
    private Double totalCredito;
    private Double totalAbonado;
    private String nombre="";
    
    private int estadoCredito = 3;
    
    private int raw;
    
    public ControladorCredito(Principal frame) {
        modeloCredito = new Credito();
        principal = frame;
        AbrirFrame();
        
        
    }

    
    private void AbrirFrame(){
        vistaCredito = new CRUDCredito();
        if(estacerrado(vistaCredito)){
            principal.contenedor.add(vistaCredito);
            vistaCredito.setLocation(centradoXY(vistaCredito));
            vistaCredito.setVisible(true);
            
            
            /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
            vistaCredito.btn_registrarAbono.setActionCommand("guardarRegistro");
            vistaCredito.btn_eliminarAbono.setActionCommand("eliminarRegistro");
            //vistaCredito.btn_actualizarAbono.setActionCommand("actualizarRegistro");
            vistaCredito.btn_reporteP.setActionCommand("reporte");
            vistaCredito.btn_limpiarCasillas.setActionCommand("limpiar");
            vistaCredito.txt_nombre.setActionCommand("buscarCliente");
        
        
         
            /*Agregar los escuchas*/
            vistaCredito.btn_registrarAbono.addActionListener(this);
            vistaCredito.btn_eliminarAbono.addActionListener(this);
            //vistaCredito.btn_actualizarAbono.addActionListener(this);
            vistaCredito.btn_reporteP.addActionListener(this);
            vistaCredito.btn_limpiarCasillas.addActionListener(this);
            vistaCredito.comboEstado.addItemListener(this);
            vistaCredito.txt_nombre.addActionListener(this);

            /*Enalazar la tabla*/
            vistaCredito.tablaCredito.addMouseListener(this);
            vistaCredito.tablaAbonos.addMouseListener(this);
            cargarTablaCreditos(estadoCredito,"");
            cargarTablaAbono();
            
            //vistaCredito.txt_cantidad.setValue(new Double(00000.00));
            llenarCombo();
            limpiarCajas();
            habilitarBotones(false,false,false);
        }
        else{
            JOptionPane.showMessageDialog(principal, "Ya tienes abierta la ventana de Creditos");
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
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el registro seleccionado ?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(resp==0)
                 {
                    if(modeloCredito.CuentaRegistros(idFactura)){ 
                        if(modeloCredito.eliminarAbono(idAbono)){
                            JOptionPane.showMessageDialog(principal,"Abono eliminado correctamnente");
                            cargarTablaAbono();
                        
                            habilitarBotones(true,false);
                            vistaCredito.tablaCredito.setValueAt("$"+modeloCredito.getAbonado(idFactura), raw, 7);
                            totalAbonado=Double.parseDouble(String.valueOf(vistaCredito.tablaCredito.getValueAt(raw, 7)).replace("$", ""));
                             vistaCredito.txt_pendiente.setText(getDecimal(0, totalCredito-totalAbonado)+"");
                        }
                        else{
                           JOptionPane.showMessageDialog(principal,"Ocurrio un error al eliminar el registro, ¡Intentalo de nuevo!");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(principal,"No se puede eliminar el ultimo registro, ¡Intente agregar un nuevo registro y eliminar el anterior!");
                    }
                 }
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "guardarRegistro":

            if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
            if(validarEspacios(vistaCredito.txt_cantidad.getText().toString())){
            
                if(verificarMontos()){
                    if(modeloCredito.registrarAbono(Double.parseDouble(vistaCredito.txt_cantidad.getText().toString()), idCliente,idFactura)){

                        JOptionPane.showMessageDialog(principal,"Pago realizado con exito");
                        
                        vistaCredito.tablaCredito.setValueAt("$"+modeloCredito.getAbonado(idFactura), raw, 7);
                        totalAbonado=Double.parseDouble(String.valueOf(vistaCredito.tablaCredito.getValueAt(raw, 7)).replace("$", ""));
                        
                        
                        if(totalCredito.equals(totalAbonado)){
                            JOptionPane.showMessageDialog(principal,"Cuenta Liquidada");
                            vistaCredito.tablaAbonos.setModel(modeloCredito.obtenerObonos(0));
                            cargarTablaCreditos(estadoCredito,"");
                            limpiarCajas();
                            habilitarBotones(false,false);
                        }
                        else{
                            
                            limpiarCajas();
                            vistaCredito.txt_pendiente.setText(getDecimal(0, totalCredito-totalAbonado)+"");
                            cargarTablaAbono();
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(principal,"Ocurrio un error al procesar el pago");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(principal,"El MONTO del pago es superior al restante de la cuenta, o esta en ceros");
                }
            }
            else{
                JOptionPane.showMessageDialog(principal,"La casilla MONTO esta vacía, por favor indique una cantidad");
            }
            
            
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/            
        case "limpiar":

            limpiarCajas();
            habilitarBotones(false,false);
            break;
        case "reporte":

            System.err.println("nombre "+nombre);
            modeloCredito.GenerarReporte(estadoCredito,nombre);
            break;
        case "buscarCliente":
            
            nombre = vistaCredito.txt_nombre.getText().toString();
            cargarTablaCreditos(estadoCredito,vistaCredito.txt_nombre.getText().toString());
            vistaCredito.txt_nombre.setText("");
            break;
    }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        
        
        
        if( e.getButton()== 1)//boton izquierdo
        {
            if(e.getSource().equals(this.vistaCredito.tablaCredito))
            {
                int fila = vistaCredito.tablaCredito.rowAtPoint(e.getPoint());
                if (fila > -1){                
                /*Habilitar/deshabilitar botones*/
                 

                idCliente=Integer.valueOf(String.valueOf(vistaCredito.tablaCredito.getValueAt(fila, 1)));
                idFactura = Integer.valueOf(String.valueOf(vistaCredito.tablaCredito.getValueAt(fila, 2)));
                totalCredito=Double.parseDouble(String.valueOf(vistaCredito.tablaCredito.getValueAt(fila, 6)).replace("$", ""));
                totalAbonado=Double.parseDouble(String.valueOf(vistaCredito.tablaCredito.getValueAt(fila, 7)).replace("$", ""));
                
                cargarTablaAbono();
                
                raw = fila;
                Double pendiente = totalCredito-totalAbonado;
                
                    System.err.println("ToalC"+totalCredito+"_________"+"Ttal A"+totalAbonado);
                    System.err.println(getDecimal(0, pendiente));
                
                vistaCredito.txt_pendiente.setText(getDecimal(0, pendiente)+"");
                 
                if(estadoCredito ==3){
                    habilitarBotones(true,false);
                }
                else if(estadoCredito==5){
                    habilitarBotones(false,false);
                }
             
             }
                 
            }
            if(e.getSource().equals(this.vistaCredito.tablaAbonos))
            {
                
                int fila = vistaCredito.tablaAbonos.rowAtPoint(e.getPoint());
                
                if (fila > -1){                
                /*Habilitar/deshabilitar botones*/
                 

                idAbono=Integer.valueOf(String.valueOf(vistaCredito.tablaAbonos.getValueAt(fila, 0)));
                System.err.println("abono id "+idAbono);
                
                 
                if(estadoCredito ==3){
                    habilitarBotones(true,true);
                }
                else if(estadoCredito==5){
                    habilitarBotones(false,false);
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
    
    private void cargarTablaCreditos(int estado,String nombre)
    {
        
         vistaCredito.tablaCredito.setModel(modeloCredito.obtenerCreditos(estado,nombre));
         
         /*Oculto la Columna ID de la tabla */
         vistaCredito.tablaCredito.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaCredito.tablaCredito.getColumnModel().getColumn(0).setMinWidth(0);
         vistaCredito.tablaCredito.getColumnModel().getColumn(0).setPreferredWidth(0);
         
         vistaCredito.tablaCredito.getColumnModel().getColumn(1).setMaxWidth(0);
         vistaCredito.tablaCredito.getColumnModel().getColumn(1).setMinWidth(0);
         vistaCredito.tablaCredito.getColumnModel().getColumn(1).setPreferredWidth(0);
    }
    
    private void cargarTablaAbono()
    {
        
         vistaCredito.tablaAbonos.setModel(modeloCredito.obtenerObonos(idFactura));
         
         /*Oculto la Columna ID de la tabla */
         vistaCredito.tablaAbonos.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaCredito.tablaAbonos.getColumnModel().getColumn(0).setMinWidth(0);
         vistaCredito.tablaAbonos.getColumnModel().getColumn(0).setPreferredWidth(0);
         
         
    }
    public void habilitarBotones(Boolean ...estado)
    {
        /*Cinco Botones*/

        vistaCredito.btn_registrarAbono.setEnabled(estado[0]);
        vistaCredito.btn_eliminarAbono.setEnabled(estado[1]); 
        
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
        vistaCredito.txt_cantidad.setText("");
        vistaCredito.txt_pendiente.setText("");
        vistaCredito.tablaAbonos.setModel(modeloCredito.obtenerObonos(0));
        
        
    }
    public boolean verificarMontos(){
    
        if((getDecimal(0, totalCredito-totalAbonado))>=Double.parseDouble(vistaCredito.txt_cantidad.getText()) && Double.parseDouble(vistaCredito.txt_cantidad.getText())>0 ){
            return true;
        }
        return false;
    }
    
    public void llenarCombo()
     {
        
         this.vistaCredito.comboEstado.removeAllItems();
         
            this.vistaCredito.comboEstado.addItem("Creditos actuales");
            this.vistaCredito.comboEstado.addItem("Creditos Liquidados");
     
     }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          Object item = e.getItem();
        //  System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaCredito.comboEstado))
                {
                    
                    
                    if(vistaCredito.comboEstado.getSelectedIndex()==0){
                        estadoCredito=3;
                        vistaCredito.txt_cantidad.enable();
                        nombre = "";
                    }
                    if(vistaCredito.comboEstado.getSelectedIndex()==1){
                        estadoCredito=5;
                        vistaCredito.txt_cantidad.disable();
                        nombre = "";
                        habilitarBotones(false,false);
                    }
                   
                    
                    cargarTablaCreditos(estadoCredito,"");
                }
        }
    }
    
    public double getDecimal(int numeroDecimal,double decimal){
        
        decimal = decimal*(java.lang.Math.pow(10, 2));
        
        decimal = java.lang.Math.round(decimal);
        
        decimal = decimal/java.lang.Math.pow(10, 2);
        
        return decimal;
    }
            
         
}

