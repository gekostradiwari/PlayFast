package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.AdminBean;
import model.beans.UtenteBean;

public interface admin {
public void doSave(AdminBean admin) throws SQLException;
	
	public void doUpdate(AdminBean admin) throws SQLException;
	
	public boolean doDelete(String email) throws SQLException;

	public AdminBean doRetrieve(String email, String password) throws SQLException;
	public AdminBean doRetrieveByMail(String email) throws SQLException;
	
	public ArrayList<AdminBean> doRetrieveAll(String order) throws SQLException;

}
