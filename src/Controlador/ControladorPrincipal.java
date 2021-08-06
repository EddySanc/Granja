/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Proveedor;
import Vista.Bascula;
import Vista.CRUDAves;
import Vista.CRUDCarros;
import Vista.CRUDChofer;
import Vista.CRUDProveedor;
import Vista.CRUDReja;
import Vista.CRUDRuta;
import Vista.EstadosFacturas;
import Vista.Facturar;
import Vista.ImpresionRutas;
import Vista.Insumos;
import Vista.MovimientoFactura;
import Vista.Pedidos;
import Vista.Principal;
import Vista.Sesion;
import Vista.VistaInsumos;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhon
 */
public class ControladorPrincipal implements ActionListener,MouseListener{
    
    //Declaracion de variables de clase
    private Principal vistaPrincipal;
    private Sesion vistaSesion;
    private ControladorPollos controlP;
    private ControladorCliente controlC;
    private ControladorUsuario controlU;
    private ControladorLogin controlL;
    private ControladorCredito controlCred;
    private CRUDProveedor vistaProveedor;
    private Proveedor modeloProveedor;
    private String idProveedor;
    private ControladorPollos  controladorP;
    
    
    
    //__________-
    private CRUDChofer vistaChofer;
    private CRUDCarros vistaCarro;
    private CRUDRuta vistaRuta;
    private CRUDReja vistaReja;
    private Pedidos vistaPedidos;
    private Bascula vistaBascula;
    public static int estado=0;
     /*Agregado: 26-03-19*/
    private Facturar vistaFactura;
    /*04.04.19*/
    private ImpresionRutas vistaImpresionRutas;
    /*----Agregado 07.04.19*/
    private MovimientoFactura vistaMovimientoFactura;
    private Insumos vistaInsumos;
    private VistaInsumos vistaReporteInsumos;
    private EstadosFacturas vistaEstadosFacturas;

    public ControladorPrincipal(Principal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        modeloProveedor = new Proveedor();
        dibujarVentana();
        Act_Desac_Opciones(0,vistaPrincipal.Proveedores,vistaPrincipal.Aves,
            vistaPrincipal.Carros,vistaPrincipal.Carros,vistaPrincipal.Clientes,
            vistaPrincipal.Ventas,vistaPrincipal.Reportes,
            vistaPrincipal.Configuraciones,vistaPrincipal.Salir);
    }

    public ControladorPrincipal(Principal vistaPrincipal,int i) {
        this.vistaPrincipal = vistaPrincipal;
    }
    
    
    
    
    
