/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triggersmysql;

import javax.swing.*;
import java.sql.*; //Import del la libreria SQL
/**
 *
 * @author JoseDeskT
 */
public class Conector {
    
    private Connection conexion; //Declaramos nuestra variable de tipo conexion
    
    public Connection getConexion(){ //Metodo para obtener la conexion
    
    return conexion;
    
    }
    
    public void setConexion(Connection conexion){
    
    this.conexion = conexion;
    
    
    }
    //Metodo para verificar y contralar las exepciones al momento de conectar a la base de datos
    public Conector conectar(){
    try{
        Class.forName("org.mariadb.jdbc.Driver");
        String BaseDatos = "jdbc:mariadb://localhost/tareabd?user=root&password=1234";
        setConexion(DriverManager.getConnection(BaseDatos));
        if(getConexion() != null){
            
         
        }else{
        JOptionPane.showMessageDialog(null, "Conexion fallida!");
        }
    //Se obtiene la exepcion en caso de que exista
    } catch(Exception e){
    e.printStackTrace();
    }
    
    return this;
    
    }
    
    //Metodo que nos devuelve si la sentencia SQL se ejecuta correctamente o no.
    public boolean ejecutar(String sql){
    try{
        Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        sentencia.executeUpdate(sql);
        sentencia.close();
    
    //asi mismo obtener la exepcion en caso de que exista.
    }catch(SQLException e) {
    e.printStackTrace();
    return false;
    }
    
    return true;
    
    }
    
    //Metodo para consulta
    public ResultSet consultar(String sql){
    
        ResultSet resultado;
        
    try{
        Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        resultado = sentencia.executeQuery(sql);
        getConexion().commit();
    
    }catch(SQLException e){
    
        e.printStackTrace();
        return null;
    
    }
    
    return resultado;
    
    }
    
    
    }
    


