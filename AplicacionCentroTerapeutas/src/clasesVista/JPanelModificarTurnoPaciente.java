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

public class JPanelModificarTurnoPaciente extends JPanelABMCTurno {

	public JPanelModificarTurnoPaciente(CentroTerapeutaModel terapeutaModel, Usuario usuario) {
		super(terapeutaModel, usuario);
	}

	@Override
	protected int getDniPaciente() throws ExcepcionTerapeuta {
		return this.usuario.getDni();
	}

	@Override
	protected int getIdTurno() throws ExcepcionTerapeuta {
		return Integer.valueOf(idTurno.getText());
	}

	@Override
	protected void initUI() {
		setLayout(new BoxLayout( this , BoxLayout.Y_AXIS));		
		String titulo = "Editar Turno";
		
		add(new JLabel(titulo));
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelIdPacienteReadOnly();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelIdTurno();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelFecha();
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelBotones();

	}

	@Override
	protected void panelBotones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));
		
		JButton botonContinuar = new JButton("Editar Turno");
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Turno turno = getTurno();
					terapeutaModel.editarTurno(turno);
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
