/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Carro;
import Modelo.Chofer;
import Modelo.Ruta;
import Modelo.Usuario;
import Vista.CRUDRuta;
import Vista.Principal;
import java.awt.Dimension;
import java.awt.ItemSelectable;
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

/**
 *
 * @author jhon
 */
public class ControladorRuta implements ActionListener,MouseListener,ItemListener {
     
    ControladorPrincipal main;
    Principal principal;
    Ruta modeloRuta;
    CRUDRuta vistaRuta;
    int idRuta;
    int idDestino=-1;
    ArrayList<Carro> listaCarros;
    ArrayList<Chofer> listaChofer;
    int idchofer=0,idcarro=0;
    
    /**/
    public ControladorRuta(ControladorPrincipal main,Principal principal,CRUDRuta vistaRuta)
    {
        this.main=main;
        this.principal=principal;
        this.vistaRuta=vistaRuta;
        modeloRuta=new Ruta();
        modeloRuta.limpiar(vistaRuta);
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaRuta);
        this.vistaRuta.setLocation(centradoXY(vistaRuta));
        this.vistaRuta.setVisible(true);
        
        /*Enlazar datos*/
        this.vistaRuta.comboAuto.addItemListener(this);
        this.vistaRuta.comboConductor.addItemListener(this);
        this.vistaRuta.txt_kilo.setValue(new Double(0.0));
        this.vistaRuta.txt_buscar.setActionCommand("buscar");
        this.vistaRuta.txt_buscar.addActionListener(this);
        this.vistaRuta.btn_agregar.setActionCommand("agregar");
        this.vistaRuta.btn_agregar.addActionListener(this);
        this.vistaRuta.btn_eliminar.setActionCommand("eliminar");
        this.vistaRuta.btn_eliminar.addActionListener(this);
        this.vistaRuta.btn_actualizar.setActionCommand("actualizar");
        this.vistaRuta.btn_actualizar.addActionListener(this);
        
        
        /*Enlazar tabla*/
        this.vistaRuta.tablaRutas.addMouseListener(this);
        this.vistaRuta.tablaRutas.setModel(modeloRuta.obtenerRutas(""));
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setMinWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setMaxWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setMinWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setMaxWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setMinWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setMaxWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setMinWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.ocultarIDdestino();
        this.vistaRuta.btn_actualizar.setEnabled(false);
        this.vistaRuta.btn_eliminar.setEnabled(false);
        
        
        /*
          this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMaxWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setMinWidth(0);
         this.vistaCarro.tablaCarro.getColumnModel().getColumn(0).setPreferredWidth(0);
        */
        
