package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sql_Conexion {
	
	public static String clase = "com.mysql.jdbc.Driver";
	
	public static Connection conectar() {
		Connection conexion = null;
		
		try {
			Class.forName(clase);
			String url = "jdbc:mysql://localhost/login_db";
			String usuario = "root";
			String contraseña = "Joanmanuelfabian1114@";
			
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			System.out.println("Conexion establecida");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conexion;
	}
}
