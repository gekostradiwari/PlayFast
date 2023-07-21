package model.DAOS;

import java.util.Date;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
	public ArrayList<ProductBean> doRetriveByData(String citta,Date data,String sport, LocalTime time) throws SQLException;
}