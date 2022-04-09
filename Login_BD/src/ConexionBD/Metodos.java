package ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Metodos {

	public static Sql_Conexion connect= new Sql_Conexion();
	
	public static PreparedStatement pst;
	public static ResultSet rst;
	public static String sql;
	public static int resultado = 0;
	
	public int guardar (String nombre, String apellidos, String usuario, String telefono, String correo, String contraseña) {
		
		int result = 0;
		Connection conexion = null;
		
		String Guardar_Datos = ("INSERT INTO usuarios (nombre, apellidos, usuario, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?, ?)");
		
		try {
			conexion = Sql_Conexion.conectar();
			pst = conexion.prepareStatement(Guardar_Datos);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, usuario);
			pst.setString(4, telefono);
			pst.setString(5, correo);
			pst.setString(6, contraseña);
			
			result = pst.executeUpdate();
			pst.close();
			conexion.close();
		} catch (Exception e) {
			
		}
		
		return result;
	}
	
	public static String buscarNombre(String usuario) {
		
		String buscar = null;
		Connection conexion = null;
		
		try {
			
			conexion = Sql_Conexion.conectar();
			String buscar_Datos = ("SELECT nombre, apellidos FROM usuarios WHERE usuario = '" + usuario + "'");
			pst = conexion.prepareStatement(buscar_Datos);
			rst = pst.executeQuery();
			
			if (rst.next()) {
				String nombre = rst.getString("nombre");
				String apellidos = rst.getString("apellidos");
				buscar = (nombre + " " + apellidos);
			}
			
			conexion.close();
			
		} catch (Exception e) {
		}
		
		return buscar;
	}
	
	public static String Usuario_registrado(String usuario, String contraseña) {
		
		String Registro = null;
		Connection conexion = null;
		
		try {
			
			conexion = Sql_Conexion.conectar();
			String Buscar_usuario = ("SELECT nombre, apellidos, correo, contraseña FROM usuarios WHERE usuario = '" + usuario + "' && contraseña = '" + contraseña + "'");
			
			pst = conexion.prepareStatement(Buscar_usuario);
			rst = pst.executeQuery();
			
			if (rst.next()) {
				Registro = "Usuario encontrado";
			}else {
				Registro = "Ususario no encontrado";
			}
			
			conexion.close();
		} catch (Exception e) {
		
		}
		
		return Registro;
	}
}
