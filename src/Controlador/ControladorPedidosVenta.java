/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Configuracion;
import Modelo.Crear;
import Modelo.PedidosVenta;
import Modelo.Pollo;
import Modelo.Reja;
import Modelo.ReporteTicket;
import Modelo.Usuario;
import Vista.Pedidos;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jhon
 */
public class ControladorPedidosVenta implements ActionListener,ItemListener
{

     private Principal principal;
     private Pedidos vistaPedidos;
     private PedidosVenta modeloPedidos;
     private Pollo modeloPollo;
     private ArrayList<PedidosVenta> lista;
     private ArrayList<Pollo> listaFactura;
     private ArrayList<Reja> listaReja;
     SerialES comunicacion;
     private int banderaImpresion=0;
     private int aux1=0;
     private int aux2=0;
     
     private int idRuta;
     private int idReja;
     private String idFolio;
     private int cantidadAvesFolio=0;
     
     private String opcionListaDestino;
     private String opcionListaDestino2;
     private int idauxiliar;
     private boolean aux=false;

    public ControladorPedidosVenta(Principal principal,Pedidos vistaPedidos) 
    {
        this.principal=principal;
        this.vistaPedidos=vistaPedidos;
        modeloPedidos=new PedidosVenta();
        modeloPollo=new Pollo();
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaPedidos);
        this.vistaPedidos.setTitle("Pedidos de Pollos");
        this.vistaPedidos.setLocation(centradoXY(vistaPedidos));
        this.vistaPedidos.setVisible(true);
        this.vistaPedidos.txt_avesContar.setText("0");
        /*Escucha*/
        this.vistaPedidos.listaReja.addItemListener(this);
        this.vistaPedidos.listaRuta.addItemListener(this);
        this.vistaPedidos.comboFactura.addItemListener(this);

        this.vistaPedidos.btn_bascula.setOpaque(false);
        this.vistaPedidos.txt_descripcion_reja.setEditable(false);
      //  this.vistaPedidos.btn_bascula.setContentAreaFilled(false);
        this.vistaPedidos.btn_bascula.setBorderPainted(false);
        
        this.vistaPedidos.btn_bascula.setActionCommand("lectura");
        this.vistaPedidos.btn_bascula.addActionListener(this);
        
        this.vistaPedidos.btn_pedir.setActionCommand("pedir");
        this.vistaPedidos.btn_pedir.addActionListener(this);
       
        this.vistaPedidos.btn_ticket.setActionCommand("ticket");
        this.vistaPedidos.btn_ticket.addActionListener(this);
        
        this.vistaPedidos.btn_agregar.setActionCommand("agregar");
        this.vistaPedidos.btn_agregar.addActionListener(this);
        
       

