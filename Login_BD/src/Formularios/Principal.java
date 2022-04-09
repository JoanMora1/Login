package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConexionBD.Sql_Conexion;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable tlbDatos;
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField txtusuario;
	private JTextField txttelefono;
	private JTextField txtcorreo;
	private JTextField txtcontraseña;
	private JTextField txtId;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Principal() {
		Objetos_Interfaz();
		MostarDatos();
		txtId.setVisible(false);
	}
	
	public void MostarDatos() {
		DefaultTableModel modelo = (DefaultTableModel) tlbDatos.getModel();
		modelo.setRowCount(0);
		
		PreparedStatement pst;
		ResultSet rs; 
		ResultSetMetaData rsmd;
		int columnas;
		
		int [] ancho = {10, 50, 50, 50, 40, 100, 50};
		for(int iterador = 0; iterador < tlbDatos.getColumnCount(); iterador++) {
			tlbDatos.getColumnModel().getColumn(iterador).setPreferredWidth(ancho[iterador]);
		}
		
		try {
			Connection conexion = Sql_Conexion.conectar();
			pst = conexion.prepareStatement("SELECT ID, nombre, apellidos, usuario, telefono, correo, contraseña FROM usuarios");
			rs = pst.executeQuery();
			
			rsmd = rs.getMetaData();
			columnas = rsmd.getColumnCount();
			
			while (rs.next()) {
				Object[] fila = new Object[columnas];
				
				for(int i=0; i<columnas; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				modelo.addRow(fila);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public void limpiar() {
		txtId.setText("");
		txtnombre.setText("");
		txtapellido.setText("");
		txtusuario.setText("");
		txttelefono.setText("");
		txtcorreo.setText("");
		txtcontraseña.setText("");
	}
	//Invocando el login
	public void Login() {
		FrmLogin login = new FrmLogin();
		login.setVisible(true);
		
		this.dispose();
	}
	
	public void Registro() {
		Frm_Registro registro = new Frm_Registro();
		registro.setVisible(true);
	}
	
	public void Objetos_Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Lista de usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCerrar = new JButton("CERRAR SESION");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 462, Short.MAX_VALUE)
							.addComponent(btnCerrar)
							.addContainerGap())
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCerrar)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JButton btnEditar_1 = new JButton("Actualizar Usuario");
		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtnombre.getText();
				String apellido = txtapellido.getText();
				String usuario = txtusuario.getText();
				String telefono = txttelefono.getText();
				String correo = txtcorreo.getText();
				String contraseña = txtcontraseña.getText();
				
				
				
				try {
					Connection conexion = Sql_Conexion.conectar();
					PreparedStatement pst = conexion.prepareStatement("UPDATE usuarios SET nombre=?, apellidos=?, usuario=?, telefono=?, correo=?, contraseña=? WHERE ID=?");
					pst.setString(1, nombre);
					pst.setString(2, apellido);
					pst.setString(3, usuario);
					pst.setString(4, telefono);
					pst.setString(5, correo);
					pst.setString(6, contraseña);
					pst.setInt(7, id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Registro modificado");
					limpiar();
					MostarDatos();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.toString());
				}
			}
		});
		btnEditar_1.setBounds(360, 23, 141, 69);
		btnEditar_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnEditar_1);
		
		JButton btnBorrar_1 = new JButton("Eliminar Usuario");
		btnBorrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				
				try {
					Connection conexion = Sql_Conexion.conectar();
					PreparedStatement pst = conexion.prepareStatement("DELETE FROM usuarios WHERE ID=?");
					pst.setInt(1, id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Registro Eliminado");
					limpiar();
					MostarDatos();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.toString());
				}
			}
		});
		btnBorrar_1.setBounds(360, 130, 141, 60);
		btnBorrar_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnBorrar_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(24, 23, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellidos");
		lblNewLabel_1_1.setBounds(24, 59, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Usuario");
		lblNewLabel_1_2.setBounds(24, 94, 46, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Telefono");
		lblNewLabel_1_3.setBounds(24, 130, 46, 14);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Correo");
		lblNewLabel_1_4.setBounds(24, 163, 46, 14);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1_5.setBounds(24, 199, 46, 14);
		panel.add(lblNewLabel_1_5);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(80, 20, 234, 20);
		panel.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtapellido = new JTextField();
		txtapellido.setColumns(10);
		txtapellido.setBounds(80, 56, 234, 20);
		panel.add(txtapellido);
		
		txtusuario = new JTextField();
		txtusuario.setColumns(10);
		txtusuario.setBounds(80, 91, 234, 20);
		panel.add(txtusuario);
		
		txttelefono = new JTextField();
		txttelefono.setColumns(10);
		txttelefono.setBounds(80, 127, 234, 20);
		panel.add(txttelefono);
		
		txtcorreo = new JTextField();
		txtcorreo.setColumns(10);
		txtcorreo.setBounds(80, 160, 234, 20);
		panel.add(txtcorreo);
		
		txtcontraseña = new JTextField();
		txtcontraseña.setColumns(10);
		txtcontraseña.setBounds(80, 196, 234, 20);
		panel.add(txtcontraseña);
		
		txtId = new JTextField();
		txtId.setBounds(324, 20, 4, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		tlbDatos = new JTable();
		tlbDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int fila = tlbDatos.getSelectedRow();
					int id = Integer.parseInt(tlbDatos.getValueAt(fila, 0).toString());
					
					Connection conexion = Sql_Conexion.conectar();
					PreparedStatement pst;
					pst = conexion.prepareStatement("SELECT nombre, apellidos, usuario, telefono, correo, contraseña FROM usuarios WHERE ID = ?");
					pst.setInt(1, id);
					ResultSet rs;
					rs = pst.executeQuery();
					
					while (rs.next()) {
						txtId.setText(String.valueOf(id));
						txtnombre.setText(rs.getString("nombre"));
						txtapellido.setText(rs.getString("apellidos"));
						txtusuario.setText(rs.getString("usuario"));
						txttelefono.setText(rs.getString("telefono"));
						txtcorreo.setText(rs.getString("correo"));
						txtcontraseña.setText(rs.getString("contraseña"));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		});
		tlbDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellidos", "Usuario", "Telefono", "Correo", "Contrase\u00F1a"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tlbDatos);
		contentPane.setLayout(gl_contentPane);
	}
	}


