package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConexionBD.Metodos;
import ConexionBD.Sql_Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frm_Registro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JPasswordField txtPass;
	private JTextField txtTelefono;
	private JTextField txtUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Registro frame = new Frm_Registro();
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
	public Frm_Registro() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Metodos metedos = new Metodos();
		Principal principal = new Principal();
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(37, 96, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellido.setBounds(37, 133, 65, 14);
		contentPane.add(lblApellido);
		
		JLabel lblNewLabel_2 = new JLabel("TELEFONO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(37, 216, 65, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblCorreo = new JLabel("CORREO");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(37, 266, 65, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setBounds(37, 305, 85, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(169, 39, 65, 22);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(147, 93, 195, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(148, 130, 194, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(148, 176, 194, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(148, 218, 195, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(147, 260, 195, 20);
		contentPane.add(txtCorreo);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(147, 299, 195, 20);
		contentPane.add(txtPass);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = metedos.guardar(txtNombre.getText(), txtApellido.getText(),txtUsuario.getText(), txtTelefono.getText(), txtCorreo.getText(), txtPass.getText());
				if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Error", i);
				}
				else if (i > 0) {
					JOptionPane.showMessageDialog(null, "Se guardaron los datos correctamente", "Mensaje", i);
					principal.MostarDatos();
					principal.limpiar();
				}else {
					JOptionPane.showMessageDialog(null, "No se pudieron guardar los datos", "Mensaje", i);
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGuardar.setBounds(75, 353, 98, 33);
		contentPane.add(btnGuardar);
		
		JButton btnBack = new JButton("REGRESAR");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menú_Login menu = new Menú_Login();
				menu.setVisible(true);
				Frm_Registro.this.dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(215, 356, 103, 33);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_3 = new JLabel("USUARIO");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(37, 178, 65, 14);
		contentPane.add(lblNewLabel_3);
		
		
		
		
		
		
	}
}


