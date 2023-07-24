package model.DAOS;
import model.beans.*;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ProductModelDM implements ProductModel {

	private static final String TABLE_NAME = "product";
	public synchronized int getIdCodice() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT MAX(ID) AS MAXID FROM " + ProductModelDM.TABLE_NAME;
		int idMax = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
	
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				idMax = rs.getInt("MAXID");
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return idMax+1;
		
	}

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDM.TABLE_NAME
				+ " (indirizzo, nome, telefono, struttura, dataCampo,tipo,mail,prezzo, urlImmagine, id_admin,citta,id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getIndirizzo());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getTelefono());
			preparedStatement.setString(4, product.getStruttura());
			preparedStatement.setDate(5,   new java.sql.Date(product.getDataCampo().getTime()));
			preparedStatement.setString(6, product.getTipo());
			preparedStatement.setString(7, product.getEmail());
			preparedStatement.setDouble(8, product.getPrezzo());
			preparedStatement.setString(9, product.getUrlImmagine());
			preparedStatement.setInt(10, product.getId_admin());
			preparedStatement.setString(11, product.getCitta());
			preparedStatement.setInt(12, product.getId());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized void doUpdate(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + ProductModelDM.TABLE_NAME
				+ " SET indirizzo = ?, nome = ?, telefono = ?, struttura = ?, dataCampo = ?, tipo = ?, email = ?, prezzo = ?, url_immagine = ?"
				+ " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, product.getIndirizzo());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getTelefono());
			preparedStatement.setString(4, product.getStruttura());
			preparedStatement.setDate(5, new java.sql.Date(product.getDataCampo().getTime()));
			preparedStatement.setString(6, product.getTipo());
			preparedStatement.setString(7, product.getEmail());
			preparedStatement.setDouble(8, product.getPrezzo());
			preparedStatement.setString(9, product.getUrlImmagine());
			preparedStatement.setInt(10, product.getId());
			
			preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	@Override
	public synchronized ProductBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				bean.setDataCampo(new java.sql.Date(rs.getDate("dataCampo").getTime()));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail(rs.getString("mail"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setUrlImmagine(rs.getString("urlImmagine"));
				bean.setCitta(rs.getString("citta"));
				bean.setId_admin(rs.getInt("id_admin"));
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDM.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				bean.setDataCampo(new java.sql.Date(rs.getDate("dataCampo").getTime()));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail(rs.getString("email"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setUrlImmagine(rs.getString("url_immagine"));
				bean.setId_admin(rs.getInt("id_admin"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	@Override
	public synchronized ArrayList<ProductBean> doRetrieveCategoria(String tipo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME
						 + " WHERE tipo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, tipo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				bean.setDataCampo(new java.sql.Date(rs.getDate("dataCampo").getTime()));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail(rs.getString("email"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setUrlImmagine(rs.getString("url_immagine"));
				bean.setId_admin(rs.getInt("id_admin"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public synchronized  ArrayList<ProductBean> doRetrieveProductBuy (int id_utente){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();
		ArrayList<Integer> idProducts = new ArrayList<Integer>();
		
		
		String selectSQL = "SELECT DISTINCT PRODOTTO FROM ORDINE,PRODOTTO WHERE ORDINE.UTENTE = ? "
						 + " AND ORDINE.CAMPO = PRODOTTO.CAMPO";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_utente);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				idProducts.add(rs.getInt("id_campo"));
			}
			
			for(int s : idProducts) {
				products.add(this.doRetrieveByKey(s));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	public synchronized ArrayList<ProductBean> doRetrieveByAdmin(int id) throws SQLException{
		ArrayList<ProductBean> result = new ArrayList<ProductBean>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE id_admin = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				bean.setDataCampo(new java.sql.Date(rs.getDate("dataCampo").getTime()));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail(rs.getString("email"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setUrlImmagine(rs.getString("url_immagine"));
				bean.setId_admin(rs.getInt("id_admin"));
				result.add(bean);
				
			}

		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return result;
	}

	@Override
	public synchronized  ArrayList<ProductBean> doRetriveByData(String citta, Date data, String sport, LocalTime time) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + ","+"ora"
						 + " WHERE product.citta = ? AND product.dataCampo = ? AND product.tipo = ? AND ora.id_campo = product.id AND ora.disponibiita = 1 AND ora.ora >= ?";
	//

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, citta);
			preparedStatement.setDate(2, new java.sql.Date(data.getTime()));
			preparedStatement.setString(3, sport);
			preparedStatement.setTime(4, Time.valueOf(time));

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setStruttura(rs.getString("struttura"));
				bean.setDataCampo(new java.sql.Date(rs.getDate("dataCampo").getTime()));
				bean.setTipo(rs.getString("tipo"));
				bean.setEmail(rs.getString("mail"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setUrlImmagine(rs.getString("urlImmagine"));
				bean.setId_admin(rs.getInt("id_admin"));
				bean.setCitta(rs.getString("citta"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}


}