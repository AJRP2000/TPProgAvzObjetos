package clasesVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import clasesModelo.LogInModel;
import entidades.Usuario;
import factory.UsuarioFactory;
import mediator.LogInMediator;

public class JFrameLogIn extends JFrame {

	//el unico usuario valido es "username" y su contrasena es "password"
	
	private JPanel titulo, logIn, este, oeste, sur;
	private JTextArea dni, contrasena;
	private LogInMediator logInMediator;
	private JCheckBox checkbox;
	
	public JFrameLogIn() {
		initUI();
	}
	
	private void initUI() {
		logInMediator = new LogInModel(this);
		setLayout(new BorderLayout());
		setTitle("Aplicacion Centro Terapeuta");
		
		panelTitulo();
		
		panelOeste();
		
		panelLogIn();
		
		panelEste();
		
		panelSur();		
	}
	
	
	private void panelTitulo() {
		titulo = new JPanel();
		titulo.setLayout(new BoxLayout(titulo , BoxLayout.X_AXIS));
		
		titulo.add(Box.createRigidArea(new Dimension(370,50)));
		
		JLabel texto = new JLabel ("Log In");
		titulo.add(texto);
		
		add(titulo, BorderLayout.NORTH);
		
	}
	
	private void panelOeste() {
		oeste = new JPanel();
		oeste.add(Box.createRigidArea(new Dimension(300,15)));
		
		add(oeste, BorderLayout.WEST);
	}
	
	private void panelEste() {
		este = new JPanel();
		este.add(Box.createRigidArea(new Dimension(300,15)));
		
		add(este, BorderLayout.EAST);
	}
	
	private Usuario getUsuario() {
		Usuario usuario = null;
		if(checkbox.isSelected()) {
			String textoDni = dni.getText();
			String textoContrasena = contrasena.getText();
			usuario = UsuarioFactory.crearAdmin(Integer.valueOf(textoDni), "", textoContrasena);
		} else {
			String textoDni = dni.getText();
			String textoContrasena = contrasena.getText();
			usuario = UsuarioFactory.crearPaciente(Integer.valueOf(textoDni), "", textoContrasena, "");
		}
		return usuario;
	}
	
	private void panelLogIn() {
		logIn = new JPanel();
		logIn.setLayout(new BoxLayout(logIn , BoxLayout.Y_AXIS));
		JLabel texto = new JLabel("DNI");
		dni = new JTextArea(3,50);
		logIn.add(texto);
		logIn.add(dni);
		JLabel texto2 = new JLabel("Contrasena");
		contrasena = new JTextArea(3,50);
		logIn.add(texto2);
		logIn.add(contrasena);
		logIn.add(Box.createRigidArea(new Dimension(0,20)));
		
		checkbox = new JCheckBox("Es Administrador");
		logIn.add(checkbox);
		
		JButton continuar = new JButton("Ingresar");
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = getUsuario();
				if(checkbox.isSelected()) 
					logInMediator.logInAdmin(usuario);
				else
					logInMediator.logInPaciente(usuario);
			}
		});
		
		logIn.add(continuar);
		
		add(logIn, BorderLayout.CENTER);
	}
	
	
	private void panelSur() {
		sur = new JPanel();
		sur.add(Box.createRigidArea(new Dimension(0,420)));
		add(sur, BorderLayout.SOUTH);
	}
}
