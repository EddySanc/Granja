/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import Modelo.ModeloInsumos;
import Modelo.Ruta;
import Modelo.Usuario;
import Vista.Insumos;
import Vista.Principal;
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

/**
 *
 * @author jhon
 */
public class ControladorInsumos implements ActionListener,MouseListener,ItemListener
{
    private Principal principal;
    private Insumos vistaInsumos;
    private ModeloInsumos modeloInsumos;
     private ArrayList<Ruta> listaRuta;
     private Factura modeloFactura;
     private int idDestino;
     private int idInsumos;
     private String descripcionInsumo;
    
    public ControladorInsumos(Principal principal,Insumos vistaInsumos)
    {
        this.principal=principal;
        this.vistaInsumos=vistaInsumos;
        modeloInsumos=new ModeloInsumos();
        modeloFactura=new Factura();
        
        this.vistaInsumos.tablaInsumos.addMouseListener(this);
        this.vistaInsumos.tablaInsumos.setModel(modeloInsumos.obtenerInsumo(""));
        this.vistaInsumos.txt_precio.setValue(new Double(0.0));
        this.vistaInsumos.comboDestino.addItemListener(this);
        
        this.vistaInsumos.btn_agregar.setActionCommand("agregar");
        this.vistaInsumos.btn_agregar.addActionListener(this);
        this.vistaInsumos.btn_eliminar.setActionCommand("eliminar");
        this.vistaInsumos.btn_eliminar.addActionListener(this);
        this.vistaInsumos.txt_buscar.setActionCommand("buscar");
        this.vistaInsumos.txt_buscar.addActionListener(this);
        
        this.vistaInsumos.btn_limpiar.setActionCommand("limpiar");
        this.vistaInsumos.btn_limpiar.addActionListener(this);
        
    }
    
    public void abrirVentana()
    {
        this.principal.contenedor.add(vistaInsumos);
        vistaInsumos.setLocation(centradoXY(vistaInsumos));
        vistaInsumos.setVisible(true);
        vistaInsumos.setTitle("Insumos");
        
        this.llenarCombo();
        this.limpiar();
        ocultar();
                
           
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

    
    private void limpiar()
    {
        this.vistaInsumos.btn_eliminar.setEnabled(false);
        this.vistaInsumos.btn_agregar.setEnabled(true);
        this.vistaInsumos.txt_descripcion.setText("");
        this.vistaInsumos.txt_buscar.setText("");
        this.vistaInsumos.txt_precio.setText("0.0");
    }
    
         /*llenar lista*/
     public void llenarCombo()
     {
         
         listaRuta=modeloFactura.obtenerRutas2();
         this.vistaInsumos.comboDestino.removeAllItems();

         for(int i=0; i<listaRuta.size(); i++)
         {
             //this.vistaFactura.comboRutas.addItem(listaRuta.get(i).getDestino());
             this.vistaInsumos.comboDestino.addItem(listaRuta.get(i).getDestino());
             //this.vistaRuta.comboConductor.addItem(listaChofer.get(i).getNombre());
         }
         //this.vistaFactura.comboRutas
     }
     
     //ocultar columna
     private void ocultar()
     {
           this.vistaInsumos.tablaInsumos.getColumnModel().getColumn(0).setMaxWidth(0);
         this.vistaInsumos.tablaInsumos.getColumnModel().getColumn(0).setMinWidth(0);
         this.vistaInsumos.tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(0);
     }
     
    @Override
    public void actionPerformed(ActionEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      String accion=e.getActionCommand();
      
      System.out.println("evento: "+accion);
       
       switch(accion)
       {
           case "buscar":
               String busqueda=vistaInsumos.txt_buscar.getText();
               this.vistaInsumos.tablaInsumos.setModel(modeloInsumos.obtenerInsumo(busqueda));
               this.limpiar();
            ocultar();
               
               break;
/*--------------------------------------------*/           
           case "agregar":
               if(!vistaInsumos.txt_descripcion.getText().equalsIgnoreCase("") && !vistaInsumos.txt_precio.getText().equals("0.0"))
               {
                   modeloInsumos.setDescripcion(vistaInsumos.txt_descripcion.getText());
                   modeloInsumos.setPrecio(Double.parseDouble(vistaInsumos.txt_precio.getText()));
                   modeloInsumos.setDestino_id(idDestino);
                   
                   if(modeloInsumos.registrarInsumo(modeloInsumos))
                   {
                       JOptionPane.showMessageDialog(principal,"Insumo registrado correctamente");
                       this.vistaInsumos.tablaInsumos.setModel(modeloInsumos.obtenerInsumo(""));
                       limpiar();
                       ocultar();
                       this.vistaInsumos.btn_agregar.setEnabled(true);
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(principal,"Ocurrio un error al registrar el insumo");
                   }
                   
               }
               else
               {
                   JOptionPane.showMessageDialog(principal,"EL campo descripción o cantidad no pueden estar vacios.");
               }
               break;
/*-------------------------------------------------------*/    
           case "eliminar":
               
               int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el insumo? \n "+this.descripcionInsumo,"AVISO",JOptionPane.YES_NO_OPTION);
                 if(dialogButton == JOptionPane.YES_OPTION) 
                {
                         if(modeloInsumos.eliminarInsumo(Usuario.idUsuario, idInsumos))
                                {
                                    JOptionPane.showMessageDialog(principal,"Insumo eliminado correctamente");
                                    this.vistaInsumos.tablaInsumos.setModel(modeloInsumos.obtenerInsumo(""));
                                    limpiar();
                                    ocultar();
                                     this.vistaInsumos.btn_agregar.setEnabled(true);
                                }
                        else
                                { 
                                    JOptionPane.showMessageDialog(principal,"Ocurrio un error al eliminar el insumo");
                                }
                          
                }
                else 
                {
                    principal.remove(dialogButton);
                }
               /*---*/
               
              
               break;
           case "limpiar":
                limpiar();
                break;
           default:
               break;
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if( e.getButton()== 1)//boton izquierdo
        {
             
             int fila = this.vistaInsumos.tablaInsumos.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
             this.vistaInsumos.btn_agregar.setEnabled(false);
             this.vistaInsumos.btn_eliminar.setEnabled(true);
             this.idInsumos=Integer.parseInt(String.valueOf(this.vistaInsumos.tablaInsumos.getValueAt(fila, 0)));
             this.descripcionInsumo=String.valueOf(this.vistaInsumos.tablaInsumos.getValueAt(fila, 2))+" "+String.valueOf(this.vistaInsumos.tablaInsumos.getValueAt(fila, 3));

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
              if(e.getSource().equals(this.vistaInsumos.comboDestino))
                {
                    

                    idDestino=listaRuta.get(vistaInsumos.comboDestino.getSelectedIndex()).getId();
                       System.out.println("id destino:"+idDestino);
                    
                }
            
       }
        
    }
    
    
    
    
         
    
}
