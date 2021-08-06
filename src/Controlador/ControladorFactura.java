/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Configuracion;
import Modelo.Factura;
import Modelo.Ruta;
import Modelo.Usuario;
import Vista.Facturar;
import Vista.Principal;
import Vista.Venta_Credito;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author jhon
 */
public class ControladorFactura implements ActionListener,MouseListener,ItemListener
{
    
    private ControladorVenta_Credito controlVenta_Credito;
    private Venta_Credito vistaVentaCredito;
    private Principal principal;
    private Facturar vistaFactura;
    private Factura modeloFactura;
    private int idPedido=0;
    private int idCantidad=0;
    private int idReja=0;
    private int noFactura=0;
    private ArrayList<Ruta> listaRuta;
    private double totalFactura=0.0;
    
    private double totalRutas=0.0;
    
    private String ruta="";
    
    
    public ControladorFactura(Principal principal,Facturar vistaFactura)
    {
        this.principal=principal;
        this.vistaFactura=vistaFactura;
        modeloFactura=new Factura();
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaFactura);
        this.vistaFactura.setTitle("Modulo de Factura");
        this.vistaFactura.setLocation(centradoXY(vistaFactura));
        this.vistaFactura.setVisible(true);
        this.vistaFactura.txt_factura.setText("");
        this.vistaFactura.txt_factura.setValue(new Integer(0));
        /*Escuchas*/
        this.vistaFactura.btn_cobrar.setActionCommand("cobrar");
        this.vistaFactura.btn_cobrar.addActionListener(this);
        
        this.vistaFactura.tablaFactura.addMouseListener(this);
        
