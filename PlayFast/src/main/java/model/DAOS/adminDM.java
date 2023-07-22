package model.DAOS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.AdminBean;
import model.beans.UtenteBean;

public class adminDM implements admin{
	private static final String TABLE_NAME = "admin";
	
	public synchronized int getIdCodice() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT MAX(ID) AS MAXID FROM " + adminDM.TABLE_NAME;
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
	public synchronized void doSave(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + adminDM.TABLE_NAME
						 + " (cognome, nome, indirizzo, password,email,id) VALUES (?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, admin.getCognome());
			preparedStatement.setString(2, admin.getNome());
			preparedStatement.setString(3, admin.getIndirizzo());
			preparedStatement.setString(4, admin.getPassword());
			preparedStatement.setString(5, admin.getEmail());
			preparedStatement.setInt(6, getIdCodice());
			
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
	public synchronized void doUpdate(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + adminDM.TABLE_NAME
						 + " SET password= ?, email= ?,nome = ?,cognome= ?,indirizzo= ?, id= ?"
						 + " WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, admin.getPassword());
			preparedStatement.setString(2, admin.getEmail());
			preparedStatement.setString(3, admin.getNome());
			preparedStatement.setString(4, admin.getCognome());
			preparedStatement.setString(5, admin.getIndirizzo());
			preparedStatement.setInt(6, getIdCodice());
			
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

		String deleteSQL = "DELETE FROM " + adminDM.TABLE_NAME + " WHERE email = ?";

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
	public synchronized AdminBean doRetrieve(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AdminBean bean = null;

		String selectSQL = "SELECT * FROM " + adminDM.TABLE_NAME + " WHERE mail = ? and password = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new AdminBean();
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("mail"));
				bean.setPassword(rs.getString("password"));
				bean.setCognome(rs.getString("cognome"));
				bean.setNome(rs.getString("nome"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	public synchronized ArrayList<AdminBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AdminBean> admin = new ArrayList<AdminBean>();

		String selectSQL = "SELECT * FROM " + adminDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AdminBean bean = new AdminBean();
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setId(rs.getInt("id"));
				admin.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return admin;
	}

	@Override
	public synchronized AdminBean doRetrieveByMail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AdminBean bean = null;

		String selectSQL = "SELECT * FROM " + adminDM.TABLE_NAME + " WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean = new AdminBean();
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCognome(rs.getString("cognome"));
				bean.setNome(rs.getString("nome"));
				bean.setIndirizzo(rs.getString("indirizzo"));
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
	

}
