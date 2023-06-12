package dao;

import entidades.Admin;
import exceptions.ExcepcionTerapeuta;

public interface AdminDAO {
	
	Admin logInAdmin(Admin admin) throws ExcepcionTerapeuta;
	
	void addAdmin(Admin admin) throws ExcepcionTerapeuta;
	
	void deleteAdmin(Admin admin) throws ExcepcionTerapeuta;

}
