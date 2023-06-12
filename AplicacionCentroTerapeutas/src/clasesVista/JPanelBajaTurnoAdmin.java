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

public class JPanelBajaTurnoAdmin extends JPanelABMCTurno {

	public JPanelBajaTurnoAdmin(CentroTerapeutaModel terapeutaModel, Usuario usuario) {
		super(terapeutaModel, usuario);
	}

	@Override
	protected int getDniPaciente() throws ExcepcionTerapeuta {
		return Integer.valueOf(dniPaciente.getText());
	}

	@Override
	protected int getIdTurno() throws ExcepcionTerapeuta {
		return Integer.valueOf(idTurno.getText());
	}

	@Override
	protected void initUI() {
		setLayout(new BoxLayout( this , BoxLayout.Y_AXIS));		
		String titulo = "Baja Turno Admin";
		
		add(new JLabel(titulo));
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelIdPacienteEditable();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		this.panelIdTurno();
		add(Box.createRigidArea(new Dimension(0,10)));

		panelBotones();
	}

	@Override
	protected void panelBotones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));
		
		JButton botonContinuar = new JButton("Baja Turno");
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Turno turno = getTurno();
					terapeutaModel.bajaTurno(turno);
					terapeutaModel.borrarPantalla();
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
