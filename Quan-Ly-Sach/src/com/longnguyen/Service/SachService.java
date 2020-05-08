package com.longnguyen.Service;

import java.sql.ResultSet;
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
}
