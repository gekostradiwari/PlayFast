package model.DAOS;

import model.beans.*;
import database.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UtenteDM implements Utente{

	private static final String TABLE_NAME = "utente";
	public synchronized int getIdCodice() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT MAX(ID) AS MAXID FROM " + UtenteDM.TABLE_NAME;
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
	public synchronized void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + UtenteDM.TABLE_NAME
						 + " (email, password, nome, cognome, dataNascita) VALUES (?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getMail());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getNome());
			preparedStatement.setString(4, utente.getCognome());
			preparedStatement.setDate(5,new java.sql.Date(utente.getDataNascita().getTime()));
			
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
	public synchronized void doUpdate(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + UtenteDM.TABLE_NAME
						 + " SET password= ?,nome= ?,cognome= ?,dataNascita= ?, mail = ?"
						 + " WHERE id = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, utente.getPassword());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setDate(4, new java.sql.Date(utente.getDataNascita().getTime()));
			preparedStatement.setString(5, utente.getMail());
			preparedStatement.setInt(6, utente.getId());
			
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
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteDM.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

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
	public synchronized UtenteBean doRetrieve(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = null;

		String selectSQL = "SELECT * FROM " + UtenteDM.TABLE_NAME + " WHERE mail = ? and password = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new UtenteBean();
				bean.setMail(rs.getString("mail"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataNascita(new java.util.Date(rs.getDate("dataNascita").getTime()));
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

	public synchronized UtenteBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = null;

		String selectSQL = "SELECT * FROM " + UtenteDM.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new UtenteBean();
				bean.setMail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataNascita(new java.util.Date(rs.getDate("dataNascita").getTime()));
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
	public synchronized ArrayList<UtenteBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<UtenteBean> utenti = new ArrayList<UtenteBean>();

		String selectSQL = "SELECT * FROM " + UtenteDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setMail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataNascita(new java.util.Date(rs.getDate("dataNascita").getTime()));
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}
	public ArrayList<UtenteBean> doRetrieveByKey(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<UtenteBean> utenti = new ArrayList<UtenteBean>();

		String selectSQL = "SELECT * FROM " + UtenteDM.TABLE_NAME + " WHERE id = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setMail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDataNascita(new java.util.Date(rs.getDate("dataNascita").getTime()));
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}

}