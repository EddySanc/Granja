/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eddy
 */
public class Privilegios {
    
    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Privilegios() {
    }

    public Privilegios(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public ArrayList<Privilegios> ObtenerPrivilegios(int id){
    
        ArrayList<Privilegios>privilegios = new ArrayList<>();
        String consulta = "Select * from usuario_privilegios as u inner join privilegios as p on "
                + "p.id = u.privilegios_id where usuario_id = ?";
        try{
            PreparedStatement pst = Conexion.obtenerConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                privilegios.add(new Privilegios(rs.getInt("privilegios_id"),rs.getString("descripcion")));
            }
            
        }
        catch(SQLException e){
            System.out.println("Error consulta privilegios"+e.toString());
        }
        
        return  privilegios;
        
    
    } 
    
    public boolean Actualizar_Usuario_Privilegio(int id,ArrayList<String> privilegios){
    
        boolean result = false;
        try{
            String consulta = "DELETE FROM usuario_privilegios WHERE usuario_id = ?";
            PreparedStatement pst = Conexion.obtenerConexion().prepareStatement(consulta);
            pst.setInt(1, id);
            pst.executeUpdate();
            
            if(!privilegios.isEmpty()){
                consulta = "INSERT INTO usuario_privilegios VALUES(?,?)";
                for(int i = 0; i<privilegios.size(); i++){
                    System.err.println("Here"+id);
                    pst = Conexion.obtenerConexion().prepareStatement(consulta);
                    pst.setInt(1, id);
                    pst.setInt(2,Integer.parseInt(privilegios.get(i)));
                    pst.executeUpdate();
                }
            }
            result = true;
        }
        catch(SQLException e){
            System.err.println("Erro privilegios:::"+e.getLocalizedMessage());
            result = false;
        }
        return  result;
    }
}
