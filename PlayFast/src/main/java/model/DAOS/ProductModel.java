package model.DAOS;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ProductBean;

public interface ProductModel {
	public void doSave(ProductBean product) throws SQLException;
	
	public void doUpdate(ProductBean product) throws SQLException;
	
	public boolean doDelete(int id) throws SQLException;

	public ProductBean doRetrieveByKey(int id) throws SQLException;
	
	public ArrayList<ProductBean> doRetrieveAll(String order) throws SQLException;

	public ArrayList<ProductBean> doRetrieveCategoria(String tipo) throws SQLException;
	
	public ArrayList<ProductBean> doRetrieveByAdmin(int id) throws SQLException;
}