package clasesVista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clasesModelo.CentroTerapeutaModel;
import entidades.Turno;
import entidades.Usuario;
import exceptions.ExcepcionTerapeuta;

public class JPanelConsultarTurnoAdmin extends JPanelABMCTurno {

	public JPanelConsultarTurnoAdmin(CentroTerapeutaModel terapeutaModel, Usuario usuario) {
		super(terapeutaModel, usuario);
	}

	@Override
	protected int getDniPaciente() throws ExcepcionTerapeuta {
		int dniPaciente = 0;
		try {
			dniPaciente = Integer.valueOf(this.dniPaciente.getText());
		} catch(Exception e) {
			throw new ExcepcionTerapeuta("Debe ingresar un DNI para el paciente");
		}
		return dniPaciente;
	}

	@Override
	protected int getIdTurno() throws ExcepcionTerapeuta {
		int turno = -1;
		try {
			turno = Integer.valueOf(idTurno.getText());
		} catch(Exception e) {}
		return turno;
	}

	@Override
	protected void initUI() {
		setLayout(new BoxLayout( this , BoxLayout.Y_AXIS));		
		String titulo = "Consultar Turno";
		
		add(new JLabel(titulo));
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelIdPacienteEditable();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelIdTurno();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelBotones();

	}

	@Override
	protected void panelBotones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));
		
		JButton botonContinuar = new JButton("Consultar Turno");
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Turno turno = getTurno();
					if(turno.getIdTurno()==-1)
						terapeutaModel.consultarTurnosPaciente(turno.getPaciente());
					else
						terapeutaModel.consultarTurno(turno);
				}catch(Exception error) {
					terapeutaModel.mensajeError(error);
				}
			}
		});
				
		panel.add(botonContinuar);
		
		JButton botonCancelar= new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				terapeutaModel.borrarPantalla();
			}
		});
		panel.add(botonCancelar);
		add(panel);

	}

}
