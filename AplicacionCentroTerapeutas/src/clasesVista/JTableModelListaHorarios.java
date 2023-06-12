package clasesVista;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import entidades.Turno;

public class JTableModelListaHorarios extends AbstractTableModel {
	
	private List<String> horarios;
	private static final int horario = 0;
	private String[] headers = {"Horario Disponible"};
	

	public JTableModelListaHorarios(List<String> horarios) {
		this.horarios=horarios;
		
	}

	@Override
	public int getRowCount() {
		return horarios.size();
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
		String hora = horarios.get(rowIndex);
		switch (columnIndex) {
			case horario: return hora;
		}
		return null;
	}

}
