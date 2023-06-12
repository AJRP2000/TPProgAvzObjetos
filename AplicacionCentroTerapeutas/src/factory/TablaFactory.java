package factory;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import clasesVista.JTableModelListaHorarios;
import clasesVista.JTableModelTurno;
import entidades.Turno;

public class TablaFactory {
	
	public static JScrollPane crearTablaTurnos(List<Turno> turnos) {
		JTableModelTurno modeloTabla = new JTableModelTurno(turnos);
		JTable tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		return scrollPane;
	}
	
	public static JScrollPane crearTablaListaHorarios(List<String> listaHorario) {
		JTableModelListaHorarios modeloTabla = new JTableModelListaHorarios(listaHorario);
		JTable tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		return scrollPane;
	}
}
