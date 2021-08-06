/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eddy
 */
public class Usuario {
    
    private Privilegios priv = new Privilegios();
    private int id;
    private String nombre;
    private String usuario;
    private String pwd;
    private ArrayList<Privilegios> privilegios = new ArrayList<>();
    public static int idUsuario;
    public static int crear = 0;
    public static int modificar = 0;
    public static int eliminar = 0;
    public static int ventas = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Usuario() {
    }

    public Usuario(int id, String nombre, String usuario, String pwd) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pwd = pwd;
    }
    
    
    
    public DefaultTableModel obtenerUsuarios (String dato) {
        //String condicion="where nombre LIKE '%"+dato+"%' OR direccion LIKE '%"+dato+"%'";
        DefaultTableModel modelo = new DefaultTableModel();
        int registros = 0;
        String[] columnas = {"ID", "NOMBRE", "USUARIO", "CONTRASEÑA","PRIVILEGIOS"};

        /*Recuperar datos de la BD*/
        try {
            String query = "SELECT count(*) as total FROM usuario where estado = 1";
            PreparedStatement st = Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos de la tabla Usuarios");
        }

        /*Crear una matriz con filas y columnas necesarias*/
        Object[][] datos = new String[registros][5];
        try {
            String cadena = "";
            String query = "SELECT * FROM usuario where estado = 1";

            PreparedStatement st = Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res = st.executeQuery();

            int i = 0;
            while (res.next()) {
                 privilegios = priv.ObtenerPrivilegios(res.getInt("id"));
                //String [] columnas={"FOLIO","CANTIDAD","FECHA","PROVEEDOR","KILOS","TIPO","OBSERVACIÒN"};
                datos[i][0] = res.getString("id");
                datos[i][1] = res.getString("nombre");
                datos[i][2] = res.getString("usuario");
                datos[i][3] = res.getString("pwd");
               
                if(!privilegios.isEmpty()){

                    for(int x = 0 ;x<privilegios.size();x++){
                        cadena = cadena+"/"+privilegios.get(x).getDescripcion();
                    }
                }

                datos[i][4] = cadena;
                cadena="";
                i++;
            }
            res.close();
            //añadir la matriz al modelo
            modelo.setDataVector(datos, columnas);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla Usuarios:" + e.toString());
        }
        return modelo;

    }

    /*Registrar Proveedor*/
    public boolean registrarUsuario(Usuario usuario,ArrayList<String> privilegios) {
        priv = new Privilegios();
        boolean result = false;
        //Inseccion en la tabla pollos
        String consulta = "INSERT INTO usuario values (0,?,?,?,1)";
        try {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getUsuario());
            preparedStatement.setString(3, usuario.getPwd());
            preparedStatement.executeUpdate();

            result = true;
            
            result = priv.Actualizar_Usuario_Privilegio(getLastId(), privilegios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el Usuario");
            result = false;
        }

        return result;
    }

    /*Eliminar Proveedor*/
    public boolean eliminarUsuario(int id) {
        boolean res = false;
        //se arma la consulta

        //se ejecuta la consulta
        try {
            String q = " UPDATE usuario SET estado = 2 WHERE  id = ?";
            PreparedStatement pstm = Conexion.obtenerConexion().prepareStatement(q);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            res = false;
        }

        return res;
    }

    /*Actualizar informacion*/
    public boolean actualizarUsuario(Usuario usuario,ArrayList<String> privilegios) {
        boolean result = false;
        priv = new Privilegios();
        String consulta = "UPDATE usuario set nombre = ?, usuario = ? , pwd = ? where id=?";
        try {
            PreparedStatement preparedStatement = Conexion.obtenerConexion().prepareStatement(consulta);

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getUsuario());
            preparedStatement.setString(3, usuario.getPwd());
            preparedStatement.setInt(4, usuario.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            result = true;
            
            
            result = priv.Actualizar_Usuario_Privilegio(usuario.getId(), privilegios);
        } catch (Exception d) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la tabla Usuario" + d.getLocalizedMessage().toString());
            result = false;
        }

        return result;
    }
    
    
    public int getLastId(){
        
        try{
            
            String query="SELECT max(id) as id FROM usuario";
            PreparedStatement st=Conexion.obtenerConexion().prepareStatement(query);
            ResultSet res=st.executeQuery();
            
            while(res.next()){
                
                return res.getInt("id");
            }
            st.close();
        }
        
        catch(Exception d)
        {
            JOptionPane.showMessageDialog(null, "Error al obtener el identificador del usuario"+d.getLocalizedMessage().toString());
            return 0;
        }
        return 0;
    }
    
    public boolean Login(String usuario,String pwd){
    
        boolean res = false;
        
        try {
            String consulta = "SELECT id FROM usuario WHERE usuario = ? and pwd = ? and estado = 1";
            PreparedStatement pst  = Conexion.obtenerConexion().prepareStatement(consulta);
            pst.setString(1,usuario);
            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                res = true;
                idUsuario = rs.getInt("id");
                
            }
            privilegios = priv.ObtenerPrivilegios(idUsuario);
            if(!privilegios.isEmpty()){
                    for(int i = 0; i<privilegios.size(); i++){
                    
                        switch(privilegios.get(i).getId()){
                            case 1:
                                crear = 1;
                                break;
                            case 2: 
                                modificar = 1;
                                break;
                            case 3:
                                eliminar = 1;
                                break;
                            case 4:
                                ventas = 1;
                                break;
                        
                        }
                        
                    }
            }
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            res = false;
        }
        return res;
    }
    
    
}
