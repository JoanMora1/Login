package Formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.Metodos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField txtContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Metodos metodos = new Metodos();
		Principal principal = new Principal();
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(180, 26, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(58, 67, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASE\u00D1A");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(58, 109, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(153, 64, 195, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(153, 106, 195, 20);
		contentPane.add(txtContraseña);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				String busqueda = metodos.Usuario_registrado(txtCorreo.getText(), txtContraseña.getText());
				
				if (txtCorreo.getText().equals("root") && txtContraseña.getText().equals("root")) {
					JOptionPane.showMessageDialog(null, "Bienvenido a iniciado sesion como admin", busqueda, i);
					principal.Objetos_Interfaz();
					principal.setVisible(true);
					FrmLogin.this.dispose();
				}if (busqueda.equals("Usuario encontrado")) {
					String nombre = metodos.buscarNombre(txtCorreo.getText());
					JOptionPane.showMessageDialog(null, "Bienvenido \n" + nombre, "Mensaje", i);
					principal.setVisible(true);
					principal.MostarDatos();
					FrmLogin.this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado, por favor registrarse.", "Mensaje", i);
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBounds(81, 170, 110, 35);
		contentPane.add(btnEntrar);
		
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menú_Login menu = new Menú_Login();
				menu.setVisible(true);
				FrmLogin.this.dispose();
			}
		});
		btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegresar.setBounds(229, 170, 119, 35);
		contentPane.add(btnRegresar);
	}
}
