package clasesVista;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entidades.Turno;

public class JTableModelTurno extends AbstractTableModel {
	
	private List<Turno> turnos;
	private static final int idTurno = 0;
	private static final int dniPaciente = 1;
	private static final int idTerapeuta =2;
	private static final int fecha = 3;
	private static final int estado = 4;
	private String[] headers = {"Id Turno","DNI Paciente","Id Terapeuta","Fecha","Estado del Turno"};
	

	public JTableModelTurno(List<Turno> turnos) {
		this.turnos=turnos;
		
	}

	@Override
	public int getRowCount() {
		return turnos.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Turno turno = turnos.get(rowIndex);
		switch (columnIndex) {
			case idTurno: return turno.getIdTurno();
			case dniPaciente: return turno.getPaciente().getDni();
			case idTerapeuta: return turno.getTerapeuta().getIdTerapeuta();
			case fecha: return turno.getFecha();
			case estado: return turno.getEstado().toString();
		}
		return null;
	}

}
