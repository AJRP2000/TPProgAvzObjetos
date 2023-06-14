package clasesVista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clasesModelo.CentroTerapeutaModel;
import entidades.Usuario;
import mediator.TurnoMediator;

public class JFrameCentroTerapeuta extends JFrame {
	
	private Usuario usuario;
	private TurnoMediator turnoMediator;
	private JMenuBar menuBar;
	private JMenu turnos;
	private JMenuItem altaTurno, bajaTurno, editarTurno, consultarTurno;
	
	private JPanel panelDisponibilidad;
	private JScrollPane tablaDisponibilidad;
	
	public JFrameCentroTerapeuta(Usuario usuario) {
		this.setUsuario(usuario);
		this.turnoMediator = new CentroTerapeutaModel(this);
		initUI();
	}
	
	private void initUI() {
		setLayout(new FlowLayout());
		setTitle("Aplicacion Centro Terapeutas");
		
		
		//Crear la barra de menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		//Crear Menu Turnos
		turnos = new JMenu("Turnos");
		
		
		altaTurno = new JMenuItem("Hacer Alta de Turno");
		altaTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoMediator.panelAltaTurno();
			}
		});
		turnos.add(altaTurno);
		
		bajaTurno = new JMenuItem("Dar de baja un Turno");
		bajaTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoMediator.panelBajaTurno();
			}
		});
		
		turnos.add(bajaTurno);
		
		editarTurno = new JMenuItem("Editar un Turno");
		editarTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoMediator.panelEditarTurno();
			}
		});
		turnos.add(editarTurno);
		
		consultarTurno = new JMenuItem("Consultar un Turno");
		consultarTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoMediator.panelConsultarTurno();
			}
		});
		turnos.add(consultarTurno);
		
		
		menuBar.add(turnos);
		
	}
	
	public void cambiarPanelDisponibilidad(JScrollPane listaHorarios) {
		if(panelDisponibilidad!=null) {
			this.remove(panelDisponibilidad);
			this.revalidate();
			this.repaint();
			this.printAll(getGraphics());
		}
		
		panelDisponibilidad = new JPanel();
		tablaDisponibilidad = listaHorarios;
		panelDisponibilidad.add(tablaDisponibilidad);
		add(panelDisponibilidad);
		revalidate();
		repaint();
        printAll(getGraphics());
	}
	
	//Crea una tabla en el frame usando la tabla parametro que recibe.
	public void cambiarPanelTabla(JScrollPane tabla) {
		getContentPane().removeAll();
		getContentPane().add(tabla);
		JButton botonCancelar = new JButton("Volver al Inicio");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarPantalla();
				
			}
		});
		getContentPane().add(botonCancelar);
		repaint();
        printAll(getGraphics());
	}
	
	public void cambiarPanelAdminTabla(JScrollPane tabla) {
		getContentPane().removeAll();
		getContentPane().add(tabla);
		JButton botonCancelar = new JButton("Volver al Inicio");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarPantalla();
				
			}
		});
		getContentPane().add(botonCancelar);
		JButton botonDescargar = new JButton("Descargar Tabla");
		botonDescargar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoMediator.descargarTabla(tabla);				
			}
		});
		getContentPane().add(botonDescargar);
		
		repaint();
        printAll(getGraphics());
	}
	
	public void borrarPantalla() {
		getContentPane().removeAll();
		repaint();
        printAll(getGraphics());
	}
	
	//Llena el frame con el panel que recibe de parametro.
	public void cambiarPanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		repaint();
        printAll(getGraphics());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario cliente) {
		this.usuario = cliente;
	}
	

}
