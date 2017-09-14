// @author Javier Fdez

package gui_ahorcado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

//****  VARIABLES
    private Connection con;
    private ResultSet rs;
    private Statement st;
    private String sql;
//****  CONSTRUCTOR
    public BaseDatos(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error con el driver");
        }
        String url = "jdbc:sqlserver://localhost:1433;databaseName=programacion;integratedSecurity=false";
        String pwd = "ases";
        String login = "sa";
        try {
            con = DriverManager.getConnection(url, login, pwd);
            st = con.createStatement();
            System.out.println("Conexion OK");
        } catch (SQLException ex) {
            System.out.println("Error conexion");
        }
    }
//****  GET / SET
//****  MÉTODOS
    public void agregarPartida(String palabra, int numFallos){
     sql = "INSERT INTO ahorcado VALUES(GETDATE(),'"+palabra+"','"+numFallos+"')";   
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean comprobarUsuario(String s){
        boolean existe = true;
        int num =0;
        sql = "  select COUNT(*) as 'usuario' from sys.database_principals where name = '"+s+"'";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()){
                num=rs.getInt("usuario");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al comprobar usuario");
        }
        if (num==0){
            existe=false;
        }
        
        return existe;
    }
}
