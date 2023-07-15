package model.DAOS;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.OreDisponibili;
import model.beans.ProductBean;

public interface oreDispo {
	public void DoSave(OreDisponibili ore) throws SQLException;
	public void DoUpdate(OreDisponibili ore) throws SQLException;
	public boolean DoDelete(OreDisponibili ore) throws SQLException;
	public ArrayList<OreDisponibili> doRetrieveAll(String order) throws SQLException;
	public ArrayList<OreDisponibili> doRetrieveAllByCampo(ProductBean campo) throws SQLException;
	

}
