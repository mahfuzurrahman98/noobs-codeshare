package com.codeshare.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codeshare.model.Language;
import com.codeshare.service.DBConnection;

public class LanguageDAO {
	private Connection conn = null;

	public LanguageDAO() throws IOException {
		conn = DBConnection.getConnection();
	}

	public ArrayList<Language> getAllLanguages() {
		ArrayList<Language> list = new ArrayList<Language>();

		String $sql = "select * from Languages order by Name";
		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery($sql);

			while (rs.next()) {
				list.add(new Language(rs.getInt("Id"), rs.getString("Name")));
			}
		} catch (SQLException e) {
			System.out.println("DAO Error");
			e.printStackTrace();
		}
		return list;

	}
}
