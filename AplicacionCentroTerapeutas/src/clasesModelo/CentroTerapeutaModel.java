package clasesModelo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import entidades.*;
import clasesVista.*;
import factory.TablaFactory;
import mediator.TurnoMediator;
import requestBuilder.RequestBuilder;
public class CentroTerapeutaModel implements TurnoMediator {
	
	private JFrameCentroTerapeuta frame;
	public CentroTerapeutaModel(JFrameCentroTerapeuta frame ) {
		this.frame=frame;
	}

	@Override
	public void panelAltaTurno() {
		Usuario usuario = frame.getUsuario();
		if(usuario.isAdmin()) {
			frame.cambiarPanel(new JPanelAltaTurnoAdmin(this,usuario));
		}
		else {
			frame.cambiarPanel(new JPanelAltaTurnoPaciente(this,usuario));
		}
	}
	
	@Override
	public void panelBajaTurno() {
		Usuario usuario = frame.getUsuario();
		if(usuario.isAdmin()) {
			frame.cambiarPanel(new JPanelBajaTurnoAdmin(this,usuario));
		}
		else {
			frame.cambiarPanel(new JPanelBajaTurnoPaciente(this,usuario));
		}
	}
	
	@Override
	public void panelEditarTurno() {
		Usuario usuario = frame.getUsuario();
		if(usuario.isAdmin()) {
			frame.cambiarPanel(new JPanelModificarTurnoAdmin(this,usuario));
		}
		else {
			frame.cambiarPanel(new JPanelModificarTurnoPaciente(this,usuario));
		}
	}
	
	@Override
	public void panelConsultarTurno() {
		Usuario usuario = frame.getUsuario();
		if(usuario.isAdmin()) {
			frame.cambiarPanel(new JPanelConsultarTurnoAdmin(this,usuario));
		}
		else {
			frame.cambiarPanel(new JPanelConsultarTurnoPaciente(this,usuario));
		}
	}
	
	@Override
	public void altaTurno(Turno turno) throws Exception {
		try {
			String mensaje = RequestBuilder.altaTurno(turno);
			JOptionPane.showMessageDialog(frame,mensaje);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void bajaTurno(Turno turno) throws Exception {
		try {
			String mensaje = RequestBuilder.bajaTurno(turno);
			JOptionPane.showMessageDialog(frame,mensaje);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void editarTurno(Turno turno) throws Exception {
		try {
			String mensaje = RequestBuilder.editarTurno(turno);
			JOptionPane.showMessageDialog(frame,mensaje);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void consultarTurno(Turno turno) throws Exception {
		try {
			List<Turno> turnoRecibido = RequestBuilder.consultarTurno(turno);
			
			JScrollPane scrollPane = TablaFactory.crearTablaTurnos(turnoRecibido);
			frame.cambiarPanelTabla(scrollPane);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void consultarTurnosPaciente(Paciente paciente)  throws Exception {
		try {
			List<Turno> listaTurnos = RequestBuilder.consultarTurnosPaciente(paciente);
			
			JScrollPane scrollPane = TablaFactory.crearTablaTurnos(listaTurnos);
			Usuario usuario = frame.getUsuario();
			if(usuario.isAdmin())
				frame.cambiarPanelAdminTabla(scrollPane);
			else
				frame.cambiarPanelTabla(scrollPane);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void mostrarDisponibilidad(String diaSeleccionado) {
		try {
			List<String> listaHorarios = RequestBuilder.mostrarDisponibilidad(diaSeleccionado);
			
			JScrollPane scrollPane = TablaFactory.crearTablaListaHorarios(listaHorarios);
			frame.cambiarPanelDisponibilidad(scrollPane);
		}catch (Exception e) {
			e.printStackTrace();
			popUpFailure("Ha ocurrido un error: " +e.getMessage());
		}
	}
	
	@Override
	public void popUpFailure(String texto) {
		JOptionPane.showMessageDialog(frame,texto);
	}
	
	@Override
	public void borrarPantalla() {
		frame.borrarPantalla();
	}
	
	@Override
	public void mensajeError(Exception error) {
		error.printStackTrace();
		popUpFailure("Ha ocurrido un error:" + error.getMessage());
	}
	
	@Override
	public void descargarTabla(JScrollPane tabla) {
		JTable table = (JTable) tabla.getViewport().getView();
        TableModel model = table.getModel();
        
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\archivo.csv")) {
            // Write column headers
            int columnCount = model.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                writer.append(model.getColumnName(i));
                if (i < columnCount - 1) {
                    writer.append(',');
                }
            }
            writer.append('\n');
            
            // Write table data
            int rowCount = model.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    writer.append(model.getValueAt(row, col).toString());
                    if (col < columnCount - 1) {
                        writer.append(',');
                    }
                }
                writer.append('\n');
            }
            
            JOptionPane.showMessageDialog(frame,"Archivo ha sido descargado con exito");
        } catch (Exception e) {
        	popUpFailure("Ha ocurrido un error al descargar el archivo: " + e.getMessage());
        }
	}
}
