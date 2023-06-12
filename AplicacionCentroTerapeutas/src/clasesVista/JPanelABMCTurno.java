package clasesVista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import clasesModelo.CentroTerapeutaModel;
import entidades.Paciente;
import entidades.Turno;
import entidades.Usuario;
import exceptions.ExcepcionTerapeuta;

public abstract class JPanelABMCTurno extends JPanel {
	
	protected CentroTerapeutaModel terapeutaModel;
	
	protected Usuario usuario;
	protected JTextField idTurno, dniPaciente, fecha;
	public JPanelABMCTurno(CentroTerapeutaModel terapeutaModel, Usuario usuario) {
		this.terapeutaModel=terapeutaModel;
		this.usuario=usuario;
		initUI();
	}
	
	protected void panelIdPacienteReadOnly() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));	
		panel.add(new JLabel("DNI Paciente"));
		dniPaciente = new JTextField(String.valueOf(usuario.getDni()));
		dniPaciente.setEditable(false);
		panel.add(dniPaciente);
		add(panel);
	}
	
	protected void panelIdPacienteEditable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));	
		panel.add(new JLabel("DNI Paciente"));
		dniPaciente = new JTextField();
		dniPaciente.setEditable(true);
		panel.add(dniPaciente);
		add(panel);
	}
	
	protected void panelIdTurno() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));	
		panel.add(new JLabel("IdTurno"));
		idTurno = new JTextField();
		panel.add(idTurno);
		add(panel);
	}
	
	private JPanelABMCTurno returnPanel() {
		return this;
	}
	
	protected void panelFecha() {		
		JPanel panelFecha = new JPanel();
		panelFecha.setLayout(new BoxLayout(panelFecha , BoxLayout.X_AXIS));	
		panelFecha.add(new JLabel("Fecha"));
		fecha = new JTextField();
		panelFecha.add(fecha);
		
		JLabel etiqueta1 = new JLabel("Ingrese una fecha en formato yyyy-mm-dd y presione ");
		JLabel etiqueta2 = new JLabel("el boton para mostrar la disponibilidad de ese dia.");
		JLabel etiqueta3 = new JLabel(" Recuerde que la fecha final debe ser ingresada en ");
		JLabel etiqueta4 = new JLabel("          formato yyyy-mm-dd+hh:mm:ss.SSS          ");
		
		JButton botonDisponibilidad = new JButton("Mostrar Disponibilidad");
		botonDisponibilidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				terapeutaModel.mostrarDisponibilidad(fecha.getText(), returnPanel());
			}
		});
		
		
		JPanel panelDisponibilidad = new JPanel();
		panelDisponibilidad.setLayout(new BoxLayout(panelDisponibilidad, BoxLayout.Y_AXIS));
		panelDisponibilidad.add(panelFecha);
		panelDisponibilidad.add(etiqueta1);
		panelDisponibilidad.add(etiqueta2);
		panelDisponibilidad.add(etiqueta3);
		panelDisponibilidad.add(etiqueta4);
		panelDisponibilidad.add(botonDisponibilidad);
		
		add(panelDisponibilidad);
	}
	
	protected Turno getTurno() throws ExcepcionTerapeuta {
		Turno resultado = null;
		
		int dniPaciente = getDniPaciente();
		int idTurno = getIdTurno();
		String fecha = "";
		try {
			fecha = this.fecha.getText();
		} catch(Exception e) {}
		Paciente paciente = new Paciente();
		paciente.setDni(dniPaciente);
			
		resultado = new Turno();
		resultado.setPaciente(paciente);
		resultado.setIdTurno(idTurno);
		resultado.setFecha(fecha);
		
		return resultado;
	}
	
	protected abstract int getDniPaciente() throws ExcepcionTerapeuta;
	
	protected abstract int getIdTurno() throws ExcepcionTerapeuta;

	protected abstract void initUI();
	
	protected abstract void panelBotones();
	
}
