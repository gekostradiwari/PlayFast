package model.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DriverManagerConnectionPool;
import model.beans.AdminBean;
import model.beans.OreDisponibili;
import model.beans.ProductBean;

public class oreDispoDM implements oreDispo{
	private static final String TABLE_NAME = "ore";

	@Override
	public void DoSave(OreDisponibili ore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + oreDispoDM.TABLE_NAME
						 + " (id_campo, ore, disp) VALUES (?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, ore.getId_campo());
			preparedStatement.setDouble(2, ore.getOrario());
			preparedStatement.setBoolean(3, ore.isDisponibilita());
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
	public void DoUpdate(OreDisponibili ore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + oreDispoDM.TABLE_NAME
						 + " SET ore= ?,disp = ?"
						 + " WHERE id_campo= ? AND ore= ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setDouble(1, ore.getOrario());
			preparedStatement.setBoolean(2, ore.isDisponibilita());
			preparedStatement.setInt(3, ore.getId_campo());
			preparedStatement.setDouble(4, ore.getOrario());
			
			
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
	public boolean DoDelete(OreDisponibili ore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + oreDispoDM.TABLE_NAME + " WHERE id_campo= ? AND ore= ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, ore.getId_campo());
			preparedStatement.setDouble(2, ore.getOrario());

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
	public ArrayList<OreDisponibili> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OreDisponibili> oreTutte = new ArrayList<OreDisponibili>();

		String selectSQL = "SELECT * FROM " + oreDispoDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OreDisponibili bean = new OreDisponibili(rs.getDouble("ore"));
				bean.setDisponibilita(rs.getBoolean("disp"));
				bean.setId_campo(rs.getInt("id_campo"));
				oreTutte.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return oreTutte;
	}

	@Override
	public ArrayList<OreDisponibili> doRetrieveAllByCampo(ProductBean campo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<OreDisponibili> oreTutteCampo = new ArrayList<OreDisponibili>();

		String selectSQL = "SELECT * FROM " + oreDispoDM.TABLE_NAME + "WHERE ID_CAMPO = ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, campo.getId());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OreDisponibili bean = new OreDisponibili(rs.getDouble("ore"));
				bean.setDisponibilita(rs.getBoolean("disp"));
				bean.setId_campo(rs.getInt("id_campo"));
				oreTutteCampo.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return oreTutteCampo;
	}

}
