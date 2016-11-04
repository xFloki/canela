
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    public void insertar(String titulo,String duracion,String letras,int id, int album){
        try {
        // Crea un statement
        Statement sta = conn1.createStatement();
        // Ejecuta la inserción
        sta.executeUpdate("INSERT INTO cancion (`titulo`, `duracion`, `letras`, `id`, `album`)"
                + "VALUES ('"+ titulo +"' , '"+ duracion +"', '"+ letras +"' ,"+ id + "  , " + album +" )"); 
        // Cierra el statement
        sta.close();
        } catch (SQLException ex) {
        System.out.println("ERROR:al hacer un Insert");
        ex.printStackTrace();
    }
} 
    // En caso de que se vaya a introducir un libro junto a una editorial utilizamos este metodo 
    // ya que quitamos el autocommit y con el rollback nos aseguramos de que si la conexion se piede cuando 
    // solo hemos introducido el album y no la concion volveriamos al comienzo en veez de dejar las transaccion a medias 
     public void insertar_con_commit(String titulo,String duracion,String letras,int id, int album,
            int _id, int _publicacion, String _titulo) {
      
        
        try {
         conn1.setAutoCommit(false);
        Statement sta =  conn1.createStatement();
         sta.executeUpdate("INSERT INTO album (`id`, `titulo`, `publicacion`)"
                 + "VALUES ( "+ _id +", '"+ _titulo +"', "+ _publicacion +")" );
        sta.executeUpdate("INSERT INTO cancion (`titulo`, `duracion`, `letras`, `id`, `album`)"
                + "VALUES ('"+ titulo +"' , '"+ duracion +"', '"+ letras +"' ,"+ id + "  , " + album +" )");    
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
     
     
        public String cargarDatosAlbum(int _id, String _dato) {
//            int ide = Integer.parseInt(_id);
            String yolo= "";
            int yolos;
          
            try {
           Statement sta = conn1.createStatement();
           String query = "SELECT * FROM album where id = " + _id ;
           ResultSet rs = sta.executeQuery(query);
           
//           int anno = rs.getInt("publicacion");
//        if(_dato.equals("titulo"))
         yolos = rs.getInt("id") ;
//        else 
//            yolo = String.valueOf(rs.getInt(_dato));
//          
//          
           rs.close();
           sta.close();
           } catch (SQLException ex) {
           System.out.println("ERROR:al consultar");
           
           }
            return yolo;
   }    
        
         public void consulta_Statement(String pregunta, JTable tabla) {
        
        
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tabla.setModel(modelo);

            //Para ejecutar la consulta
            Statement s = conn1.createStatement();
            //Ejecutamos la consulta que escribimos en la caja de texto
            //y los datos lo almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(pregunta);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            conn1.close();
           } catch (Exception ex) {
            ex.printStackTrace();
           }

          
        
              
}
    
}

