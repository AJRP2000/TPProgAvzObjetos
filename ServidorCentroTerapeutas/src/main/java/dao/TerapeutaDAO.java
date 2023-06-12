package dao;

import entidades.Terapeuta;
import exceptions.ExcepcionTerapeuta;

public interface TerapeutaDAO {
	
	Terapeuta getTerapeutaTurno(String turno) throws ExcepcionTerapeuta;
	
	Terapeuta getTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta;
	
	void addTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta;
	
	void deleteTerapeuta(Terapeuta terapeuta) throws ExcepcionTerapeuta;

}
