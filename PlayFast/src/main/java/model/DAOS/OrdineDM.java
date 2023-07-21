package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import database.DriverManagerConnectionPool;
import model.beans.ComposizioneBean;
import model.beans.OrdineBean;

public class OrdineDM implements Ordine{

	private static final String TABLE_NAME = "Prenotazione";
	
	public synchronized int getIdCodice() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT MAX(ID) AS MAXID FROM " + OrdineDM.TABLE_NAME;
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
	public synchronized void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + OrdineDM.TABLE_NAME
						 + " (stato, data, utente, mod_pagamento) VALUES (?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1,ordine.getStato());
			preparedStatement.setDate(2,new java.sql.Date(ordine.getDataPrenotazione().getTime()));
			preparedStatement.setInt(3, ordine.getUtente());
			preparedStatement.setString(4,ordine.getModPagamento());
			
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
	public synchronized void doUpdate(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + OrdineDM.TABLE_NAME
						 + " SET stato = ?, data= ?, utente= ?, mod_pagamento= ?"
						 + " WHERE ID = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1,ordine.getStato());
			preparedStatement.setDate(2,new java.sql.Date(ordine.getDataPrenotazione().getTime()));
			preparedStatement.setInt(3,ordine.getUtente());
			preparedStatement.setString(4,ordine.getModPagamento());		
			
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
	public synchronized boolean doDelete(int ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDM.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, ID);

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
	public synchronized OrdineBean doRetrieveByKey(int ID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDM.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, ID);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(ID);
				bean.setStato(rs.getString("stato"));
				bean.setDataPrenotazione(new java.util.Date(rs.getDate("dataPrenotazione").getTime()));
				bean.setUtente(rs.getInt("utente"));
				bean.setModPagamento(rs.getString("mod_pagamento"));
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
	public synchronized ArrayList<OrdineBean> doRetrieveByDate(Date da, Date a) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		java.sql.Date daSQL = new java.sql.Date(da.getTime());
		java.sql.Date aSQL = new java.sql.Date(a.getTime());
		String selectSQL = "SELECT * FROM Ordine WHERE data >= ? and data <= ?;";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, daSQL);
			preparedStatement.setDate(2, aSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setID(rs.getInt("ID"));
				bean.setStato(rs.getString("stato"));
				bean.setDataPrenotazione(new java.util.Date(rs.getDate("dataPrenotazione").getTime()));
				bean.setUtente(rs.getInt("utente"));
				bean.setModPagamento(rs.getString("mod_pagamento"));
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveAllByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDM.TABLE_NAME
						 + " WHERE utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setID(rs.getInt("ID"));
				bean.setStato(rs.getString("stato"));
				bean.setDataPrenotazione(new java.util.Date(rs.getDate("dataPrenotazione").getTime()));
				bean.setUtente(rs.getInt("utente"));
				bean.setModPagamento(rs.getString("mod_pagamento"));
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}

	@Override
	public synchronized ArrayList<OrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setID(rs.getInt("ID"));
				bean.setStato(rs.getString("stato"));
				bean.setDataPrenotazione(new java.util.Date(rs.getDate("dataPrenotazione").getTime()));
				bean.setUtente(rs.getInt("utente"));
				bean.setModPagamento(rs.getString("mod_pagamento"));
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
	
	public ArrayList<OrdineBean> doRetrieveByCampo(int id_campo) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineDM.TABLE_NAME
						 + " WHERE utente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_campo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setID(rs.getInt("ID"));
				bean.setStato(rs.getString("stato"));
				bean.setDataPrenotazione(new java.util.Date(rs.getDate("dataPrenotazione").getTime()));
				bean.setUtente(rs.getInt("utente"));
				bean.setModPagamento(rs.getString("mod_pagamento"));
				ordini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
		
	}
	

}