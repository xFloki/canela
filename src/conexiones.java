
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
public class conexiones {
    Connection conn1 = null;
    
     public void GestorConexion() {
       
        try {
        String url1 = "jdbc:mysql://localhost:3306/discografia";
        String user = "root";
        String password = "root";
        conn1 = (Connection) DriverManager.getConnection(url1, user, password);
        if (conn1 != null) {
        System.out.println("Conectado a discográfica…");
        }
        } catch (SQLException ex) {
        System.out.println("ERROR: dirección o usuario/clave no válida");
        ex.printStackTrace();
        }
 } 
    public void cerrar_conexion() {
        try {
         conn1.close();
        } catch (SQLException ex) {
         System.out.println("ERROR:al cerrar la conexión");
         ex.printStackTrace();
         }
} 
    
     public void insertar_con_commit(String titulo,String duracion,String letras,int id, int album) {
      
        
        try {
         conn1.setAutoCommit(false);
        Statement sta =  conn1.createStatement();
        sta.executeUpdate("INSERT INTO cancion VALUES ('"+ titulo +"' , '"+ duracion +"', '"+ letras +"' ,"+ id + "  , " + album +" )");

         conn1.commit();
        } catch (SQLException ex) {
        System.out.println("ERROR:al hacer un Insert");
        try{
        if( conn1!=null)
          conn1.rollback();
        }catch(SQLException se2){
        se2.printStackTrace();
        }
        ex.printStackTrace();
        }
} 
     
     
     
    
}