        //Llenar combo destino
        this.llenarRuta();
        this.llenarReja();
        this.desactivar();
        this.serial();
        this.llenarFacturas();
      //  this.botonBascula();
        modeloPedidos.limpiarCampos(vistaPedidos);
        modeloPedidos.limpiarTodo(vistaPedidos);
        this.vistaPedidos.txt_aves.setValue(new Integer(0));
        this.vistaPedidos.txt_kilos.setValue(new Double(0.0));
        getFact();
        getTotalAves();
        /*ticket desactivado por primera vez*/
        this.vistaPedidos.btn_ticket.setEnabled(false);

        
    }
    
    private void serial()
    {
        if(ControladorPrincipal.estado==1)
        {
         Pedidos.btn_bascula.setVisible(false);
            Pedidos.btn_pedir.setVisible(true); 
        }
        else
        {
         Pedidos.btn_bascula.setVisible(true);
            Pedidos.btn_pedir.setVisible(false); 
        }
    }
    
    
    private boolean getFact()
    {
        if(Configuracion.getFactura()>0)
        {
            this.vistaPedidos.txt_factura.setText(""+Configuracion.getIdFolioFactura());
            return true;
        }
        else
            return false;
    }
    
    private boolean getTotalAves()
    {
        if(Configuracion.getTotalPollos()>0)
        {
            this.vistaPedidos.txt_avesInventario.setText(""+Configuracion.getTotalPollos());
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(principal, "Ocurrio un errror al obtener el total de aves\n La aplicación se cerrará");
            System.exit(0);
            return false;
        }
    }
    
    public void llenarRuta()
    {
     
        lista=modeloPedidos.obtenerRutas();
        this.vistaPedidos.listaRuta.removeAllItems();
        
        
        for(int i=0; i<lista.size(); i++)
        {
            this.vistaPedidos.listaRuta.addItem(lista.get(i).getDestino());
            System.out.println("Eleccion: "+opcionListaDestino2);
            System.out.println("Base: "+lista.get(i).getDestino());
                
            if(lista.get(i).getDestino().equals(opcionListaDestino2))
            {
                idauxiliar=i;
                aux=true;
                System.out.println("*********"+"llego");
            }
         
            
          
        }
        
          llenarCamposRuta(lista.get(0).getComentario_ruta(),lista.get(0).getConductor(),lista.get(0).getPlacas()+"   "+lista.get(0).getDescripcion_auto(),lista.get(0).getPrecio_kilo());
        //llenarCamposRuta(lista, conductor, automovil, 0);
        
        //asignar
        System.out.println("Aux: "+aux);
        if(aux==true)
        {
        vistaPedidos.listaRuta.setSelectedIndex(idauxiliar);
        System.out.println("ID AUX: "+idauxiliar);
        }
        else
        {
            System.out.println("-->"+idauxiliar);
        }
        
        
    }
    
    
    public void llenarFacturas()
    {
       listaFactura=modeloPollo.getTotalPollos();
       this.vistaPedidos.comboFactura.removeAllItems();
       for(int i=0; i<listaFactura.size(); i++)
       {
           this.vistaPedidos.comboFactura.addItem("Folio Factura: "+listaFactura.get(i).getFolio()+"           Cantidad:  "+listaFactura.get(i).getCantidad());
       }
        
    }
    
    public void llenarReja()
    {
      try{
        Reja l=new Reja();
        listaReja=l.obtenerRejasLibres();
        this.vistaPedidos.listaReja.removeAllItems();
        for(int i=0; i<listaReja.size(); i++)
        {
            this.vistaPedidos.listaReja.addItem(""+listaReja.get(i).getNumero());
        }
        
        this.vistaPedidos.txt_descripcion_reja.setText(listaReja.get(0).getDescripcion());
      }catch(Exception t)
      {
          JOptionPane.showMessageDialog(principal, "Ya no hay rejas disponibles");
          this.vistaPedidos.btn_agregar.setEnabled(false);
          this.vistaPedidos.txt_descripcion_reja.setText("");
                  
      }
    }
    
    
    public void llenarCamposRuta(String observacion,String conductor,String automovil,double kilo)
    {
        this.vistaPedidos.txt_obsRuta.setText(observacion);
        this.vistaPedidos.txt_conductor.setText(conductor);
        this.vistaPedidos.txt_auto.setText(automovil);
        //this.vistaPedidos.txt_kiloRuta2.setText(""+kilo);
        this.vistaPedidos.txt_kiloRuta.setText(""+kilo);
    }
    
    public void desactivar()
    {
        this.vistaPedidos.txt_obsRuta.setEditable(false);
       
        
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
        
    private  boolean basculaLista()
    {
           try
           {
               String port=Crear.leerPuerto().trim();
               comunicacion=new SerialES(port);
               return true;
           }catch(Exception w)
           {
               JOptionPane.showMessageDialog(principal, "La Bascula no esta disponible");
               return false;
           }
               
        
    }
    
    private void botonBascula()
    {
        if(basculaLista())
        {
            this.vistaPedidos.btn_bascula.setEnabled(true);
            
        }
        else
        {
            this.vistaPedidos.btn_bascula.setEnabled(false);
        }
    }
        
    
    /*Metodo para registrar un pedidos*/
    private void registroPedidos()
    {
        PedidosVenta registro=new PedidosVenta();
                      //son distintos de ceros
                     
                        int cantidadAves=Integer.parseInt(this.vistaPedidos.txt_aves.getText());
                        double peso=Double.parseDouble(this.vistaPedidos.txt_kilos.getText());
                        System.out.println("todo bien");
                         registro.setCantidadAves(cantidadAves);
                        registro.setKilos(peso);
                        registro.setIdRuta(idRuta);
                        registro.setIdReja(idReja);
                        registro.setFactura(Integer.parseInt(this.vistaPedidos.txt_factura.getText()));
                        registro.setFoliofactura(idFolio);
                        if(registro.registrarRuta(registro))
                        {
                            JOptionPane.showMessageDialog(principal, "Pedido realizado con éxito");
                            llenarReja();
                            llenarFacturas();
                            this.vistaPedidos.txt_aves.setText("0");
                            Pedidos.txt_kilos.setText("0");
                            this.vistaPedidos.txt_avesInventario.setText(""+Configuracion.getTotalPollos());
                           // this.vistaPedidos.txt_descripcion_reja.setText("");
                        }else
                        {
                            JOptionPane.showMessageDialog(principal, "Ocurrio un error");
                        }
    }
    /*Fin metodo de pedidos*/
    
    @Override
    public void actionPerformed(ActionEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       String accion=e.getActionCommand();
      
      
      
      switch(accion)
      {
          case "ticket":
              
              ReporteTicket reporte=new ReporteTicket();
              reporte.generarticket(Integer.parseInt(this.vistaPedidos.txt_factura.getText()));
              JOptionPane.showMessageDialog(principal,"¡El ticket se envió directamente a la impresora!");
              Configuracion.registrarFactura(1);
             
              this.vistaPedidos.txt_factura.setText(""+Configuracion.getIdFolioFactura());
              this.banderaImpresion=0;
              this.vistaPedidos.btn_ticket.setEnabled(false);
              this.vistaPedidos.txt_total_rejas.setText("0");
              this.vistaPedidos.txt_avesContar.setText("0");
              this.vistaPedidos.txt_total.setText("0");
              
              /*condifcion*/
              opcionListaDestino2=opcionListaDestino;
               
              llenarRuta();
              llenarReja();
              break;
/*------------------------------------------------------------------------------------------------------------*/              
          case "agregar":
              if(Usuario.crear == 0){
                JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                break;
                }
              if(banderaImpresion>0) //ya existe un pedido 
              {
                  //recogemos las variables
                  if(!this.vistaPedidos.txt_aves.getText().equalsIgnoreCase("0") && !Pedidos.txt_kilos.getText().equalsIgnoreCase("0") 
                      && Integer.parseInt(this.vistaPedidos.txt_aves.getText()) <= this.cantidadAvesFolio
                          )
                  {
                        
                        //verificar que el mismo pedido corresponda a la ruta origen
                      if(aux1==aux2)
                      {
                                int dialogButton = JOptionPane.showConfirmDialog (null, "¿Desaa agregar una reja mas a la factura: "+this.vistaPedidos.txt_factura.getText(),"AVISO",JOptionPane.YES_NO_OPTION);


                                if(dialogButton == JOptionPane.YES_OPTION) 
                                {
                                      registroPedidos();
                                      banderaImpresion++;
                                      this.vistaPedidos.txt_total_rejas.setText(""+banderaImpresion);
                                    this.vistaPedidos.btn_ticket.setEnabled(true);
                                     double precioKilo=Double.parseDouble(this.vistaPedidos.txt_kiloRuta.getText());
                                //     System.out.println("Precio Kilo: "+precioKilo);

                    int f=Integer.parseInt(this.vistaPedidos.txt_factura.getText());
                               // System.out.println("Total de kilos: "+Configuracion.getCantidadPollos(f));
                    
                               //agregar un delimitador de decimales
                                    DecimalFormat formateador = new DecimalFormat("####.##");
                                    
                               this.vistaPedidos.txt_total.setText("$ "+(formateador.format(Configuracion.getCantidadPollos(f)*precioKilo)));
                    
                    this.vistaPedidos.txt_avesContar.setText(""+(Configuracion.getCantidadTotalAves(f)));
                    
                                    aux2=aux1;
                                }
                                else 
                                {
                                    principal.remove(dialogButton);
                                }  
                      }else
                      {
                          JOptionPane.showMessageDialog(principal, "No se puede cambiar la ruta destino a este pedido");
                      }
                     
                       
                      
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(principal, "El campo kilos o cantidad de aves no es válido");
                  }
                  

                  
                  
              }
              else //no hay pedidos
              {
                  if(!this.vistaPedidos.txt_aves.getText().equalsIgnoreCase("0") && !Pedidos.txt_kilos.getText().equalsIgnoreCase("0")
                          && Integer.parseInt(this.vistaPedidos.txt_aves.getText()) <= this.cantidadAvesFolio
                          )
                  {
                      //son distintos de ceros
                    registroPedidos();
                    banderaImpresion++;
                    this.vistaPedidos.btn_ticket.setEnabled(true);
                    this.vistaPedidos.txt_total_rejas.setText(""+banderaImpresion);
                    double precioKilo=Double.parseDouble(this.vistaPedidos.txt_kiloRuta.getText());
                    int f=Integer.parseInt(this.vistaPedidos.txt_factura.getText());
                     System.out.println("Precio Kilo: "+precioKilo);
                      System.out.println("Total de kilos: "+Configuracion.getCantidadPollos(f));
                      
                    
                      //this.vistaPedidos.txt_total.setText(""+(Configuracion.getCantidadPollos(f)*precioKilo));
                     DecimalFormat formateador = new DecimalFormat("####.##");
                     this.vistaPedidos.txt_total.setText("$ "+(formateador.format(Configuracion.getCantidadPollos(f)*precioKilo)));
                    
                    
                    this.vistaPedidos.txt_avesContar.setText(""+(Configuracion.getCantidadTotalAves(f)));
                    aux2=aux1;
                       
                        
                      
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(principal, "El campo kilos o cantidad de aves no es válido");
                  }
                  
              }
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------*/                       
          case "pedir":
              try{
             SerialES.solicitarPeso();
              }catch(Exception et)
              {
                   JOptionPane.showMessageDialog(null, "No se puede comunicar con la báscula"+et.getMessage());
              }
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------*/              
          case "lectura":
              
              //comunicacion.solicitarPeso();
                new BackgroundWorker().execute();
              break;
          default:
              System.exit(0);
              JOptionPane.showMessageDialog(principal, "Opción no válida, el sistema se cerrará");
      }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
         if (e.getStateChange() == ItemEvent.SELECTED) {
          Object item = e.getItem();
        //  System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaPedidos.listaRuta))
                {
                    idRuta=lista.get(vistaPedidos.listaRuta.getSelectedIndex()).getIdRuta();
                   aux1=idRuta;

                     opcionListaDestino=vistaPedidos.listaRuta.getSelectedItem().toString();
                     System.out.println("Ruta Elegida: "+opcionListaDestino);
                   
                    int numero=vistaPedidos.listaRuta.getSelectedIndex();
                 //   System.out.println("Ruta:"+idRuta);  ID RUTA BD
                   // System.out.println("Indice"+vistaPedidos.listaRuta.getSelectedIndex());  Posicion
                 llenarCamposRuta(lista.get(numero).getComentario_ruta(),lista.get(numero).getConductor(),lista.get(numero).getPlacas()+"  "+lista.get(numero).getDescripcion_auto(),lista.get(numero).getPrecio_kilo());
                    
                }
              if(e.getSource().equals(this.vistaPedidos.listaReja))
                {
                   // System.out.println("Soy Conductor"+e.getItem());
                    idReja=listaReja.get(vistaPedidos.listaReja.getSelectedIndex()).getId();
                   // System.out.println("Id reja: "+idReja);
                     this.vistaPedidos.txt_descripcion_reja.setText(listaReja.get(vistaPedidos.listaReja.getSelectedIndex()).getDescripcion());
                     
                 
                }
              /*Combo facturas*/
                 if(e.getSource().equals(this.vistaPedidos.comboFactura))
                {
                   // System.out.println("Soy Conductor"+e.getItem());

                    idFolio=listaFactura.get(vistaPedidos.comboFactura.getSelectedIndex()).getFolio();
                    cantidadAvesFolio=listaFactura.get(vistaPedidos.comboFactura.getSelectedIndex()).getCantidad();
                    System.out.println("Id Folio Factura: "+idFolio);
                     
                     
                 
                }
              
              
       }
       
    }
     
    //Clase interna
    
    public class BackgroundWorker extends SwingWorker<Void, Void> {

        private JProgressBar pb;
        private JDialog dialog;
        private Pedidos vistaP;

        public BackgroundWorker() {
            
            addPropertyChangeListener(new PropertyChangeListener() {
                
                
                
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
                        if (dialog == null) {

                            dialog = new JDialog();
                            dialog.setTitle("Configurando");
                            dialog.setLayout(new GridBagLayout());
                            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.insets = new Insets(2, 2, 2, 2);
                            gbc.weightx = 1;
                            gbc.gridy = 0;
                            dialog.add(new JLabel("Buscando puerto disponible"), gbc);
                            pb = new JProgressBar();
                            gbc.gridy = 1;
                            dialog.add(pb, gbc);
                            dialog.pack();
                            dialog.setLocationRelativeTo(null);
                            dialog.setVisible(true);
                        }
                        pb.setValue(getProgress());
                    }
                }

            });
        }

        @Override
        protected void done() {
            if (dialog != null) {
                
                dialog.dispose();
            }
        }

        @Override
        protected Void doInBackground() throws Exception {

            String port="";
            try
           {
               port=Crear.leerPuerto().trim();
               comunicacion=new SerialES(port);
              
           }catch(Exception w)
           {
               JOptionPane.showMessageDialog(principal, "La Bascula no esta disponible");
              
           }
               
            for (int index = 0; index < 50; index++) {
                setProgress(index);
                Thread.sleep(85);
            }
           Pedidos.btn_bascula.setVisible(false);
            Pedidos.btn_pedir.setVisible(true);
            ControladorPrincipal.estado=1;

            return null;
        }

        
    }
    //fin clse
    
}