    /*cargar valores por defecto*/
    private void dibujarVentana()
    {
        
             // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaPrincipal);
            vistaPrincipal.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        
        this.vistaPrincipal.setTitle("Sistema Control de Ventas");
        this.vistaPrincipal.setLocationRelativeTo(null);
        this.vistaPrincipal.setExtendedState(this.vistaPrincipal.MAXIMIZED_BOTH);
        this.vistaPrincipal.setVisible(true);
        
        /*Escucha boton login*/
        this.vistaPrincipal.btn_login.setActionCommand("Login");
        this.vistaPrincipal.btn_login.addActionListener(this);
        
        /*Escucha boton login*/
        this.vistaPrincipal.btn_salir.setActionCommand("salir");
        this.vistaPrincipal.btn_salir.addActionListener(this);
        /*Escucha boton proveedores*/
        this.vistaPrincipal.btn_proveedores.setActionCommand("Proveedor");
        this.vistaPrincipal.btn_proveedores.addActionListener(this);
        
        //Escucha boton aves
        this.vistaPrincipal.btn_CRUDAves.setActionCommand("CRUDAves");
        this.vistaPrincipal.btn_CRUDAves.addActionListener(this);
        
        //Escucha boton clientes
        this.vistaPrincipal.btn_CRUDClientes.setActionCommand("CRUDClientes");
        this.vistaPrincipal.btn_CRUDClientes.addActionListener(this);
        
        //Escucha boton Usuarios
        this.vistaPrincipal.btn_CRUDUsuarios.setActionCommand("CRUDUsuarios");
        this.vistaPrincipal.btn_CRUDUsuarios.addActionListener(this);
        
        //Escucha boton Rutas
        this.vistaPrincipal.btn_CRUDCarros.setActionCommand("CRUDCarros");
        this.vistaPrincipal.btn_CRUDCarros.addActionListener(this);
        
        //Escucha boton Rutas
        this.vistaPrincipal.btn_CRUDRutas.setActionCommand("CRUDRutas");
        this.vistaPrincipal.btn_CRUDRutas.addActionListener(this);
        
         //Escucha boton Chofer
        this.vistaPrincipal.btn_CRUDChofer.setActionCommand("CRUDChofer");
        this.vistaPrincipal.btn_CRUDChofer.addActionListener(this);
        
        //Escucha boton Ventas
        this.vistaPrincipal.btn_Ventas.setActionCommand("Ventas");
        this.vistaPrincipal.btn_Ventas.addActionListener(this);

        //Escucha boton Ventas
        this.vistaPrincipal.btn_ConfigBascula.setActionCommand("ConfigBascula");
        this.vistaPrincipal.btn_ConfigBascula.addActionListener(this);
        
        //Escucha boton Rejas
        this.vistaPrincipal.btn_CRUDRejas.setActionCommand("CRUDRejas");
        this.vistaPrincipal.btn_CRUDRejas.addActionListener(this);
        
         //Escucha boton Rejas
        this.vistaPrincipal.btn_Credito.setActionCommand("Credito");
        this.vistaPrincipal.btn_Credito.addActionListener(this);
        
        /*escucha facturar agregado 26-03-19*/
        this.vistaPrincipal.btn_facturar.setActionCommand("facturar");
        this.vistaPrincipal.btn_facturar.addActionListener(this);
        
        /*04.04.19*/
        this.vistaPrincipal.btn_impresiones.setActionCommand("impresionfactura");
        this.vistaPrincipal.btn_impresiones.addActionListener(this);
        
        /*---07-04-19*/
        this.vistaPrincipal.btn_movimientoFactura.setActionCommand("movimientoFactura");
        this.vistaPrincipal.btn_movimientoFactura.addActionListener(this);
        this.vistaPrincipal.btn_insumos.setActionCommand("insumos");
        this.vistaPrincipal.btn_insumos.addActionListener(this);
        this.vistaPrincipal.reporetInsumos.setActionCommand("reporteInsumos");
        this.vistaPrincipal.reporetInsumos.addActionListener(this);
        
        this.vistaPrincipal.btn_estadoFactura.setActionCommand("estadoFactura");
        this.vistaPrincipal.btn_estadoFactura.addActionListener(this);
        this.vistaPrincipal.btn_estadoFactura.setVisible(false);
        
    }


    
    
    /*Abrir JInternal Proveedores*/
    private void abrirProveedor()
    {
        if(estacerrado(vistaProveedor))
        {
         vistaProveedor = new CRUDProveedor();
         this.vistaPrincipal.contenedor.add(vistaProveedor);
         vistaProveedor.setLocation(centradoXY(vistaProveedor));
         vistaProveedor.setVisible(true);
         
         /*Enlazar los botones de la vista CRUDProveedor al controladorr*/
         vistaProveedor.btn_guardarP.setActionCommand("guardarP");
         vistaProveedor.btn_eliminarP.setActionCommand("eliminarProveedor");
         vistaProveedor.btn_actualizarP.setActionCommand("actualizarP");
         vistaProveedor.btn_reporteP.setActionCommand("reporteP");
         vistaProveedor.btn_limpiarP.setActionCommand("limpiar");
         vistaProveedor.txt_busquedaProveedor.setActionCommand("buscarProveedor");
         vistaProveedor.txt_kiloP.setValue(new Double(3));
         
         /*Agregar los escuchas*/
         vistaProveedor.btn_guardarP.addActionListener(this);
         vistaProveedor.btn_eliminarP.addActionListener(this);
         vistaProveedor.btn_actualizarP.addActionListener(this);
         vistaProveedor.btn_reporteP.addActionListener(this);
         vistaProveedor.btn_limpiarP.addActionListener(this);
         vistaProveedor.txt_busquedaProveedor.addActionListener(this);
                
         /*Enalazar la tabla*/
         vistaProveedor.tablaProveedor.addMouseListener(this);
         vistaProveedor.tablaProveedor.setModel(this.modeloProveedor.obtenerProveedores(""));
         
         /*Oculto la Columna ID de la tabla Proveedor*/
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMinWidth(0);
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(0);

         
         modeloProveedor.limpiarCajas(vistaProveedor);
         modeloProveedor.habilitarBotones(vistaProveedor, true,false,false);
        }else
        {
            JOptionPane.showMessageDialog(vistaPrincipal, "Ya tienes abierta esta ventana");
        }
    }
    
