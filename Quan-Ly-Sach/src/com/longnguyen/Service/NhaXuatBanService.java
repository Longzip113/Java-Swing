package com.longnguyen.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.longnguyen.model.NhaXuatBan;
import com.longnguyen.util.PagingUtils;
import com.mysql.jdbc.PreparedStatement;

public class NhaXuatBanService extends MySqlService {

	public ArrayList<NhaXuatBan> findAll(PagingUtils pagingUtils) {

		ArrayList<NhaXuatBan> dsNXB = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from NHAXUATBAN limit ?,?";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setInt(1, pagingUtils.getOfset());
			preparedStatement.setInt(2, pagingUtils.getLimit());
			resultSet = preparedStatement.executeQuery();
			dsNXB = new ArrayList<NhaXuatBan>();
			while (resultSet.next()) {
				NhaXuatBan NXB = new NhaXuatBan();
				NXB.setMaNhaSanXuat(resultSet.getString(1));
				NXB.setTenNhaSanXuat(resultSet.getString(2));
				NXB.setDiaChi(resultSet.getString(3));
				NXB.setSoDienThoi(resultSet.getString(4));
				dsNXB.add(NXB);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return dsNXB;
	}

	public int countItem() {
		
		int count = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "SELECT count(*) FROM NHAXUATBAN";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1); // return id tự tăng
			}
			return count;
		} catch (Exception e) {
			return 0;
		} 
	}

	public boolean insert(NhaXuatBan NXB) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO NHAXUATBAN  ");
			sql.append(" (MANXB, TENNXB, DIACHI, SDT) ");
			sql.append(" VALUES(?, ?, ?, ?)");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, NXB.getMaNhaSanXuat());
			preparedStatement.setString(2, NXB.getTenNhaSanXuat());
			preparedStatement.setString(3, NXB.getDiaChi());
			preparedStatement.setString(4, NXB.getSoDienThoi());
			preparedStatement.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
	
	public NhaXuatBan findByMaNXB(String maNXB) {

		NhaXuatBan NXB = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from NHAXUATBAN where MANXB = ?";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, maNXB);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NXB = new NhaXuatBan();
				NXB.setMaNhaSanXuat(resultSet.getString(1));
				NXB.setTenNhaSanXuat(resultSet.getString(2));
				NXB.setDiaChi(resultSet.getString(3));
				NXB.setSoDienThoi(resultSet.getString(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return NXB;
	}
	
	public void delete(String maNXB) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM NHAXUATBAN WHERE MANXB = ?");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, maNXB);
			preparedStatement.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean update(NhaXuatBan NXB) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE NHAXUATBAN SET ");
			sql.append(" TENNXB = ?, DIACHI = ?, SDT = ?");
			sql.append(" WHERE MANXB = ?");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, NXB.getTenNhaSanXuat());
			preparedStatement.setString(2, NXB.getDiaChi());
			preparedStatement.setString(3, NXB.getSoDienThoi());
			preparedStatement.setString(4, NXB.getMaNhaSanXuat());
			preparedStatement.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}

}
