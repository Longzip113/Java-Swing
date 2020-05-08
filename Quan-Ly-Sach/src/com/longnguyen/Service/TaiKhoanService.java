package com.longnguyen.Service;

import java.sql.ResultSet;

import com.longnguyen.model.TaiKhoan;
import com.mysql.jdbc.PreparedStatement;

public class TaiKhoanService extends MySqlService{

	public TaiKhoan findByUserNameAndPassWord(String userName, String passWord) {
		
		TaiKhoan tk = new TaiKhoan();
		try {
			String sql = "select * from TAIKHOAN where USERNAME = ? and PASSWORD = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, passWord);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				tk.setUserName(resultSet.getString(1));
				tk.setPassWord(resultSet.getString(2));
				tk.setFullName(resultSet.getString(3));
			}
			return tk;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tk;
		
	}
}