    /*Agregado el dia 26.03.19*/
    private void abrirFactura()
    {
        if(estacerrado(vistaFactura))
        {
            vistaFactura=new Facturar();
            ControladorFactura fr=new ControladorFactura(vistaPrincipal,vistaFactura);
            fr.abrirVentana();
        }
    }
    
    /*JInternalFrame CRUDCarro*/
    private void abrirCRUDCarro()
    {
        if(estacerrado(vistaCarro))
        {
            vistaCarro=new CRUDCarros();
            ControladorCarro cr=new ControladorCarro(vistaPrincipal, this, vistaCarro);
            cr.abrirVentana();
        }
    }
    
    private void abrirReja()
    {
        if(estacerrado(vistaReja))
        {
            vistaReja=new CRUDReja();
            ControladorReja reja=new ControladorReja(vistaPrincipal,vistaReja);
            reja.abrirVentana();
        }
    }
    
    private void abrirBascula()
    {
        if(estacerrado(vistaBascula))
        {
            vistaBascula=new Bascula();
            ControladorBascula bas=new ControladorBascula(vistaPrincipal,vistaBascula);
            bas.abrirVentana();
        }
    }
    
    /*JInternalFrame CRUDRuta*/
    private void abrirCRIDRuta()
    {
        if(estacerrado(vistaRuta))
        {
            vistaRuta=new CRUDRuta();
            ControladorRuta ruta=new ControladorRuta(this, vistaPrincipal, vistaRuta);
            ruta.abrirVentana();
            
        }
    }
    
    /*Venta JInternal*/
    private void abrirVenta()
    {
        if(estacerrado(vistaPedidos))
        {
            vistaPedidos=new Pedidos();
            ControladorPedidosVenta pedidosV=new ControladorPedidosVenta(vistaPrincipal,vistaPedidos);
            pedidosV.abrirVentana();
            
        }
    }
      /*JInternalFram Chofer*/
    private void abrirCRUDChofer()
    {
        if(estacerrado(vistaChofer))
        {
            vistaChofer=new CRUDChofer();
           
            ControladorChofer xy=new ControladorChofer(vistaPrincipal, vistaChofer);
            xy.abrirChofer();
            
        }
    }
    
    /*Internal VistaImpresionFactura*/
    private void abrirImpresionFactura()
    {
        if(estacerrado(vistaImpresionRutas))
        {
            vistaImpresionRutas=new ImpresionRutas();
            ControladorImpresionRutas xp=new ControladorImpresionRutas(vistaPrincipal,vistaImpresionRutas);
            xp.abrirVentana();
        }
        
    }
    
   /*07.04.19*/
      private void abrirMovimientoFactura()
    {
        if(estacerrado(vistaMovimientoFactura))
        {
            vistaMovimientoFactura=new MovimientoFactura();
            ControladorMovimientoFactura xp2=new ControladorMovimientoFactura(vistaPrincipal,vistaMovimientoFactura);
            xp2.abrirVentana();
        }
        
    }
      
        private void abrirInsumos()
    {
        if(estacerrado(vistaInsumos))
        {
            vistaInsumos=new Insumos();
            ControladorInsumos xp3=new ControladorInsumos(vistaPrincipal,vistaInsumos);
            xp3.abrirVentana();
        }
        
    }
        
              private void abrirReporteInsumos()
    {
        if(estacerrado(vistaReporteInsumos))
        {
            vistaReporteInsumos=new VistaInsumos();
            ControladorVistaInsumos xp4=new ControladorVistaInsumos(vistaPrincipal,vistaReporteInsumos);
            xp4.abrirVentana();
        }
        
    }
              
              /*----------------------*/
 private void abrirEstadosFacturas()
    {
        if(estacerrado(vistaEstadosFacturas))
        {
            vistaEstadosFacturas=new EstadosFacturas();
            ControladorEstadoFactura xp5=new ControladorEstadoFactura(vistaPrincipal,vistaEstadosFacturas);
            xp5.abrirVentana();
        }
        
    }
   
   
    
     // CIERRA UN JInternalFrame
    private void cerrar(JInternalFrame jif)
    { 
        try {
            jif.setClosed(true);
        } catch (PropertyVetoException ex) {}
    }

    /*_Centrar Jinternal*/
    private Point centradoXY(JInternalFrame jif)
    {
        Point p = new Point(0,0);
        //se obtiene dimension del contenedor
        Dimension pantalla = this.vistaPrincipal.contenedor.getSize();
        //se obtiene dimension del JInternalFrame
        Dimension ventana = jif.getSize();
        //se calcula posición para el centrado
        p.x = (pantalla.width - ventana.width) / 2;
        p.y = (pantalla.height - ventana.height) / 2;
        return p;
    }
        