        this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(ruta,1));
        //this.llenar(this.vistaFactura.tablaFactura);
        
        this.vistaFactura.txt_factura.setActionCommand("buscar");
        this.vistaFactura.txt_factura.addActionListener(this);
        
        /*03.04.19*/
        this.vistaFactura.btn_cancelar.setActionCommand("cancelar");
        this.vistaFactura.btn_cancelar.addActionListener(this);
        
        /*03.04.19*/
        this.vistaFactura.btn_credito.setActionCommand("ventaCredito");
        this.vistaFactura.btn_credito.addActionListener(this);
        
        this.vistaFactura.comboRutas.addItemListener(this);
        
        ocultarId();
        llenarCombo();
        habilitar(false,false,false);
        
        
        
        
        
    }
    /*desactivar botones*/
    public void habilitar(Boolean ...i)
    {
        this.vistaFactura.btn_cancelar.setEnabled(i[0]);
        this.vistaFactura.btn_cobrar.setEnabled(i[1]);
        this.vistaFactura.btn_credito.setEnabled(i[2]);
    }
    
    
        private void ocultarId()
    {
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(0).setMinWidth(0);
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(0).setPreferredWidth(0);
        

        this.vistaFactura.tablaFactura.getColumnModel().getColumn(1).setPreferredWidth(50);
      //  this.vistaFactura.tablaFactura.getColumnModel().getColumn(1).setMaxWidth(70);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(2).setPreferredWidth(130);
        //this.vistaFactura.tablaFactura.getColumnModel().getColumn(2).setMaxWidth(100);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(3).setPreferredWidth(35);
        ///this.vistaFactura.tablaFactura.getColumnModel().getColumn(3).setMaxWidth(60);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(4).setPreferredWidth(30);
        //this.vistaFactura.tablaFactura.getColumnModel().getColumn(4).setMaxWidth(50);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(30);
      //  this.vistaFactura.tablaFactura.getColumnModel().getColumn(5).setMaxWidth(45);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(40);
        //this.vistaFactura.tablaFactura.getColumnModel().getColumn(6).setMaxWidth(50);
        
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(7).setPreferredWidth(40);
        //this.vistaFactura.tablaFactura.getColumnModel().getColumn(7).setMaxWidth(55);
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(8).setPreferredWidth(130);
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(9).setPreferredWidth(70);
        this.vistaFactura.tablaFactura.getColumnModel().getColumn(10).setPreferredWidth(100);
        
      
    }
        
        
    
    public void llenar(JTable table)
    {
    double total=-1;
        
     for(int i = 0; i < table.getRowCount(); i++){
        //int total = 0;
        String partes[]=table.getValueAt(i, 7).toString().split(" ");
      
        
        totalRutas+=Double.parseDouble(partes[1]);
        //total = Amount+total;
        //System.out.println(total);
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

     /*llenar lista*/
     public void llenarCombo()
     {
         
         listaRuta=modeloFactura.obtenerRutas();
         this.vistaFactura.comboRutas.removeAllItems();
         for(int i=0; i<listaRuta.size(); i++)
         {
             this.vistaFactura.comboRutas.addItem(listaRuta.get(i).getDestino());
             //this.vistaRuta.comboConductor.addItem(listaChofer.get(i).getNombre());
         }
         //this.vistaFactura.comboRutas
     }
        
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         String accion=e.getActionCommand();
          switch(accion)
          {
              case "cancelar":
                  
                int opcion=-1;  
                if(Usuario.modificar == 0){
                    JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
                  int seleccion=JOptionPane.showOptionDialog(null,
                          "Seleccione una opción","¿Que desea realizar?",
                          JOptionPane.YES_NO_CANCEL_OPTION,
                          JOptionPane.QUESTION_MESSAGE,null,
                          new Object[]{"Cancelar","Devolución Parcial","Devolución Total"},""
                          );
                  
                  switch(seleccion)
                  {

                      case 2://Devolucion total
                          
                      
                      /*Agregue la condicion para quitar del inventario o regresar*/
                 int respInventario = JOptionPane.showConfirmDialog(null, "¿La canelacion TOTAL de la factura "+noFactura+" '¿Deseas regresar todas las AVES al Inventario?", "¡AVISO!", JOptionPane.YES_NO_OPTION); 
                 
                 if(respInventario==0)  //Con regreso de aves
                 {
                     opcion=0;
                 }
                 else if(respInventario==1)
                 {
                     opcion=1;
                 }
                 else
                 {
                     opcion=-1;
                 }
                      
                      //---                                                  
                          
                          Factura dev=new Factura();
                          // registrarDevolucion(int idPedido,double kilos,int cantidad,String comentarios,String folio)
                          //String folioFacPollos=Configuracion.obtenerFoliodePedido(noFactura);
                          ArrayList<Factura> listaPedidos=Configuracion.listaPedidos(noFactura,0);
                          //System.out.println("Folio de factura pollos: "+folioFacPollos);
                          String comen=JOptionPane.showInputDialog("Indique un comentario por que se esta cancelando la factura número: "+noFactura);
                          for(int i=0; i<listaPedidos.size(); i++)
                          {
                            //  System.out.println("ID PEDIDO: "+listaPedidos.get(i).toString());
                              int iDP=listaPedidos.get(i).getIdpedido();
                              String FolioFacturaPollos=listaPedidos.get(i).getFolioFactura();
                              if(opcion==0)
                              {    
                              dev.registrarDevolucion(iDP, 0, 0, comen,FolioFacturaPollos);
                              }
                              else if(opcion==1)
                              {
                              dev.registrarDevolucionSin(iDP, 0, 0, comen,FolioFacturaPollos);                                  
                              }
                              else
                              {
                                  JOptionPane.showMessageDialog(principal, "Elegiste una opción no válida, El sistema se cerrará");
                                  System.exit(0);
                              }
                              
                          }
                                                  totalRutas=0;
                          this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(ruta,1));
                        llenar(vistaFactura.tablaFactura);
                        this.vistaFactura.txt_total.setText(""+totalRutas);
                        ocultarId();
                        habilitar(false,false,false);
                      
                          //String comen=JOptionPane.showInputDialog("Indique un comentario por que se esta cancelando la factura número: "+noFactura);
                          //dev.registrarDevolucion(idPedido, 0, 0, comen,folioFacPollos);
                          break;
                      case 1: //devolucion parcial
                      
                        int respInventario2 = JOptionPane.showConfirmDialog(null, "¿La canelacion PARCIAL de la factura "+noFactura+" '¿Deseas regresar las AVES al Inventario?", "¡AVISO!", JOptionPane.YES_NO_OPTION); 
                 
                 if(respInventario2==0)  //Con regreso de aves
                 {
                     opcion=0;
                 }
                 else if(respInventario2==1)
                 {
                     opcion=1;
                 }
                 else
                 {
                     opcion=-1;
                 }   
                          
                          Factura dev2=new Factura();
                      
                          try{
                          int numAves=Integer.parseInt(JOptionPane.showInputDialog("De la factura No. "+noFactura+" \n ¿Cuántas aves desea quitar de la reja No."+idReja+" ?"));
                          double kilAves=Double.parseDouble(JOptionPane.showInputDialog("De la factura No. "+noFactura+" \n Usted quito "+numAves+" aves. \n¿Cuántos kilos desea disminuir?"));
                          
                          ArrayList<Factura> listaPedidos2=Configuracion.listaPedidos(noFactura,idPedido);
                          String comenta=JOptionPane.showInputDialog("Indique un comentario por que se esta cancelando aves de la reja"+idReja+" \nCon número de factura: "+noFactura);
                          for(int i=0; i<listaPedidos2.size(); i++)
                          {
                            //  System.out.println("ID PEDIDO: "+listaPedidos.get(i).toString());
                              int iDP=listaPedidos2.get(i).getIdpedido();
                              String FolioFacturaPollos=listaPedidos2.get(i).getFolioFactura();
                        
                              if(opcion==0)
                              {
                              dev2.registrarDevolucion(iDP, kilAves, numAves, comenta,FolioFacturaPollos);
                              }
                              else if(opcion==1)
                              {
                              dev2.registrarDevolucionSin(iDP, kilAves, numAves, comenta,FolioFacturaPollos);                                  
                              }
                              else
                              {
                                  JOptionPane.showMessageDialog(principal, "Elegiste una opción no válida, El sistema se cerrará");
                                  System.exit(0);                                  
                              }
                              
                          }
                                                  totalRutas=0;
                          this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(ruta,1));
                        llenar(vistaFactura.tablaFactura);
                        this.vistaFactura.txt_total.setText(""+totalRutas);
                        ocultarId();
                        habilitar(false,false,false);
                          
                          
                          }catch(Exception yt)
                          {
                              JOptionPane.showMessageDialog(principal, "Hubo un error al ingresar el número de aves"+yt.getLocalizedMessage().toString());
                          }
                          break;
                      case 0: //cancelar
                          break;
                      case -1://tecla scape o cerrar
                          break;
                      default:
                          break;
                          
                  }
                  
                  ocultarId();
                  habilitar(false,false,false);
                  noFactura=0;
                  
                  break;
/*------------------------------------------------------------------------------------------------------------------------------------------------*/                                
              case "buscar":
                  
                    this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(this.vistaFactura.txt_factura.getText(),0));
                        ocultarId();
                        habilitar(false,false,false);
                  break;
/*------------------------------------------------------------------------------------------------------------------------------------------------*/                  
              case "cobrar":
                 
                if(Usuario.crear == 0){
                    JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                } 
                 int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de poner la factura número "+noFactura+" como PAGADA?", "Alerta!", JOptionPane.YES_NO_OPTION);
                  System.out.println("Respuesta: "+resp);
                  if(resp==0)
                  {
                        Factura up=new Factura();
                        up.facturarPedido(noFactura);
                        up.actualizarHistorico(noFactura);
                        this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(ruta,1));
                        llenar(vistaFactura.tablaFactura);
                        this.vistaFactura.txt_total.setText(""+totalRutas);
                        ocultarId();
                        habilitar(false,false,false);
                      
                      
                              
                  }else
                  {
                      
                  }
                  break;
                  /*------------------------------------------------------------------------------------------------------------------------------------------------*/                  
              case "ventaCredito":
                  
                   controlVenta_Credito = new ControladorVenta_Credito(principal, noFactura);
                   this.vistaFactura.dispose();
                    
                  break;
