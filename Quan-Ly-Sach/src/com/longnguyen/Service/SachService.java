package com.longnguyen.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.longnguyen.model.Sach;
import com.mysql.jdbc.PreparedStatement;

public class SachService extends MySqlService{
	public ArrayList<Sach> findByMaNXB(String maNXB){
		ArrayList<Sach> dsSachs = new ArrayList<Sach>();
		try {
			String sql = "select * from SACH where MANXB = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, maNXB);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sach sach = new Sach();
				sach.setMaSach(resultSet.getString(1));
				sach.setTenSach(resultSet.getString(2));
				sach.setMaNhaXuatBan(resultSet.getString(3));
				dsSachs.add(sach);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsSachs;
	}
	
	public Sach findByMaSach(String maSach){
		Sach Sach = new Sach();
		try {
			String sql = "select * from SACH where MASACH = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, maSach);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sach.setMaSach(resultSet.getString(1));
				Sach.setTenSach(resultSet.getString(2));
				Sach.setMaNhaXuatBan(resultSet.getString(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Sach;
	}
	
	public int countItem(String maNXB) {
		
		int count = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "SELECT count(*) FROM SACH WHERE MANXB = ?";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, maNXB);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1); // return id tự tăng
			}
			return count;
		} catch (Exception e) {
			return 0;
		} 
	}
	
	public boolean insert(Sach sach) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO SACH  ");
			sql.append(" (MASACH, TENSACH, MANXB) ");
			sql.append(" VALUES(?, ?, ?)");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, sach.getMaSach());
			preparedStatement.setString(2, sach.getTenSach());
			preparedStatement.setString(3, sach.getMaNhaXuatBan());
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
	
	public Boolean delete(String maSach) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM SACH WHERE MASACH = ?");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, maSach);
			preparedStatement.executeUpdate();
			conn.commit();
			return true;
			
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean update(Sach sach) {
		PreparedStatement preparedStatement = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE SACH SET ");
			sql.append(" TENSACH = ?, MANXB = ?");
			sql.append(" WHERE MASACH = ?");
			conn.setAutoCommit(false);
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql.toString());
			preparedStatement.setString(1, sach.getTenSach());
			preparedStatement.setString(2, sach.getMaNhaXuatBan());
			preparedStatement.setString(3, sach.getMaSach());
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