   //METODO QUE DEVUELVE UN VALOR BOOLEAN PARA SABER SI UN JINTERNALFRAME ESTA ABIERTO O NO
    private boolean estacerrado(Object obj)
    {
        
        JInternalFrame[] activos=this.vistaPrincipal.contenedor.getAllFrames();
        boolean cerrado=true;
        int i=0;
        while (i<activos.length && cerrado){
            if(activos[i]==obj){
               
                cerrado=false;
            }
            i++;
        }
        return cerrado;
       }     
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        String accion=e.getActionCommand();
   
    
    switch(accion)
    {

        case "estadoFactura":
            abrirEstadosFacturas();
            break;
        case "reporteInsumos":
            abrirReporteInsumos();
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------*/
        case "insumos":
            this.abrirInsumos();
        
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------*/  
        /*07.04.19*/
        case "movimientoFactura":
            this.abrirMovimientoFactura();
            break;
            
/*-------------------------------------------------------------------------------------------------------------------------------------------*/         
        case "impresionfactura":
            this.abrirImpresionFactura();
            break;
/*-------------------------------------------------------------------------------------------------------------------------------------------*/          
        /*agregado 26.03.19*/
        case "facturar":
            this.abrirFactura();
                break;
/*-------------------------------------------------------------------------------------------------------------------------------------------*/                
        case "Login":
            controlL = new ControladorLogin(vistaPrincipal);
           
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/             
            
        case "buscarProveedor":
            String busqueda=vistaProveedor.txt_busquedaProveedor.getText().toString();
            vistaProveedor.tablaProveedor.setModel(this.modeloProveedor.obtenerProveedores(busqueda));
             /*Oculto la Columna ID de la tabla Proveedor*/
            vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMaxWidth(0);
            vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMinWidth(0);
            vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(0);
            modeloProveedor.limpiarCajas(vistaProveedor);
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/  
        case "Proveedor":
            System.out.println("Checado");
            this.abrirProveedor();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "limpiar":
            modeloProveedor.limpiarCajas(vistaProveedor);
            modeloProveedor.habilitarBotones(vistaProveedor, true,false,false);
          
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/            
        case "eliminarProveedor":
          
            Proveedor eliminar=new Proveedor();
            int dialogButton = JOptionPane.showConfirmDialog (null, "¿Esta seguro de eliminar el Proveedor","AVISO",JOptionPane.YES_NO_OPTION);

                if(dialogButton == JOptionPane.YES_OPTION) 
                {
                            if(eliminar.eliminarProveedor(Integer.parseInt(idProveedor)))
                             {
                                 this.cargarTablaProveedor();
                                 JOptionPane.showMessageDialog(vistaPrincipal, "Proveedor eliminado correctamente");
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(vistaPrincipal, "Ocurrio un error al eliminar el Proveedor, \n Inténtelo nuevamente");
                             }
                }
                else 
                {
                    vistaPrincipal.remove(dialogButton);
                }

         
            
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        case "guardarP":
            String nombreP=vistaProveedor.txt_nombreP.getText();
            String direccionP=vistaProveedor.txt_direccionP.getText();
            String kilo=vistaProveedor.txt_kiloP.getText();
            //double valorK=vistaProveedor.txt_kiloP.getValue();
            String ob=vistaProveedor.txt_obsP.getText();
            
            if(modeloProveedor.validarEspacios(nombreP,direccionP,kilo))
            {
                //Registrar datos  public Proveedor(int id, String nombre, String direccion, double preciokilo, String observacion, String fecha) {
                Proveedor datos=new Proveedor(0,nombreP,direccionP,Double.parseDouble(kilo),ob,"");
                if(datos.registrarProveedor(datos))
                {
                      vistaProveedor.tablaProveedor.setModel(this.modeloProveedor.obtenerProveedores(""));
                       modeloProveedor.limpiarCajas(vistaProveedor);
                      JOptionPane.showMessageDialog(vistaPrincipal, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaPrincipal, "Ocurrio un error al registrar el Proveedor, \n Inténtelo nuevamente");
                   modeloProveedor.limpiarCajas(vistaProveedor);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(vistaPrincipal, "Existe una casilla en blanco, Verifique");
            }
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/            
        case "actualizarP":
       
            if(modeloProveedor.validarEspacios(vistaProveedor.txt_nombreP.getText(),vistaProveedor.txt_direccionP.getText(),vistaProveedor.txt_kiloP.getText()))
            {
                Proveedor act=new Proveedor(Integer.parseInt(idProveedor),vistaProveedor.txt_nombreP.getText(),vistaProveedor.txt_direccionP.getText(),Double.parseDouble(vistaProveedor.txt_kiloP.getText()),vistaProveedor.txt_obsP.getText(),"");
                if(act.actualizarProveedor(act))
                {
                     vistaProveedor.tablaProveedor.setModel(this.modeloProveedor.obtenerProveedores(""));
                     modeloProveedor.limpiarCajas(vistaProveedor);
                     modeloProveedor.habilitarBotones(vistaProveedor,true,false,false);
                     JOptionPane.showMessageDialog(vistaPrincipal, "Registro Exitoso");
                }
                else
                {
                   JOptionPane.showMessageDialog(vistaPrincipal, "Ocurrio un error al actualizar el Proveedor, \n Inténtelo nuevamente");
                   modeloProveedor.limpiarCajas(vistaProveedor);
                }
                
            }
            else
            {
             JOptionPane.showMessageDialog(vistaPrincipal, "Existe una casilla en blanco, Verifique");   
            }
            break;
        case "CRUDAves":
      
            controlP = new ControladorPollos(vistaPrincipal);
      
            break;
            
        case "CRUDClientes":
            controlC = new ControladorCliente(vistaPrincipal);
            break;
        case "CRUDUsuarios":
            controlU = new ControladorUsuario(vistaPrincipal);
            break;
            
            /*___________________________________________________________________________________________________________________________________________________________________*/            
        case "ConfigBascula":
            
            abrirBascula();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/                        
        case "Ventas":
            this.abrirVenta();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/                        
        case "CRUDRutas":
            this.abrirCRIDRuta();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/                       
        case "CRUDCarros":
            this.abrirCRUDCarro();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/                       
        case "CRUDRejas":
            this.abrirReja();
            break;
/*___________________________________________________________________________________________________________________________________________________________________*/                                   
        case "CRUDChofer":
            this.abrirCRUDChofer();
            break;
        case "Credito":
            controlCred = new ControladorCredito(vistaPrincipal);
            break;
        case "salir":
            int opcion = JOptionPane.showConfirmDialog (null, "¿Esta seguro de salir de la aplicación","AVISO",JOptionPane.YES_NO_OPTION);

            if(opcion == JOptionPane.YES_OPTION) 
            {
                System.exit(0);
            }
            break;
            
/*___________________________________________________________________________________________________________________________________________________________________*/              
        default:
            JOptionPane.showMessageDialog(vistaPrincipal, "Opción No Válida \n El sistema se cerrará");
            System.exit(0);
            break;
            
    }
    
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*Evento Clic sobre la tabla*/
         if( e.getButton()== 1)//boton izquierdo
            {
             
             int fila = this.vistaProveedor.tablaProveedor.rowAtPoint(e.getPoint());

             if (fila > -1){                
             /*Habilitar/deshabilitar botones*/
                modeloProveedor.habilitarBotones(vistaProveedor, false,true,true);   

                this.idProveedor=String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 0));
                this.vistaProveedor.txt_nombreP.setText(String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 1)));
                this.vistaProveedor.txt_direccionP.setText(String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 2)));
                this.vistaProveedor.txt_kiloP.setText(String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 3)));
                this.vistaProveedor.txt_obsP.setText(String.valueOf(this.vistaProveedor.tablaProveedor.getValueAt(fila, 4)));
             }
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*MEtodo para cargar tabla Proveedor*/
    private void cargarTablaProveedor()
    {
        
         vistaProveedor.tablaProveedor.setModel(this.modeloProveedor.obtenerProveedores(""));
         
         /*Oculto la Columna ID de la tabla Proveedor*/
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMaxWidth(0);
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setMinWidth(0);
         vistaProveedor.tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void Act_Desac_Opciones(int tipo,JMenu ...x){
    
        if(tipo == 0){
            for(int i=0; i<x.length; i++)
            {
                vistaPrincipal.Entrar.setEnabled(true);
                x[i].setEnabled(false);
                
            }
        }
        else if(tipo == 1){
        
            for(int i=0; i<x.length; i++)
            {
                vistaPrincipal.Entrar.setEnabled(false);
                x[i].setEnabled(true);
                
            }
        }
    
    }
}
