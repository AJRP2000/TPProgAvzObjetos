package clasesModelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entidades.Admin;
import entidades.Paciente;
import entidades.Usuario;
import clasesVista.JFrameCentroTerapeuta;
import clasesVista.JFrameLogIn;
import exceptions.ExcepcionTerapeuta;
import requestBuilder.RequestBuilder;

public class LogInModel {
	
	private JFrameLogIn frame;
	
	public LogInModel(JFrameLogIn frame) {
		this.frame=frame;
	}
	
	public void logInPaciente(Usuario usuario) {
		try {
			Usuario usuarioPanel = null;
			
			usuarioPanel = RequestBuilder.requestLogInPaciente((Paciente)usuario);
			
		    
			if (usuarioPanel!=null) {
				frame.dispose();
				JFrame frameCentroTerapeuta = new JFrameCentroTerapeuta(usuarioPanel);
				frameCentroTerapeuta.setSize(900,750);
				frameCentroTerapeuta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frameCentroTerapeuta.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(frame, "El Usuario y/o contrasena ingresado son invalidos");
		} catch (ExcepcionTerapeuta e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error: " +e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado");
		}
	}
	
	public void logInAdmin(Usuario usuario) {
		try {
			Usuario usuarioPanel = null;
			
			usuarioPanel = RequestBuilder.requestLogInAdmin((Admin)usuario);
			
		    
			if (usuarioPanel!=null) {
				frame.dispose();
				JFrame frameCentroTerapeuta = new JFrameCentroTerapeuta(usuarioPanel);
				frameCentroTerapeuta.setSize(800,650);
				frameCentroTerapeuta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frameCentroTerapeuta.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(frame, "El DNI y/o contrasena ingresado son invalidos");
		} catch (ExcepcionTerapeuta e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error: " +e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado");
		}
	}
}