        llenarChofer();
        llenarCarros();
    }
    
    private void llenarCarros()
    {
        listaCarros=modeloRuta.listaCarros();
        this.vistaRuta.comboAuto.removeAllItems();
        for(int i=0; i<listaCarros.size(); i++)
        {
            this.vistaRuta.comboAuto.addItem(listaCarros.get(i).getPlacas()+"   "+listaCarros.get(i).getDescripcion());
        }
      
                
    }
    
    private void llenarChofer()
    {
      listaChofer=modeloRuta.listaChofer();
      this.vistaRuta.comboConductor.removeAllItems();
      for(int i=0; i<listaChofer.size(); i++)
      {
          this.vistaRuta.comboConductor.addItem(listaChofer.get(i).getNombre());
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

    @Override
    public void actionPerformed(ActionEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      String accion=e.getActionCommand();
      
      
      
      switch(accion)
      {
          case "buscar":
              String busqueda=this.vistaRuta.txt_buscar.getText();
              this.vistaRuta.tablaRutas.setModel(modeloRuta.obtenerRutas(busqueda));
              this.cargarTabla();
              this.ocultarIDdestino();
              this.vistaRuta.btn_actualizar.setEnabled(false);
              this.vistaRuta.btn_eliminar.setEnabled(false);
              this.vistaRuta.btn_agregar.setEnabled(true);
              this.vistaRuta.txt_comentario.setText("");
              this.vistaRuta.txt_kilo.setText("0");
              this.vistaRuta.txt_destino.setEnabled(true);
              this.vistaRuta.txt_destino.setText("");
              this.vistaRuta.txt_comentario.setText("");
              this.llenarCarros();
              this.llenarChofer();
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/          
          case "eliminar":
                if(Usuario.eliminar == 0){
                    JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                    break;
               }
               int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el usuario","AVISO",JOptionPane.YES_NO_OPTION);

                Ruta eliminar=new Ruta();
                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarRuta(idRuta))
                             {
                                this.vistaRuta.tablaRutas.setModel(modeloRuta.obtenerRutas(""));
                                this.cargarTabla();
                                this.llenarCarros();
                                this.llenarChofer();
                                modeloRuta.limpiar(vistaRuta);
                                this.vistaRuta.btn_actualizar.setEnabled(false);
                                this.vistaRuta.btn_eliminar.setEnabled(false);
                                this.vistaRuta.btn_agregar.setEnabled(true);
                                this.vistaRuta.txt_destino.setEnabled(true);
                                this.ocultarIDdestino();
                                 JOptionPane.showMessageDialog(principal, "Ruta Eliminada correctamente");
                                 
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(principal, "Ocurrio un error al eliminar una ruta, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    principal.remove(dialogButton);
                }
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/          
          case "actualizar":
                if(Usuario.modificar == 0){
                    JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                    break;
                }
              if(this.vistaRuta.txt_kilo.getText().equalsIgnoreCase("") || this.vistaRuta.txt_kilo.getText().equalsIgnoreCase("0"))
              {
                   JOptionPane.showMessageDialog(principal, "El valor del campo kilo no es correcto.");
              }
              else
              {
                  if(this.vistaRuta.txt_comentario.getText().equalsIgnoreCase(""))
                  {
                       JOptionPane.showMessageDialog(principal, "El campo comentario esta vacio");
                       
                  }
                  else
                  {
                      System.out.println("Vamos a actualizar: ---"+idDestino);
                      Ruta update=new Ruta();
                      if(update.registrarHistorico(idDestino, Double.parseDouble(this.vistaRuta.txt_kilo.getText()),vistaRuta.txt_comentario.getText()))
                      {
                            this.vistaRuta.tablaRutas.setModel(modeloRuta.obtenerRutas(""));
                                modeloRuta.limpiar(vistaRuta);
                                this.cargarTabla();
                                this.llenarCarros();
                                this.llenarChofer();
                                this.ocultarIDdestino();
                                this.vistaRuta.txt_destino.setEnabled(true);
                                this.vistaRuta.btn_eliminar.setEnabled(false);
                                this.vistaRuta.btn_actualizar.setEnabled(false);
                                this.vistaRuta.btn_agregar.setEnabled(true);
                          JOptionPane.showMessageDialog(principal, "Ruta actualizada correctamente");
                      }else
                      {
                          JOptionPane.showMessageDialog(principal, "Error al actualizar la ruta");
                      }
                  }
              }
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/                        
          case "agregar":
              if(Usuario.crear == 0){
                        JOptionPane.showMessageDialog(principal, "No Cuentas con los permisos suficientes para realizar esta accion");
                        break;
              }
              String destino=vistaRuta.txt_destino.getText();
              String comentarios=vistaRuta.txt_comentario.getText();
              double kilo=Double.parseDouble(vistaRuta.txt_kilo.getText());
              
              String valorCarro=(String)this.vistaRuta.comboAuto.getSelectedItem();
              String valorChofer=(String)this.vistaRuta.comboConductor.getSelectedItem();
              if( valorCarro==null  && valorChofer==null && vistaRuta.txt_kilo.getText().trim().equalsIgnoreCase(""))
              {
                 JOptionPane.showMessageDialog(principal,"No existen persona o carro disponible para asignar");
                 modeloRuta.limpiar(vistaRuta);
                 
             

              }
              else
              {
                

                        if(modeloRuta.validarEspacios(destino,comentarios,""+kilo))
                        {
                            if(modeloRuta.validarCombos(idcarro, idchofer))
                            {    
                                Ruta alta=new Ruta();
                                alta.setDestino(destino);
                                alta.setObservacion(comentarios);
                                alta.setKilo(kilo);
                                alta.setChofer_id(idchofer);
                                alta.setCarro_id(idcarro);
                                if(alta.registrarRuta(alta))
                                {
                                this.vistaRuta.tablaRutas.setModel(modeloRuta.obtenerRutas(""));
                                modeloRuta.limpiar(vistaRuta);
                                this.cargarTabla();
                                this.llenarCarros();
                                this.llenarChofer();
                                this.ocultarIDdestino();

                                try
                                {
                                    idcarro=listaCarros.get(vistaRuta.comboAuto.getSelectedIndex()).getId();
                                    idchofer=listaChofer.get(vistaRuta.comboConductor.getSelectedIndex()).getId();
                                }catch(Exception ee)
                                {
                                  System.out.println(ee.getCause());
                                  idcarro=-1;
                                  idchofer=-1;
                                }

                                this.vistaRuta.btn_actualizar.setEnabled(false);
                                this.vistaRuta.btn_eliminar.setEnabled(false);
        
                                 JOptionPane.showMessageDialog(principal,"Ruta registrada correctamente");
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(principal,"La ruta ya se encuentra REGISTRADA, favor de elegir otra");
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(principal,"No existen carros y/o conductores disponibles");
                            }
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(principal,"No se permiten espacios vacios o el precio *CERO*");
                        }
              }

      
              break;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/                    
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
             
             int fila=this.vistaRuta.tablaRutas.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
             //this.idProveedor=String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 0));
             this.idRuta=Integer.parseInt(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila,0)));
             this.idDestino=Integer.parseInt(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila,7)));
             this.vistaRuta.txt_destino.setText(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 2)));
             this.vistaRuta.txt_kilo.setText(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 5)));
             this.vistaRuta.txt_comentario.setText(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 6)));
             
             this.vistaRuta.comboAuto.removeAllItems();
             this.vistaRuta.comboConductor.removeAllItems();
             this.vistaRuta.comboAuto.addItem(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 3)+"    "+String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 4))));
             this.vistaRuta.comboConductor.addItem(String.valueOf(this.vistaRuta.tablaRutas.getValueAt(fila, 1)));
             
             System.out.println("ID destino: "+idDestino);
             /*
                 this.vistaChofer.txt_nombreChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 1)));
             this.vistaChofer.txt_direccionChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 2)));
             this.vistaChofer.txt_telefonoChofer.setText(String.valueOf(this.vistaChofer.tablaChofer.getValueAt(fila, 3)));
             */
             
             
             
             this.vistaRuta.btn_actualizar.setEnabled(true);
             this.vistaRuta.btn_eliminar.setEnabled(true);
             this.vistaRuta.btn_agregar.setEnabled(false);
             this.vistaRuta.txt_destino.setEnabled(false);
             
        
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getStateChange() == ItemEvent.SELECTED) {
          Object item = e.getItem();
        //  System.out.println("Seleccion: "+item);
              if(e.getSource().equals(this.vistaRuta.comboAuto))
                {
                    
                    try{
                    idcarro=listaCarros.get(vistaRuta.comboAuto.getSelectedIndex()).getId();
                    }catch(Exception ex)
                    {
                        idcarro=-1;
                        System.out.println("Error"+ex.getLocalizedMessage());
                    }
                    
                }
              if(e.getSource().equals(this.vistaRuta.comboConductor))
                {
                   // System.out.println("Soy Conductor"+e.getItem());
                   try{
                    idchofer=listaChofer.get(vistaRuta.comboConductor.getSelectedIndex()).getId();
                   }catch(Exception et)
                   {
                       idchofer=-1;
                   }
    
                }
       }
        
        
    
    }
    
    /*Cargar tabla */
    private void cargarTabla()
    {
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setMinWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setMaxWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setMinWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setMaxWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setMinWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setMaxWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setMinWidth(100);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    
    private void ocultarIDdestino()
    {
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(7).setMaxWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(7).setMinWidth(0);
        this.vistaRuta.tablaRutas.getColumnModel().getColumn(7).setPreferredWidth(0);
    }
    
}