/*------------------------------------------------------------------------------------------------------------------------------------*/                  
              default:
                  JOptionPane.showMessageDialog(principal,"Opción no válida, el sistema se cerrará");
                  System.exit(0);
                  break;
          }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              
      
       if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila=this.vistaFactura.tablaFactura.rowAtPoint(e.getPoint());

             if (fila > -1)
             {                
           
                this.idPedido=Integer.parseInt(String.valueOf(this.vistaFactura.tablaFactura.getValueAt(fila,0)));
                this.noFactura=Integer.parseInt(String.valueOf(this.vistaFactura.tablaFactura.getValueAt(fila,1)));
                this.idReja=Integer.parseInt(String.valueOf(this.vistaFactura.tablaFactura.getValueAt(fila,4)));
                //System.out.println("Factura: "+noFactura);
                
               
             
                /*this.vistaReja.txt_numero.setText(String.valueOf(this.vistaReja.tablaRejas.getValueAt(fila, 1)));
                this.vistaReja.txt_descripcion.setText(String.valueOf(this.vistaReja.tablaRejas.getValueAt(fila, 2)));*/
                 idCantidad=Configuracion.getCantidad(idPedido);
                 
                 habilitar(true,true,true);

             }
        }
       
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          if (e.getStateChange() == ItemEvent.SELECTED) {
          //Object item = e.getItem();
        //  System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaFactura.comboRutas))
                {
                    
                     ruta=this.vistaFactura.comboRutas.getSelectedItem().toString();
                  //  System.out.println("Valor: "+ruta);
                    
                        totalRutas=0;
                        this.vistaFactura.tablaFactura.setModel(this.modeloFactura.obtenerFacturas(ruta,1));
                        llenar(this.vistaFactura.tablaFactura);
                        ocultarId();
                        habilitar(false,false,false);
                        
                     
                        this.vistaFactura.txt_total.setText(""+totalRutas);
                           totalRutas=0;
                    
                }
       
       }
      
      
    }

 
    
}
