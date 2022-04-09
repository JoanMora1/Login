package ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador_tablas {

	public static Sql_Conexion connect = new Sql_Conexion();
	
	
	public DefaultTableModel MostrarUsuarios()
	{
		Connection conexion = null;
		String [] nombrarColumnas = {"nombre", "apellidos", "usuario", "telefono", "correo", "contraseña"};
		String [] resgistro = new String [6];
		DefaultTableModel modelo = new DefaultTableModel(null, nombrarColumnas);
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String Consulta = "SELECT FROM usuarios";
		
		try {
			conexion = Sql_Conexion.conectar();
			
			pst = conexion.prepareStatement(Consulta);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				resgistro[0] = rs.getString("nombre");
				resgistro[1] = rs.getString("apellidos");
				resgistro[2] = rs.getString("usuario");
				resgistro[3] = rs.getString("telefono");
				resgistro[4] = rs.getString("correo");
				resgistro[5] = rs.getString("contraseña");
				modelo.addRow(resgistro);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar");
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (conexion != null) conexion.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		return modelo;
}
}