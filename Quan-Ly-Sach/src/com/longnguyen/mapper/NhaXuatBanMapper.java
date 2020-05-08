package com.longnguyen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.longnguyen.model.NhaXuatBan;

public class NhaXuatBanMapper {
	
	
	public NhaXuatBan mapRow(ResultSet resultSet) {
		NhaXuatBan model = null;
		try {
			model = new NhaXuatBan();
			
			model.setMaNhaSanXuat(resultSet.getString(1));
			model.setTenNhaSanXuat(resultSet.getString(2));
			model.setDiaChi(resultSet.getString(3));
			model.setSoDienThoi(resultSet.getString(4));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
